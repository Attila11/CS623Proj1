CREATE DATABASE IF NOT EXISTS rev;

USE rev;

CREATE TABLE IF NOT EXISTS paper
    (Id INT unsigned NOT NUll AUTO_INCREMENT,
    Title VARCHAR(150) NOT NULL,
    Abstract VARCHAR(150) NOT NULL,
    FileName VARCHAR(150) NOT NULL,
    PRIMARY KEY (Id)
);

INSERT IGNORE INTO paper(Title, Abstract, FileName) VALUES
('Paper 1', 'Paper about the number 1','Paper1.PDF');
INSERT IGNORE INTO paper(Title, Abstract, FileName) VALUES
('Paper 2', 'Paper about the number 2','Paper2.PDF');
INSERT IGNORE INTO paper(Title, Abstract, FileName) VALUES
('Paper 3', 'Paper about the number 3','Paper3.PDF');
INSERT IGNORE INTO paper(Title, Abstract, FileName) VALUES
('Paper 4', 'Paper about the number 4','Paper4.PDF');

CREATE TABLE IF NOT EXISTS author
 (
    EmailAddr VARCHAR(150) NOT NULL,
    FirstName VARCHAR(150) NOT NULL,
    LastName VARCHAR(150) NOT NULL,
    Id INT unsigned UNIQUE  NOT NUll,	
    PRIMARY KEY (EmailAddr),
    CONSTRAINT FK_Submits FOREIGN KEY (Id)
    REFERENCES paper(Id)
 );

INSERT IGNORE INTO author(EmailAddr, FirstName, LastName, Id) VALUES
('ap@attila.com', 'Attila','Papp', '1');
INSERT IGNORE INTO author(EmailAddr, FirstName, LastName, Id) VALUES
('cc@attila.com', 'Caroline','Cary', '2');
INSERT IGNORE INTO author(EmailAddr, FirstName, LastName, Id) VALUES
('sp@attila.com', 'Sebastian','Papp', '4');
INSERT IGNORE INTO author(EmailAddr, FirstName, LastName, Id) VALUES
('mp@attila.com', 'Mat','Papp', '3');


CREATE TABLE IF NOT EXISTS reviewer(
	EmailAddr VARCHAR(150) NOT NULL,
	FirstName VARCHAR(150) NOT NULL,
    	LastName VARCHAR(150) NOT NULL,
	AuthorFeedback VARCHAR(150),
   	CommiteeFeedback VARCHAR(150),
	PhoneNum INT NOT NULL,
	Affiliation VARCHAR(150),
	AssgndReview INT unsigned NOT NUll,
	AssgndTopic Int unsigned NOT NUll,
	PRIMARY KEY (EmailAddr)
);
INSERT INTO reviewer(EmailAddr,FirstName, LastName, AuthorFeedback, CommiteeFeedback, PhoneNum,
Affiliation, AssgndReview, AssgndTopic) VALUES
('fr@frank.com','Frank', 'Raven', 'Nice Guy', 'OK guy', '914',
'Pace University', '1', '2');
INSERT INTO reviewer(EmailAddr,FirstName, LastName, AuthorFeedback, CommiteeFeedback, PhoneNum,
Affiliation, AssgndReview, AssgndTopic) VALUES
('gr@frank.com','Greg', 'Raven', 'Bad Guy', 'Amazing guy', '914',
'Pace University', '2', '3');

CREATE TABLE IF NOT EXISTS topic(
	Id Int unsigned NOT NUll AUTO_INCREMENT,
	TopicName VARCHAR(150) NOT NULL,
	ReviewerId VARCHAR(150) NOT NULL,
	CONSTRAINT FK_topic FOREIGN KEY (ReviewerId)
    	REFERENCES reviewer(EmailAddr),
	PRIMARY KEY (Id)
);
INSERT INTO topic(Id,TopicName, ReviewerId) VALUES
('1', 'Science','fr@frank.com');
INSERT IGNORE INTO topic(Id,TopicName, ReviewerId) VALUES
('2', 'Math','gr@frank.com');
INSERT INTO topic(Id,TopicName, ReviewerId) VALUES
('3', 'History','gr@frank.com');
INSERT INTO topic(Id,TopicName, ReviewerId) VALUES
('4', 'English','fr@frank.com');


CREATE TABLE IF NOT EXISTS review(
	Id INT unsigned NOT NUll,
	Reccomendation VARCHAR(150) NOT NULL,
	MeritScore INT NOT NULL,
	PaperId INT NOT NULL,
	ReadabilityScore INT NOT NULL,
    	ReviewerId VARCHAR(150) UNIQUE NOT NULL,
	OriginalityScore INT NOT NULL,
	RelevanceScore INT NOT NULL,
	CONSTRAINT FK_Reviewer FOREIGN KEY (ReviewerId)
    	REFERENCES reviewer(EmailAddr),
	PRIMARY KEY (Id)
);
INSERT INTO review(Id, Reccomendation, MeritScore, PaperId, ReadabilityScore, ReviewerID,
OriginalityScore, RelevanceScore) VALUES
('1','Need more passion', '5', '1', '5', 'gr@frank.com',
'5', '5');
INSERT INTO review(Id, Reccomendation, MeritScore, PaperId, ReadabilityScore, ReviewerID,
OriginalityScore, RelevanceScore) VALUES
('2','Need less passion', '7', '2', '7', 'fr@frank.com',
'7', '7');



CREATE TABLE IF NOT EXISTS assigned(
	Id INT unsigned NOT NUll,
	EmailAddr VARCHAR(150) NOT NULL,
	FOREIGN KEY (Id) REFERENCES paper (Id),
	FOREIGN KEY (EmailAddr) REFERENCES reviewer (EmailAddr),
	PRIMARY KEY (Id,EmailAddr));

INSERT IGNORE INTO assigned(Id, EmailAddr) VALUES
('1', 'fr@frank.com');

INSERT IGNORE INTO assigned(Id, EmailAddr) VALUES
('3', 'gr@frank.com');
INSERT IGNORE INTO assigned(Id, EmailAddr) VALUES
('3', 'fr@frank.com');
INSERT IGNORE INTO assigned(Id, EmailAddr) VALUES
('2', 'gr@frank.com');

ALTER TABLE reviewer ADD CONSTRAINT fk_AssgndR FOREIGN KEY (AssgndReview) REFERENCES review(Id);
ALTER TABLE reviewer ADD CONSTRAINT fk_Rtopic FOREIGN KEY (AssgndTopic) REFERENCES topic(Id);

