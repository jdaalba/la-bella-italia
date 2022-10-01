package com.jdaalba.service.impl;

import com.jdaalba.entity.Reserva;
import com.jdaalba.repository.ReservaRepository;
import com.jdaalba.service.ReservaService;
import com.jdaalba.service.SenderService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public record ReservaServiceImpl(
    ReservaRepository repository,
    SenderService service
) implements ReservaService {

  @Override
  public void salvar(Reserva reserva) {
    repository.save(reserva);
    service.send(reserva);
  }

  @Override
  public Page<Reserva> buscarPendientesDeConfirmar(int pagina) {
    return repository.findAllByConfirmadaFalse(Pageable.ofSize(10).withPage(pagina));
  }

  @Override
  public Page<Reserva> buscar(LocalDate dia, int pagina) {
    return repository.findAllByMomentoReservaBetweenAndConfirmadaTrueOrderByMomentoReservaAsc(
        LocalDateTime.of(dia, LocalTime.MIN),
        LocalDateTime.of(dia, LocalTime.MAX),
        Pageable.ofSize(10).withPage(pagina)
    );
  }

  @Override
  public void confirmar(String id) {
    final var reserva = repository.findById(id).orElseThrow();
    service.enviarConfirmacion(reserva);
    reserva.setConfirmada(true);
    repository.save(reserva);
  }
}
