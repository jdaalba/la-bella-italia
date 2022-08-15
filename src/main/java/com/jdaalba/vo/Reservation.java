package com.jdaalba.vo;

import java.time.LocalDateTime;

public record Reservation(
    String name,
    String email,
    String phone,
    int numberOfPersons,
    LocalDateTime at,
    String otherComments
) {

}
