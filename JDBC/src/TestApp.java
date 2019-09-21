import java.sql.*;
import java.util.*;
public class TestApp {

	public static void main(String[] args) {

			Connection con=null;
			Scanner sc=new Scanner(System.in);
			ResultSet rs=null;
			Statement st;
			int id,sal,ch;
			String name;
			PreparedStatement ps=null;
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","redhat");
				System.out.println("Connected...");
				System.out.println("1.Insert\n2.Delete\n3.Update\n4.Search\n5.Display\nEnter your choice:");
				ch=sc.nextInt();
				do{
				switch(ch)
				{
				case 1:
					ps=con.prepareStatement("insert into employee1 values(?,?,?)");
					System.out.println("Enter employee name id and salary");
					id=sc.nextInt();
					name=sc.next();
					sal=sc.nextInt();
					ps.setInt(1, id);
					ps.setString(2,name);
					ps.setInt(3,sal);
					int i=ps.executeUpdate();
					if(i>0)
						System.out.println("Record saved");
					break;
				
				case 2:
					ps=con.prepareStatement("delete from employee1 where eno=?");
					System.out.println("Enter the id to be deleted");
					id=sc.nextInt();
					ps.setInt(1, id);
					i=ps.executeUpdate();
					if(i>0)
						System.out.println("Record deleted");
					break;
					
				case 3:
					System.out.println("Enter the id:");
					id=sc.nextInt();
					System.out.println("Enter the new salary to be updated:");
					sal=sc.nextInt();
					ps=con.prepareStatement("update employee1 set sal=? where eno=?");
					ps.setInt(2,id);
					ps.setInt(1, sal);
					i=ps.executeUpdate();
					if(i>0)
						System.out.println("Record updated");
					break;
					
				case 4:
					System.out.println("Enter id to be searched:");
					id=sc.nextInt();
					ps=con.prepareStatement("select * from employee1 where eno=?");
					ps.setInt(1, id);
					rs=ps.executeQuery();
					if(rs.next())
					{
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3));
						System.out.println("Record searched");
						
					}
					else
					{
						System.out.println("Record not found");
					}
					break;
					
				case 5:
					System.out.println("Displaying the records");
					String str="select * from employee1";
					st=con.createStatement();
					rs=st.executeQuery(str);
					ResultSetMetaData rsmd=rs.getMetaData();
					for(int j=1;j<rsmd.getColumnCount();j++)
					{
						System.out.println(rsmd.getColumnName(j));
					}
					System.out.println();
					while(rs.next())
					{
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3));
						
					}
					System.out.println("Record displayed");
					break;
					
				default:
					System.out.println("Invalid choice");
				}
				}while(ch==0);
					
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(SQLException e1)
			{
				e1.printStackTrace();
			}
			
			
	}

}
