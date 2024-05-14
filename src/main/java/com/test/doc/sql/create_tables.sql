
/*

 # mysql -u your_username -p rhythm < create_tables.sql

 */


-- 删除已存在的表格
DROP TABLE IF EXISTS play_history;
DROP TABLE IF EXISTS favorites;
DROP TABLE IF EXISTS music;
DROP TABLE IF EXISTS music_categories;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS advertisements;

-- 创建用户表
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(255) COMMENT '用户名',
    password VARCHAR(255) COMMENT '密码',
    email VARCHAR(255) COMMENT '邮箱',
    avatar VARCHAR(255) COMMENT '头像',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 创建音乐分类表
CREATE TABLE music_categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '分类ID',
    category_name VARCHAR(255) COMMENT '分类名称',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 创建音乐表
CREATE TABLE music (
    `music_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '音乐ID',
    `title` VARCHAR(255) NOT NULL COMMENT '歌曲名称',
    `artist` VARCHAR(255) COMMENT '歌手',
    `album` VARCHAR(255) COMMENT '专辑',
    `category_id` INT COMMENT '分类ID',
    `music_file` VARCHAR(255) COMMENT '音乐文件路径',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `music_play_count` int DEFAULT NULL COMMENT '播放次数统计',
    `music_play_count_week` int DEFAULT NULL COMMENT '本周播放次数统计'
);

-- 创建收藏表
CREATE TABLE favorites (
    favorite_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '收藏ID',
    user_id INT NOT NULL COMMENT '用户ID',
    music_id INT NOT NULL COMMENT '音乐ID',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 创建广告表
CREATE TABLE advertisements (
    ad_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '广告ID',
    title VARCHAR(255) COMMENT '广告标题',
    content TEXT COMMENT '广告内容',
    start_date DATE COMMENT '开始日期',
    end_date DATE COMMENT '结束日期',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    platform VARCHAR(50) COMMENT '广告展示平台',
    target_audience VARCHAR(100) COMMENT '目标受众'
);

-- 创建历史播放表
CREATE TABLE play_history (
    history_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '播放历史ID',
    user_id INT COMMENT '用户ID，外键关联用户表',
    song_id INT COMMENT '歌曲ID，外键关联歌曲表',
    play_date DATETIME COMMENT '播放日期',
    platform VARCHAR(50) COMMENT '播放平台',
    duration_seconds INT COMMENT '播放时长（秒)'
);


-- 向 users 表格插入10个测试数据
INSERT INTO users (username, password, email, avatar) VALUES
                                                          ('user1', 'pass1', 'user1@example.com', 'avatar1.jpg'),
                                                          ('user2', 'pass2', 'user2@example.com', 'avatar2.jpg'),
                                                          ('user3', 'pass3', 'user3@example.com', 'avatar3.jpg'),
                                                          ('user4', 'pass4', 'user4@example.com', 'avatar4.jpg'),
                                                          ('user5', 'pass5', 'user5@example.com', 'avatar5.jpg'),
                                                          ('user6', 'pass6', 'user6@example.com', 'avatar6.jpg'),
                                                          ('user7', 'pass7', 'user7@example.com', 'avatar7.jpg'),
                                                          ('user8', 'pass8', 'user8@example.com', 'avatar8.jpg'),
                                                          ('user9', 'pass9', 'user9@example.com', 'avatar9.jpg'),
                                                          ('user10', 'pass10', 'user10@example.com', 'avatar10.jpg');

-- 向 music_categories 表格插入10个测试数据
INSERT INTO music_categories (category_name) VALUES
                                                 ('Pop'),
                                                 ('Rock'),
                                                 ('Hip Hop'),
                                                 ('Classical'),
                                                 ('Jazz'),
                                                 ('Electronic'),
                                                 ('R&B'),
                                                 ('Country'),
                                                 ('Metal'),
                                                 ('Blues');

-- 向 music 表格插入10个测试数据
INSERT INTO music (title, artist, album, category_id, music_file) VALUES
                                                                      ('Song1', 'Artist1', 'Album1', 1, 'song01.mp3'),
                                                                      ('Song2', 'Artist2', 'Album2', 2, 'song02.mp3'),
                                                                      ('Song3', 'Artist3', 'Album3', 1, 'song03.mp3'),
                                                                      ('Song4', 'Artist4', 'Album4', 3, 'song04.mp3'),
                                                                      ('Song5', 'Artist5', 'Album5', 2, 'song05.mp3'),
                                                                      ('Song6', 'Artist6', 'Album6', 4, 'song06.mp3'),
                                                                      ('Song7', 'Artist7', 'Album7', 3, 'song07.mp3'),
                                                                      ('Song8', 'Artist8', 'Album8', 5, 'song08.mp3'),
                                                                      ('Song9', 'Artist9', 'Album9', 4, 'song09.mp3'),
                                                                      ('Song10', 'Artist10', 'Album10', 5, 'song10.mp3');

-- 向 favorites 表格插入10个测试数据
INSERT INTO favorites (user_id, music_id) VALUES
                                              (1, 1),
                                              (2, 3),
                                              (3, 2),
                                              (4, 5),
                                              (5, 7),
                                              (6, 9),
                                              (7, 4),
                                              (8, 6),
                                              (9, 8),
                                              (10, 10);

-- 向 advertisements 表格插入10个测试数据
INSERT INTO advertisements (title, content, start_date, end_date, platform, target_audience) VALUES
                                                                                                 ('Ad1', 'Content for Ad1', '2024-04-01', '2024-04-30', 'Web', 'All users'),
                                                                                                 ('Ad2', 'Content for Ad2', '2024-04-15', '2024-05-15', 'Mobile', 'Targeted users'),
                                                                                                 ('Ad3', 'Content for Ad3', '2024-04-10', '2024-04-25', 'Web', 'Premium users'),
                                                                                                 ('Ad4', 'Content for Ad4', '2024-04-05', '2024-04-20', 'Mobile', 'New users'),
                                                                                                 ('Ad5', 'Content for Ad5', '2024-04-12', '2024-04-27', 'Web', 'Returning users'),
                                                                                                 ('Ad6', 'Content for Ad6', '2024-04-18', '2024-05-03', 'Mobile', 'VIP users'),
                                                                                                 ('Ad7', 'Content for Ad7', '2024-04-22', '2024-05-07', 'Web', 'All users'),
                                                                                                 ('Ad8', 'Content for Ad8', '2024-04-28', '2024-05-13', 'Mobile', 'Targeted users'),
                                                                                                 ('Ad9', 'Content for Ad9', '2024-04-14', '2024-04-29', 'Web', 'Premium users'),
                                                                                                 ('Ad10', 'Content for Ad10', '2024-04-08', '2024-04-23', 'Mobile', 'New users');

-- 向 play_history 表格插入10个测试数据
INSERT INTO play_history (user_id, song_id, play_date, platform, duration_seconds) VALUES
                                                                                       (1, 1, '2024-04-01 10:00:00', 'Web', 180),
                                                                                       (2, 2, '2024-04-02 15:30:00', 'Mobile', 240),
                                                                                       (3, 3, '2024-04-03 12:45:00', 'Web', 300),
                                                                                       (4, 4, '2024-04-04 08:20:00', 'Mobile', 210),
                                                                                       (5, 5, '2024-04-05 17:10:00', 'Web', 270),
                                                                                       (6, 6, '2024-04-06 14:55:00', 'Mobile', 320),
                                                                                       (7, 7, '2024-04-07 11:30:00', 'Web', 190),
                                                                                       (8, 8, '2024-04-08 09:40:00', 'Mobile', 260),
                                                                                       (9, 9, '2024-04-09 16:25:00', 'Web', 230),
                                                                                       (10, 10, '2024-04-10 13:15:00', 'Mobile', 280);
