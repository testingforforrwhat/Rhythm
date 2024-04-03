
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
    music_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '音乐ID',
    title VARCHAR(255) COMMENT '歌曲名称',
    artist VARCHAR(255) COMMENT '歌手',
    album VARCHAR(255) COMMENT '专辑',
    category_id INT COMMENT '分类ID',
    music_file VARCHAR(255) COMMENT '音乐文件路径',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 创建收藏表
CREATE TABLE favorites (
    favorite_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '收藏ID',
    user_id INT COMMENT '用户ID',
    music_id INT COMMENT '音乐ID',
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
