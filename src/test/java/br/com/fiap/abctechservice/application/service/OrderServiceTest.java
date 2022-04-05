package br.com.fiap.abctechservice.application.service;

import br.com.fiap.abctechservice.handler.Exception.MaxAssistsException;
import br.com.fiap.abctechservice.handler.Exception.MinimiumAssistsRequiredException;
import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.repository.AssistanceRepository;
import br.com.fiap.abctechservice.repository.OrderRepository;
import br.com.fiap.abctechservice.service.OrderService;
import br.com.fiap.abctechservice.service.impl.OrderServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {
    
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private AssistanceRepository assistaneRepository;
    
    private OrderService orderService;
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        
        orderService = new OrderServiceImpl(orderRepository, assistaneRepository);
        
        when(assistaneRepository.findById(any())).thenReturn(Optional.of(new Assistance(1L, "Teste", "Teste Mock")));
    }
    
    @Test
    public void order_service_not_null() {
        Assertions.assertNotNull(orderService);
    }
    
    @Test
    public void create_order_sucess() throws Exception {
        Order order = new Order();
        order.setOperatorId(1234L);
        
        orderService.saveOrder(order, generate_mock_assistace(1));
        
        verify(orderRepository, times(1)).save(order);
        
    }
    
    @Test
    public void create_order_error_minimum() throws Exception {
        Order order = new Order();
        order.setOperatorId(1234L);
        
        Assertions.assertThrows(MinimiumAssistsRequiredException.class, () -> orderService.saveOrder(order, List.of()));
        
        verify(orderRepository, never()).save(order);
        
    }
    
    @Test
    public void create_order_error_maximum() throws Exception {
        Order order = new Order();
        order.setOperatorId(1234L);
        
        Assertions.assertThrows(MaxAssistsException.class, () -> orderService.saveOrder(order, generate_mock_assistace(20)));
        
        verify(orderRepository, never()).save(order);
    }
    
    private List<Long> generate_mock_assistace(int number) {
        ArrayList<Long> assistances = new ArrayList<>();
        
        for (int i = 0; i < number; i++) {
            assistances.add(Integer.toUnsignedLong(i));
        }
        
        return assistances;
    }
}
