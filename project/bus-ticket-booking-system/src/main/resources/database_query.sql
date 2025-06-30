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
