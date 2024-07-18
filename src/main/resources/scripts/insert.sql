TRUNCATE TABLE INSTITUTION cascade;
TRUNCATE TABLE STUDENT cascade;
TRUNCATE TABLE TEACHER cascade;
TRUNCATE TABLE TEACHER_INSTITUTIONS cascade;

INSERT INTO INSTITUTION(id, email, location, name, rc_number, status) VALUES
 (200, 'test@email.com', '12 Ejire street', 'test', '2345689', 'ACTIVE');

INSERT INTO INSTITUTION(id, email, location, name, rc_number, status) VALUES
 (202, 'test@email.com', '12 Ejire street', 'test', '2345689', 'ACTIVE');

INSERT INTO TEACHER(id, email, name, password) VALUES
 (203, 'ojot630@gmail.com', 'tobi', 'olawale123');

INSERT INTO TEACHER(id, email, name, password) VALUES
 (205, 'oluwatobi484@gmail.com', 'tobi', 'password123');

INSERT INTO TEACHER_INSTITUTIONS(institutions_id, teacher_id) VALUES
 (200, 203);

INSERT INTO TEACHER_INSTITUTIONS(institutions_id, teacher_id) VALUES
 (200, 205);

INSERT INTO STUDENT(id, email, name, password)VALUES
  (205, 'ooluwatobi895@gmail.com', 'oluwatobi', 'password');

INSERT INTO STUDENT(id, email, name, password, institution_id)VALUES
  (207, 'ooluwatobi825@gmail.com', 'oluwatobi', 'password', 200);

INSERT INTO STUDENT(id, email, name, password, institution_id)VALUES
  (206, 'ooluwatobi885@gmail.com', 'oluwatobi', 'password', 200);

