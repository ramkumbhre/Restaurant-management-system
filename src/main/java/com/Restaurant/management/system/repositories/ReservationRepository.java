package com.Restaurant.management.system.repositories;

import com.Restaurant.management.system.dtos.ReservationDto;
import com.Restaurant.management.system.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUserId(Long customerId);
}
