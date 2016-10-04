import java.io.*;
import java.sql.*;
import java.util.Scanner;

class a2 
{
	public static void main(String[] args) throws IOException
	{
		
		
		ResultSet rs;
		int ch,id,age,sal;
		String fname,lname,hdate;
  		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test1","root","root");  
  
			Statement stmnt=con.createStatement(); 
			do{
	  			System.out.print("\n---------- Main Menu ---------");
	 			 System.out.print("\n1.Create Tables\n2.Insert record\n3.Show Tables\n4.Perform Inner Join\n5.Perform Left Join\n6.Perform Right Join\n7.exit");
	  			System.out.print("\nEnter your choice : ");
	  			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	  			ch  = Integer.parseInt(br.readLine());
	  		switch(ch){
				case 1:
					stmnt.executeUpdate("drop table if exists employee");
					stmnt.executeUpdate("drop table if exists dept");
					String sql = "create table employee (emp_id int(10) primary key,emp_name varchar(30),emp_salary int(10),dept_id int(4))";
					stmnt.executeUpdate(sql);
					System.out.println("\nEmployee table created successfully!");
		 			sql = "create table dept (dept_id int(4) primary key,dept_name varchar(30))";
					stmnt.executeUpdate(sql);
					System.out.println("\nDepartment table created successfully!");
				break;
				case 2:
					System.out.println("---------- Insert Menu ----------");
					System.out.println("1.Insert in table employee\n2.Insert in table department\nEnter yout choice : ");
					int insertChoice = Integer.parseInt(br.readLine());
					switch(insertChoice){
					case 1:
						System.out.println("Enter employee id : ");
						int eid = Integer.parseInt(br.readLine());
						System.out.println("Enter employee name :");
						String ename = br.readLine();
						System.out.println("Enter salary : ");
						int esal = Integer.parseInt(br.readLine());
						System.out.println("Enter department id :");
						int edeptid = Integer.parseInt(br.readLine());
						String insertEmployeeQuery = "insert into employee values("+eid+",'"+ename+"',"+esal+","+edeptid+");";
						stmnt.executeUpdate(insertEmployeeQuery);
						System.out.println("Inserted into employee table successfully!");
						break;
					case 2:
						System.out.println("Enter department id :");
						int deptid = Integer.parseInt(br.readLine());
						System.out.println("Enter department name :");
						String dname = br.readLine();
						String insertDepartmentQuery = "insert into dept values("+deptid+",'"+dname+"');";
						stmnt.executeUpdate(insertDepartmentQuery);
						System.out.println("Inserted into department table successfully!");
						break;
					}
					break;
					case 3:
						System.out.println(" Tables ");
						System.out.println("---------- Employee Table ----------");
						rs = stmnt.executeQuery("select * from employee ");
						System.out.println("\nEmp ID\tEmp Name\tEmp salary\tDept ID");
						while(rs.next()){
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getInt(3)+"\t\t"+rs.getInt(4));
						}
						System.out.println("---------- Department Table ----------");
						rs = stmnt.executeQuery("select * from dept ");
						System.out.println("\nDept ID\tDept Name");
						while(rs.next()){
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
						}
						break;
					case 4:
						System.out.println(" Inner Join ");
						rs = stmnt.executeQuery("select * from employee INNER JOIN dept ON employee.dept_id = dept.dept_id");
						System.out.println("\nEmp ID\tEmp Name\tEmp salary\tDept ID\tDemp Name ");
						while(rs.next()){
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getInt(3)+"\t\t"+rs.getInt(4)+"\t"+rs.getString(6));
						}
						break;
					case 5:
						System.out.println(" Left Join ");
						rs = stmnt.executeQuery("select * from employee LEFT JOIN dept ON employee.dept_id = dept.dept_id");
						System.out.println("\nEmp ID\tEmp Name\tEmp salary\tDept ID\tDemp Name ");
						while(rs.next()){
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getInt(3)+"\t\t"+rs.getInt(4)+"\t"+rs.getString(6));
						}
						break;
					case 6:
						System.out.println(" Right Join ");
						rs = stmnt.executeQuery("select * from employee RIGHT JOIN dept ON employee.dept_id = dept.dept_id");	
						System.out.println("\nEmp ID\tEmp Name\tEmp salary\tDept ID\tDemp Name ");
						while(rs.next()){
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getInt(3)+"\t\t"+rs.getInt(4)+"\t"+rs.getString(6));
						}
						break;
					case 7:
						break;
					default:
						System.out.println("Invalid Choice");
						break;
				}		
			}while(ch!=8);

		}catch(Exception e)
			{ System.out.println(e);}  
    } 
}

