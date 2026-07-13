package com.gabriel.agendamento.repository;

import com.gabriel.agendamento.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    Agendamento findByServicoAndDataHoraAgendamentoBetween(
            String servico,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    void deleteByDataHoraAgendamentoAndCliente(
            LocalDateTime dataHoraAgendamento,
            String cliente
    );

    List<Agendamento> findByDataHoraAgendamentoBetween(
            LocalDateTime inicio,
            LocalDateTime fim
    );

    Agendamento findByDataHoraAgendamentoAndCliente(
            LocalDateTime dataHoraAgendamento,
            String cliente
    );
}