package com.jdaalba.mapper;

import com.jdaalba.dto.ReservaDto;
import com.jdaalba.entity.Reserva;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservaDtoMapper {

  ReservaDtoMapper INSTACE = Mappers.getMapper(ReservaDtoMapper.class);

  @Mapping(target = "hora", source = "momentoReserva")
  ReservaDto from(Reserva reserva);

  default LocalTime map(LocalDateTime value) {
    return value.toLocalTime();
  }
}
