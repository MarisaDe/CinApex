 #Manager Level Transactions
#1. Add, Edit and Delete movies

INSERT INTO Movie
VALUES(4, 'Swept Away', 'Foreign', 5, 5000, 2);

UPDATE Movie
SET Rating = 2
WHERE Id = 1

UPDATE Movie
SET NumCopies = 10
WHERE Id =2

DELETE FROM Movie
WHERE Id =1

#2. Add, Edit AND Delete information for an employee

INSERT INTO Person
VALUES ('222-22-2222', 'Kang', 'Sangwook', '700 Health Science Drive', 11790, '631-413-5555')

INSERT INTO Employee
VALUES (7, '999-99-9999', '2009-10-17', 100)

UPDATE Person
SET Telephone = '631-413-6666'
WHERE SSN = '123-45-6789'

UPDATE Employee
SET HourlyRate = 50
WHERE Id = 5

DELETE FROM Employee
WHERE SSN='123-45-6789'

#3. Obtain a sales report (i.e. the overall income from all active subscriptions) for a particular month

CREATE TABLE Cost (
AcctType     CHAR(50),
MonthlyFee   INT,
PRIMARY KEY(AcctType)  )	

INSERT INTO Cost			
VALUES('Limited', 10)             

INSERT INTO Cost
VALUES('Unlimited-1', 15)

INSERT INTO Cost
VALUES('Unlimited-3', 25)

INSERT INTO Cost			 
VALUES('Unlimited-2', 20)		

SELECT SUM(C.MonthlyFee)
FROM Account A, Cost C
WHERE A.DateOpened >'2004-11-1' AND A.AccType = C.AcctType

#4. Produce a comprehensive listing of all movies

SELECT Id, Name, Type, Rating
FROM Movie

#5. Produce a list of movie rentals by movie name, movie type or
   customer name

CREATE VIEW Name(CustId, AcctId, FirstName, LastName, Rating)
AS
SELECT C.Id, A.Id, P.FirstName, P.LastName, C.Rating
FROM Customer C, Account A, Person P
WHERE C.Id = A.Id AND C.Id = P.SSN

SELECT R.OrderId, M.Id, M.Type, N.CustId
FROM Movie M, Rental R, Name N
WHERE N.AcctId = R.AccountId AND M.Id = R.MovieId AND M.Name = 'The Godfather'

SELECT R.OrderId, M.Id, M.Type, N.CustId
FROM Movie M, Rental R, Name N
WHERE N.AcctId = R.AccountId AND M.Id = R.MovieId AND M.Type = 'Drama'

SELECT R.OrderId, M.Id, M.Type, N.CustId
FROM Movie M, Rental R, Name N
WHERE N.AcctId = R.AccountId AND M.Id = R.MovieId AND N.LastName = 'Smith'

#6. Determine which customer representative oversaw the most transactions (rentals)

CREATE VIEW CountTrans(CustRepId, NumTrans)
AS
SELECT R.CustRepId, COUNT(*)
FROM RENTAL R
GROUP BY R.CustRepId

SELECT S.CustRepId
FROM CountTrans C 
WHERE C.NumTrans >= (SELECT MAX(D.NumTrans) FROM CountTrans D)

#7. Produce a list of most active customers

CREATE VIEW CountOrders (CustId, NumOrders)
AS
SELECT N.CustID, COUNT(*)
FROM Rental R, Name N
WHERE N.AcctId=R.AccountId
GROUP BY N.CustId

SELECT N.CustId, N.FirstName, N.LastName, N.Rating, C.NumOrders
FROM CountOrders C, Name N
WHERE N.CustId = C.CustId AND C.NumOrders >= (SELECT MAX(D.NumOrders) FROM CountOrders D)

#8. Produce a list of most actively rented movies

CREATE VIEW OrderList(MovieId, NumOrders)
AS
SELECT R.MovieId, COUNT(R.MovieId) 
FROM Rental R
GROUP BY R.MovieId

SELECT M.ID, M.Name, M.Rating, O.NumOrders
FROM OrderList O, Movie M
WHERE O.MovieId = M.ID AND O.NumOrders >= (SELECT MAX(R.NumOrders) FROM OrderList R)

Customer Representative Level Transactions

#1. Record an order

INSERT INTO Order (Id, DateTime, ReturnDate) VALUES (3, '2009-11-12 09:30:00',NULL)

INSERT INTO Rental (AccountId, CustRepId, OrderId, MovieId) VALUES (1, 2, 3, 3);

#2. Add, Edit and Delete information for a customer

INSERT INTO Person
VALUES ('777-77-7777', 'Nihan', 'Hiden', '700 Health Science Drive', 11790, '631-413-7777')

INSERT INTO Customer
VALUES ('777-77-7777', 'hiden@aol.com', 1, 373411111111111)

UPDATE Person
SET Telephone = '631-413-6666'
WHERE SSN = '777-77-7777'

UPDATE Customer
SET Rating = 2
WHERE Id = '777-77-7777'

DELETE FROM Person
WHERE SSN = '777-77-7777'

DELETE FROM Customer
WHERE Id = '777-77-7777'

#3. Produce customer mailing lists

SELECT  P.SSN, P.FirstName, P.LastName, P.Address, C.Email
FROM    Customer C, Person P
WHERE   C.Id = P.SSN 

#4. Produce a list of movie suggestions for a given customer (using
#   the recommender system which uses information about the customer's past
#   orders and that of nearest neighbors)

CREATE VIEW PastOrder(CustId, MovieId, MovieType)
AS
SELECT N.CustI, R.MovieId, M.MovieType
FROM Name N, Rental R, Movie M
WHERE N.AcctId = R.AccountId AND R.MovieId = M.Id 

SELECT M.Id, M.Name, M.Type
FROM Movie M
WHERE M.Type IN (SELECT O.Type FROM PastOrder O
		WHERE O.CustId = '444444444') 
AND M.Id NOT IN (SELECT O.MovieId FROM PastOrder O
	     WHERE O.CustId = '444444444')

#Customer Level Transactions

#1. A customer's currently held movies

SELECT M.Name
FROM Name N, Movie M, Rental R, Order O 
WHERE N.AcctId = R.AccountId AND R.OrderId = O.Id AND M.Id = R.MovieId AND
O.ReturnDate = NULL AND N.CustId = '444444444'

#2. A customer's queue of movies it would like to see

SELECT M.Name
FROM Name N, MovieQ Q, Movie M
WHERE N.AcctId = Q.AccountId AND Q.MovieId = M.Id AND N.CustID = '444444444'

#3. A customer's account settings

SELECT A.Id, A.DateOpened, A.Type
FROM Account A
WHERE A.Customer = '444444444'

UPDATE Account SET Type='Unlimited'
WHERE Customer = '444444444'

#4. A history of all current and past orders a customer has placed

SELECT O.Id, R.MovieId, R.CustRepId, O.DateTime, O.ReturnDate
FROM Name N, Rental R, Order O
WHERE N.AcctId = R.AccountId AND R.Id = O.Id AND N.CustId = '444444444'

#5. Movies available of a particular type

CREATE VIEW handout(MovieId, NumOut)
AS 
SELECT R.MovieId, COUNT(*)
FROM Rental R, Order O
WHERE R.OrderId = O.Id AND O.ReturnDate =NULL
GROUP BY R.MovieId

SELECT M.Id, M.Name, M.Type
FROM Movie M, handout H
WHERE M.Id = H.MovieId AND M.NumCopies>H.NumOut AND M.Type ='Drama'

#6. Movies available with a particular keyword or set of keywords in
   the movie name

SELECT M.Id, M.Name, M.Type
FROM Movie M, handout H
WHERE M.Id = H.MovieId AND M.NumCopies>H.NumOut AND M.Name LIKE "%Harry%Potter%" 

#7. Movies available starring a particular actor or group of actors

SELECT M.Id, M.Name, M.Type
FROM Movie M, handout H, AppearedIn I, ACTOR A
WHERE M.Id = H.MovieID AND M.NumCopies>H.NumOut AND A.NAME = "Al Pacino" AND
I.ActorId = A.Id AND I.MovieId = M.Id 

#8. Best-Seller list of movies

SELECT M.Id, M.Name, M.Type, M.Rating, N.NumOrders
FROM MovieOrder N, Movie M
WHERE N.MovieId = M.Id
ORDER BY N.NumOrders DESC

#9. Personalized movie suggestion list

SELECT M.Id, M.Name, M.Type
FROM Movie M
WHERE M.Type IN (SELECT O.Type FROM PastOrder O
		WHERE O.CustId = '444-44-4444') 
AND M.Id NOT IN (SELECT O.MovieId FROM PastOrder O
	     WHERE O.CustId = '444-44-4444'




SELECT  R.AccountId, P.FirstName, P.LastName, R.CustRepId , R.OrderId ,R.MovieId, M.Name, M.Type, M.Rating, M.DistrFee, M.NumCopies
FROM    Rental R, Movie M, Person P, Account A 
WHERE   
	R.MovieId = M.Id AND
	M.Name LIKE "%The%" AND
	A.CustomerId = P.SSN AND
	R.AccountId = A.Id;