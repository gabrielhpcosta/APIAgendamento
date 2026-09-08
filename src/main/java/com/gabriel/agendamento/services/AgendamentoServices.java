package com.gabriel.agendamento.services;

import com.gabriel.agendamento.entity.Agendamento;
import com.gabriel.agendamento.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoServices {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento) {
        validarConflitoDeHorario(agendamento, null);
        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente) {
        Agendamento agendamento = buscarAgendamento(dataHoraAgendamento, cliente);
        agendamentoRepository.delete(agendamento);
    }

    public List<Agendamento> buscarAgendamentosDia(LocalDate data) {
        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime inicioDoProximoDia = data.plusDays(1).atStartOfDay();

        return agendamentoRepository.findByDataHoraAgendamentoGreaterThanEqualAndDataHoraAgendamentoLessThan(
                inicioDoDia,
                inicioDoProximoDia
        );
    }

    public Agendamento alterarAgendamento(Agendamento agendamento,
                                          String cliente,
                                          LocalDateTime dataHoraAgendamento) {
        Agendamento agendamentoExistente = buscarAgendamento(dataHoraAgendamento, cliente);

        validarConflitoDeHorario(agendamento, agendamentoExistente.getId());

        agendamento.setId(agendamentoExistente.getId());
        agendamento.setDataInsercao(agendamentoExistente.getDataInsercao());

        return agendamentoRepository.save(agendamento);
    }

    private Agendamento buscarAgendamento(LocalDateTime dataHoraAgendamento, String cliente) {
        return agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    private void validarConflitoDeHorario(Agendamento agendamento, Long idIgnorado) {
        LocalDateTime inicio = agendamento.getDataHoraAgendamento();
        LocalDateTime fim = inicio.plusHours(1);

        List<Agendamento> conflitos = agendamentoRepository
                .findByProfissionalAndDataHoraAgendamentoGreaterThanEqualAndDataHoraAgendamentoLessThan(
                        agendamento.getProfissional(),
                        inicio,
                        fim
                );

        boolean possuiConflito = conflitos.stream()
                .anyMatch(conflito -> idIgnorado == null || !conflito.getId().equals(idIgnorado));

        if (possuiConflito) {
            throw new RuntimeException("Horário indisponível para o profissional selecionado");
        }
    }
}
