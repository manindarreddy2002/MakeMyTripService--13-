package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Flight;
import com.example.demo.exceptions.BookingIDNotFoundException;
import com.example.demo.exceptions.CancelBookingException;
import com.example.demo.exceptions.FlightNotFoundException;
import com.example.demo.exceptions.SeatsNotFoundException;
import com.example.demo.model.BookingReqDto;
import com.example.demo.repository.BookingRepo;
import com.example.demo.repository.FlightRepo;
import com.example.demo.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private FlightRepo flightRepo;

	@Autowired
	private BookingRepo bookingRepo;

	@Override
	public Booking bookFlight(BookingReqDto bookingReqDto) {

		Flight flight = flightRepo.findById(bookingReqDto.getFlightId())
				.orElseThrow(() -> new FlightNotFoundException("Flight not found"));

		if (bookingReqDto.getPassengers() > flight.getAvailableSeats()) {

			throw new SeatsNotFoundException("Flight Not enough seats avalible");
		}

		flight.setAvailableSeats(flight.getAvailableSeats() - bookingReqDto.getPassengers());

		flightRepo.save(flight);

//		Booking book = new Booking();
//		book.setFlight(flight);
//		book.setPassengerName(bookingReqDto.getPassengerName());
//		book.setPassengers(bookingReqDto.getPassengers());
//		book.setEmail(bookingReqDto.getEmail());
//		book.setPhone(bookingReqDto.getPhone());
//		book.setTotalCost(bookingReqDto.getPassengers() * flight.getPrice());

		Booking book = Booking.builder().flight(flight).passengerName(bookingReqDto.getPassengerName())
				.passengers(bookingReqDto.getPassengers()).email(bookingReqDto.getEmail())
				.phone(bookingReqDto.getPhone()).totalCost(bookingReqDto.getPassengers() * flight.getPrice()).build();
		return bookingRepo.save(book);

	}

	@Override
	public String bookCancelFlight(Long bookId) {
		try {
			Booking booking = bookingRepo.findById(bookId)
					.orElseThrow(() -> new BookingIDNotFoundException("Booking Id Not found"));

			if (booking.isCancelled()) {
				throw new CancelBookingException("Booking already cancelled");
			}
			

			// Restore the seats
			Flight flight = booking.getFlight();

			flight.setAvailableSeats(flight.getAvailableSeats() + booking.getPassengers());

			flightRepo.save(flight);

			// update the ticktes
			booking.setCancelled(true);

			bookingRepo.save(booking);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Flight booking cancel Sucessfully and Restore the seats";

	}

	@Override
	public Booking getByBookFlightId(Long bookId) {
		
//		return bookingRepo.findById(bookId).orElseThrow(()-> new BookingIDNotFoundException("Booking Id Not found"));
		
		
		 Optional<Booking> byId = bookingRepo.findById(bookId);
		 
		 if(byId.isPresent()) {
			 
			 return byId.get();
		 }else {
			 
			 throw new BookingIDNotFoundException("Booking Id Not found");
		 }
		
	}

	@Override
	public List<Booking> getByEmailListBookingHistroy(String email) {
		
		List<Booking> byEmail = bookingRepo.findByEmail(email);
		
		return byEmail;
	}

}
