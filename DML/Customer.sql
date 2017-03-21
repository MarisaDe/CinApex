--- Add Account
INSERT INTO Account(Id, DateOpened, Type, CustomerId)
	Values(1, 10-1-06, umlimited-2, 444-44-4444);
INSERT INTO Account(Id, DateOpened, Type, CustomerId)
	Values(2, 10-15-06, limited, 222-22-2222);


--- 1.	A customer's currently held movies
SELECT MovieId
FROM Rental
WHERE AccountId =1 AND EXISTS
	SELECT ReturnDate
	FROM Order
	WHERE OrderId = Id AND ReturnDate > NOW()
---2.	A customer's queue of movies it would like to see
SELECT MovieId
FROM MovieQ
WHERE AccountId = 1
---3.	A customer's account settings
SELECT *
FROM Customer
WHERE Id = 1
---4.	A history of all current and past orders a customer has placed
SELECT Id 
FROM Order
WHERE DateTime <= NOW()
---5.	Movies available of a particular type
SELECT *
FROM Movies
WHERE Type=’Comedy’
---6.	Movies available with a particular keyword or set of keywords in the movie name
SELECT * 
FROM Movies
WHERE Name LIKE ‘Godfather’ 
	AND LIKE ‘Borat’
---7.	Movies available starring a particular actor or group of actors
SELECT MovieId 
FROM AppearedIn, Actor
WHERE ActorId=Id AND (
 Name LIKE ‘Al Pacino’ 
	OR Name LIKE ‘Tim Robbins’)

---8.	Best-Seller list of movies
---SELECT TOP 10 MovieId
---FROM Rental
---WHERE 
---9.	Personalized movie suggestion list

---10.	Rate the movies they have rented
---UPDATE Movie
---SET 
