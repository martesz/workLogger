DELETE FROM WORKINGHOUR WHERE 1=1;
DELETE FROM ISSUE WHERE 1=1;
DELETE FROM PROJECT WHERE 1=1;
DELETE FROM USERS WHERE 1=1;


INSERT INTO USERS(GOOGLEID, NAME, LEVEL)
VALUES('101812010762749781224', 'Imre Torma', 'ADMIN');


INSERT INTO PROJECT(ID, NAME, DESCRIPTION)
VALUES(1, 'project1', 'descr1');
INSERT INTO PROJECT(ID, NAME, DESCRIPTION)
VALUES(2, 'project2', 'descr2');
INSERT INTO PROJECT(ID, NAME, DESCRIPTION)
VALUES(3, 'project3', 'descr3');


INSERT INTO ISSUE(ID, NAME, DESCRIPTION, PROJECT_ID)
VALUES(1, 'issue1', 'descr1', 1);
INSERT INTO ISSUE(ID, NAME, DESCRIPTION, PROJECT_ID)
VALUES(2, 'issue2', 'descr2', 1);
INSERT INTO ISSUE(ID, NAME, DESCRIPTION, PROJECT_ID)
VALUES(3, 'issue3', 'descr3', 2);
INSERT INTO ISSUE(ID, NAME, DESCRIPTION, PROJECT_ID)
VALUES(4, 'issue4', 'descr4', 3);


INSERT INTO WORKINGHOUR(ID, STARTING, DURATION, USER_GOOGLEID, ISSUE_ID)
VALUES(1, Date('2017-01-01'), 1, '101812010762749781224', 1);
INSERT INTO WORKINGHOUR(ID, STARTING, DURATION, USER_GOOGLEID, ISSUE_ID)
VALUES(2, Date('2017-02-02'), 2, '101812010762749781224', 2);
INSERT INTO WORKINGHOUR(ID, STARTING, DURATION, USER_GOOGLEID, ISSUE_ID)
VALUES(3, Date('2017-03-03'), 3, '101812010762749781224', 3);
INSERT INTO WORKINGHOUR(ID, STARTING, DURATION, USER_GOOGLEID, ISSUE_ID)
VALUES(4, Date('2017-04-04'), 4, '101812010762749781224', 4);
INSERT INTO WORKINGHOUR(ID, STARTING, DURATION, USER_GOOGLEID, ISSUE_ID)
VALUES(5, Date('2017-05-05'), 5, '101812010762749781224', 1);