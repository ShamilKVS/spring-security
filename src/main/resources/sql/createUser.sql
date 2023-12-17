-- init.sql
CREATE TABLE IF NOT EXISTS example_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO example_table (name) VALUES ('Example Data 1');
INSERT INTO example_table (name) VALUES ('Example Data 2');
