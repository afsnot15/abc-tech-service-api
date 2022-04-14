package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.handler.Exception.MaxAssistsException;
import br.com.fiap.abctechservice.handler.Exception.MinimiumAssistsRequiredException;
import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.repository.AssistanceRepository;
import br.com.fiap.abctechservice.repository.OrderRepository;
import br.com.fiap.abctechservice.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    
    private OrderRepository orderRepository;
    private AssistanceRepository assistanceRepository;

    public OrderServiceImpl(@Autowired OrderRepository orderRepository,
            @Autowired AssistanceRepository assistanceRepository) {
        this.orderRepository = orderRepository;
        this.assistanceRepository = assistanceRepository;
    }

    @Override
    public void saveOrder(Order order, List<Long> arrayAssists) {
        ArrayList<Assistance> assistances = new ArrayList<>();
        
        arrayAssists.forEach(a -> {
            Assistance assistance = this.assistanceRepository.findById(a).orElseThrow();
            assistances.add(assistance);
        });
        
        order.setServices(assistances);
        
        if (!order.hasMinAssists()) {
            throw new MinimiumAssistsRequiredException("Array de assistências inválido", "Por favor envie ao menos 1 assitência!");
        } else if (order.exceedsMaxAssists()) {
            throw new MaxAssistsException("Array de assistências inválido", "O número máximo de assistencias é 15!");
        }

        orderRepository.save(order);
    }

    @Override
    public List<Order> listOrderByOperator(Long operatorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }
}
