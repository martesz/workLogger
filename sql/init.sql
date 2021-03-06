DELETE FROM WORKINGHOUR WHERE 1=1;
DELETE FROM REPORT WHERE 1=1;
DELETE FROM ISSUE WHERE 1=1;
DELETE FROM PROJECT WHERE 1=1;
DELETE FROM USERS WHERE 1=1;

INSERT INTO USERS(GOOGLEID, NAME, LEVEL)
VALUES('101812010762749781224', 'Imre Torma', 'PROJECT_LEADER');
INSERT INTO USERS(GOOGLEID, NAME, LEVEL)
VALUES('101498757619441151086', 'Imre Torma', 'ADMIN');


INSERT INTO PROJECT(ID, NAME, DESCRIPTION)
VALUES(1, 'Ultimate search', 'Make better search algorithm.');
INSERT INTO PROJECT(ID, NAME, DESCRIPTION)
VALUES(2, 'Project Alien', 'Trying out innovative functions.');
INSERT INTO PROJECT(ID, NAME, DESCRIPTION)
VALUES(3, 'C.R.U.D', 'To implement correct CRUD functions to the software.');


INSERT INTO ISSUE(ID, NAME, DESCRIPTION, PROJECT_ID)
VALUES(1, 'Reduce nested cycles', 'Reducing nested FOR cycles.', 1);
INSERT INTO ISSUE(ID, NAME, DESCRIPTION, PROJECT_ID)
VALUES(2, 'Newest Android API', 'To find better solutions.', 2);
INSERT INTO ISSUE(ID, NAME, DESCRIPTION, PROJECT_ID)
VALUES(3, 'Screen process', 'To identify objects on screens.', 2);
INSERT INTO ISSUE(ID, NAME, DESCRIPTION, PROJECT_ID)
VALUES(4, 'Safer read implementation', 'To avoid some known bugs.', 3);