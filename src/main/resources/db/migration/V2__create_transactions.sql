CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    sender_id UUID REFERENCES users(id) NOT NULL,
    receiver_id UUID REFERENCES users(id) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);
