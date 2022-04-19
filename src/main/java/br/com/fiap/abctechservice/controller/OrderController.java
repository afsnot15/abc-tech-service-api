package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.OrderApplication;
import br.com.fiap.abctechservice.application.dto.OrderDto;
import br.com.fiap.abctechservice.application.dto.OrderOperatorResponseDto;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderApplication orderApplication;

    public OrderController(@Autowired OrderApplication orderApplication) {
        this.orderApplication = orderApplication;
    }

    @PostMapping
    public ResponseEntity createOrder(
            @Valid
            @RequestBody OrderDto orderDto) {
        orderApplication.createorder(orderDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<OrderOperatorResponseDto>> getOrderByOperator(@RequestParam(name = "registration") String registration) {
        List<OrderOperatorResponseDto> list = this.orderApplication.listOrderByOperator(registration);
        return ResponseEntity.ok(list);
    }
}
