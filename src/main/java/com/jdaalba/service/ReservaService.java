package com.jdaalba.service;

import com.jdaalba.entity.Reserva;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ReservaService {

  void salvar(Reserva reserva);

  Page<Reserva> buscarPendientesDeConfirmar(int pagina);

  List<Reserva> buscar(LocalDate fecha);

  void confirmar(String id);
}
