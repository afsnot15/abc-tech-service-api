package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.repository.AssistanceRepository;
import br.com.fiap.abctechservice.service.AssistanceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistanceServiceImpl implements AssistanceService {

    private AssistanceRepository repository;

    public AssistanceServiceImpl(@Autowired AssistanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Assistance> getAssistsList() {
        return this.repository.findAll();
    }
}
