package com.gabriel.agendamento.services;


import com.gabriel.agendamento.entity.Agendamento;
import com.gabriel.agendamento.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor

public class AgendamentoServices {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento) {

        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1);

        Agendamento agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(), horaAgendamento, horaFim);

        if(Objects.nonNull(agendados)){
            throw new RuntimeException("Horário Indisponivel");
        }
        return agendamentoRepository.save(agendamento);

    }

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente ){
        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
    }

    public List<Agendamento> buscarAgendamentosDia(LocalDate data) {
        LocalDateTime primeiraHoraDoDia = data.atStartOfDay();
        LocalDateTime horaFinalDoDia = data.atTime(23, 59, 59);

        return agendamentoRepository.findByDataHoraAgendamentoBetween(
                primeiraHoraDoDia,
                horaFinalDoDia
        );
    }
    public Agendamento alterarAgendamento(Agendamento agendamento, String cliente, LocalDateTime dataHoraAgendamento) {
        Agendamento agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);


        if(Objects.nonNull(agenda)){
            throw new RuntimeException("Horário Disponivel");
        }
        agendamento.setId(agenda.getId());
        return agendamentoRepository.save(agendamento);
    }



}
