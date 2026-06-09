package com.javarafa.agendador_horarios.service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.javarafa.agendador_horarios.infrastructure.entities.Agendamento;
import com.javarafa.agendador_horarios.infrastructure.repository.AgendamentoRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento){
        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1);

       Agendamento agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(), horaAgendamento, horaFim);

        if(Objects.nonNull(agendados)){
            throw new RuntimeException("Horário já está preenchido");
        }


        return agendamentoRepository.save(agendamento); 
    }
}
