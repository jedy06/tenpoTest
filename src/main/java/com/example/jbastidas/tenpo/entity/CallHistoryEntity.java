package com.example.jbastidas.tenpo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "parametros")
    private String parametros;

    @Column(name = "respuesta")
    private String respuesta;

}
