TRUNCATE TABLE INSTITUTION cascade;


INSERT INTO INSTITUTION(id, email, location, name, rc_number, status) VALUES
 (200, 'test@email.com', '12 Ejire street', 'test', '2345689', 'ACTIVE');


INSERT INTO TEACHER(id, email, name, password) VALUES
 (203, 'ojot630@gmail.com', 'tobi', 'olawale123');

INSERT INTO TEACHER_INSTITUTIONS(institutions_id, teacher_id) VALUES
 (200, 203);