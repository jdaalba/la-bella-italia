package com.jdaalba.service;

import com.jdaalba.entity.Reserva;
import java.time.LocalDate;
import org.springframework.data.domain.Page;

public interface ReservaService {

  void salvar(Reserva reserva);

  void borrar(String id);

  Page<Reserva> buscarPendientesDeConfirmar(int pagina);

  Page<Reserva> buscar(LocalDate dia, int pagina);

  void confirmar(String id);

  Reserva buscar(String id);

  void rechazar(String id, String mensaje);

  void modificar(String id, Reserva reserva);
}
