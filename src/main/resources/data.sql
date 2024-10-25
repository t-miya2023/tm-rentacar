/* rolesテーブル */
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');

/* usersテーブル */
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, license_number, password, role_id, enabled) VALUES (1, '管理ユーザー', 'カンリユーザー', '123-4567', '長野県伊那市美篶', '080-1234-5678', 'test@test.com', '999999999999','$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, license_number, password, role_id, enabled) VALUES (2, 'テストユーザー', 'テストユーザー', '123-4567', '長野県伊那市美篶', '080-1234-5678', 'testuser@test.com', '999999999999','$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true);

-- cars テーブルに10台分のデータを挿入
INSERT IGNORE INTO cars (id, make, model, year, license_plate, type, rental_rate, status, created_at, updated_at) VALUES
(1, 'Toyota', 'Corolla', '2020', 'ABC-123', 'SEDAN', 25.00, 'AVAILABLE', NOW(), NOW()),
(2, 'Nissan', 'Fairlady Z', '2021', 'BCD-234', 'SPORT', 35.00, 'RENTED', NOW(), NOW()),
(3, 'Suzuki', 'Alto', '2019', 'CDE-345', 'LIGHTVEHICLE', 20.00, 'AVAILABLE', NOW(), NOW()),
(4, 'Toyota', 'HiAce', '2018', 'DEF-456', 'ONEBOX', 40.00, 'UNDER_MAINTENANCE', NOW(), NOW()),
(5, 'Honda', 'Fit', '2022', 'EFG-567', 'COMPACT', 23.00, 'AVAILABLE', NOW(), NOW()),
(6, 'Subaru', 'Outback', '2020', 'FGH-678', 'STATIONWAGON', 33.00, 'RENTED', NOW(), NOW()),
(7, 'Mazda', 'CX-5', '2021', 'GHI-789', 'SUV', 37.00, 'AVAILABLE', NOW(), NOW()),
(8, 'BMW', 'Z4', '2020', 'HIJ-890', 'OPENCAR', 45.00, 'UNDER_MAINTENANCE', NOW(), NOW()),
(9, 'Toyota', '86', '2019', 'JKL-901', 'COUPE', 32.00, 'AVAILABLE', NOW(), NOW()),
(10, 'Nissan', 'Leaf', '2022', 'KLM-012', 'EV', 28.00, 'RENTED', NOW(), NOW());
