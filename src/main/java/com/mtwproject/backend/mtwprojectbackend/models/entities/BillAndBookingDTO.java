package com.mtwproject.backend.mtwprojectbackend.models.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillAndBookingDTO {

    private Bill bill;
    private List<Booking> bookings;

}
