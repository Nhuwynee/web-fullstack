-- Tạo database

-- Bật extension để dùng uuid_generate_v4()
CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

-- Tạo bảng users
CREATE TABLE users
(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name    VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone   VARCHAR(20) UNIQUE
);
