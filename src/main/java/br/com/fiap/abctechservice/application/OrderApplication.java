package br.com.fiap.abctechservice.application;

import br.com.fiap.abctechservice.application.dto.OrderDto;
import br.com.fiap.abctechservice.application.dto.OrderOperatorResponseDto;
import br.com.fiap.abctechservice.application.dto.OrderResponseDto;

import java.util.List;

public interface OrderApplication {
    
    public OrderResponseDto getOrder(Long id);

    void createorder(OrderDto orderDto);

    List<OrderOperatorResponseDto> listOrderByOperator(String operatorRegistration);
}