package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;




public class Operations {
	
	static String  Url="jdbc:mysql://localhost:3306/sys";
	static String Username="root";
	static String Password="root@123";
	static Connection con;
	static Statement stmt;
	static ResultSet result;
	static PreparedStatement pstmt;
	
    public static int details() {
    	System.out.println("What is the change want to do?");
		System.out.println("1 "+" Fetching the data");
		System.out.println("2 "+" Inserting the data");
		System.out.println("3 "+" Updating the data");
		System.out.println("4 "+" Deleting the data");
		System.out.println("5 "+" Exit the program");
		System.out.println("Enter the number");
		int number=new Scanner(System.in).nextInt();
		return number;
    }
	
	public static void main(String args[]) {
	
           try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con=DriverManager.getConnection(Url,Username,Password);
		    System.out.println("Database Connected Successfully");
		    
		    while (true) {
                int number = details();

                switch (number) {
                    case 1:
                        System.out.println("\nFetching Data:");
                        fetchData();
                        break;
                    case 2:
                        System.out.println("\nInserting Data:");
                        insertData();
                        break;
                    case 3:
                        System.out.println("\nUpdating Data:");
                        updateData();
                        break;
                    case 4:
                        System.out.println("\nDeleting Data:");
                        deleteData();
                        break;
                    case 5:
                        System.out.println("Exiting the program...");
                        con.close(); 
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                        break;
                }
            }
	     } 
         catch (ClassNotFoundException | SQLException e) {
		
		      e.printStackTrace();
	     }
		
	}
		
    public static void fetchData() {
		 try {
			
			
			
			
			stmt=con.createStatement();
			
			
			
			result=stmt.executeQuery("Select * from student_table");
			
			
			
			while(result.next())
			{
				System.out.println(result.getString(1)+" "+result.getInt(2)+" "+result.getString(3)+" "+result.getString(4)+" "+result.getLong(5));
				
			}
			
			
			
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
    }
		
		
	static String sql="INSERT INTO Student_table(`student name`,`student id`,`student branch`,`student address`,`student phone number`) VALUES (?,?,?,?,?)";
	public static void insertData() {
		  Scanner sc=new Scanner(System.in);
		  Scanner sc1=new Scanner(System.in);
		  System.out.println("Enter the student name: ");
		  String name=sc.nextLine();
		  
		  System.out.println("Enter the student id");
		  int  id=sc.nextInt();
		  
		  System.out.println("Enter the student branch: ");
		  String branch_name=sc1.nextLine();
		  
		  System.out.println("Enter the student address: ");
		  String student_address=sc1.nextLine();
		  
		  System.out.println("Enter the student Phone number: ");
		  long phone=sc1.nextLong();
		  
		  try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setInt(2,id);
			pstmt.setString(3,branch_name);
			pstmt.setString(4,student_address);
			pstmt.setLong(5,phone);
			
			int x=pstmt.executeUpdate();
			
			System.out.println(x+" rows affected");
			System.out.println();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		  
		  
		  
	}
	
	static String sqlUpdate ="UPDATE student_table SET `student address`=? , `student phone number`=? WHERE `student id`=?";
	public static void updateData() {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter student address: ");
		String address=scan.nextLine();
		System.out.println("Enter Phone number: ");
		Long phone=scan.nextLong();
		System.out.println("Enter student id");
		int id=scan.nextInt();
		
		try {
			pstmt=con.prepareStatement(sqlUpdate);
			pstmt.setString(1,address);
			pstmt.setLong(2, phone);
			pstmt.setInt(3, id);
			int x = pstmt.executeUpdate();
			
			System.out.println(x+" is affected");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	static String sqlDelete="DELETE FROM student_table WHERE `student id`=?";
	public static void deleteData() {
		System.out.println("Enter the student id");
		int id=new Scanner(System.in).nextInt();
		try {
			pstmt=con.prepareStatement(sqlDelete);
			
			pstmt.setInt(1, id);
			
			int x=pstmt.executeUpdate();
			
			System.out.println(x+" Rows are affected");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
