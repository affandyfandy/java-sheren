-- Create view to show list products customer bought
CREATE VIEW customer_product_purchases AS
SELECT
	t1.id AS customer_id,
    t1.name AS customer_name,
    t2.id AS product_id,
    t2.name AS product_name,
    t3.quantity,
    t3.amount,
    t4.created_date
FROM
	customer t1
JOIN
	invoice t4 ON t1.id = t4.customer_id
JOIN
	invoice_detail t3 ON t3.invoice_id = t4.id
JOIN
	product t2 ON t2.id = t3.product_id;

-- Query to show the view
SELECT * FROM customer_product_purchases;

-- Create function to calculate revenue by cashier
DELIMITER $$

CREATE FUNCTION revenue_by_cashier(cashier_id_input INT) 
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE revenue DECIMAL(10,2);
    
    SELECT SUM(amount) 
    INTO revenue 
    FROM invoice
    WHERE cashier_id = cashier_id_input;
    
    RETURN revenue;
END $$

DELIMITER ;

-- Query to show revenue by cashier
SELECT revenue_by_cashier(1) AS revenue_by_cashier_1;
SELECT revenue_by_cashier(2) AS revenue_by_cashier_2;

-- Create table revenue_report
CREATE TABLE revenue_report (
    id INT AUTO_INCREMENT PRIMARY KEY,
    year INT,
    month INT,
    day INT,
    amount DECIMAL(10,2) NOT NULL
);

-- Create procedure to calculate daily revenue
DELIMITER $$

CREATE PROCEDURE calculate_daily_revenue(IN input_day DATE)
BEGIN
    DECLARE daily_revenue DECIMAL(10,2);
    
    SELECT SUM(amount) INTO daily_revenue
    FROM invoice
    WHERE created_date = input_day;
    
    INSERT INTO revenue_report (year, month, day, amount)
    VALUES (YEAR(input_day), MONTH(input_day), DAY(input_day), daily_revenue);
END $$

DELIMITER ;

-- Create procedure to calculate monthly revenue
DELIMITER $$

CREATE PROCEDURE calculate_monthly_revenue(IN input_year INT, IN input_month INT)
BEGIN
    DECLARE monthly_revenue DECIMAL(10,2);
    
    SELECT SUM(amount) INTO monthly_revenue
    FROM invoice
    WHERE YEAR(created_date) = input_year AND MONTH(created_date) = input_month;
    
    INSERT INTO revenue_report (year, month, day, amount)
    VALUES (input_year, input_month, 0, monthly_revenue);
END $$

DELIMITER ;

-- Create procedure to calculate annual revenue
DELIMITER $$

CREATE PROCEDURE calculate_annual_revenue(IN input_year INT)
BEGIN
    DECLARE annual_revenue DECIMAL(10,2);
    
    SELECT SUM(amount) INTO annual_revenue
    FROM invoice
    WHERE YEAR(created_date) = input_year;
    
    INSERT INTO revenue_report (year, month, day, amount)
    VALUES (input_year, 0, 0, annual_revenue);
END $$

DELIMITER ;

-- Execute the procedures
CALL calculate_daily_revenue('2024-06-01');
CALL calculate_daily_revenue('2024-06-02');
CALL calculate_monthly_revenue(2024, 6);
CALL calculate_annual_revenue(2024);