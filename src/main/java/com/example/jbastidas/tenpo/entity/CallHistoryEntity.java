package com.example.jbastidas.tenpo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "call_history")
public class CallHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "call_id")
    private Long callId;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "codigo_retorno")
    private int codigo;

    @Column(name = "parametro_id_externo")
    private BigDecimal parametro;

    @Column(name = "respuesta_externa")
    private String respuesta;

}
