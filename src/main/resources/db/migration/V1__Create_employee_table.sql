CREATE TABLE Employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    salary DECIMAL(10, 2),
    join_date DATE,
    CONSTRAINT email_unique UNIQUE (email)
);