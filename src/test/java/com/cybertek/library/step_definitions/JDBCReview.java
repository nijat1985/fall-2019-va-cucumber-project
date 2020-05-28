package com.cybertek.library.step_definitions;

import org.junit.Test;

import java.sql.*;

/*
* this is class not step def class. we are using it to review jdbc
* */
public class JDBCReview {
    @Test
    public void testConnection() throws SQLException {
        //what database you used in your project
        //in my project i used mysql database, but prior to that i have worked
        //with oracle database as well
        //i have done manual testing as well as automated testing on database.

        //how do you connect to database using selenium/java?
        //in java there is jdbc api that handles connections to the database.
        //So this is what I used in my project to connect to db and automate.
        //First based on the type of the database i need to add the driver to the project. Since I used
        //maven, I added to the pom file. for mysql i useed the mysql-connector-java driver, for oracle ojdbc
        //driver
        //next i need to create CONNECTION for this I use the connection class in Java/JDBC
        //Connection class reuires url, username, passsword
        //after connection is created, i use the Statement class to execute queries and ResultSet class
        //to store the query result and interact with it.

//        qa2_db_host=ec2-18-233-97-71.compute-1.amazonaws.com
//        qa2_db_name=library2
//        qa2_db_username=library2_client
//        qa2_db_password=6s2LQQTjBcGFfDhY

        String url = "jdbc:mysql://ec2-18-233-97-71.compute-1.amazonaws.com:3306/library2";
        String username = "library2_client";
        String password = "6s2LQQTjBcGFfDhY";
        Connection connection = DriverManager.getConnection(url,username,password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM book_categories");

        //get the first column
        System.out.println(resultSet.getMetaData().getColumnName(1));

        //get all column names
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
        }
        System.out.println();
        //how to print all data from the resultset
        while (resultSet.next()){
            //in each row go through each cell
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                System.out.print(resultSet.getString(i) + "\t\t");
            }
            System.out.println();
        }
    }


}
