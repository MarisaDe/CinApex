
--- Add Account
INSERT INTO Account(Id, DateOpened, Type, CustomerId)
	Values(1, 10-1-06, umlimited-2, 444-44-4444);
INSERT INTO Account(Id, DateOpened, Type, CustomerId)
	Values(2, 10-15-06, limited, 222-22-2222);

INSERT INTO MovieQ(AccountId, MovieId)
	Values(1,1);
INSERT INTO MovieQ(AccountId, MovieId)
	Values(1,3);
INSERT INTO MovieQ(AccountId, MovieId)
	Values(2,2);

INSERT INTO AppearedIn(ActorId, MovieId)
	Values(2,2);
INSERT INTO AppearedIn(ActorId, MovieId)
	Values(1,2);
INSERT INTO AppearedIn(ActorId, MovieId)
	Values(1,1);
--- 1.	A customer's currently held movies
SELECT MovieId
FROM Rental
WHERE AccountId =2 AND EXISTS(
	SELECT ReturnDate
	FROM MovieOrder
	WHERE OrderId = Id AND ReturnDate > NOW());
---2.	A customer's queue of movies it would like to see [WORKS]
SELECT MovieId, Name
FROM MovieQ, Movie
WHERE AccountId = 1 AND MovieId=Id;
---3.	A customer's account settings 	[Not sure what settings are, but it does show the stats]
SELECT *
FROM Customer
WHERE Id = '111-11-1111';
---4.	A history of all current and past orders a customer has placed [WORKS]
SELECT *
FROM MovieOrder 
WHERE DateAndTime <= NOW() AND AccountId =1
ORDER BY DateAndTime;
---5.	Movies available of a particular type [WORKS]
SELECT *
FROM Movie
WHERE Type='Comedy';
---6.	Movies available with a particular keyword or set of keywords in the movie name [WORKS]
SELECT * 
FROM Movie
WHERE Name LIKE '%Godfather%' 
	OR Name LIKE '%Borat%';
---7.	Movies available starring a particular actor or group of actors [WORKS]
SELECT B.MovieId, M.Name, A.Name 
FROM AppearedIn B, Actor A, Movie M
WHERE B.ActorId=A.Id AND B.MovieId=M.Id AND (
 	A.Name LIKE '%Al Pacino%' 
	OR A.Name LIKE '%Will Smith%');

---8.	Best-Seller list of movies [WORKS]
SELECT Name, Rating
FROM Movie
ORDER BY Rating DESC
LIMIT 2;

---9.	Personalized movie suggestion list
SELECT m1.Name
FROM Movie m1
WHERE m1.Type = (
	SELECT m.Type
	From Movie m, Rental r
	WHERE m.Id=r.MovieId AND r.AccountId=1)


	select * 
	from movie
	where id not in(
		select MovieId
		from rental 
		where accountid=1)
		and type in(
			select type
			from rental, movie
			where accountid =1 and id=movieid
		
	);
---10.	Rate the movies they have rented
INSERT INTO UserRatings(CustomerId, MovieId, Rating)
	Values('111-11-1111', 3, 1);

UPDATE Movie m 
INNER JOIN UserRatings u
ON m.Id = u.MovieId
SET m.Rating=AVG(u.Rating)
WHERE Id=3;