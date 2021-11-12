package edu.ucalgary.ensf409;

/**
 * This class handles the printing of the orderform
 * in an output.txt file.
 * @version 1.0
 * @since 1.0
 * @author Arindam Mishra, Kaitlin Culligan, Kunal Dhawan
 */
import java.io.*;

public class Printing {

	private BufferedWriter buffWrite;
	private FileWriter fileWrite;
	private String requestString;
	private String[] itemIDs;
	private String [] suggestedManufaturers;
	private int totalCost;

	
    /**
     * This is the constructor, it just initializes the local data members
     */
 public Printing(String requestString,String[] itemIds, String[] suggestedManufaturers) {
	 this.requestString=requestString;
	 this.itemIDs=itemIds;
	 this.setSuggestedManufaturers(suggestedManufaturers);
 }
 
 /**
  * This writes the order form into an output.txt file ( if not present, it creates one)
  */
 public String writeFile() {
	 try {
		 File file = new File("output.txt");
		 String toWrite;
		  if (!file.exists()) {
			     file.createNewFile();
			  }  
		  fileWrite = new FileWriter(file);
		  buffWrite=new BufferedWriter(fileWrite);
		  if(itemIDs!=null && itemIDs.length>0) {
			  
	      totalCost = Integer.parseInt(itemIDs[0]);
	      
		  toWrite ="Furniture order Form ->\n\n\n";
		  toWrite = toWrite +"Faculty Name:\n";
		  toWrite = toWrite +"Contact:\n";
		  toWrite = toWrite +"Date:\n\n\n";
		 toWrite = toWrite+"Original Requeast: "+requestString;
		 toWrite = toWrite +"\n\n\nItems Ordered:\n";
		 for(int i=1;i<itemIDs.length;i++) {
			if(itemIDs[i]!=null){
			   toWrite = toWrite + "ID: "+itemIDs[i];
			   toWrite = toWrite +"\n";	
			}		  
		}
		toWrite = toWrite +"\n\n";
		toWrite = toWrite + "Total Price: $" +totalCost;
		buffWrite.write(toWrite);	
		  }else {
			 toWrite = "Order cannot be fulfilled based on current inventory."
			 		+ "Suggested manufactures are ";
			 if(suggestedManufaturers.length==1) {
				 toWrite = toWrite + suggestedManufaturers[0];
			 }else if(suggestedManufaturers.length==2){
				 toWrite = toWrite + suggestedManufaturers[0]+" and "+suggestedManufaturers[1];
			 }
			 else {
			 for(int i=0;i<suggestedManufaturers.length-2;i++) {
				 toWrite = toWrite + suggestedManufaturers[i]+", ";
		
			 }
			 toWrite = toWrite + suggestedManufaturers[(suggestedManufaturers.length)-2]+" and ";
			 toWrite = toWrite + suggestedManufaturers[(suggestedManufaturers.length)-1];
		  }	
		  buffWrite.write(toWrite);		 
		  }
		  
		  System.out.println("Order form created Successfully");
		  return toWrite;
		  
		  
	 }
	 
	 catch(IOException ioe){
		 ioe.printStackTrace();
	 }
		finally
		{ 
		   try{
		      if(buffWrite!=null)
			 buffWrite.close();
		   }catch(Exception ex){
		       System.out.println("Error in closing the BufferedWriter"+ex);
		    }
		}
	 return "Something went very wrong";
    }
 
 /**
  * This is the getter for the suggestedManufactures array
  */

public String [] getSuggestedManufaturers() {
	return suggestedManufaturers;
}

/**
 * this is the setter for the array of suggestedManufacturers
 * @return string array of suggestedManufactures
 */
public void setSuggestedManufaturers(String [] suggestedManufaturers) {
	this.suggestedManufaturers = suggestedManufaturers;
}

}