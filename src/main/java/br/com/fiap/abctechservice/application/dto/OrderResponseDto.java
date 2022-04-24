package br.com.fiap.abctechservice.application.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderResponseDto {

    private Long id;
    private Long operatorId;
    private List<AssistDto> services;
    private OrderLocationDto start;
    private OrderLocationDto end;

}
