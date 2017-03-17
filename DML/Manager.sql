--- Add, Edit and Delete movies  [ TESTED AND WORKING ]
INSERT INTO Movie(Id, Name, Type, Rating, DistrFee, NumCpy )
            VALUES 
                 (1, "The GodFather", "Drama", 5, 10000, 3);

UPDATE Movie
SET    Rating = 3
WHERE  Id = 1;

DELETE FROM Movie
WHERE Id = 1;

--- Add, Edit and Delete information for an employee
INSERT INTO Employee(Id, SSN, StartDate, HourlyRate)
                VALUES
                    (2, 123-45-6789, 2015-11-05, 60);

UPDATE Employee
SET    HourlyRate = 65
WHERE  Id = 2

DELETE FROM Employee
WHERE       Id = 2

--- Obtain a sales report (i.e. the overall income from all active subscriptions) for a particular month

--- Produce a comprehensive listing of all movies
SELECT *
FROM   Movie

--- Produce a list of movie rentals by movie name, movie type or customer name

SELECT  R.*
FROM    Rental R, Movie M
WHERE   R.MovieId = M.Id AND M.Name = “The GodFather”

SELECT  R.*
FROM    Rental R, Movie M
WHERE   R.MovieId = M.Id AND M.Type = “Drama”

SELECT  R.*
FROM    Rental R, Customer C
WHERE   R.AccountId = C.Id AND C.LastName = “Smith” AND       
        C.FirstName = “John”


--- Determine which customer representative oversaw the most transactions (rentals)

CREATE VIEW CustomerRepTransCount(Id, TransCount) AS
	SELECT E.ID , COUNT(E.Id) 
    FROM   Rental R, Employee E
    WHERE  E.Id = R.EmpID
	
SELECT Id, Max(TransCount)
FROM   CustomerRepTransCount

--- Produce a list of most active customers

SELECT  C.ID
FROM    Customer C , Rental R
WHERE  

--- Produce a list of most actively rented movies

