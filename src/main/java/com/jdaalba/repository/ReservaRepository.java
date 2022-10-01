package com.jdaalba.repository;

import com.jdaalba.entity.Reserva;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservaRepository extends MongoRepository<Reserva, String> {

  Page<Reserva> findAllByConfirmadaFalse(Pageable pageable);

  Page<Reserva> findAllByMomentoReservaBetweenAndConfirmadaTrueOrderByMomentoReservaAsc(
      LocalDateTime l1, LocalDateTime l2, Pageable pageable);
}
