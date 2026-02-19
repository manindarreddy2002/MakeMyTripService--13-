# MakeMyTrip Frontend

## Setup Instructions

1. Make sure the backend is running on http://localhost:7777

2. Open `index.html` in your browser or use a simple HTTP server:

   Using Python:
   ```
   python -m http.server 8000
   ```
   
   Using Node.js:
   ```
   npx http-server
   ```

3. Access the frontend at http://localhost:8000

## Features

- User Registration & Login
- Search Flights by source, destination, date, and passengers
- Book Flight Tickets
- View Booking History
- Cancel Bookings

## API Endpoints Used

- POST /userregister - Register new user
- POST /userlogin - User login
- GET /searchByFlights - Search available flights
- POST /bookingflightticket - Book a flight
- GET /bookinghistory/{email} - Get booking history
- DELETE /bookingflightCancel/{bookId} - Cancel booking
