-- Insert sample flights
INSERT INTO flight (flight_name, flight_number, source, destination, departure_time, arrival_time, available_seats, ticket_price, travel_date) VALUES
('IndiGo', '6E-2045', 'Delhi', 'Mumbai', '06:00', '08:15', 180, 4500, CURRENT_DATE),
('Air India', 'AI-860', 'Delhi', 'Mumbai', '09:30', '11:45', 150, 5200, CURRENT_DATE),
('SpiceJet', 'SG-8709', 'Delhi', 'Mumbai', '14:20', '16:35', 189, 4200, CURRENT_DATE),
('Vistara', 'UK-995', 'Delhi', 'Mumbai', '18:45', '21:00', 158, 6800, CURRENT_DATE),

('IndiGo', '6E-5089', 'Mumbai', 'Delhi', '07:15', '09:30', 180, 4600, CURRENT_DATE),
('Air India', 'AI-865', 'Mumbai', 'Delhi', '12:00', '14:15', 150, 5300, CURRENT_DATE),
('SpiceJet', 'SG-8134', 'Mumbai', 'Delhi', '16:30', '18:45', 189, 4300, CURRENT_DATE),

('IndiGo', '6E-6112', 'Delhi', 'Bangalore', '05:45', '08:30', 180, 5500, CURRENT_DATE),
('Air India', 'AI-804', 'Delhi', 'Bangalore', '10:20', '13:05', 150, 6200, CURRENT_DATE),
('Vistara', 'UK-851', 'Delhi', 'Bangalore', '15:40', '18:25', 158, 7500, CURRENT_DATE),

('IndiGo', '6E-463', 'Bangalore', 'Delhi', '06:30', '09:15', 180, 5600, CURRENT_DATE),
('Air India', 'AI-503', 'Bangalore', 'Delhi', '13:45', '16:30', 150, 6300, CURRENT_DATE),

('IndiGo', '6E-6177', 'Mumbai', 'Bangalore', '08:00', '10:15', 180, 4800, CURRENT_DATE),
('SpiceJet', 'SG-1635', 'Mumbai', 'Bangalore', '13:30', '15:45', 189, 4500, CURRENT_DATE),
('Vistara', 'UK-863', 'Mumbai', 'Bangalore', '19:00', '21:15', 158, 6500, CURRENT_DATE),

('IndiGo', '6E-5315', 'Bangalore', 'Mumbai', '07:45', '10:00', 180, 4900, CURRENT_DATE),
('SpiceJet', 'SG-502', 'Bangalore', 'Mumbai', '14:15', '16:30', 189, 4600, CURRENT_DATE),

('IndiGo', '6E-2175', 'Delhi', 'Hyderabad', '06:15', '08:30', 180, 5200, CURRENT_DATE),
('Air India', 'AI-544', 'Delhi', 'Hyderabad', '11:45', '14:00', 150, 5900, CURRENT_DATE),

('IndiGo', '6E-6024', 'Hyderabad', 'Delhi', '09:00', '11:15', 180, 5300, CURRENT_DATE),
('Air India', 'AI-541', 'Hyderabad', 'Delhi', '15:30', '17:45', 150, 6000, CURRENT_DATE),

('IndiGo', '6E-6897', 'Delhi', 'Chennai', '07:30', '10:15', 180, 6200, CURRENT_DATE),
('Air India', 'AI-433', 'Delhi', 'Chennai', '14:00', '16:45', 150, 6900, CURRENT_DATE),

('IndiGo', '6E-2218', 'Chennai', 'Delhi', '08:15', '11:00', 180, 6300, CURRENT_DATE),
('Air India', 'AI-440', 'Chennai', 'Delhi', '16:30', '19:15', 150, 7000, CURRENT_DATE),

('IndiGo', '6E-6191', 'Mumbai', 'Goa', '09:30', '11:00', 180, 3500, CURRENT_DATE),
('SpiceJet', 'SG-1623', 'Mumbai', 'Goa', '15:00', '16:30', 189, 3200, CURRENT_DATE),

('IndiGo', '6E-5038', 'Goa', 'Mumbai', '12:00', '13:30', 180, 3600, CURRENT_DATE),
('SpiceJet', 'SG-1624', 'Goa', 'Mumbai', '17:30', '19:00', 189, 3300, CURRENT_DATE),

('IndiGo', '6E-2156', 'Delhi', 'Kolkata', '06:45', '09:00', 180, 5800, CURRENT_DATE),
('Air India', 'AI-764', 'Delhi', 'Kolkata', '13:15', '15:30', 150, 6500, CURRENT_DATE),

('IndiGo', '6E-2003', 'Kolkata', 'Delhi', '10:00', '12:15', 180, 5900, CURRENT_DATE),
('Air India', 'AI-765', 'Kolkata', 'Delhi', '16:45', '19:00', 150, 6600, CURRENT_DATE);
