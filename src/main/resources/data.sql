/* rolesテーブル */
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');

/* usersテーブル */
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, license_number, password, role_id, enabled) VALUES
(1, '管理ユーザー', 'カンリユーザー', '123-4567', '長野県伊那市美篶', '080-1234-5678', 'test@test.com', '999999999999','$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true),
(2, 'テストユーザー', 'テストユーザー', '123-4567', '長野県伊那市美篶', '080-1234-5678', 'testuser@test.com', '999999999999','$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(3, '山田太郎', 'ヤマダタロウ', '150-0001', '東京都渋谷区神宮前', '080-3333-4444', 'yamada@test.com', '777777777777', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(4, '佐藤花子', 'サトウハナコ', '060-0005', '北海道札幌市中央区北五条西', '080-5555-6666', 'sato@test.com', '666666666666', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(5, '鈴木一郎', 'スズキイチロウ', '530-0001', '大阪府大阪市北区梅田', '080-7777-8888', 'suzuki@test.com', '555555555555', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(6, '高橋二郎', 'タカハシジロウ', '460-0003', '愛知県名古屋市中区錦', '080-9999-0000', 'takahashi@test.com', '444444444444', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(7, '伊藤美咲', 'イトウミサキ', '980-0001', '宮城県仙台市青葉区中央', '080-1112-3333', 'ito@test.com', '333333333333', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(8, '加藤大輔', 'カトウダイスケ', '810-0001', '福岡県福岡市中央区天神', '080-4444-5555', 'kato@test.com', '222222222222', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(9, '中村陽子', 'ナカムラヨウコ', '730-0011', '広島県広島市中区基町', '080-6666-7777', 'nakamura@test.com', '111111111111', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(10, '小林健', 'コバヤシケン', '980-0811', '宮城県仙台市青葉区一番町', '080-8888-9999', 'kobayashi@test.com', '123456789012', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(11, '森田光', 'モリタヒカル', '064-0809', '北海道札幌市中央区南九条西', '080-1113-2222', 'morita@test.com', '234567890123', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(12, '長谷川真由美', 'ハセガワマユミ', '600-8001', '京都府京都市下京区四条通', '080-3334-4444', 'hasegawa@test.com', '345678901234', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(13, '石井拓', 'イシイタク', '650-0021', '兵庫県神戸市中央区三宮町', '080-5555-6667', 'ishii@test.com', '456789012345', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(14, '渡辺麻衣', 'ワタナベマイ', '330-0845', '埼玉県さいたま市大宮区大門町', '080-7777-8889', 'watanabe@test.com', '567890123456', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(15, '清水浩', 'シミズヒロシ', '812-0012', '福岡県福岡市博多区博多駅中央街', '080-9999-0001', 'shimizu@test.com', '678901234567', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(16, '木村陽子', 'キムラヨウコ', '420-0858', '静岡県静岡市葵区追手町', '080-1114-3333', 'kimura@test.com', '789012345678', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(17, '松本学', 'マツモトマナブ', '980-0014', '宮城県仙台市青葉区花京院', '080-4445-5555', 'matsumoto@test.com', '890123456789', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(18, '原田真', 'ハラダマコト', '060-0042', '北海道札幌市中央区大通西', '080-6667-7777', 'harada@test.com', '901234567890', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(19, '村田直美', 'ムラタナオミ', '310-0021', '茨城県水戸市南町', '080-8889-9999', 'murata@test.com', '012345678901', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true),
(20, '吉田大輔', 'ヨシダダイスケ', '790-0012', '愛媛県松山市湊町', '080-1234-5679', 'yoshida@test.com', '109876543210', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true);

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
