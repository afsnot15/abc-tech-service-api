package br.com.fiap.abctechservice.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderOperatorResponseDto {

    private String operatorName;
    private String operatorRegistration;
    private List<AssistDto> services;
    private OrderLocationDto start;
    private OrderLocationDto end;
}
