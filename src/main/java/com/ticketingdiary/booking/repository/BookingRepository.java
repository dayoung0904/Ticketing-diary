package com.ticketingdiary.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketingdiary.booking.Entiry.BookingEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

	public List<BookingEntity> findByUserId(int userId);
}
