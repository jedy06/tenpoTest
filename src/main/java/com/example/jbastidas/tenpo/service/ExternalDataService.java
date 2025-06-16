package com.example.jbastidas.tenpo.service;

import com.example.jbastidas.tenpo.dto.ExternalPercentDto;
import com.example.jbastidas.tenpo.entity.CallHistoryEntity;
import com.example.jbastidas.tenpo.repository.CallHistroyRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ExternalDataService {

    @Value("${url.external.service}")
    private String urlExternal;


    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CallHistroyRepo callHistroyRepo;

    public ExternalDataService(WebClient.Builder webClientBuilder,
                               CallHistroyRepo callHistroyRepo) {
        this.webClient = webClientBuilder
                .build();
        this.callHistroyRepo = callHistroyRepo;
    }

    public ExternalPercentDto getPercent(int id) {
        String uri = urlExternal + id;

        return webClient.get()
                .uri(uri)
                .exchangeToMono(response -> handleResponse("GET", uri, response, id))
                .block();
    }


    private Mono<ExternalPercentDto> handleResponse(String method, String uri, ClientResponse response, int id) {
        HttpStatus status = (HttpStatus) response.statusCode();
        HttpHeaders headers = response.headers().asHttpHeaders();

        System.out.println("handleResponse() llamado con status: " + status);

        return response.bodyToMono(String.class)
                .doOnNext(body -> {
                    System.out.println("Body recibido: " + body);
                    saveCall(method, uri, status.value(), headers.toString(), body, id);
                })
                .flatMap(body -> {
                    if (status.is2xxSuccessful()) {
                        try {
                            ExternalPercentDto dto = objectMapper.readValue(body, ExternalPercentDto.class);
                            return Mono.just(dto);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return Mono.error(new RuntimeException("Error al parsear respuesta", e));
                        }
                    } else {
                        return Mono.error(new RuntimeException("Error HTTP: " + status + " - " + body));
                    }
                });
    }


    private void saveCall(String method, String uri, int status, String headers, String body, int id) {
        CallHistoryEntity callHistory = new CallHistoryEntity();
        callHistory.setFecha(LocalDateTime.now());
        callHistory.setEndpoint(uri);
        callHistory.setCodigo(status);
        callHistory.setParametro(BigDecimal.valueOf(id));
        callHistory.setRespuesta(body);

        callHistroyRepo.save(callHistory);
    }


    @Cacheable(value = "PERCENT_EXTERNAL")
    public CallHistoryEntity findPercentBD() throws ClassNotFoundException {
        Optional<CallHistoryEntity> ch = callHistroyRepo.findFirstByCodigoOrderByFechaDesc(200);
        return ch.orElseThrow(() -> new ClassNotFoundException("No se ha encontrado porcentaje en tabla"));
    }


    public BigDecimal findPercent() throws ClassNotFoundException {
        BigDecimal percent = new BigDecimal(0);
        CallHistoryEntity callHistory = findPercentBD();
        Gson gson = new Gson();
        ExternalPercentDto externalPercentDto = gson.fromJson(callHistory.getRespuesta(), ExternalPercentDto.class);
        percent = externalPercentDto.porcentaje();
        return percent;
    }

    public Page<CallHistoryEntity> getHistory(Pageable pageable) {
        return callHistroyRepo.findAll(pageable);
    }
}
