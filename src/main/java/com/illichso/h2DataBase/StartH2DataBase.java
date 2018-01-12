package com.illichso.h2DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StartH2DataBase {
    public static void main(String[] args)
    {
        try
        {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:~/test", "test", "" );
            Statement stmt = con.createStatement();
//            stmt.executeUpdate( "DROP TABLE posts" );
            stmt.executeUpdate( "CREATE TABLE posts ( POST_ID varchar(50), POST_TEXT varchar(50))" );
            stmt.executeUpdate( "INSERT INTO posts ( POST_ID, POST_TEXT ) VALUES ( '0001', 'text1' )" );
            stmt.executeUpdate( "INSERT INTO posts ( POST_ID, POST_TEXT ) VALUES ( '0002', 'text2' )" );

            ResultSet rs = stmt.executeQuery("SELECT * FROM posts");
            while( rs.next() )
            {
                String id = rs.getString("POST_ID");
                System.out.print( id + " - " );
                String text = rs.getString(2);
                System.out.println(text);
            }
            stmt.close();
            con.close();
        }
        catch( Exception e )
        {
            System.out.println( e.getMessage() );
        }
    }
}
