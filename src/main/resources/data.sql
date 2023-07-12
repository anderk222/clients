#Validate main address

CREATE OR REPLACE FUNCTION validate_new_address()
RETURNS TRIGGER AS '
BEGIN
    IF NEW.main = TRUE AND NEW.user_id IN (SELECT user_id from customer_addresses where main=true ) THEN
        RAISE EXCEPTION ''El usuario ya tiene una direccion matriz'';
    END IF;
    RETURN NEW;
END;
' LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION validate_update_address()
RETURNS TRIGGER AS '
BEGIN
    IF NEW.main = TRUE AND 0 < (SELECT count(*) from customer_addresses where main=true and id <> NEW.id ) THEN
        RAISE EXCEPTION ''El usuario ya tiene una direccion matriz'';
    END IF;
    RETURN NEW;
END;
' LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER validate_new_trigger
BEFORE INSERT ON customer_addresses
FOR EACH ROW
EXECUTE FUNCTION validate_new_address();

CREATE OR REPLACE TRIGGER validate_update_trigger
BEFORE UPDATE ON customer_addresses
FOR EACH ROW
EXECUTE FUNCTION validate_update_address();