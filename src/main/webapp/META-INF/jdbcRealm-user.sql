CREATE TABLE UserPass (
	Username varchar(15) NOT NULL PRIMARY KEY,
	Password varchar(15) NOT NULL
);

INSERT INTO UserPass VALUES ('andea', 'password'),('joel', 'password'),('anne', 'password');

CREATE TABLE UserRole (
	Username varchar(15) NOT NULL,
	RoleName varchar(15) NOT NULL,
	
	PRIMARY KEY (Username, Rolename)
);

INSERT INTO UserRole VALUES ('andea', 'service'),('andrea', 'programmer'),('joel', 'programmer');
