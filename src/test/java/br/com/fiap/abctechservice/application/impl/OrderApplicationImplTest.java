package br.com.fiap.abctechservice.application.impl;

import br.com.fiap.abctechservice.application.dto.*;
import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.model.Operator;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.model.OrderLocation;
import br.com.fiap.abctechservice.repository.OperatorRepository;
import br.com.fiap.abctechservice.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class OrderApplicationImplTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OperatorRepository operatorRepository;

    private OrderApplicationImpl orderApplication;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        orderApplication = new OrderApplicationImpl(orderService, operatorRepository);
    }

    @Test
    void getOrderOrderLocationNull() {
        Assistance assistance = new Assistance();
        assistance.setId(0L);
        assistance.setDescription("Mock Assistance description");
        assistance.setName("Mock Assistance name");

        AssistDto assistDto = new AssistDto();
        assistDto.setId(0L);
        assistDto.setDescription("Mock Assistance description");
        assistDto.setName("Mock Assistance name");

        Order order = new Order();
        order.setId(0L);
        order.setOperatorId(0L);
        order.setServices(Arrays.asList(assistance));

        when(orderService.getOrderById(anyLong())).thenReturn(order);

        OrderResponseDto orderDtoExpcted = new OrderResponseDto();
        orderDtoExpcted.setId(0L);
        orderDtoExpcted.setOperatorId(0L);
        orderDtoExpcted.setServices(Arrays.asList(assistDto));

        OrderResponseDto orderDtoActual = orderApplication.getOrder(0L);

        Assertions.assertEquals(orderDtoExpcted, orderDtoActual);
    }

    @Test
    void getOrderOrderLocationNotNull() {
        Assistance assistance = new Assistance();
        assistance.setId(0L);
        assistance.setDescription("Mock Assistance description");
        assistance.setName("Mock Assistance name");

        Order order = new Order();
        order.setId(0L);
        order.setOperatorId(0L);
        order.setServices(Arrays.asList(assistance));
        order.setStartOrderLocation(getOrderLocationMock());
        order.setEndOrderLocation(getOrderLocationMock());

        AssistDto assistDto = new AssistDto();
        assistDto.setId(0L);
        assistDto.setDescription("Mock Assistance description");
        assistDto.setName("Mock Assistance name");

        when(orderService.getOrderById(anyLong())).thenReturn(order);

        OrderResponseDto orderDtoExpcted = new OrderResponseDto();
        orderDtoExpcted.setId(0L);
        orderDtoExpcted.setOperatorId(0L);
        orderDtoExpcted.setServices(Arrays.asList(assistDto));
        orderDtoExpcted.setStart(getOrderLocationDtoMock());
        orderDtoExpcted.setEnd(getOrderLocationDtoMock());

        OrderResponseDto orderDtoActual = orderApplication.getOrder(0L);

        Assertions.assertEquals(orderDtoExpcted.toString(), orderDtoActual.toString());
    }

    @Test
    void createorder() {
        Optional<Operator> operatorOptional = Optional.ofNullable(new Operator());

        OrderDto orderDto = new OrderDto();
        orderDto.setOperatorId(123456L);
        orderDto.setStart(getOrderLocationDtoMock());
        orderDto.setEnd(getOrderLocationDtoMock());

        when(operatorRepository.findFirstByRegistration(anyString())).thenReturn(operatorOptional);

        Assertions.assertDoesNotThrow(() -> orderApplication.createorder(orderDto));
    }

    @Test
    void createorderLocationNull() {
        Optional<Operator> operatorOptional = Optional.ofNullable(new Operator());

        OrderDto orderDto = new OrderDto();
        orderDto.setOperatorId(123456L);

        when(operatorRepository.findFirstByRegistration(anyString())).thenReturn(operatorOptional);

        Assertions.assertDoesNotThrow(() -> orderApplication.createorder(orderDto));
    }

    @Test
    void listOrderByOperator() {
        Optional<Operator> operatorOptional = Optional.ofNullable(new Operator(0L, "Mock", "Mock", "Mock"));

        when(operatorRepository.findFirstByRegistration(anyString())).thenReturn(operatorOptional);

        when(orderService.listOrderByOperator(anyLong())).thenReturn(Arrays.asList(getOrderMock()));

        List<OrderOperatorResponseDto> responseDtosActual = orderApplication.listOrderByOperator("123456789");

        Assertions.assertTrue(responseDtosActual.size() > 0);
    }

    private OrderLocation getOrderLocationMock(){
        Calendar calndr = Calendar.getInstance();
        calndr.set(2022,1,1, 1, 1, 1);

        OrderLocation orderLocation = new OrderLocation();
        orderLocation.setDate(calndr.getTime());
        orderLocation.setLatitude(34.5);
        orderLocation.setLongitude(34.5);
        orderLocation.setId(0L);

        return orderLocation;
    }

    private OrderLocationDto getOrderLocationDtoMock(){
        Calendar calndr = Calendar.getInstance();
        calndr.set(2022,1,1, 1, 1, 1);

        OrderLocationDto orderLocation = new OrderLocationDto();
        orderLocation.setDateTime(calndr.getTime());
        orderLocation.setLatitude(34.5);
        orderLocation.setLongitude(34.5);

        return orderLocation;
    }

    private Order getOrderMock(){
        Assistance assistance = new Assistance();
        assistance.setId(0L);
        assistance.setDescription("Mock Assistance description");
        assistance.setName("Mock Assistance name");

        Order order = new Order();
        order.setId(0L);
        order.setOperatorId(0L);
        order.setServices(Arrays.asList(assistance));

        return order;
    }
}