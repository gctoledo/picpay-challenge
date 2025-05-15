CREATE TABLE users (
    id UUID PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(200),
    document VARCHAR(25) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    balance NUMERIC(19,2),
    user_type VARCHAR(50),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);
