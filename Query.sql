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
