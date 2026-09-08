package com.gabriel.agendamento.repository;

import com.gabriel.agendamento.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByProfissionalAndDataHoraAgendamentoGreaterThanEqualAndDataHoraAgendamentoLessThan(
            String profissional,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    List<Agendamento> findByDataHoraAgendamentoGreaterThanEqualAndDataHoraAgendamentoLessThan(
            LocalDateTime inicio,
            LocalDateTime fim
    );

    Optional<Agendamento> findByDataHoraAgendamentoAndCliente(
            LocalDateTime dataHoraAgendamento,
            String cliente
    );
}
