package edu.ucalgary.ensf409;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.junit.*;
import edu.ucalgary.ensf409.MySQLHandler;
/**
 * This class handles unit testing of MySQLHandler
 * @version 1.0
 * @since 1.0
 * @author Kaitlin Culligan, Arindam Mishra, Kunal Dhawan
 */
//unit tests for project
//need dbconnect getter to fix a lot of issues
public class MySQLHandlerTest {
    //test pulling manufacturers list

    private Connection dbConnect;
    private ResultSet results;
    private String chair = "chair";
    private Calculations calculator;

	//test delete function
    /**
     * tests the delete method
     */
    @Test
    public void testDelete(){
        //make a queury, delete some of the stuff, try a queury again and see if its there
        MySQLHandler sql = new MySQLHandler("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        sql.initializeConnection();
        try{
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM " + chair);
            String[] resultOne = calculator.calculatePrices(results, "Mesh");
            myStmt.close();
            for(int i = 1; i < resultOne.length; i++){
                sql.deleteFurniture("chair", resultOne[i]);
            }
            Statement myStmtTwo = dbConnect.createStatement();
            results = myStmtTwo.executeQuery("SELECT * FROM " + chair);
            String[] resultTwo = calculator.calculatePrices(results, "Mesh");
            myStmtTwo.close();
            assertNotEquals(resultOne,resultTwo);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //test connection
    /**
     * tests the connection to the database
     */
    @Test
    public void testConnection(){
        MySQLHandler sql = new MySQLHandler("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        sql.initializeConnection();
        assertNotNull("connection is null", sql.dbConnectGet());
    }

    //go through db and find cases where this is possible
    //test handling multiple furniture pieces (and can make all of them)
    /**
     * tests the ability of the class to return the cost of multiple item orders
     */
    @Test
    public void testMultiplePossible(){
        MySQLHandler sql = new MySQLHandler("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        sql.initializeConnection();
        String[] one = sql.selectFurnitureToOrder("desk", "Adjustable", "2");
        assertEquals("800",one[0]); //maybe a better way to assert it works
    }
    //test handling multiple furniture pieces (and can not make all of them)
    /**
     * tests the ability of the class to return the manufacturers list on a multiple item order
     */
   @Test
    public void testMultipleNotPossible(){
        MySQLHandler sql = new MySQLHandler("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        sql.initializeConnection();
        String[] one = sql.selectFurnitureToOrder("desk", "Adjustable", "4");
        assert(one.length == 4);
    }
    //test handling single furniture pieces (and can make it)
    /**
     * tests the ability of the class to return the cost of single item orders
     */
    @Test
    public void testSinglePossible(){
        MySQLHandler sql = new MySQLHandler("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        sql.initializeConnection();
        String[] one = sql.selectFurnitureToOrder("chair", "Mesh", "1");
        assertEquals("225",one[0]);
    }
    

    //test handling single furniture pieces (and can not make it)
    /**
     * tests the ability of the class to return the manufacturers list on a single item order
     */
    @Test
    public void testSingleNotPossible(){
        MySQLHandler sql = new MySQLHandler("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        sql.initializeConnection();
        String[] one = sql.selectFurnitureToOrder("chair", "Kneeling", "1");
        assert(one.length == 4);
    }
}