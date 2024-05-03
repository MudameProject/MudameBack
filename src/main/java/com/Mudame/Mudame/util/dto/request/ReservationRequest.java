package com.Mudame.Mudame.util.dto.request;

import java.sql.Time;
import java.util.Date;

public class ReservationRequest {
    @NotBlank(message = "la fecha es requerida")
    private Date date;
    private String origin;
    private String destiny;
    private Time hour;
    private int id_driver;
    private String plate_truck;
}
