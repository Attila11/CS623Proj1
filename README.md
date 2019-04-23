# CS623Proj1
SQL Script for first part of project

To run and create the database, please complete the following steps.

Ensure that MySql is installed on your workstation, can be installed at "https://www.mysql.com/downloads/"

Once it is installed, run the MySQL 8.0 command line client

Enter the root password that was set during the SQL installation

Save the files to a directory of your choice

type the following command: "source 'Path to\Project.sql'" this will be the path where you saved the 'Project.sql' file
this will create and populate the database

after that use the command: "source 'Path to\Display.sql'" This will be the path where you saved the 'Display.sql' file
This will show all tables in the database and the contents of all tables

at this point you can follow the format of the insert statements to insert into the database or refer to the MySQL tutorials for further information on how to manipulate and interact with the database through the command line.

The database will look as follows 
+---------------+
| Tables_in_rev |
+---------------+
| assigned      |
| author        |
| paper         |
| review        |
| reviewer      |
| topic         |
+---------------+

Assigned is a relational table for the many to many relationship between Paper and Reviewer 

the tables other than that are straightforward with 3 other relations being represented with foreign keys where each reviewer can be assigned multiple reviews as well as multiple topics, and the last being each paper being assigned to an author and each author to one paper
