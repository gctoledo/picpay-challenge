CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    amount NUMERIC(19,2),
    sender_id UUID REFERENCES users(id),
    receiver_id UUID REFERENCES users(id),
    date TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);
