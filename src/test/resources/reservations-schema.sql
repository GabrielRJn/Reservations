DROP TABLE IF EXISTS reservation_test;

CREATE TABLE IF NOT EXISTS reservation_test(
reservationid int PRIMARY KEY AUTO_INCREMENT,
customer_name VARCHAR(100) NOT NULL,
email varchar(100) NOT NULL,
time_created datetime(6),
reservation_details TEXT
);