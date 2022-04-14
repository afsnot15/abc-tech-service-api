package br.com.fiap.abctechservice.application;

import br.com.fiap.abctechservice.application.dto.OrderDto;
import br.com.fiap.abctechservice.application.dto.OrderResponseDto;

public interface OrderApplication {
    
    public OrderResponseDto getOrder(Long id);
    void createorder(OrderDto orderDto);
}
