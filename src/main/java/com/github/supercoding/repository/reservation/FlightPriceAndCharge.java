package com.github.supercoding.repository.reservation;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlightPriceAndCharge {
    private Double flightPrice;
    private Double charge;
}