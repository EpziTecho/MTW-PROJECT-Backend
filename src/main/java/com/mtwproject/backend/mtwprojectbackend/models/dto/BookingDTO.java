package com.mtwproject.backend.mtwprojectbackend.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDTO {
    private Long idBooking;
    private String status;
}
