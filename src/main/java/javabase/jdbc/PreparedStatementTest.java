package javabase.jdbc;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by huazhao on 17/2/28.
 */
public class PreparedStatementTest {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "taobao1234";

    public static void main(String[] args) {
        String iSQL = "insert into Employees(name, birth, sex, entry, gmt_create, gmt_modified) "
                + "values(?, ?, ?, ?, now(), now())";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(iSQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, "中国");
            pstmt.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            pstmt.setInt(3, 1);
            pstmt.setDate(4, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            pstmt.addBatch();

            pstmt.setString(1, "日本");
            pstmt.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            pstmt.setInt(3, 0);
            pstmt.setDate(4, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            pstmt.addBatch();

            int[] count = pstmt.executeBatch();
            if (true) {
                throw new RuntimeException("user-defined-exception");
            }
            conn.commit();


            pstmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
            try {
                conn.rollback();
            }catch (SQLException se) {
                se.printStackTrace();
            }
        }finally{
            //finally block used to close resources
            try{
                if(pstmt!=null)
                    pstmt.close();
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
}
