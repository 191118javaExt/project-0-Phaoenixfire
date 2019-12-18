DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users(user_id SERIAL PRIMARY KEY,
user_name VARCHAR (50) NOT NULL,
user_password NUMERIC NOT NULL,
first_name VARCHAR (50),
last_name VARCHAR (50),
is_employee BOOLEAN,
is_admin BOOLEAN);

INSERT INTO users ("user_name","user_password","first_name","last_name","is_employee","is_admin")
VALUES('Test','1216985755','Tester','Mctesty',FALSE,FALSE);
INSERT INTO users ("user_name","user_password","first_name","last_name","is_employee","is_admin")
VALUES('temp1','1216985755','Bob','Aram',FALSE,FALSE);
Insert into users("user_name","user_password","first_name","last_name","is_employee","is_admin")
VALUES('Big Boss','1216985755', 'Solid', 'Snake', FALSE,FALSE);
INSERT INTO users("user_name","user_password", "first_name", "last_name", "is_employee", "is_admin")
VALUES ('Agent','1216985755', 'Sterling', 'Archer', TRUE, FALSE);
INSERT INTO users("user_name", "user_password","first_name", "last_name", "is_employee", "is_admin") 
VALUES ('BossLady', '1216985755', 'Mallory', 'Archer', TRUE, TRUE);

SELECT * from users u;

DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts(
account_id SERIAL PRIMARY KEY,
owner_id INTEGER NOT NULL REFERENCES project0.users (user_id),
account_balance NUMERIC (25,2),
account_type VARCHAR(50),
account_approved Boolean DEFAULT false);

SELECT * FROM accounts;

INSERT INTO accounts("owner_id", "account_balance", "account_type")
VALUES(2, 800, 'Checking');

INSERT INTO accounts ("owner_id", "account_balance", "account_type")
VALUES(4,10000,'Savings');

INSERT INTO accounts("owner_id", "account_balance", "account_type")
values (3,1000000,'Checking'),
(4,10000000000000, 'Savings');

INSERT INTO accounts("owner_id", "account_balance", "account_type")
VALUES(2, 800, 'Savings');


