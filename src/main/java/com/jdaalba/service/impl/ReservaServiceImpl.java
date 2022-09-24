package com.jdaalba.service.impl;

import com.jdaalba.entity.Reserva;
import com.jdaalba.repository.ReservaRepository;
import com.jdaalba.service.ReservaService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public record ReservaServiceImpl(ReservaRepository repository) implements ReservaService {

  @Override
  public void salvar(Reserva reserva) {
    repository.save(reserva);
  }

  @Override
  public List<Reserva> buscarPendientesDeConfirmar() {
    return repository.findAllByConfirmadaFalse();
  }

  @Override
  public List<Reserva> buscar(LocalDate fecha) {
    return repository.findAllByMomentoReservaBetweenAndConfirmadaTrueOrderByMomentoReservaDesc(
        LocalDateTime.of(fecha, LocalTime.MIN),
        LocalDateTime.of(fecha, LocalTime.MAX)
    );
  }
}
