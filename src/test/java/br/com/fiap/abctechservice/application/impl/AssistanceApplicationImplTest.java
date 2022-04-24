package br.com.fiap.abctechservice.application.impl;

import br.com.fiap.abctechservice.application.dto.AssistDto;
import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.repository.OrderRepository;
import br.com.fiap.abctechservice.service.AssistanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AssistanceApplicationImplTest {

    @Mock
    private AssistanceService assistanceService;

    private AssistanceApplicationImpl assistanceApplication;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        assistanceApplication = new AssistanceApplicationImpl(assistanceService);
    }

    @Test
    void getAssist() {
        Assistance assistance = new Assistance(0L, "Mock Name", "Mock Description");

        AssistDto assistDtoExpected = new AssistDto(0L, "Mock Name", "Mock Description");

        when(assistanceService.getAssistsList()).thenReturn(Arrays.asList(assistance));

        List<AssistDto> assistanceList = assistanceApplication.getAssist();

        assertEquals(assistDtoExpected, assistanceList.stream().findFirst().get());
    }
}