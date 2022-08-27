DROP TABLE reservation_test;

CREATE TABLE IF NOT EXISTS reservation_test (
reservationsId int PRIMARY KEY AUTO_INCREMENT,
customerName VARCHAR(100) NOT NULL,
email varchar(100) NOT NULL,
timeCreated time(6),
reservationDetails TEXT,
);