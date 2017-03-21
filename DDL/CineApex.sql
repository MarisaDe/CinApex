CREATE TABLE Location (
	ZipCode INTEGER,
	City    CHAR(20) NOT NULL,
	State   CHAR(20) NOT NULL,
	
    PRIMARY KEY (ZipCode) );  

CREATE TABLE Person (
	SSN       CHAR(15),
	LastName  CHAR(20) NOT NULL,
	FirstName CHAR(20) NOT NULL,
	Address   CHAR(20),
	ZipCode   INTEGER,
	Telephone CHAR(20),
	
    PRIMARY KEY (SSN),
	FOREIGN KEY (ZipCode) REFERENCES Location (ZipCode)
		ON DELETE NO ACTION
		ON UPDATE CASCADE );


CREATE TABLE Employee (
	Id         CHAR(15),
	SSN        CHAR(15),
	StartDate  DATE,
	HourlyRate INTEGER,
	
    PRIMARY KEY (ID),
    FOREIGN KEY (SSN) REFERENCES Person (SSN)
		ON DELETE NO ACTION
		ON UPDATE CASCADE );

CREATE TABLE Customer (
	Id               CHAR(15),
	Email            CHAR(32),
	Rating           INTEGER,
	CreditCardNumber CHAR(20),
	
    PRIMARY KEY (Id),
	FOREIGN KEY (Id) REFERENCES Person (SSN)
		ON DELETE NO ACTION
		ON UPDATE CASCADE );


CREATE TABLE Account (
	Id         CHAR(15),
	DateOpened DATE,
	Type       CHAR(30),
	CustomerId CHAR(15),

    PRIMARY KEY (Id),
	FOREIGN KEY (CustomerId) REFERENCES Customer (Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE );
		
CREATE TABLE MovieOrder (
	Id         INTEGER,
	AccountId  CHAR(15),
	MovieId    INTEGER,
	DateAndTime   DATETIME,
	ReturnDate DATE,
	
    PRIMARY KEY (Id) ,
	
	FOREIGN KEY(MovieId) REFERENCES Movie(Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	FOREIGN KEY(AccountId) REFERENCES Account(Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE);

CREATE TABLE Movie (
	Id        INTEGER,
	Name      CHAR(20) NOT NULL,
	Type      CHAR(20) NOT NULL,
	Rating    INTEGER,
	DistrFee  INTEGER,
	NumCopies INTEGER,
	
    PRIMARY KEY (Id) );

CREATE TABLE Actor (
	Id     INTEGER,
	Name   CHAR(20) NOT NULL,
	Age    INTEGER NOT NULL,
	Sex    CHAR(1) NOT NULL,
	Rating INTEGER,
	
    PRIMARY KEY (Id) );

CREATE TABLE Rental (
	AccountId CHAR(15),
	CustRepId CHAR(15),
	OrderId   INTEGER,
	MovieId   INTEGER,
	
    PRIMARY KEY (AccountId, CustRepId, OrderId, MovieId),
	FOREIGN KEY (AccountId) REFERENCES Account (Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	FOREIGN KEY (CustRepId) REFERENCES Employee (Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	FOREIGN KEY (OrderId) REFERENCES MovieOrder (Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
	FOREIGN KEY (MovieId) REFERENCES Movie (Id)
		ON DELETE NO ACTION
		ON UPDATE CASCADE );

CREATE TABLE MovieQ(
    AccountId CHAR(15),
    MovieId   INTEGER,
    
    PRIMARY KEY (AccountId, MovieId),
    FOREIGN KEY (AccountId) REFERENCES Account(Id)
            ON DELETE NO ACTION
            ON UPDATE CASCADE,
    FOREIGN KEY (MovieId) REFERENCES Movie(Id)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
);
    

CREATE TABLE AppearedIn(
    ActorId INTEGER,
    MovieId INTEGER,
    
    PRIMARY KEY (ActorId, MovieId),
    FOREIGN KEY (ActorId) REFERENCES Actor(Id)
            ON DELETE NO ACTION
            ON UPDATE CASCADE,
    FOREIGN KEY (MovieId) REFERENCES Movie(Id)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
    
);

CREATE TABLE UserRatings(
    CustomerId CHAR(15),
    MovieId INTEGER,
    Rating INTEGER,
    
    PRIMARY KEY (CustomerId, MovieId),
    FOREIGN KEY (CustomerId) REFERENCES Rental(AccountId)
            ON DELETE NO ACTION
            ON UPDATE CASCADE,
    FOREIGN KEY (MovieId) REFERENCES Rental(MovieId)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
);