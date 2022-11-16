package com.jdaalba.scheduler;

import com.jdaalba.repository.ReservaRepository;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public record ReservasJob(ReservaRepository reservaRepository) {

  @Scheduled(cron = "${app.jobs.reservas-job}")
  public void run() {
    log.info("Limpiando reservas");
    reservaRepository.deleteAllByMomentoReservaBefore(LocalDateTime.now());
  }
}