const API_BASE = 'http://localhost:7777';
let currentUser = null;
let allFlights = [];
let selectedFlight = null;
let discount = 0;

document.addEventListener('DOMContentLoaded', () => {
    const today = new Date().toISOString().split('T')[0];
    const dateInput = document.getElementById('travelDate');
    if (dateInput) {
        dateInput.min = today;
        dateInput.value = today;
    }
});

function showLoader() {
    document.getElementById('loader').style.display = 'flex';
}

function hideLoader() {
    document.getElementById('loader').style.display = 'none';
}

function showMessage(msg, type) {
    const msgEl = document.getElementById('message');
    msgEl.textContent = msg;
    msgEl.className = `message ${type}`;
    msgEl.style.display = 'block';
    setTimeout(() => msgEl.style.display = 'none', 4000);
}

function showSection(section) {
    document.getElementById('searchSection').style.display = section === 'search' ? 'block' : 'none';
    document.getElementById('offersSection').style.display = section === 'offers' ? 'block' : 'none';
}

function showLogin() {
    document.getElementById('loginForm').style.display = 'block';
    document.getElementById('registerForm').style.display = 'none';
    document.querySelectorAll('.tab')[0].classList.add('active');
    document.querySelectorAll('.tab')[1].classList.remove('active');
}

function showRegister() {
    document.getElementById('loginForm').style.display = 'none';
    document.getElementById('registerForm').style.display = 'block';
    document.querySelectorAll('.tab')[0].classList.remove('active');
    document.querySelectorAll('.tab')[1].classList.add('active');
}

function swapCities() {
    const from = document.getElementById('from').value;
    const to = document.getElementById('to').value;
    document.getElementById('from').value = to;
    document.getElementById('to').value = from;
}

async function register() {
    const name = document.getElementById('regName').value.trim().split(' ');
    const phone = document.getElementById('regPhone').value.trim();
    const password = document.getElementById('regPassword').value;
    
    if (phone.length !== 10 || isNaN(phone)) {
        showMessage('Please enter a valid 10-digit phone number', 'error');
        return;
    }
    
    if (password.length < 6) {
        showMessage('Password must be at least 6 characters', 'error');
        return;
    }

    const data = {
        firstName: name[0] || '',
        lastName: name.slice(1).join(' ') || '',
        email: document.getElementById('regEmail').value.trim(),
        password: password,
        phoneNumber: parseInt(phone)
    };

    showLoader();
    try {
        const res = await fetch(`${API_BASE}/userregister`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        });
        const result = await res.json();
        
        if (result.status === 'success') {
            showMessage('🎉 Registration successful! Please login.', 'success');
            showLogin();
            document.getElementById('loginEmail').value = data.email;
        } else {
            showMessage(result.message || 'Registration failed', 'error');
        }
    } catch (err) {
        showMessage('Registration failed. Please try again.', 'error');
    } finally {
        hideLoader();
    }
}

async function login() {
    const data = {
        email: document.getElementById('loginEmail').value.trim(),
        password: document.getElementById('loginPassword').value
    };

    showLoader();
    try {
        const res = await fetch(`${API_BASE}/userlogin`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        });
        const result = await res.json();
        
        if (result.status === 'success') {
            currentUser = result.data;
            document.getElementById('authSection').style.display = 'none';
            document.getElementById('mainSection').style.display = 'block';
            document.getElementById('heroSection').style.display = 'none';
            document.getElementById('userEmail').textContent = currentUser.email;
            document.getElementById('logoutBtn').style.display = 'block';
            showMessage(`Welcome back, ${currentUser.firstName || 'User'}! 👋`, 'success');
            showSection('search');
        } else {
            showMessage(result.message || 'Invalid credentials', 'error');
        }
    } catch (err) {
        showMessage('Login failed. Please try again.', 'error');
    } finally {
        hideLoader();
    }
}

function logout() {
    currentUser = null;
    document.getElementById('authSection').style.display = 'block';
    document.getElementById('mainSection').style.display = 'none';
    document.getElementById('heroSection').style.display = 'block';
    document.getElementById('logoutBtn').style.display = 'none';
    document.getElementById('userEmail').textContent = '';
    resetBooking();
    showMessage('Logged out successfully', 'success');
}

document.getElementById('logoutBtn').addEventListener('click', logout);

async function searchFlights() {
    const from = document.getElementById('from').value;
    const to = document.getElementById('to').value;
    const travelDate = document.getElementById('travelDate').value;
    const passengers = document.getElementById('passengers').value;

    if (!from || !to) {
        showMessage('Please select both departure and destination cities', 'error');
        return;
    }

    if (from === to) {
        showMessage('Departure and destination cities cannot be the same', 'error');
        return;
    }

    showLoader();
    try {
        const res = await fetch(`${API_BASE}/searchByFlights?from=${from}&to=${to}&travelDate=${travelDate}&passengers=${passengers}`);
        const result = await res.json();
        
        if (result.status === 'success' && result.data && result.data.length > 0) {
            allFlights = result.data;
            displayFlights(allFlights);
            showMessage(`Found ${result.data.length} flights ✈️`, 'success');
        } else {
            document.getElementById('flightsSection').style.display = 'none';
            showMessage('No flights available for selected route', 'error');
        }
    } catch (err) {
        showMessage('Search failed. Please try again.', 'error');
    } finally {
        hideLoader();
    }
}

function sortFlights() {
    const sortBy = document.getElementById('sortBy').value;
    let sorted = [...allFlights];
    
    switch(sortBy) {
        case 'price-low':
            sorted.sort((a, b) => a.ticketPrice - b.ticketPrice);
            break;
        case 'price-high':
            sorted.sort((a, b) => b.ticketPrice - a.ticketPrice);
            break;
        case 'departure':
            sorted.sort((a, b) => a.departureTime.localeCompare(b.departureTime));
            break;
    }
    
    displayFlights(sorted);
}

function filterFlights() {
    const airline = document.getElementById('filterAirline').value;
    const filtered = airline ? allFlights.filter(f => f.flightName === airline) : allFlights;
    displayFlights(filtered);
}

function displayFlights(flights) {
    const list = document.getElementById('flightsList');
    list.innerHTML = flights.map(f => `
        <div class="flight-card">
            <div class="flight-info">
                <h3><i class="fas fa-plane"></i> ${f.flightName || 'Flight'} <span style="color: #666;">(${f.flightNumber || 'N/A'})</span></h3>
                <p class="flight-route">${f.source} <i class="fas fa-arrow-right"></i> ${f.destination}</p>
                <p><i class="fas fa-clock"></i> ${f.departureTime || 'N/A'} - ${f.arrivalTime || 'N/A'}</p>
                <p><i class="fas fa-chair"></i> Available: <strong>${f.availableSeats || 0}</strong> seats</p>
                <p class="flight-price">₹${f.ticketPrice || 0}</p>
            </div>
            <button onclick='selectFlightForBooking(${JSON.stringify(f).replace(/'/g, "&#39;")})'><i class="fas fa-ticket-alt"></i> Book Now</button>
        </div>
    `).join('');
    document.getElementById('flightsSection').style.display = 'block';
    document.getElementById('flightsSection').scrollIntoView({ behavior: 'smooth' });
}

function selectFlightForBooking(flight) {
    selectedFlight = flight;
    const passengers = parseInt(document.getElementById('passengers').value);
    
    if (flight.availableSeats < passengers) {
        showMessage(`Only ${flight.availableSeats} seats available`, 'error');
        return;
    }
    
    document.getElementById('selectedFlight').innerHTML = `
        <div class="booking-card">
            <h3><i class="fas fa-plane"></i> ${flight.flightName} (${flight.flightNumber})</h3>
            <p>${flight.source} → ${flight.destination}</p>
            <p>Date: ${document.getElementById('travelDate').value}</p>
            <p>Class: ${document.getElementById('travelClass').value}</p>
        </div>
    `;
    
    let passengerHTML = '';
    for(let i = 1; i <= passengers; i++) {
        passengerHTML += `
            <div style="margin-bottom: 1rem;">
                <h4>Passenger ${i}</h4>
                <input type="text" placeholder="Full Name" id="pax${i}name" required>
                <input type="number" placeholder="Age" id="pax${i}age" required>
            </div>
        `;
    }
    document.getElementById('passengerInputs').innerHTML = passengerHTML;
    
    const baseFare = flight.ticketPrice * passengers;
    const taxes = Math.round(baseFare * 0.18);
    
    document.getElementById('baseFare').textContent = `₹${baseFare}`;
    document.getElementById('taxes').textContent = `₹${taxes}`;
    document.getElementById('totalAmount').textContent = `₹${baseFare + taxes}`;
    
    document.getElementById('bookingSection').style.display = 'block';
    document.getElementById('bookingSection').scrollIntoView({ behavior: 'smooth' });
}

function applyPromo() {
    const code = document.getElementById('promoCode').value.toUpperCase();
    const baseFare = parseInt(document.getElementById('baseFare').textContent.replace('₹', ''));
    const taxes = parseInt(document.getElementById('taxes').textContent.replace('₹', ''));
    
    const promos = {
        'FIRST20': 0.20,
        'WEEKEND1000': 1000,
        'GROUP15': 0.15,
        'HDFC10': 0.10
    };
    
    if (promos[code]) {
        if (typeof promos[code] === 'number' && promos[code] < 1) {
            discount = Math.round(baseFare * promos[code]);
        } else {
            discount = promos[code];
        }
        
        document.getElementById('discount').textContent = `-₹${discount}`;
        document.getElementById('discountRow').style.display = 'flex';
        document.getElementById('totalAmount').textContent = `₹${baseFare + taxes - discount}`;
        document.getElementById('promoMessage').innerHTML = `<p style="color: #28a745; margin-top: 0.5rem;"><i class="fas fa-check-circle"></i> Promo applied! You saved ₹${discount}</p>`;
    } else {
        showMessage('Invalid promo code', 'error');
    }
}

function proceedToPayment() {
    document.getElementById('bookingSection').style.display = 'none';
    document.getElementById('paymentSection').style.display = 'block';
    document.getElementById('paymentSection').scrollIntoView({ behavior: 'smooth' });
}

function selectPayment(method) {
    const forms = {
        'card': `
            <div style="background: white; padding: 2rem; border-radius: 12px; margin-top: 1rem;">
                <h3>Card Details</h3>
                <input type="text" placeholder="Card Number" style="width: 100%; padding: 0.8rem; margin: 0.5rem 0; border: 2px solid #e0e0e0; border-radius: 8px;">
                <input type="text" placeholder="Card Holder Name" style="width: 100%; padding: 0.8rem; margin: 0.5rem 0; border: 2px solid #e0e0e0; border-radius: 8px;">
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
                    <input type="text" placeholder="MM/YY" style="padding: 0.8rem; border: 2px solid #e0e0e0; border-radius: 8px;">
                    <input type="text" placeholder="CVV" style="padding: 0.8rem; border: 2px solid #e0e0e0; border-radius: 8px;">
                </div>
                <button onclick="confirmBooking()" class="btn-primary" style="margin-top: 1rem;"><i class="fas fa-lock"></i> Pay Now</button>
            </div>
        `,
        'upi': `
            <div style="background: white; padding: 2rem; border-radius: 12px; margin-top: 1rem;">
                <h3>UPI Payment</h3>
                <input type="text" placeholder="Enter UPI ID" style="width: 100%; padding: 0.8rem; margin: 0.5rem 0; border: 2px solid #e0e0e0; border-radius: 8px;">
                <button onclick="confirmBooking()" class="btn-primary" style="margin-top: 1rem;"><i class="fas fa-mobile-alt"></i> Pay with UPI</button>
            </div>
        `,
        'netbanking': `
            <div style="background: white; padding: 2rem; border-radius: 12px; margin-top: 1rem;">
                <h3>Select Bank</h3>
                <select style="width: 100%; padding: 0.8rem; margin: 0.5rem 0; border: 2px solid #e0e0e0; border-radius: 8px;">
                    <option>HDFC Bank</option>
                    <option>ICICI Bank</option>
                    <option>SBI</option>
                    <option>Axis Bank</option>
                </select>
                <button onclick="confirmBooking()" class="btn-primary" style="margin-top: 1rem;"><i class="fas fa-university"></i> Proceed</button>
            </div>
        `,
        'wallet': `
            <div style="background: white; padding: 2rem; border-radius: 12px; margin-top: 1rem;">
                <h3>Select Wallet</h3>
                <select style="width: 100%; padding: 0.8rem; margin: 0.5rem 0; border: 2px solid #e0e0e0; border-radius: 8px;">
                    <option>Paytm</option>
                    <option>PhonePe</option>
                    <option>Google Pay</option>
                    <option>Amazon Pay</option>
                </select>
                <button onclick="confirmBooking()" class="btn-primary" style="margin-top: 1rem;"><i class="fas fa-wallet"></i> Pay Now</button>
            </div>
        `
    };
    
    document.getElementById('paymentForm').innerHTML = forms[method];
}

async function confirmBooking() {
    const passengers = parseInt(document.getElementById('passengers').value);
    const data = {
        flightId: selectedFlight.flightId,
        userId: currentUser.userId,
        email: currentUser.email,
        passengers: passengers
    };

    showLoader();
    try {
        const res = await fetch(`${API_BASE}/bookingflightticket`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        });
        const result = await res.json();
        
        if (result.status === 'success') {
            document.getElementById('paymentSection').style.display = 'none';
            displayConfirmation(result.data);
            showMessage('🎉 Payment successful! Booking confirmed!', 'success');
        } else {
            showMessage(result.message || 'Booking failed', 'error');
        }
    } catch (err) {
        showMessage('Booking failed. Please try again.', 'error');
    } finally {
        hideLoader();
    }
}

function displayConfirmation(booking) {
    document.getElementById('bookingConfirmation').innerHTML = `
        <div style="background: #f8f9fa; padding: 2rem; border-radius: 12px; margin: 2rem 0; text-align: left;">
            <p><strong>Booking ID:</strong> #${booking.bookingId}</p>
            <p><strong>Flight:</strong> ${booking.flightName || 'N/A'}</p>
            <p><strong>Route:</strong> ${booking.source} → ${booking.destination}</p>
            <p><strong>Date:</strong> ${booking.travelDate || 'N/A'}</p>
            <p><strong>Passengers:</strong> ${booking.passengers}</p>
            <p><strong>Amount Paid:</strong> <span style="color: #28a745; font-size: 1.5rem; font-weight: bold;">₹${booking.totalAmount || 0}</span></p>
            <p><strong>Status:</strong> <span style="color: #28a745; font-weight: bold;">CONFIRMED</span></p>
        </div>
    `;
    document.getElementById('confirmationSection').style.display = 'block';
    document.getElementById('confirmationSection').scrollIntoView({ behavior: 'smooth' });
}

function downloadTicket() {
    showMessage('📥 Ticket downloaded successfully!', 'success');
}

function emailTicket() {
    showMessage('📧 Ticket sent to your email!', 'success');
}

function resetBooking() {
    document.getElementById('flightsSection').style.display = 'none';
    document.getElementById('bookingSection').style.display = 'none';
    document.getElementById('paymentSection').style.display = 'none';
    document.getElementById('confirmationSection').style.display = 'none';
    selectedFlight = null;
    discount = 0;
    showSection('search');
}

async function loadBookingHistory() {
    showLoader();
    try {
        const res = await fetch(`${API_BASE}/bookinghistory/${currentUser.email}`);
        const result = await res.json();
        
        if (result.status === 'success' && result.data && result.data.length > 0) {
            displayHistory(result.data);
            showMessage(`Found ${result.data.length} bookings`, 'success');
        } else {
            document.getElementById('historyList').innerHTML = '<p style="text-align: center; color: #666; padding: 2rem;">No bookings found</p>';
        }
    } catch (err) {
        showMessage('Failed to load booking history', 'error');
    } finally {
        hideLoader();
    }
}

function displayHistory(bookings) {
    const list = document.getElementById('historyList');
    list.innerHTML = bookings.map(b => `
        <div class="booking-card" style="border-left: 5px solid ${b.bookingStatus === 'CANCELLED' ? '#dc3545' : '#667eea'};">
            <h4><i class="fas fa-ticket-alt"></i> Booking #${b.bookingId}</h4>
            <p><strong>${b.flightName || 'Flight'}:</strong> ${b.source} → ${b.destination}</p>
            <p><i class="fas fa-calendar"></i> ${b.travelDate || 'N/A'} | <i class="fas fa-users"></i> ${b.passengers} passengers</p>
            <p><i class="fas fa-rupee-sign"></i> Amount: ₹${b.totalAmount || 0}</p>
            <p><strong>Status:</strong> <span style="color: ${b.bookingStatus === 'CANCELLED' ? '#dc3545' : '#28a745'}; font-weight: bold;">${b.bookingStatus || 'CONFIRMED'}</span></p>
            ${b.bookingStatus !== 'CANCELLED' ? `<button class="cancel-btn" onclick="cancelBooking(${b.bookingId})"><i class="fas fa-times"></i> Cancel</button>` : ''}
        </div>
    `).join('');
}

async function cancelBooking(bookingId) {
    if (!confirm('Are you sure you want to cancel this booking?')) return;

    showLoader();
    try {
        const res = await fetch(`${API_BASE}/bookingflightCancel/${bookingId}`, {
            method: 'DELETE'
        });
        const result = await res.json();
        
        if (result.status === 'success') {
            showMessage('Booking cancelled successfully', 'success');
            loadBookingHistory();
        } else {
            showMessage(result.message || 'Cancellation failed', 'error');
        }
    } catch (err) {
        showMessage('Cancellation failed. Please try again.', 'error');
    } finally {
        hideLoader();
    }
}
