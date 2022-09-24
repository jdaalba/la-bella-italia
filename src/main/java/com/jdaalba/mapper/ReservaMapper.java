package com.jdaalba.mapper;

import com.jdaalba.entity.Reserva;
import com.jdaalba.request.ReservaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservaMapper {

  ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);

  Reserva from(ReservaRequest reservaRequest);
}
