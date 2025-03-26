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

CREATE TABLE instructor_db(
  instructor_id SERIAL PRIMARY KEY ,
  instructor_name VARCHAR(200) NOT NULL,
  email VARCHAR(200) NOT NULL
);

SELECT * FROM  instructor_db;

INSERT INTO instructor_db (instructor_name, email)
VALUES
    ('John Doe', 'john.doe@example.com'),
    ('Jane Smith', 'jane.smith@example.com'),
    ('Mark Johnson', 'mark.johnson@example.com'),
    ('Emily Davis', 'emily.davis@example.com'),
    ('Michael Brown', 'michael.brown@example.com');

SELECT * FROM instructor_db WHERE instructor_id = 2;

UPDATE instructor_db SET instructor_name = 'Panha' WHERE instructor_id = 1 ;

DELETE FROM instructor_db where instructor_id = 2;



CREATE TABLE course_db(
      course_id SERIAL PRIMARY KEY ,
      course_name VARCHAR(50) NOT NULL ,
      instructor_id INT,
      CONSTRAINT fk_instructor FOREIGN KEY (instructor_id) REFERENCES instructor_db(instructor_id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO course_db(course_name, instructor_id)
VALUES ('math',3);

SELECT * FROM course_db;

SELECT * FROM course_db WHERE course_id = 1;

DELETE FROM course_db WHERE course_id = 1 ;

UPDATE course_db SET course_name = 'english'
WHERE course_id=4;

CREATE TABLE student_db(
     student_id SERIAL PRIMARY KEY ,
     student_name VARCHAR(50) not null ,
     phone_number VARCHAR(40) NOT NULL ,
     course_id INT,
     CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course_db(course_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE student_course_db(
    id SERIAL PRIMARY KEY ,
    student_id INT,
    course_id INT,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student_db(student_id) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT fk_course FOREIGN KEY  (course_id) REFERENCES  course_db(course_id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO  student_db(student_name, phone_number, course_id)
VALUES ('panha','0974821711' , 4);

SELECT * FROM student_db WHERE student_id = 1;

SELECT c.course_id , c.course_name , c.instructor_id FROM course_db c
JOIN student_course_db scd on c.course_id = scd.course_id
WHERE student_id = 1;

INSERT INTO student_course_db(student_id, course_id)
VALUES (1 ,4 );

SELECT * FROM student_course_db;

SELECT * FROM student_db;

DELETE FROM student_db WHERE student_id = 1;

INSERT INTO student_db(student_name, phone_number, course_id)
VALUES ('panha','0974821711' , 4);

UPDATE student_db SET student_name = 'Nita' , phone_number = '054896578' , course_id = 4
WHERE student_id = 2;

INSERT INTO student_course_db(student_id, course_id)
VALUES (2,4);

DELETE FROM student_course_db WHERE student_id = 2;

SELECT * FROM student_db WHERE student_name = 'panha';