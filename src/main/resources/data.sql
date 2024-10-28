/* rolesテーブル */
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');

/* usersテーブル */
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, license_number, password, role_id, enabled) VALUES (1, '管理ユーザー', 'カンリユーザー', '123-4567', '長野県伊那市美篶', '080-1234-5678', 'test@test.com', '999999999999','$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, license_number, password, role_id, enabled) VALUES (2, 'テストユーザー', 'テストユーザー', '123-4567', '長野県伊那市美篶', '080-1234-5678', 'testuser@test.com', '999999999999','$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true);

-- cars テーブルに10台分のデータを挿入
INSERT IGNORE INTO cars (id, make, model, year, license_plate, type, rental_rate, status, created_at, updated_at) VALUES
(1, 'Toyota', 'Corolla', '2020', '品川　あ　12-34', 'SEDAN', 1000, 'AVAILABLE', NOW(), NOW()),
(2, 'Honda', 'Civic', '2021', '渋谷　あ　34-12', 'SEDAN', 1100, 'RENTED', NOW(), NOW()),
(3, 'Nissan', 'Leaf', '2022', '新宿　あ　56-78', 'EV', 1200, 'UNDER_MAINTENANCE', NOW(), NOW()),
(4, 'Mazda', 'CX-5', '2019', '池袋　あ　90-12', 'SUV', 1300, 'AVAILABLE', NOW(), NOW()),
(5, 'Subaru', 'Outback', '2020', '横浜　あ　12-34', 'LIGHTVEHICLE', 1400, 'RENTED', NOW(), NOW()),
(6, 'Ford', 'Mustang', '2023', '名古屋　あ　34-56', 'SPORT', 1500, 'AVAILABLE', NOW(), NOW()),
(7, 'Chevrolet', 'Camaro', '2022', '福岡　あ　78-90', 'COUPE', 1600, 'UNDER_MAINTENANCE', NOW(), NOW()),
(8, 'Hyundai', 'Elantra', '2021', '札幌　あ　12-34', 'SEDAN', 1700, 'AVAILABLE', NOW(), NOW()),
(9, 'Kia', 'Soul', '2020', '仙台　あ　34-12', 'ONEBOX', 1800, 'RENTED', NOW(), NOW()),
(10, 'Volkswagen', 'Golf', '2019', '京都　あ　56-78', 'COMPACT', 1900, 'UNDER_MAINTENANCE', NOW(), NOW()),
(11, 'Audi', 'A4', '2021', '神戸　あ　90-12', 'SEDAN', 2000, 'AVAILABLE', NOW(), NOW()),
(12, 'Mercedes-Benz', 'C-Class', '2022', '横須賀　あ　12-34', 'SEDAN', 2100, 'RENTED', NOW(), NOW()),
(13, 'BMW', '3 Series', '2023', '福島　あ　34-56', 'SEDAN', 2200, 'UNDER_MAINTENANCE', NOW(), NOW()),
(14, 'Lexus', 'RX', '2020', '千葉　あ　78-90', 'SUV', 2300, 'AVAILABLE', NOW(), NOW()),
(15, 'Porsche', '911', '2021', '静岡　あ　12-34', 'SPORT', 2400, 'RENTED', NOW(), NOW()),
(16, 'Jaguar', 'F-Pace', '2022', '岡山　あ　34-12', 'SUV', 2500, 'UNDER_MAINTENANCE', NOW(), NOW()),
(17, 'Tesla', 'Model 3', '2023', '広島　あ　56-78', 'EV', 2600, 'AVAILABLE', NOW(), NOW()),
(18, 'Nissan', 'Rogue', '2020', '熊本　あ　90-12', 'SUV', 2700, 'RENTED', NOW(), NOW()),
(19, 'Toyota', 'Highlander', '2021', '長崎　あ　12-34', 'SUV', 2800, 'UNDER_MAINTENANCE', NOW(), NOW()),
(20, 'Chevrolet', 'Tahoe', '2022', '鹿児島　あ　34-56', 'SUV', 2900, 'AVAILABLE', NOW(), NOW()),
(21, 'Dodge', 'Charger', '2023', '秋田　あ　78-90', 'SEDAN', 3000, 'RENTED', NOW(), NOW()),
(22, 'Hyundai', 'Tucson', '2020', '山口　あ　12-34', 'SUV', 3100, 'UNDER_MAINTENANCE', NOW(), NOW()),
(23, 'Kia', 'Sportage', '2021', '青森　あ　34-12', 'SUV', 3200, 'AVAILABLE', NOW(), NOW()),
(24, 'Mazda', 'CX-30', '2022', '富山　あ　56-78', 'SUV', 3300, 'RENTED', NOW(), NOW()),
(25, 'Subaru', 'Forester', '2023', '石川　あ　90-12', 'SUV', 3400, 'UNDER_MAINTENANCE', NOW(), NOW()),
(26, 'Volkswagen', 'Tiguan', '2020', '岐阜　あ　12-34', 'SUV', 3500, 'AVAILABLE', NOW(), NOW()),
(27, 'Ford', 'Explorer', '2021', '滋賀　あ　34-56', 'SUV', 3600, 'RENTED', NOW(), NOW()),
(28, 'Nissan', 'Kicks', '2022', '沖縄　あ　78-90', 'LIGHTVEHICLE', 3700, 'UNDER_MAINTENANCE', NOW(), NOW()),
(29, 'Honda', 'CR-V', '2023', '群馬　あ　12-34', 'SUV', 3800, 'AVAILABLE', NOW(), NOW()),
(30, 'Toyota', 'Yaris', '2020', '茨城　あ　34-12', 'COMPACT', 3900, 'RENTED', NOW(), NOW());
