package br.com.fiap.abctechservice.service;

import br.com.fiap.abctechservice.application.dto.OrderOperatorResponseDto;
import br.com.fiap.abctechservice.model.Order;
import java.util.List;

public interface OrderService {

    void saveOrder(Order order, List<Long> arrayAssists);

    List<Order>  listOrderByOperator(Long operatorId);

    Order getOrderById(Long orderId);
}
