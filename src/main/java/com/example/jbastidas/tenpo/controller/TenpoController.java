package com.example.jbastidas.tenpo.controller;

import com.example.jbastidas.tenpo.dto.CalculatorReqDto;
import com.example.jbastidas.tenpo.dto.ExternalPercentDto;
import com.example.jbastidas.tenpo.dto.ResponseDto;
import com.example.jbastidas.tenpo.entity.CallHistoryEntity;
import com.example.jbastidas.tenpo.service.CalculatorService;
import com.example.jbastidas.tenpo.service.ExternalDataService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

@RestController
public class TenpoController {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private ExternalDataService externalDataService;

    @GetMapping("/healthCheck")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Service on");
    }

    @GetMapping("/historial")
    public ResponseEntity<Page<CallHistoryEntity>> listRecords(Pageable pageable) {
        Page<CallHistoryEntity> pagina = externalDataService.getHistory(pageable);
        return ResponseEntity.ok(pagina);
    }

    @PostMapping("/calculo")
    public ResponseEntity<ResponseDto> calculate(@RequestBody CalculatorReqDto calculatorReqDto) throws ClassNotFoundException {

        BigDecimal numero1 = calculatorReqDto.getNumero1();
        BigDecimal numero2 = calculatorReqDto.getNumero2();

        BigDecimal percent = new BigDecimal(0);
        CallHistoryEntity callHistory = externalDataService.findPercentBD();
        Gson gson = new Gson();
        ExternalPercentDto externalPercentDto = gson.fromJson(callHistory.getRespuesta(), ExternalPercentDto.class);
        percent = externalPercentDto.porcentaje();

        BigDecimal res = calculatorService.percentResult(numero1, numero2, percent);
        ResponseDto responseDto = new ResponseDto(numero1, numero2, percent, res);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
