package com.jdaalba.service.impl;

import static java.util.Objects.requireNonNull;

import com.jdaalba.entity.Reserva;
import com.jdaalba.service.SenderService;
import java.time.format.DateTimeFormatter;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

  @Override
  public void enviarConfirmacion(Reserva reserva) {
    final var nombrePlantilla = "confirmacion-reserva";
    final var asunto = "Confirmación de reserva";
//    extracted(reserva, asunto, nombrePlantilla);
  }

  @Override
  public void send(Reserva reserva) {
    final var asunto = "Solicitud recibida correctamente";
    final var nombrePlantilla = "confirmacion-recibo";
//    extracted(reserva, asunto, nombrePlantilla);
  }

  private void extracted(Reserva reserva, String asunto, String nombrePlantilla) {
    log.info("Enviando {} para la reserva: {}", asunto, reserva);
    final var ctx = new Context();
    ctx.setVariable("name", reserva.getNombre());
    final var dFormat = DateTimeFormatter.ofPattern("dd-MM");
    final var tFormat = DateTimeFormatter.ofPattern("HH:mm");
    final var formattedDate = dFormat.format(reserva.getMomentoReserva());
    final var formattedTime = tFormat.format(reserva.getMomentoReserva());

    ctx.setVariable("name", reserva.getNombre());
    ctx.setVariable("date", formattedDate);
    ctx.setVariable("time", formattedTime);
    ctx.setVariable("numberOfPersons", reserva.getNumeroComensales());

    final var htmlContent = this.templateEngine.process("html/" + nombrePlantilla + ".html", ctx);

    try {
      final var mimeMessage = mailSender.createMimeMessage();
      final var helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
      helper.setFrom(requireNonNull(mailSender.getUsername()));
      helper.setTo(reserva.getMail());
      helper.setSubject(asunto);
      helper.setText(htmlContent, true);
      mailSender.send(mimeMessage);
      log.info("Reserva enviada correctamente");
    } catch (MessagingException e) {
      log.error("Error en el envío de la confirmación", e);
    }
  }
}
