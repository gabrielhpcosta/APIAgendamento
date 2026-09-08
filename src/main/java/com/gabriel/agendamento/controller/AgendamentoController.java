package com.gabriel.agendamento.controller;

import com.gabriel.agendamento.entity.Agendamento;
import com.gabriel.agendamento.services.AgendamentoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoServices agendamentoServices;

    @PostMapping
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody Agendamento agendamento) {
        Agendamento agendamentoSalvo = agendamentoServices.salvarAgendamento(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoSalvo);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(@RequestParam String cliente,
                                                   @RequestParam LocalDateTime dataHoraAgendamento) {
        agendamentoServices.deletarAgendamento(dataHoraAgendamento, cliente);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> buscarAgendamentosDia(@RequestParam LocalDate data) {
        return ResponseEntity.ok(agendamentoServices.buscarAgendamentosDia(data));
    }

    @PutMapping
    public ResponseEntity<Agendamento> alterarAgendamento(@RequestBody Agendamento agendamento,
                                                          @RequestParam String cliente,
                                                          @RequestParam LocalDateTime dataHoraAgendamento) {
        return ResponseEntity.ok(
                agendamentoServices.alterarAgendamento(agendamento, cliente, dataHoraAgendamento)
        );
    }
}
