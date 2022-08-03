package com.jdaalba.service.impl;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class EmailSenderService implements CommandLineRunner {

  private final JavaMailSenderImpl mailSender;

  private final TemplateEngine templateEngine;

  @Override
  public void run(String... args) throws Exception {

    final Context ctx = new Context();
    ctx.setVariable("name", "Menda Lerenda");
    ctx.setVariable("subscriptionDate", LocalDateTime.now().toString());
    ctx.setVariable("hobbies", List.of("Cinema", "Sports", "Music"));
//    ctx.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML

    final String htmlContent = this.templateEngine.process("html/confirmacion-reserva.html", ctx);
//    SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
//    msg.setTo("jdaalba@gmail.com");
//    msg.setText(htmlContent);
//    mailSender.send(msg);

    final var mimeMessage = mailSender.createMimeMessage();
    final var helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
    helper.setFrom(requireNonNull(mailSender.getUsername()));
    helper.setTo("jdaalba@gmail.com");
    helper.setSubject("Hola mundo");
    helper.setText(htmlContent, true);

    mailSender.send(mimeMessage);
  }
}
