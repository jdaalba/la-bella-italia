package com.jdaalba.config;

import static java.lang.Integer.parseInt;

import java.util.Properties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
class MailConfig {

  @Bean
  JavaMailSenderImpl mailSender(Properties mailProperties) {
    final var mailSender = new JavaMailSenderImpl();

    mailSender.setHost(mailProperties.getProperty("host"));
    mailSender.setPort(parseInt(mailProperties.getProperty("port")));

    mailSender.setUsername(mailProperties.getProperty("username"));
    mailSender.setPassword(mailProperties.getProperty("password"));

    final var props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    return mailSender;
  }

  @Bean
  TemplateEngine emailTemplateEngine() {
    final var templateEngine = new SpringTemplateEngine();
    templateEngine.addTemplateResolver(stringTemplateResolver());
    templateEngine.setTemplateEngineMessageSource(emailMessageSource());
    return templateEngine;
  }

  @Bean
  ResourceBundleMessageSource emailMessageSource() {
    final var messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("mail/MailMessages");
    return messageSource;
  }

  @Bean
  @ConfigurationProperties("spring.mail")
  Properties mailProperties() {
    return new Properties();
  }

  private ITemplateResolver stringTemplateResolver() {
    final var templateResolver = new StringTemplateResolver();
    templateResolver.setOrder(1);
    templateResolver.setTemplateMode("HTML5");
    templateResolver.setCacheable(false);
    return templateResolver;
  }
}
