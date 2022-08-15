package com.jdaalba.service.impl;

import static java.util.Objects.requireNonNull;

import com.jdaalba.service.SenderService;
import com.jdaalba.vo.Reservation;
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
  public void send(Reservation reservation) {
    log.info("Enviando reserva: {}", reservation);
    final var ctx = new Context();
    ctx.setVariable("name", reservation.name());
    final var dFormat = DateTimeFormatter.ofPattern("dd-MM");
    final var tFormat = DateTimeFormatter.ofPattern("HH:mm");
    final var formattedDate = dFormat.format(reservation.at());
    final var formattedTime = tFormat.format(reservation.at());

    ctx.setVariable("name", reservation.name());
    ctx.setVariable("date", formattedDate);
    ctx.setVariable("time", formattedTime);
    ctx.setVariable("numberOfPersons", reservation.numberOfPersons());

    final var htmlContent = this.templateEngine.process("html/confirmacion-reserva.html", ctx);

    try {
      final var mimeMessage = mailSender.createMimeMessage();
      final var helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
      helper.setFrom(requireNonNull(mailSender.getUsername()));
      helper.setTo(reservation.email());
      helper.setSubject("Confirmación de reserva - " + formattedDate);
      helper.setText(htmlContent, true);
      mailSender.send(mimeMessage);
      log.info("Reserva enviada correctamente");
    } catch (MessagingException e) {
      log.error("Error en el envío de la confirmación", e);
    }
  }
}
