package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.repository.OrderRepository;
import br.com.fiap.abctechservice.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    
    private OrderRepository orderRepository;

    public OrderServiceImpl(@Autowired OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(Order order) throws Exception {
        
        if (!order.hasMinAssists()) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (order.exceedsMaxAssists()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        orderRepository.save(order);
    }

    @Override
    public List<Order> listOrderByOperator(Long operatorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
