package com.example.demo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bagage;
import com.example.demo.entity.Booking;
import com.example.demo.exceptions.BookingIDNotFoundException;
import com.example.demo.exceptions.FlightTicketCancel;
import com.example.demo.model.BaggageReqDto;
import com.example.demo.model.BaggageResponse;
import com.example.demo.repository.BageRepo;
import com.example.demo.repository.BookingRepo;
import com.example.demo.service.BaggageService;

@Service
public class BaggageServiceImpl implements BaggageService {

	@Autowired
	private BookingRepo bookingRepo;

	@Autowired
	private BageRepo bageRepo;

	@Override
	public BaggageResponse checkLaugageWeight(BaggageReqDto baggageReqDto) {

		Booking booking = bookingRepo.findById(baggageReqDto.getBookingId())
				.orElseThrow(() -> new BookingIDNotFoundException("Book Id Not found"));

		if (booking.isCancelled()) {

			throw new FlightTicketCancel("Booking is cancelled. Cannot drop baggage.");
		}

		// if already check in

		Optional<Bagage> alreadyExist = bageRepo.findByBookingId(baggageReqDto.getBookingId());

		if (alreadyExist.isPresent() && alreadyExist.get().isDropped()) {

			throw new RuntimeException("Baggage already dropped for this booking");

		}

		double weight = baggageReqDto.getWeight();

		double extracharge = 0;

		if (weight >= 20) {

			extracharge = (weight - 20) * 300;
		}

		Bagage bg = new Bagage();
		bg.setBooking(booking);
		bg.setExtraCharge(extracharge);
		bg.setWeight(weight);
		bg.setDropped(true);
		bageRepo.save(bg);
		return new BaggageResponse(booking.getId(), weight, extracharge, "lagauge is check success");
	}

}
