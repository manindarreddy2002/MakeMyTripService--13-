package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Bagage;

public interface BageRepo extends JpaRepository<Bagage, Long>{


	Optional<Bagage> findByBookingId(Long bookingId);

}
