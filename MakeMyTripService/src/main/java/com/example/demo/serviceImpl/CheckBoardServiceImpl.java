package com.example.demo.serviceImpl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Flight;
import com.example.demo.exceptions.BookingIDNotFoundException;
import com.example.demo.model.BoardingPassDto;
import com.example.demo.model.CheckReqDto;
import com.example.demo.repository.BookingRepo;
import com.example.demo.repository.FlightRepo;
import com.example.demo.service.CheckBoardService;

@Service
public class CheckBoardServiceImpl implements CheckBoardService {

	@Autowired
	private BookingRepo bookingRepo;

	@Autowired
	private FlightRepo flightRepo;

	@Override
	public BoardingPassDto doCheckIn(CheckReqDto checkReqDto) {
		Booking booking = bookingRepo.findById(checkReqDto.getBookingId())
				.orElseThrow(() -> new RuntimeException("Booking not found"));

		// Email match validation

		if (!booking.getEmail().equalsIgnoreCase(checkReqDto.getPassengerEmail())) {
			throw new RuntimeException("Passenger email mismatch");
		}
	

		if (booking.isCancelled()) {
			throw new RuntimeException("You cannot check-in because booking is already cancelled");
		}

		if (booking.isCheckedIn()) {
			throw new RuntimeException("Already checked-in, boarding pass already generated");
		}

		
		// Genarete seat number randlomly
		
		String seatNo = "A-" + (new Random().nextInt(50) +1);
		booking.setCheckedIn(true);
		booking.setSeatNumber(seatNo);
		bookingRepo.save(booking);
		
		Flight flight = booking.getFlight();
		BoardingPassDto pass = new BoardingPassDto();
	        pass.setBookingId(booking.getId());
	        pass.setPassengerName(booking.getPassengerName());
	        pass.setEmail(booking.getEmail());
	        pass.setPhone(booking.getPhone());
	        pass.setFlightNumber(flight.getFlightNumber());
	        pass.setFromCity(flight.getFromCity());
	        pass.setToCity(flight.getToCity());
	        pass.setTravelDate(flight.getTravelDate().toString());
	        pass.setDepartureTime(flight.getDepartureTime().toString());
	        pass.setSeatNumber(seatNo);

	        return pass;
	}
		


}
