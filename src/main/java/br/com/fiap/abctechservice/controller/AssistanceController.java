package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.AssistanceApplication;
import br.com.fiap.abctechservice.application.dto.AssistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assistance")
public class AssistanceController {

    private final AssistanceApplication assistance;
    
    public AssistanceController (@Autowired AssistanceApplication assistance ){
        this.assistance = assistance;
    }

    @GetMapping()
    public ResponseEntity<List<AssistDto>> getAssists() {
        List<AssistDto> list = this.assistance.getAssist();
        return ResponseEntity.ok(list);
    }
}
