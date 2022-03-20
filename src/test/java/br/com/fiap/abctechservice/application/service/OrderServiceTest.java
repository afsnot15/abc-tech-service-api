package br.com.fiap.abctechservice.application.service;

import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.repository.OrderRepository;
import br.com.fiap.abctechservice.service.OrderService;
import br.com.fiap.abctechservice.service.impl.OrderServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    public void order_service_not_null() {
        Assertions.assertNotNull(orderService);
    }

    @Test
    public void create_order_sucess() throws Exception {
        Order order = new Order();
        order.setOperatorId(1234L);
        order.setServices(generate_mock_assistace(1));

        orderService.saveOrder(order);

        verify(orderRepository, times(1)).save(order);

    }

    @Test
    public void create_order_error_minimum() throws Exception {
        Order order = new Order();
        order.setOperatorId(1234L);
        order.setServices(List.of());

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> orderService.saveOrder(order));

        verify(orderRepository, never()).save(order);

    }

    @Test
    public void create_order_error_maximum() throws Exception {
        Order order = new Order();
        order.setOperatorId(1234L);
        order.setServices(generate_mock_assistace(20));

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> orderService.saveOrder(order));

        verify(orderRepository, never()).save(order);

    }
    
    private List<Assistance> generate_mock_assistace(int number){
        ArrayList<Assistance> assistances = new ArrayList<>();
        
        for (int i = 0; i < number; i++) {
            assistances.add(new Assistance(Integer.toUnsignedLong(i), "Mock service name", "Mock service description"));
        }
        
        return assistances;
    }
}
