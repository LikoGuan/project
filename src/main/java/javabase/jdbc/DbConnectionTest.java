package javabase.jdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


/**
 * Created by huazhao on 17/2/28.
 */


public class DbConnectionTest {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "taobao1234";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                Date birth = null;
                if (rs.getDate("birth") != null) {
                    birth = new Date(rs.getDate("birth").getTime());
                }
                String sex = rs.getInt("sex")==1 ? "male" : "female";
                Date entry = null;
                if (rs.getDate("entry") != null) {
                    entry = new Date(rs.getDate("entry").getTime());
                }
                Date gmtCreate = new Date(rs.getDate("gmt_create").getTime());
                Date gmtModified = new Date(rs.getDate("gmt_modified").getTime());

                //Display values
                System.out.println("ID: " + id + ", Name: " + name + ", Sex: " + sex
                        + ", birth: " + birth + ", entry: " + entry
                        + ", gmt_create: " + gmtCreate + ", gmt_modifed: " + gmtModified);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample
