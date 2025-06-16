package com.example.jbastidas.tenpo.task;

import com.example.jbastidas.tenpo.service.ExternalDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
public class PercentExternalTask {

    @Autowired
    private ExternalDataService externalDataService;

    @Scheduled(fixedRate = 1500000) // 1500000 milisegundos = 25 minutos
    public void executeTask(){
        int numeroAleatorio = ThreadLocalRandom.current().nextInt(1, 101);
        externalDataService.getPercent(numeroAleatorio);
    }
}
