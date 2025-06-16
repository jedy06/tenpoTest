package com.example.jbastidas.tenpo.service;

/*import com.example.jbastidas.tenpo.dto.ExternalPercentDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.reactive.function.client.*;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest*/
public class ExternalDataService2Test {
/*
    @Autowired
    private ExternalDataService service;

    @MockitoBean
    private WebClient webClient;

    @MockitoBean
    private WebClient.RequestHeadersUriSpec<?> requestUriSpec;

    @MockitoBean
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @MockitoBean
    private WebClient.ResponseSpec responseSpec;

    @MockitoBean
    private ObjectMapper objectMapper;

    @Test
    void testGetPercent_shouldReturnDto() throws Exception {
        int id = 123;
        ExternalPercentDto expectedDto = new ExternalPercentDto("10", new BigDecimal(10));
        String json = "{\"valor\": \"10\", \"porcentaje\": 10}";

        Mockito.when(webClient.get()).thenReturn(requestUriSpec);
        Mockito.when(requestUriSpec.uri("http://fakeurl.com/" + id)).thenReturn(requestHeadersSpec);
        Mockito.when(requestHeadersSpec.exchangeToMono(Mockito.any()))
                .then(invocation -> {
                    var handler = invocation.getArgument(0);
                    ClientResponse response = ClientResponse
                            .create(HttpStatus.OK)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .body(json)
                            .build();
                    return ((java.util.function.Function<ClientResponse, Mono<ExternalPercentDto>>) handler).apply(response);
                });

        Mockito.when(objectMapper.readValue(json, ExternalPercentDto.class)).thenReturn(expectedDto);

        ExternalPercentDto result = service.getPercent(id);

        assertThat(result).isNotNull();
        assertThat(result.porcentaje()).isEqualTo(expectedDto.porcentaje());
    }*/
}
