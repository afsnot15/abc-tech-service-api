package br.com.fiap.abctechservice.application;

import br.com.fiap.abctechservice.application.dto.OrderDto;

public interface OrderApplication {
    
    void createorder(OrderDto orderDto);
}
