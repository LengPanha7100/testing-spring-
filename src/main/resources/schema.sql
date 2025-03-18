CREATE TABLE tickets (
         ticket_id SERIAL PRIMARY KEY,
         passenger_name VARCHAR(255) NOT NULL,
         travel_date DATE NOT NULL,
         source_station VARCHAR(255) NOT NULL,
         destination_station VARCHAR(255) NOT NULL,
         price DECIMAL(10, 2) NOT NULL,
         payment_status VARCHAR(50) NOT NULL,
         ticket_status VARCHAR(50) NOT NULL,
         seat_number VARCHAR(10) NOT NULL
);

INSERT INTO tickets (passenger_name, travel_date, source_station, destination_station, price, payment_status, ticket_status, seat_number)
VALUES
    ('John Doe', '2025-03-15', 'New York', 'Los Angeles', 150.00, 'Paid', 'Confirmed', 'A1'),
    ('Jane Smith', '2025-03-16', 'Chicago', 'Houston', 120.50, 'Paid', 'Confirmed', 'B3'),
    ('Alice Brown', '2025-03-17', 'San Francisco', 'Seattle', 90.75, 'Pending', 'Pending', 'C2'),
    ('Bob White', '2025-03-18', 'Miami', 'Atlanta', 85.00, 'Paid', 'Cancelled', 'D5'),
    ('Emma Johnson', '2025-03-19', 'Denver', 'Las Vegas', 110.25, 'Pending', 'Pending', 'E4');


SELECT * FROM tickets;

SELECT * FROM tickets WHERE passenger_name = 'John Doe';

UPDATE tickets SET passenger_name = 'John' WHERE ticket_id = 8;

SELECT * FROM tickets WHERE ticket_status = 'BOOKED' AND travel_date = '2025-03-15';

DELETE FROM tickets WHERE ticket_id = 2;