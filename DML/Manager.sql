--- Add, Edit and Delete movies  [ TESTED AND WORKING ]
INSERT INTO Movie(Id, Name, Type, Rating, DistrFee, NumCopies )
            VALUES 
                 (5, "The Departed", "Drama", 5, 5000, 3);

UPDATE Movie
SET    Rating = 3
WHERE  Id = 1;

DELETE FROM Movie
WHERE Id = 1;

----- OTHER DATA FOR Movie

-- 2	Shawshank Redemption	Drama	1,000	2	4
INSERT INTO Movie(Id, Name, Type, Rating, DistrFee, NumCopies )
            VALUES 
                 (2, "Shawshank Redemption", "Drama", 4, 1000, 2);

-- 3	Borat	Comedy	500	1	3
INSERT INTO Movie(Id, Name, Type, Rating, DistrFee, NumCopies )
            VALUES 
                 (3, "Borat", "Comedy", 3, 500, 1);
-----

--- Add, Edit and Delete information for an employee [TESTED AND WORKING]

INSERT INTO Location(ZipCode, City, State)
            VALUES
            (11790, "Stony Brook", "NY");

INSERT INTO Person(SSN, LastName, FirstName, Address, ZipCode, Telephone)
                VALUES 
                    ("123-45-6789", "Smith", "David", "123 College road", "11790", "516-2152-345");

INSERT INTO Employee(Id, SSN, StartDate, HourlyRate)
                VALUES
                    (1, "123-45-6789", 20151105, 60);

UPDATE Employee
SET    HourlyRate = 50
WHERE  Id = 1;

-- DELETE EMPLOYEE

UPDATE Rental
SET    CustRepId = "-"
WHERE  CustRepId = 1;

DELETE FROM Employee
WHERE       Id = "-";

----- OTHER EMPLOYEE

-- 789-12-3456	Warren	David	456 Sunken Street	Stony Brook	NY	11794	631-632-9987	2-2-06	$50

INSERT INTO Location(ZipCode, City, State)
            VALUES
            (11794, "Stony Brook", "NY");

INSERT INTO Person(SSN, LastName, FirstName, Address, ZipCode, Telephone)
                VALUES 
                    ("789-12-3456", "Warren", "David", "456 Sunken Street", "11794", "631-632-9987");

INSERT INTO Employee(Id, SSN, StartDate, HourlyRate)
                VALUES
                    (2, "789-12-3456", 20060202, 50);
-----

--- Obtain a sales report (i.e. the overall income from all active subscriptions) for a particular month
--- [ WORKING ]

CREATE VIEW monthlyReport(Id, DistrFee) AS
    SELECT  M.Id, M.DistrFee
    FROM    Movie M, MovieOrder MO
    WHERE   M.Id = MO.Id AND
            MONTH(MO.DateAndTime) = 11;        --- NOV

SELECT SUM(DistrFee) as totalSales
FROM   monthlyReport;


--- Produce a comprehensive listing of all movies [ TESTED AND WORKING ]
SELECT *
FROM   Movie;

--- Produce a list of movie rentals by movie name, movie type or customer name [WORKING]

SELECT  *
FROM    Rental R, Movie M
WHERE   R.MovieId = M.Id AND M.Name = "The GodFather";

SELECT  *
FROM    Rental R, Movie M
WHERE   R.MovieId = M.Id AND M.Type = "Drama";

SELECT  *
FROM    Rental R, Customer C, Person P
WHERE   R.AccountId = C.Id AND P.SSN = C.Id AND P.LastName = "Yang" AND       
        P.FirstName = "Shang";


--- Determine which customer representative oversaw the most transactions (rentals)
--- [ TESTED AND WORKING ]

SELECT   CustRepId, P.FirstName, P.LastName, COUNT(CustRepId) totalCount
FROM     Rental R, Employee E, Person P
WHERE    E.Id  = R.CustRepId AND
         P.SSN = E.SSN
GROUP BY CustRepId 
HAVING   COUNT(CustRepId) =
(
  SELECT   COUNT(CustRepId) totalCount
  FROM     Rental R, Employee E 
  WHERE    E.Id = R.CustRepId
  GROUP BY CustRepId 
  ORDER BY totalCount DESC
  LIMIT 1  
);

--- Produce a list of most active customers
--- [ WORKING AND TESTED ]

SELECT   R.AccountId, P.FirstName, P.LastName, COUNT(AccountId) totalCount
FROM     Rental R, Person P, Account A
WHERE    A.Id = R.AccountId AND
         P.SSN = A.CustomerId
GROUP BY AccountId
HAVING   totalCount >= 2;    -- If you have 2 or more rental you're an active user

--- Produce a list of most actively rented movies
--- [ WORKING AND TESTED ]
]
SELECT   M.Name, COUNT(M.Id) totalCount
FROM     Rental R, Movie M
WHERE    M.Id = R.MovieId
GROUP BY MovieId
HAVING   totalCount >= 2;    -- If a movie is rented 2 or more time then its actively rented
