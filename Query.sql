-- manage_book_issue
DELIMITER $$

CREATE TRIGGER manage_book_issue
BEFORE INSERT ON issue_book_details
FOR EACH ROW
BEGIN
    -- Declare variables first
    DECLARE book_quantity INT;
    DECLARE duplicate_count INT;

    -- Check if book is available
    SELECT quantity INTO book_quantity FROM book_details WHERE book_id = NEW.book_id;

    IF book_quantity <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Book not available.';
    END IF;

    -- Check for duplicate issue
    SELECT COUNT(*) INTO duplicate_count
    FROM issue_book_details
    WHERE book_id = NEW.book_id AND student_id = NEW.student_id AND status = 'Pending';

    IF duplicate_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'This student has already issued this book.';
    END IF;

    -- Decrease book quantity
    UPDATE book_details
    SET quantity = quantity - 1
    WHERE book_id = NEW.book_id;
END$$

DELIMITER ;

-- check_book_details_before_insert
DELIMITER $$

CREATE TRIGGER check_book_details_before_insert
BEFORE INSERT ON book_details
FOR EACH ROW
BEGIN
    -- Check if the book with the same book_id already exists
    DECLARE book_count INT;
    SELECT COUNT(*) INTO book_count
    FROM book_details
    WHERE book_id = NEW.book_id;
    
    -- If a book with the same book_id exists, prevent the insertion and raise an error
    IF book_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Book ID already exists in the database.';
    END IF;
    
    -- Check if the quantity is greater than 0
    IF NEW.quantity <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Quantity must be greater than 0.';
    END IF;
    
    -- Check if the author name is at least 3 characters long
    IF LENGTH(NEW.author) < 3 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Author name must be at least 3 characters long.';
    END IF;
    
    -- Check if the book name is not empty
    IF NEW.book_name IS NULL OR TRIM(NEW.book_name) = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Book name cannot be empty.';
    END IF;
    
END$$

DELIMITER ;

