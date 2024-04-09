

/*

 users 添加两个新字段 phone 和 salt

 添加10个测试数据

 */
ALTER TABLE rhythm.users
ADD COLUMN phone VARCHAR(20) COMMENT 'phone',
ADD COLUMN salt VARCHAR(50) COMMENT 'salt';


TRUNCATE TABLE rhythm.users;

INSERT INTO rhythm.users (username, password, email, avatar, created_at, updated_at, phone, salt) VALUES
('user1', 'password1', 'user1@example.com', 'avatar1.jpg', NOW(), NOW(), '1234567890', 'salt1'),
('user2', 'password2', 'user2@example.com', 'avatar2.jpg', NOW(), NOW(), '2345678901', 'salt2'),
('user3', 'password3', 'user3@example.com', 'avatar3.jpg', NOW(), NOW(), '3456789012', 'salt3'),
('user4', 'password4', 'user4@example.com', 'avatar4.jpg', NOW(), NOW(), '4567890123', 'salt4'),
('user5', 'password5', 'user5@example.com', 'avatar5.jpg', NOW(), NOW(), '5678901234', 'salt5'),
('user6', 'password6', 'user6@example.com', 'avatar6.jpg', NOW(), NOW(), '6789012345', 'salt6'),
('user7', 'password7', 'user7@example.com', 'avatar7.jpg', NOW(), NOW(), '7890123456', 'salt7'),
('user8', 'password8', 'user8@example.com', 'avatar8.jpg', NOW(), NOW(), '8901234567', 'salt8'),
('user9', 'password9', 'user9@example.com', 'avatar9.jpg', NOW(), NOW(), '9012345678', 'salt9'),
('user10', 'password10', 'user10@example.com', 'avatar10.jpg', NOW(), NOW(), '0123456789', 'salt10');
