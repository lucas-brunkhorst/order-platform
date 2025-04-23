CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    public_id UUID NOT NULL UNIQUE,
    customer_id BIGINT,
    status VARCHAR(50),
    created_at TIMESTAMP
);

CREATE TABLE order_items (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT,
    quantity INTEGER,
    order_id BIGINT REFERENCES orders(id)
);
