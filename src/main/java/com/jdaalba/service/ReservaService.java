package com.jdaalba.service;

import com.jdaalba.entity.Reserva;
import java.time.LocalDate;
import java.util.List;

public interface ReservaService {

  void salvar(Reserva reserva);

  List<Reserva> buscarPendientesDeConfirmar();

  List<Reserva> buscar(LocalDate fecha);

  void confirmar(String id);
}
