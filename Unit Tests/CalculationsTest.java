package edu.ucalgary.ensf409;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.*;
import java.sql.*;

/**
 * This class handles unit testing of Calculations
 * @version 1.0
 * @since 1.0
 * @author Kaitlin Culligan, Arindam Mishra, Kunal Dhawan
 */
//unit tests for project
public class CalculationsTest {
  private final String DBURL = "jdbc:mysql://localhost/inventory";
  private final String USERNAME = "scm";
  private final String PASSWORD = "ensf409";
  private Connection dbConnect;
  private ResultSet results;

  /**
   * initializes db connection
   */
  @Before
  public void setup(){
    try{
      dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

  //need to test when it doesnt work an produces the correct manufacturers list
  //will need one of these tests for each type of furniture

  /**
   * tests the ability of class to recognize when a item can not be made
   * and uses a kneeling chair to do this
   */
  @Test
  public void testManufacturersList(){
    String expected = "0";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM chair");
      String[] result = calculator.calculatePrices(results, "Kneeling");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

  /**
   * tests programs ability to find right cost for a tradional desk
   */
  @Test
  public void testCorrectComboSelectedOne(){
    String expected = "100";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM desk");
      String[] result = calculator.calculatePrices(results, "Traditional");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

   /**
   * tests programs ability to find right cost for a task chair
   */
  @Test
  public void testCorrectComboSelectedTwo(){
    String expected = "150";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM chair");
      String[] result = calculator.calculatePrices(results, "Task");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }
 
 /**
   * tests programs ability to find right cost for a mesh chair
   */
  @Test
  public void testCorrectComboSelectedThree(){
    String expected = "200";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM chair");
      String[] result = calculator.calculatePrices(results, "Mesh");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

   /**
   * tests programs ability to find right cost for a executive chair
   */
  @Test
  public void testCorrectComboSelectedFour(){
    String expected = "400";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM chair");
      String[] result = calculator.calculatePrices(results, "Executive");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

   /**
   * tests programs ability to find right cost for a ergonomic desk
   */
  @Test
  public void testCorrectComboSelectedFive(){
    String expected = "250";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM chair");
      String[] result = calculator.calculatePrices(results, "Ergonomic");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

   /**
   * tests programs ability to find right cost for a adjustable desk
   */
  @Test
  public void testCorrectComboSelectedSix(){
    String expected = "400";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM desk");
      String[] result = calculator.calculatePrices(results, "Adjustable");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

   /**
   * tests programs ability to find right cost for a standing desk
   */
  @Test
  public void testCorrectComboSelectedSeven(){
    String expected = "300";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM desk");
      String[] result = calculator.calculatePrices(results, "Standing");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

   /**
   * tests programs ability to find right cost for a desk lamp
   */
  @Test
  public void testCorrectComboSelectedEight(){
    String expected = "20";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM lamp");
      String[] result = calculator.calculatePrices(results, "Desk");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

   /**
   * tests programs ability to find right cost for a swing arm lamp
   */
  @Test
  public void testCorrectComboSelectedNine(){
    String expected = "30";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM lamp");
      String[] result = calculator.calculatePrices(results, "Swing Arm");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

   /**
   * tests programs ability to find right cost for a study lamp
   */
  @Test
  public void testCorrectComboSelectedTen(){
    String expected = "10";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM lamp");
      String[] result = calculator.calculatePrices(results, "Study");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

   /**
   * tests programs ability to find right cost for a small filing cabinet
   */
  @Test
  public void testCorrectComboSelectedEleven(){
    String expected = "100";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM filing");
      String[] result = calculator.calculatePrices(results, "Small");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

   /**
   * tests programs ability to find right cost for a medium filing cabinet
   */
  @Test
  public void testCorrectComboSelectedTwelve(){
    String expected = "200";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM filing");
      String[] result = calculator.calculatePrices(results, "Medium");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

   /**
   * tests programs ability to find right cost for a large filing cabinet
   */
  @Test
  public void testCorrectComboSelectedThirteen(){
    String expected = "300";
    Calculations calculator = new Calculations();
    try{
      Statement myStmt = dbConnect.createStatement();
      results = myStmt.executeQuery("SELECT * FROM filing");
      String[] result = calculator.calculatePrices(results, "Large");
      myStmt.close();
      assertEquals(expected,result[0]);
  }catch(SQLException e){
      e.printStackTrace();
  }
  }

}
