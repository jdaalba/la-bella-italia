package com.jdaalba.service;

import com.jdaalba.entity.Reserva;

public interface SenderService {

  void send(Reserva reserva);

  void enviarConfirmacion(Reserva reserva);

  void enviarRechazo(Reserva reserva, String mensaje);
}
