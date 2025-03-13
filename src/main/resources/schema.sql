CREATE TABLE tickets (
         ticketId SERIAL PRIMARY KEY,
         passengerName VARCHAR(255) NOT NULL,
         travelDate DATE NOT NULL,
         sourceStation VARCHAR(255) NOT NULL,
         destinationStation VARCHAR(255) NOT NULL,
         price DECIMAL(10, 2) NOT NULL,
         paymentStatus VARCHAR(50) NOT NULL,
         ticketStatus VARCHAR(50) NOT NULL,
         seatNumber VARCHAR(10) NOT NULL
);

INSERT INTO tickets (passengerName, travelDate, sourceStation, destinationStation, price, paymentStatus, ticketStatus, seatNumber)
VALUES
    ('John Doe', '2025-03-15', 'New York', 'Los Angeles', 150.00, 'Paid', 'Confirmed', 'A1'),
    ('Jane Smith', '2025-03-16', 'Chicago', 'Houston', 120.50, 'Paid', 'Confirmed', 'B3'),
    ('Alice Brown', '2025-03-17', 'San Francisco', 'Seattle', 90.75, 'Pending', 'Pending', 'C2'),
    ('Bob White', '2025-03-18', 'Miami', 'Atlanta', 85.00, 'Paid', 'Cancelled', 'D5'),
    ('Emma Johnson', '2025-03-19', 'Denver', 'Las Vegas', 110.25, 'Pending', 'Pending', 'E4');


SELECT * FROM tickets;

SELECT * FROM  tickets WHERE ticketId = 1;


UPDATE tickets SET passengerName = 'John' WHERE ticketId = 1;

DELETE FROM tickets WHERE ticketId = 2;