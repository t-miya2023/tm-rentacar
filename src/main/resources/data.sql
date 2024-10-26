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
(10, 'Nissan', 'Leaf', '2022', 'KLM-012', 'EV', 28.00, 'RENTED', NOW(), NOW()),
(11, 'Honda', 'Civic', '2020', 'LMN-123', 'SEDAN', 26.00, 'AVAILABLE', NOW(), NOW()),
(12, 'Mitsubishi', 'Lancer', '2019', 'MNO-234', 'SEDAN', 24.00, 'RENTED', NOW(), NOW()),
(13, 'Toyota', 'Prius', '2021', 'NOP-345', 'HYBRID', 29.00, 'AVAILABLE', NOW(), NOW()),
(14, 'Mazda', 'MX-5', '2018', 'OPQ-456', 'COUPE', 34.00, 'UNDER_MAINTENANCE', NOW(), NOW()),
(15, 'Nissan', 'X-Trail', '2020', 'PQR-567', 'SUV', 36.00, 'AVAILABLE', NOW(), NOW()),
(16, 'Toyota', 'C-HR', '2021', 'QRS-678', 'SUV', 38.00, 'RENTED', NOW(), NOW()),
(17, 'Honda', 'CR-V', '2019', 'RST-789', 'SUV', 35.00, 'AVAILABLE', NOW(), NOW()),
(18, 'Nissan', 'Serena', '2020', 'STU-890', 'ONEBOX', 42.00, 'UNDER_MAINTENANCE', NOW(), NOW()),
(19, 'Subaru', 'BRZ', '2021', 'TUV-901', 'SPORT', 33.00, 'AVAILABLE', NOW(), NOW()),
(20, 'Toyota', 'Camry', '2022', 'UVW-012', 'SEDAN', 27.00, 'RENTED', NOW(), NOW()),
(21, 'Mazda', 'Atenza', '2018', 'VWX-123', 'STATIONWAGON', 32.00, 'AVAILABLE', NOW(), NOW()),
(22, 'BMW', 'X3', '2021', 'WXY-234', 'SUV', 40.00, 'RENTED', NOW(), NOW()),
(23, 'Tesla', 'Model 3', '2022', 'XYZ-345', 'EV', 50.00, 'AVAILABLE', NOW(), NOW()),
(24, 'Mitsubishi', 'Outlander', '2019', 'YZA-456', 'SUV', 39.00, 'UNDER_MAINTENANCE', NOW(), NOW()),
(25, 'Toyota', 'Aqua', '2020', 'ZAB-567', 'COMPACT', 22.00, 'AVAILABLE', NOW(), NOW()),
(26, 'Honda', 'Vezel', '2021', 'ABC-678', 'COMPACT', 25.00, 'RENTED', NOW(), NOW()),
(27, 'Nissan', 'Juke', '2019', 'BCD-789', 'SUV', 30.00, 'AVAILABLE', NOW(), NOW()),
(28, 'Mazda', 'Verisa', '2020', 'CDE-890', 'COMPACT', 21.00, 'UNDER_MAINTENANCE', NOW(), NOW()),
(29, 'Toyota', 'RAV4', '2021', 'DEF-901', 'SUV', 41.00, 'AVAILABLE', NOW(), NOW()),
(30, 'Honda', 'Insight', '2022', 'EFG-012', 'HYBRID', 31.00, 'RENTED', NOW(), NOW());
