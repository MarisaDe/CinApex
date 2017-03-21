#Record an order. -- Working --

#Order 1
INSERT INTO MovieOrder(Id, AccountId, MovieId, DateAndTime, ReturnDate)
VALUES(1, '1', 1, '2009-11-09 10:00', 20091114);

INSERT INTO rental(AccountId, CustRepId, OrderId, MovieId)
        VALUES
            ('1', '2', 1, 1);

#Order 2
INSERT INTO MovieOrder(Id, AccountId, MovieId, DateAndTime,ReturnDate)
VALUES(2, '2', 3, '2009-11-09 18:15',20201020);

INSERT INTO rental(AccountId, CustRepId, OrderId, MovieId)
        VALUES
            ('2', '2', 2, 3);

#Order 3
INSERT INTO MovieOrder(Id, AccountId, MovieId, DateAndTime)
VALUES(3, '1', 3, '2009-11-12 9:30',20170102);

INSERT INTO rental(AccountId, CustRepId, OrderId, MovieId)
        VALUES
            ('1', '1', 3, 3);

#Order 4
INSERT INTO MovieOrder(Id, AccountId, MovieId, DateAndTime)
VALUES(4, '2', 2, '2009-11-21 22:22',20120319);

INSERT INTO rental(AccountId, CustRepId, OrderId, MovieId)
        VALUES
            ('2', '2', 4, 2);

###############################################################################
#Add information for a customer -- Working --
#Customer 1
INSERT INTO Location(ZipCode, City, State)
VALUES(11790, 'Stony Brook', 'NY');

INSERT INTO Person(SSN,LastName,FirstName,Address,ZipCode,Telephone)
VALUES('111-11-1111', 'Yang', 'Shang', '123 Success Street', 11790, '516-632-8959');

INSERT INTO Customer(Id, Email, Rating, CreditCardNumber)
VALUES('111-11-1111', 'syang@cs.sunysb.edu', 1, '1234-5678-1234-5678');

#Customer 2
INSERT INTO Person(SSN,LastName,FirstName,Address,ZipCode,Telephone)
VALUES('222-22-2222', 'Du', 'Victor', '456 Fortune Road', 11790, '516-632-4360');

INSERT INTO Customer(Id, Email, Rating, CreditCardNumber)
VALUES('222-22-2222', 'vicdu@cs.sunysb.edu', 1, '5678-1234-5678-1234');

#Customer 3
INSERT INTO Location(ZipCode, City, State)
VALUES(93536, 'Los Angeles', 'CA');

INSERT INTO Person(SSN,LastName,FirstName,Address,ZipCode,Telephone)
VALUES('333-33-3333', 'Smith', 'John', '789 Peace Blvd.', 93536, '315-443-4321');

INSERT INTO Customer(Id, Email, Rating, CreditCardNumber)
VALUES('333-33-3333', 'jsmith@ic.sunysb.edu', 1, '2345-6789-2345-6789');

#Customer 4
INSERT INTO Location(ZipCode, City, State)
VALUES(11794, 'Stony Brook', 'NY');

INSERT INTO Person(SSN,LastName,FirstName,Address,ZipCode,Telephone)
VALUES('444-44-4444', 'Philip', 'Lewis', '135 Knowledge Lane', 11794, '516-666-8888');

INSERT INTO Customer(Id, Email, Rating, CreditCardNumber)
VALUES('444-44-4444', 'pml@cs.sunysb.edu', 1, '6789-2345-6789-2345');

################################################################################
#Edit information for a customer -- Working --
UPDATE Customer
SET    Rating = 3
WHERE  Id = '111-11-1111';

#Delete information about a customer -- Working --
DELETE FROM Customer
WHERE Id = '111-11-1111';


#Produce customer mailing lists -- Working -- 

SELECT C.email
FROM Customer C;

#Record an order. 

#Order 1
INSERT INTO MovieOrder(Id, AccountId, MovieId, DateAndTime, ReturnDate)
VALUES(1, '1', 1, '2009-11-09 10:00', 20091114);

#Order 2
INSERT INTO MovieOrder(Id, AccountId, MovieId, DateAndTime)
VALUES(2, '2', 3, '2009-11-09 18:15');

#Order 3
INSERT INTO MovieOrder(Id, AccountId, MovieId, DateAndTime)
VALUES(3, '1', 3, '2009-11-12 9:30');

#Order 4
INSERT INTO MovieOrder(Id, AccountId, MovieId, DateAndTime)
VALUES(4, '2', 2, '2009-11-21 22:22');

################################################################################
#Actor 1
INSERT INTO Actor(Id, Name, Age, Sex, Rating)
VALUES(1, 'Al Pacino', 63, 'M', 5);

#Actor 2
INSERT INTO Actor(Id, Name, Age, Sex, Rating)
VALUES(2, 'Tim Robbins', 53, 'M', 2);

#Produce a list of movie suggestions for a given customer (using the recommender system which uses information about the customer's past orders and that of nearest neighbors)
