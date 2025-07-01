use bus_ticket_booking_system;

-- 1. Bus Company Table
CREATE TABLE bus_company (
                             company_id INT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(100) NOT NULL UNIQUE
);

-- 2. Bus Table
CREATE TABLE bus (
                     bus_id INT AUTO_INCREMENT PRIMARY KEY,
                     name VARCHAR(100) NOT NULL,
                     capacity INT NOT NULL,
                     company_id INT NOT NULL,
                     FOREIGN KEY (company_id) REFERENCES bus_company(company_id)
);

-- 3. Route Table
CREATE TABLE route (
                       route_id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL UNIQUE
);

-- 4. Bus-Route Mapping Table (Many-to-Many)
CREATE TABLE bus_route (
                           bus_id INT NOT NULL,
                           route_id INT NOT NULL,
                           PRIMARY KEY (bus_id, route_id),
                           FOREIGN KEY (bus_id) REFERENCES bus(bus_id) ON DELETE CASCADE,
                           FOREIGN KEY (route_id) REFERENCES route(route_id) ON DELETE CASCADE
);

CREATE TABLE trip (
                      trip_id INT AUTO_INCREMENT PRIMARY KEY,
                      bus_id INT NOT NULL,
                      route_id INT NOT NULL,
                      departure_time DATETIME NOT NULL,
                      arrival_time DATETIME,
                      price DECIMAL(10, 2),
                      FOREIGN KEY (bus_id) REFERENCES bus(bus_id),
                      FOREIGN KEY (route_id) REFERENCES route(route_id)
);

CREATE TABLE booking (
                         booking_id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id INT NOT NULL,
                         trip_id INT NOT NULL,
                         seat_number VARCHAR(10) NOT NULL,
                         booking_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                         UNIQUE (trip_id, seat_number),
                         FOREIGN KEY (user_id) REFERENCES users(user_id),
                         FOREIGN KEY (trip_id) REFERENCES trip(trip_id)
);
