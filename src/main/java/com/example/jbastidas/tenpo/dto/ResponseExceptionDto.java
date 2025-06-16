package com.example.jbastidas.tenpo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseExceptionDto {
    private String responseMessage;
    private int responseCode;
    private String timestamp;
}
