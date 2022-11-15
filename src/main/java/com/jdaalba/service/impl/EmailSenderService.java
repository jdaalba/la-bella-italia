package com.jdaalba.service.impl;

import static java.util.Objects.requireNonNull;

import com.jdaalba.entity.Reserva;
import com.jdaalba.service.SenderService;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailSenderService implements SenderService {

  private final JavaMailSenderImpl mailSender;

  private final TemplateEngine templateEngine;

  @Value("${app.direccion}")
  private String direccion;

  @Override
  public void enviarConfirmacion(Reserva reserva) {
    final var nombrePlantilla = "confirmacion-reserva";
    final var asunto = "Confirmación de reserva";
    send(getParams(reserva), reserva, asunto, nombrePlantilla);
  }

  @Override
  public void send(Reserva reserva) {
    if (reserva.isConfirmada()) {
      enviarConfirmacion(reserva);
    } else {
      final var asunto = "Solicitud recibida correctamente";
      final var nombrePlantilla = "confirmacion-recibo";
      send(getParams(reserva), reserva, asunto, nombrePlantilla);
    }
  }

  @Override
  public void enviarRechazo(Reserva reserva, String mensaje) {
    send(getParams(reserva, mensaje), reserva, "Reserva rechazada", "rechazo-reserva");
  }

  private void send(Map<String, Object> params, Reserva reserva, String asunto, String plantilla) {
    final var ctx = new Context();
    ctx.setVariables(params);
    final var htmlContent = this.templateEngine.process("html/" + plantilla + ".html", ctx);

    try {
      final var mimeMessage = mailSender.createMimeMessage();
      final var helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
      helper.setFrom(requireNonNull(mailSender.getUsername()));
      helper.setTo(reserva.getMail());
      helper.setSubject(asunto);
      helper.setText(htmlContent, true);
      mailSender.send(mimeMessage);
      log.info("Email enviado enviada correctamente");
    } catch (MessagingException e) {
      log.error("Error en el envío de la confirmación", e);
    }
  }

  private Map<String, Object> getParams(Reserva reserva) {
    final var dFormat = DateTimeFormatter.ofPattern("dd-MM");
    final var tFormat = DateTimeFormatter.ofPattern("HH:mm");
    final var formattedDate = dFormat.format(reserva.getMomentoReserva());
    final var formattedTime = tFormat.format(reserva.getMomentoReserva());

    return Map.of(
        "name", reserva.getNombre(),
        "date", formattedDate,
        "time", formattedTime,
        "numberOfPersons", reserva.getNumeroComensales()
    );
  }

  private Map<String, Object> getParams(Reserva reserva, String mensaje) {
    return Map.of(
        "name", reserva.getNombre(),
        "parrafos", mensaje.split("\r?\n"),
        "direccion", direccion
    );
  }
}
