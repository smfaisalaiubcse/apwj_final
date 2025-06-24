-- USERS TABLE (for authentication and roles)
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role ENUM('ADMIN', 'USER') NOT NULL
);

-- PRODUCTS TABLE (for product information)
CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          category ENUM('BEAUTY_CARE', 'VEGETABLES', 'MEAT', 'GROCERIES', 'OTHERS') NOT NULL,
                          price DECIMAL(10, 2) NOT NULL,
                          quantity INT NOT NULL,
                          expiry_date DATE,
                          discount DECIMAL(5, 2) DEFAULT 0.00,
                          available BOOLEAN DEFAULT TRUE
);

-- WISHLISTS TABLE (user-specific wishlist)
CREATE TABLE wishlists (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT NOT NULL,
                           product_id BIGINT NOT NULL,
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                           FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- CARTS TABLE (for user's shopping cart)
CREATE TABLE carts (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       product_id BIGINT NOT NULL,
                       quantity INT NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                       FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- ORDERS TABLE (for order information)
CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        total_price DECIMAL(10, 2) NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ORDER_ITEMS TABLE (to record items under orders)
CREATE TABLE order_items (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             order_id BIGINT NOT NULL,
                             product_id BIGINT NOT NULL,
                             quantity INT NOT NULL,
                             price DECIMAL(10, 2) NOT NULL,
                             FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
                             FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- LOG TABLE (optional, for storing logs in database)
CREATE TABLE logs (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50),
                      accessed_method VARCHAR(255),
                      timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);