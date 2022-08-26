package com.rvs.ReservationRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rvs.model.Reservation;

public interface ReservationRepo extends JpaRepository <Reservation, Long> {

}