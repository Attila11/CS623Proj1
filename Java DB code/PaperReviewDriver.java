import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PaperReviewDriver {

	   public static void main(String[] args) {
		   
		  String email;
		  int paperID =-1;
		  String paperInfo=null;
		  String authorInfo=null;
		  String delAuthor = null;
		  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		  System.out.println("Enter Author Email");
		  try {
			email = in.readLine();
			while(!email.contains("@")) {
				System.out.println("Please enter a valid email address");
				email = in.readLine();
			}
			Query1(email);
		} 
		 catch (IOException e) {
			e.printStackTrace();
		}
		  System.out.println();
		  System.out.println("Please enter a Paper ID, if recommended review will be printed: ");
		  
		  while(paperID==-1) {
			    try {
				paperID = Integer.parseInt(in.readLine());
			    Query2(paperID);
		} catch (NumberFormatException e) {
			System.out.println("enter a number only");
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
		  
		  System.out.println();
		  Query3();
		  System.out.println("FOR THE NEXT TWO INPUTS PLEASE ENTER THE DATA WITH A COMMA AND A SPACE IN BETWEEN");
		  System.out.println("Enter Paper Title, abstract, file name to insert into the database:");
		  try {
				paperInfo = in.readLine();
			} 
			 catch (IOException e) {
				e.printStackTrace();
			}
		  System.out.println("Enter Author email address, first name, last name to insert into database:");
		  try {
				authorInfo = in.readLine();
			} 
			 catch (IOException e) {
				e.printStackTrace();
			}
		  Query4(paperInfo,authorInfo);

	   System.out.println("Enter Author email address to delete from database");
		  try {
				delAuthor = in.readLine();
			} 
			 catch (IOException e) {
				e.printStackTrace();
			}
		  Query5(delAuthor);
	   }
	  
	 //Get a submitted paper’s details by the author’s Primary Key. The query should return the
	 //following data (columns): Paper.Id, Paper.Title, Paper.Abstract, Author.EmailAddress,
	 //Author.FirstName, Author.LastName   
	   
public static void Query1(String e) {
	Connection con = null;
	String query1 = "SELECT paper.Id, paper.Title, paper.Abstract, author.EmailAddr,author.FirstName, author.LastName FROM author INNER JOIN paper ON author.ID=paper.Id WHERE EmailAddr=?";
	
	try{
			con = ConnectionManager.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(query1);
			prepStmt.setString(1, e);
			ResultSet rs = prepStmt.executeQuery();
		    
			if(rs.next()==false) {
				System.out.println("No Data Found");
			}
			else {
			do {
	                int id = rs.getInt(1);
	                String title = rs.getString(2);
	                String abstrac = rs.getString(3);
	                String email = rs.getString(4);
	                String fName = rs.getString(5);
	                String lName = rs.getString(6);
	                //System.out.printf("%1d %5s %5s %5s %5s %5s\n",id,title,abstrac,email,fName,lName);
	                System.out.println("Id = "+id);
	                System.out.println("Title = "+title);
	                System.out.println("Abstract = "+abstrac);
	                System.out.println("Email = "+email);
	                System.out.println("First Name = "+fName);
	                System.out.println("Last Name = "+lName);
	            } while (rs.next());
			}

		   rs.close();
		   prepStmt.close();
		   con.close();
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception ex){
		      ex.printStackTrace();
		   }finally{
			   try{
		         if(con!=null)
		            con.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		 }
	}

//Get all reviews for a paper by the paper’s Id, where the paper was recommended to be
//published. The query should return the following data (columns): All columns from the
//Review table.

public static void Query2(int e) {
	Connection con = null;
	String query1 = "SELECT * FROM review WHERE PaperId=? AND Reccomendation = 'Publish'";
	
	try{
			con = ConnectionManager.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(query1);
			prepStmt.setInt(1, e);
			ResultSet rs = prepStmt.executeQuery();
		    
			if(rs.next()==false) {
				System.out.println("No Data Found");
			}
			else {
			do {
	                int id = rs.getInt(1);
	                String recc = rs.getString(2);
	                int meritScore = rs.getInt(3);
	                int paperid = rs.getInt(4);
	                int rScore = rs.getInt(5);
	                String revID = rs.getString(6);
	                int oScore = rs.getInt(7);
	                int relScore = rs.getInt(8);
	                //System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",id,recc,meritScore,paperid,rScore,revID,oScore,relScore);
	                System.out.println("Id = "+id);
	                System.out.println("Reccomendation = "+recc);
	                System.out.println("Merit Score = "+meritScore);
	                System.out.println("paperid = "+paperid);
	                System.out.println("Readability Score = "+rScore);
	                System.out.println("Relavance Score= "+relScore);
	                System.out.println("Originality Score = "+oScore);
	                System.out.println("Reviewer ID = "+revID);
	            } while (rs.next());
			}
		   rs.close();
		   prepStmt.close();
		   con.close();
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception ex){
		      ex.printStackTrace();
		   }finally{
			   try{
		         if(con!=null)
		            con.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		 }
	}


//Get a count of all papers submitted.

public static void Query3() {
	Connection con = null;
	String query1 = "SELECT COUNT(*) FROM paper";
	
	try{
			con = ConnectionManager.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(query1);
			ResultSet rs = prepStmt.executeQuery();
			int count = 0;
		                while (rs.next()){
		                    count = rs.getInt(1);
		                }
		                System.out.println("Number of Papers submitted = "+count);
		   rs.close();
		   prepStmt.close();
		   con.close();
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception ex){
		      ex.printStackTrace();
		   }finally{
			   try{
		         if(con!=null)
		            con.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		 }
	}

//Create a new paper submission. Remember this includes creating new records in both
//the Author and Paper tables.

public static void Query4(String a, String b) {
	
	String[] split = a.trim().split("\\s*,");
	String[] split1 = b.trim().split("\\s*,");
	
	Connection con = null;
	String query = "INSERT IGNORE INTO paper(Title, Abstract, FileName) VALUES (?, ?,?)";
	try{
			con = ConnectionManager.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(query);
			prepStmt.setString(1, (split[0]));
			prepStmt.setString(2, (split[1]));
			prepStmt.setString(3, (split[2]));
			int rs = prepStmt.executeUpdate();
		   prepStmt.close();
		  
		   }catch(SQLException se){
			      se.printStackTrace();
			   }catch(Exception ex){
			      ex.printStackTrace();
			   }
	
		   finally{
			   try{
		         if(con!=null)
		            con.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		 }
	String query1 = "INSERT IGNORE INTO author(EmailAddr, FirstName, LastName, Id) VALUES(?,?,?,?)";
	String query2= "SELECT Id FROM paper ORDER BY id DESC LIMIT 1";
	try{
		con = ConnectionManager.getConnection();
		PreparedStatement prepStmt2 = con.prepareStatement(query2);
		ResultSet rs = prepStmt2.executeQuery();
		while (rs.next()) {
		    int id = rs.getInt("id");
		
		PreparedStatement prepStmt1 = con.prepareStatement(query1);
		prepStmt1.setString(1, (split1[0]));
		prepStmt1.setString(2, (split1[1]));
		prepStmt1.setString(3, (split1[2]));
		prepStmt1.setInt(4, id);
		int rs1 = prepStmt1.executeUpdate();
		}
	   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception ex){
		      ex.printStackTrace();
		   }
		finally{
		   try{
	         if(con!=null)
	            con.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
		}
	}

//Try and Delete the first “Author” row in your Author table by the author’s id. Did you
//receive an error? If yes, print to the console the error you received. Also note in your
//message why the query failed. If it didn’t fail, print a message explaining why you were
//able to delete the row.

public static void Query5(String a) {
	Connection con = null;
	String query = "DELETE FROM author WHERE EmailAddr = ?";
	
	try{
			con = ConnectionManager.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(query);
			prepStmt.setString(1, a);
			int rs = prepStmt.executeUpdate();
		   prepStmt.close();
		   con.close();
		   System.out.println("Operation success because the author did not reference any existing papers or author does not exist");
		   }catch(SQLException se){
		      se.printStackTrace(System.err);
		      System.out.println("Operation failed Because:  ");
		      System.err.println("Sql State "+((SQLException)se).getSQLState());
		      System.err.println("ERROR code "+((SQLException)se).getErrorCode());
		      System.err.println("Sql Message "+((SQLException)se).getMessage());
		   }catch(Exception ex){
		      ex.printStackTrace();
		   }finally{
			   try{
		         if(con!=null)
		            con.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		 }
	}

}





