package edu.ucalgary.ensf409;


/**
 * This class handles the printing of the orderform
 * in an output.txt file.
 * @version 1.0
 * @since 1.0
 * @author Arindam Mishra, Kaitlin Culligan, Kunal Dhawan
 */

import java.util.Scanner;

//class begins here
public class userInput {
	
	private  String numberOfItems;
	private  String category;
	private  String type;
	private static Scanner scanner;
	private static String [] orderedItems;
	private static Printing output;


	/**
     * this is the constructor for the userInput object
     */
	public userInput(String category,String type, String numberOfItems) {
		this.category=category;
		this.type=type;
		this.numberOfItems=numberOfItems;
	}
	
	
	/**
	    * This main method first prints a request to the user to 
	    * type in the furniture category, type and also the number 
	    * of items that are required by the user and then scans then
	    * processes it to make sure the user input received is correct 
	    * @param category of furniture
	    * @param type of furniture category
	    * @param number of items required 
	    */
	public static void main(String args[]) {
	
    MySQLHandler myJDBC = new MySQLHandler ("jdbc:mysql://localhost/inventory", "scm", "ensf409");
    myJDBC.initializeConnection();
    userInput input = new userInput("","","");
	scanner = new Scanner(System.in);
	System.out.println("Please press the enter key after every input you give.");
	System.out.println("Please enter the furniture category (chair, desk, filing or lamp), furniture type and the number of items required");
    input.category= scanner.nextLine();
    input.type=scanner.nextLine();
    input.numberOfItems=scanner.nextLine();
    

/** 
* the if statements here make sure that the values entered
* by the user are valid and returns a request to re-enter 
* them in case they seem to be invalid with the help of the 
* boolean defined later below 
*/
    if(input.checkForValidCategory(input)) {
    	if(input.checkForValidType(input)) {
    	if(input.checkForInValidNumber(input)) {
    		System.out.println("Enter a number greater than 0 for the number of furniture items you require. Please run the program again and re-enter the values");
    		System.exit(0);
    	}else {
	orderedItems=myJDBC.selectFurnitureToOrder(input.category, input.type, input.numberOfItems);
    	}
    	}else {
        	System.out.println("The given type does not exist in the desired category. Please run the program again and re-enter the values");
        	System.exit(0);
    	}
    }else {
    	System.out.println("The given table does not exist in the database. Please run the program again and re-enter the values");
    	System.exit(0);
    }
    
    
	/** Here the boolean isOrderedItemsID checks if the first
	* element in the array orderedItems is a number and it returns
	* true if the cost is contained in it and false if it contains
	* the data of the manufacturers in it
	*/
	boolean isOrderedItemsID= userInput.isNumeric(orderedItems[0]);
	if(isOrderedItemsID) {
		/*if true it sends the request and the array containing 
		*the items and cost and sends null for manufacturers id
		*/
		output = new Printing(input.type+" "+input.category+", "+input.numberOfItems,orderedItems,null);
		output.writeFile();
	}else {
	       /* In this false case it sends the input string
		*itemid null and the array for the manufacturer data
		*/
		output = new Printing(input.type+" "+input.category+", "+input.numberOfItems,null,orderedItems);
		output.writeFile();
	}		
	}

	
	/**
	    *This boolean checks whether the furniture category 
	    * entered by the user is a valid category and if
	    * not it sets checkForValidCategory as false
	    *    
	    */
	public boolean checkForValidCategory(userInput input) {
		 if(input.category.equals("chair")||input.category.equals("desk")||input.category.equals("filing")||input.category.equals("lamp")) {
			 return true;
		 }
		 return false;
	}
	
	
	/**
	    * This boolean checks whether the type of furniture
	    * is valid one for each category that could be 
	    * entered by the user. If its not found to be 
	    * a type it sets checkForValidType as false
	    */
	public boolean checkForValidType(userInput input) {
		 if(input.category.equals("chair")) {
			 if(input.type.equals("Mesh")||input.type.equals("Task")||input.type.equals("Kneeling")||input.type.equals("Executive")||input.type.equals("Ergonomic")) {
				 return true;
			 }
			 return false;
		 }
		 if(input.category.equals("desk")) {
			 if(input.type.equals("Traditional")||input.type.equals("Adjustable")||input.type.equals("Standing")) {
				 return true;
			 }
			 return false;
		 }
		 if(input.category.equals("filing")) {
			 if(input.type.equals("Small")||input.type.equals("Medium")||input.type.equals("Large")) {
				 return true;
			 }
			 return false;
		 }
		 if(input.category.equals("lamp")) {
			 if(input.type.equals("Desk")||input.type.equals("Swing Arm")||input.type.equals("Study")) {
				 return true;
			 }
			 return false;
		 }
		 return false;
	}
	
	
	  /**
	    *This boolean checks whether the number of items requested
	    * by the user is a valid number or if it is positive number  
	    * and if not is not it sets checkForValidNumber as false
	    * else true
	    */
	public boolean checkForInValidNumber(userInput input) {
		 if(Integer.parseInt(input.numberOfItems)<1) {
			 return true;
		 }
		 return false;
	}

	
      /**
	* getter for the string type of furniture needed
        * @return String type
        */
	public String getType() {
		return type;
	}

	
   /**
     * getter for the printing object that calls onto the printing class
     * to print the output form
     * @return furniture's type needed
     */

	public Printing getOutput() {
		return output;
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}


      /**
        * getter for the string category of furniture needed
        * @return furniture's category needed
        */

	public String getCategory() {
		return category;
	}

 
   /**
     * getter for the integer number of items required by the user
     * @return number of furniture pieces required
     */

	public String getNumberofItems() {
		return numberOfItems;
	}

}