/* rolesテーブル */
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');

/* usersテーブル */
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, license_number, password, role_id, enabled) VALUES (1, '管理ユーザー', 'カンリユーザー', '123-4567', '長野県伊那市美篶', '080-1234-5678', 'test@test.com', '999999999999','$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, license_number, password, role_id, enabled) VALUES (2, 'テストユーザー', 'テストユーザー', '123-4567', '長野県伊那市美篶', '080-1234-5678', 'testuser@test.com', '999999999999','$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true);

-- cars テーブルに10台分のデータを挿入
INSERT INTO cars (make, model, year, license_plate, type, rental_rate, status, created_at, updated_at)
VALUES
('Toyota', 'Corolla', '2020', 'ABC123', 'SEDAN', 30.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Honda', 'Civic', '2019', 'DEF456', 'SEDAN', 28.50, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ford', 'Mustang', '2021', 'GHI789', 'COUPE', 50.00, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Chevrolet', 'Malibu', '2018', 'JKL012', 'SEDAN', 25.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Tesla', 'Model 3', '2022', 'MNO345', 'SEDAN', 70.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('BMW', 'X5', '2020', 'PQR678', 'SUV', 80.00, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Mercedes-Benz', 'C-Class', '2019', 'STU901', 'SEDAN', 65.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Audi', 'Q7', '2021', 'VWX234', 'SUV', 85.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Nissan', 'Altima', '2017', 'YZA567', 'SEDAN', 22.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Hyundai', 'Sonata', '2019', 'BCD890', 'SEDAN', 27.00, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
