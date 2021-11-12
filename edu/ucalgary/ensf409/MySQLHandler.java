package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class handles SQL
 * @version 1.2
 * @since 1.0
 * @author Kunal Dhawan, Kaitlin Culligan, Arindam Mishra
 */

 public class MySQLHandler {
 
    private String DBURL;
    private String USERNAME;
    private String PASSWORD;
    private String [] manufacturerID;
    private Connection dbConnect;
    private ResultSet results;
    private Calculations calculator;
    private String [] arrayOfIDs = new String [50] ;

    /**
     * this is the constructor for the MySQLHandler object
     */
    public MySQLHandler(String url, String user, String password){
		this.DBURL = url ;
		this.USERNAME= user;
		this.PASSWORD= password;
    }
    
	public String getDBURL() {
		return this.DBURL;
	}
	public String getUSERNAME() {
		return this.USERNAME;
	}
	public String getPASSWORD() {
		return this.PASSWORD;
	}
    
    /**
     * this initializes the connection between the mySQL database using the
     * URL, User name and password of the user
     */
    public void initializeConnection(){
        try{
            dbConnect = DriverManager.getConnection(this.DBURL,this.USERNAME,this.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method creates a statement to store the data from a specific table into
     * a ResultSet and passes it on to the calculation class.
     * It receives back a String array consisting the Ids of the various furniture
     * pieces used or an array of length 1 if the inventory cannot accommodate the desired furniture. 
     * It then adds every id to a common String array and returns it back to the userInput class
     * @param table category from which the furniture item is required
     * @param type of furniture that is required
     * @param number of furniture pieces that are required
     * @return String array of IDs or the manufacturers name
     */
    public String [] selectFurnitureToOrder(String table, String type, String number){

        int numberOfItems= Integer.parseInt(number);
        arrayOfIDs[0]="0";
        int j=1;
        
        try {   
        	while(numberOfItems>0) {
                this.calculator = new Calculations();
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM "+ table);
            
             String [] arrayTemp = calculator.calculatePrices(results, type);
            if(arrayTemp.length==1) {
            	manufacturerID= selectManufacturers(table);
            	return manufacturerID;
            }else {
            	for(int i=1;i<arrayTemp.length;i++) {
            	deleteFurniture(table, arrayTemp[i]);
            	}      	
            }
            if(numberOfItems>0) {
            	int cost= Integer.parseInt(arrayOfIDs[0]);
            	int costFromTemp=Integer.parseInt(arrayTemp[0]);
            	arrayOfIDs[0]=String.valueOf(cost+costFromTemp);
            	for(int i=1;i<arrayTemp.length;i++) {
            		arrayOfIDs[j]=arrayTemp[i];
            		j++;
            	}
            	
            }
            myStmt.close();
            numberOfItems--; 
        	}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }       
        return arrayOfIDs;
    }
    
    /**
     * this method writes the name of the suggested manufacturers in the maufacturersID array
     * if the furniture pieces are not available in the inventory and returns it to the 
     * selectFurnitureToOrder method
     * @param table category from which the suggested manufacturer needs to be selected
     * @return String array manufacturers' name
     */
    
    public String [] selectManufacturers(String table) {
    	if(table.equalsIgnoreCase("chair")) {
    		manufacturerID=new String[4];
    		manufacturerID[0]="Office Furnishings";
    		manufacturerID[1]="Chairs R Us";
    		manufacturerID[2]="Furniture Goods";
    		manufacturerID[3]="Fine Office Supplies";
    		return manufacturerID;
    	}else if(table.equalsIgnoreCase("desk")) {
    		manufacturerID=new String[4];
    		manufacturerID[0]="Academic Desks";
    		manufacturerID[1]="Office Furnishings";
    		manufacturerID[2]="Furniture Goods";
    		manufacturerID[3]="Fine Office Supplies";
    		return manufacturerID;
    	}else if(table.equalsIgnoreCase("lamp")) {
    		manufacturerID=new String[3];
    		manufacturerID[0]="Office Furnishings";
    		manufacturerID[1]="Furniture Goods";
    		manufacturerID[2]="Fine Office Supplies";
    		return manufacturerID;
    	}else if(table.equalsIgnoreCase("filing")) {
    		manufacturerID=new String[3];
    		manufacturerID[0]="Office Furnishings";
    		manufacturerID[1]="Furniture Goods";
    		manufacturerID[2]="Fine Office Supplies";
    		return manufacturerID;
    	}
    	return null;
    }

	  /**
	    *This method deletes the details of the specified furniture
	    * from the specified table in the database
	    * @param table category from which the furniture needs to be deleted
	    * @param id of furniture  that needs to be deleted    
	    */
    public void deleteFurniture(String table, String id){
        try {   
            String query = "DELETE FROM "+table+" WHERE ID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, id);
            myStmt.executeUpdate();
            myStmt.close();           

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

	  /**
	    *This method closes  the ResultSet and the Statement query
	    */
    public void close(){
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public Object dbConnectGet() {
		return this.dbConnect;
	}
}