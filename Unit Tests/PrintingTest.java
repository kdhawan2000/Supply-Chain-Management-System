package edu.ucalgary.ensf409;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.*;
import edu.ucalgary.ensf409.MySQLHandler;

/**
 * This class handles unit testing of Printing
 * @version 1.0
 * @since 1.0
 * @author Kaitlin Culligan, Arindam Mishra, Kunal Dhawan
 */
//unit tests for project
public class PrintingTest {
  
  //may need to rewrite part of class to do unit tests of this
  //test manufacturers list printout
  /**
   * tests printing of manufacturers list
   */
  @Test
  public void testManufacturers(){
    String [] suggested = {"Office Furnishings", "Chairs R Us", "Furniture Goods", "Fine Office Supplies"};
    String expected = "Order cannot be fulfilled based on current inventory.Suggested manufactures are Office Furnishings, Chairs R Us, Furniture Goods and Fine Office Supplies";
    Printing p = new Printing("Kneeling chair, 1", null, suggested);
    String result = p.writeFile();
    assertEquals(expected,result);
  }
  //test order form
  /**
   * tests printing of order form
   */
@Test
  public void testWorking(){
    String [] ids = {"200", "C8138", "C9890"};
    String expected = "Furniture order Form ->\n\nFaculty Name: \nContact: \nDate: \n\n\nOriginal Request: " +"Mesh chair, 1\n\n\n"+ 
    "Items Ordered:\nID: C8138\nID: C9890\n\n\nTotal Price: $200";
    Printing p = new Printing("Mesh chair, 1", ids, null);
    String result = p.writeFile();
    assertEquals(expected,result);
  }
}