package br.com.fiap.abctechservice.application.impl;

import br.com.fiap.abctechservice.application.OrderApplication;
import br.com.fiap.abctechservice.application.dto.*;
import br.com.fiap.abctechservice.model.Operator;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.model.OrderLocation;
import br.com.fiap.abctechservice.repository.OperatorRepository;
import br.com.fiap.abctechservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderApplicationImpl implements OrderApplication{
    
    private final OrderService orderService;
    private final OperatorRepository operatorRepository;

    public OrderApplicationImpl(@Autowired OrderService orderService, OperatorRepository operatorRepository) {
        this.orderService = orderService;
        this.operatorRepository = operatorRepository;
    }

    @Override
    public OrderResponseDto getOrder(Long id) {
        Order order = this.orderService.getOrderById(id);

        List<AssistDto> assistsDtoList = order.getServices().stream().map(assistance ->
                new AssistDto(assistance.getId(), assistance.getName(), assistance.getDescription())).collect(Collectors.toList());

        OrderResponseDto orderDto = new OrderResponseDto(order.getId(), order.getOperatorId(), assistsDtoList,
                this.getOrderLocationDtoFromOrderLocation(order.getStartOrderLocation()),
                getOrderLocationDtoFromOrderLocation(order.getEndOrderLocation()));

        return orderDto;
    }

    @Override
    public void createorder(OrderDto orderDto) {
        Order order = new Order();

        Operator operator = operatorRepository.findFirstByRegistration(orderDto.getOperatorId().toString()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        order.setOperatorId(operator.getId());
        order.setStartOrderLocation(getOrderLocationFromOrderLocationDto(orderDto.getStart()));
        order.setEndOrderLocation(getOrderLocationFromOrderLocationDto(orderDto.getEnd()));
        
        this.orderService.saveOrder(order, orderDto.getServices());
    }

    @Override
    public List<OrderOperatorResponseDto> listOrderByOperator(String operatorRegistration) {
        List<OrderOperatorResponseDto> ordersResponse = new ArrayList<>();

        Operator operator = operatorRepository.findFirstByRegistration(operatorRegistration).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Order> orders = orderService.listOrderByOperator(operator.getId());

        orders.forEach(order -> {
            OrderOperatorResponseDto orderResponse = new OrderOperatorResponseDto();
            orderResponse.setStart(getOrderLocationDtoFromOrderLocation(order.getStartOrderLocation()));
            orderResponse.setEnd(getOrderLocationDtoFromOrderLocation(order.getEndOrderLocation()));
            orderResponse.setOperatorName(operator.getName());
            orderResponse.setOperatorRegistration(operator.getRegistration());

            List<AssistDto> servicesDto = new ArrayList<>();

            order.getServices().forEach(services -> {
                servicesDto.add(new AssistDto(services.getId(), services.getName(), services.getDescription()));
            });

            orderResponse.setServices(servicesDto);

            ordersResponse.add(orderResponse);
        });

        return ordersResponse;
    }
    
    private OrderLocation getOrderLocationFromOrderLocationDto(OrderLocationDto orderLocationDto){
        OrderLocation location = new OrderLocation();

        if(orderLocationDto == null){
            return null;
        }
        
        location.setLatitude(orderLocationDto.getLatitude());
        location.setLongitude(orderLocationDto.getLongitude());
        location.setDate(orderLocationDto.getDateTime());
        
        return location;
    }


    private OrderLocationDto getOrderLocationDtoFromOrderLocation(OrderLocation orderLocation){
        OrderLocationDto location = new OrderLocationDto();

        if(orderLocation == null){
            return null;
        }

        location.setLatitude(orderLocation.getLatitude());
        location.setLongitude(orderLocation.getLongitude());
        location.setDateTime(orderLocation.getDate());

        return location;
    }
}
