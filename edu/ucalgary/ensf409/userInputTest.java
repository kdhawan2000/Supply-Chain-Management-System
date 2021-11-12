package edu.ucalgary.ensf409;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.*;
import edu.ucalgary.ensf409.userInput;
/**
 * This class handles unit testing of userInput
 * @version 1.0
 * @since 1.0
 * @author Kaitlin Culligan, Arindam Mishra, Kunal Dhawan
 */
//unit tests for project
public class userInputTest {
  
    /**
     * tests userInput's response to incorrect type
     */
    @Test
    public void testIncorrectTypeInput(){	
    	boolean expected = false;
        userInput incorrectType = new userInput("chair","web","1");
        assertEquals(expected,  incorrectType.checkForValidType(incorrectType));    

    }

    /**
     * tests userInput's response to incorrect category
     */
    @Test
    public void testIncorrectCategoryInput(){
    	boolean expected= false;
        userInput incorrectCategory = new userInput("table","wood","1");
        assertEquals(expected, incorrectCategory.checkForValidCategory(incorrectCategory));  
        //say a table
    }

    /**
     * tests userInput's response to checking that the correct args are numeric
     */
    @Test
    public void testForIsNumericMethod(){
    	boolean expected = true;
    	userInput random = new userInput("chair","mesh","1");	
        String[] strArray= {"10","A1","A2"};    
        assertEquals(expected, userInput.isNumeric(strArray[0]));  

    }


}