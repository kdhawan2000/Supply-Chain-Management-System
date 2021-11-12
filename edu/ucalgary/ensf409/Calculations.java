package edu.ucalgary.ensf409;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucalgary.ensf409.Furniture;

/**
 * This class handles the calulating of the best option
 * to create the desired furniture piece
 * @version 1.1
 * @since 1.0
 * @author Kaitlin Culligan, Arindam Mishra, Kunal Dhawan
 */
public class Calculations {
    private ArrayList<Integer> costOptions;
    private ArrayList<Furniture> furniture;
    private ArrayList<String[]> idsForEachCostOption;
    private ArrayList<ArrayList<boolean[]>> piecesPerOption;
    /**
     * This is the constructor, it just initializes the local data members
     */
    public Calculations(){
        this.costOptions = new ArrayList<Integer>();
        this.furniture = new ArrayList<Furniture>();
        this.idsForEachCostOption = new ArrayList<String[]>();
        this.piecesPerOption = new ArrayList<ArrayList<boolean[]>>();
    }

    /**
     * this loops through the ArrayList of potential costs
     * and selects the lowest one
     * @return the lowest possible cost
     */
    public int choseBestPrice(){
        //add option that if # of costOptions is 0 it'll return 0
        if(costOptions.size() == 0){
            return 0;
        }
        int bestPrice = this.costOptions.get(0);
        for(int i = 0; i < this.costOptions.size();i++){
            if(this.costOptions.get(i)<bestPrice){
                bestPrice=this.costOptions.get(i);
            }
        }
        return bestPrice;
    }

    /**
     * Pulls the needed info from results, and sends it off to be 
     * used in calculating the best cost option
     * @param results the ResultSet read in MySQL
     * @param furnitureType The desired type of furniture (sub type)
     * @return String array containing the total price, and ids of needed funiture
     */
    public String[] calculatePrices(ResultSet results, String furnitureType) throws SQLException{
        String[] ids = {"0"};
        try{
            while(results.next()){
                if(checkFurnitureType(furnitureType, results.getString("Type"))){
                    ArrayList<String> s = new ArrayList<String>();
                    ResultSetMetaData rsmd = results.getMetaData();
                    for(int i = 1; i < rsmd.getColumnCount()+1; i++){
                        if(results.getString(i).equals("Y") || results.getString(i).equals("N")){
                            s.add(results.getString(i));
                        }
                    }
                    boolean[] availability = makeBooleanArray(toStringArray(s));
                    furniture.add(new Furniture(results.getString("Type"),results.getString("ID"),results.getInt("Price"), availability));
                }
            }
            
            String[] temp = createOptions();
            if(temp[0].equals("0")){
                return ids;
            }
            else{
                ids = temp;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ids;
    }

   /**
    * Compares to furniture types to see if they match
    * @param wantedFurniture desired furniture type
    * @param furnitureToCheck furniture type being checked
    * @return true if types match, false if they don't
    */
    private boolean checkFurnitureType(String wantedFurniture, String furnitureToCheck){
        if(wantedFurniture.equals(furnitureToCheck)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Converts Y and N strings from the ResultTable to boolean values
     * @param available String to convert
     * @return corresponding boolean from string
     */
    private boolean convertPieceAvalabilityToBoolean(String available){
        if(available.equals("Y")){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Converts a String array of Y and N strings to a boolean array
     * @param booleans String array to convert
     * @return converted boolean array
     */
    public boolean[] makeBooleanArray(String[] booleans){
        boolean[] b = new boolean[booleans.length];
        for(int i = 0; i < b.length; i++){
            b[i] = convertPieceAvalabilityToBoolean(booleans[i]);
        }
        return b;
    }

    /**
     * This method loops through all the possible furniture pieces and calculates the possible
     * options to make a piece of furniture, does os by comparing boolean arrays
     * @return String array containing the best cost, and corresponding ids
     */
    public String[] createOptions(){
        //need to add print statements to figure out whats gone wrong
        if(furniture.size()==0){
            String[] error = {"0"};
           return error;
        }
        boolean[] b = createAllTrueArray(furniture.get(0).getPiecesAvailable().length);
       for(int i = 0; i <furniture.size();i++){
           String[] idCombo = {furniture.get(i).getID()};
           boolean[] parts = furniture.get(i).getPiecesAvailable();
           ArrayList<boolean[]> partsUsed = new ArrayList<boolean[]>();
           partsUsed.add(parts);
           for(int j=0; j<furniture.size();j++){
                if(!checkArrayEquivalency(parts, furniture.get(j).getPiecesAvailable()) && 
                !checkArrayEquivalency(parts, b)&& !isInArray(idCombo, furniture.get(j).getID())){
                    parts = addToBooleanArray(parts, furniture.get(j).getPiecesAvailable());
                    idCombo = addToStringArray(idCombo, furniture.get(j).getID());
                    partsUsed.add(furniture.get(j).getPiecesAvailable());
                }
           }
           if(checkArrayEquivalency(parts, b)){
                costOptions.add(calculateCostFromIds(idCombo));
                idsForEachCostOption.add(idCombo);
                piecesPerOption.add(partsUsed);
           }
       }
       for(int i = 0; i < furniture.size(); i++){
         secondCheck(i);
       }
       
       int cost = choseBestPrice();
       if(cost == 0){
           String[] error = {"0"};
           return error;
       }
       String[] idForSelected = new String[idsForEachCostOption.get(costOptions.indexOf(cost)).length+1];
       idForSelected[0] = String.valueOf(cost);
       for(int i = 1; i <idsForEachCostOption.get(costOptions.indexOf(cost)).length+1;i++){
        idForSelected[i] = idsForEachCostOption.get(costOptions.indexOf(cost))[i-1];
       }
       return idForSelected;
       }


    /**
     * adds a String to a String array
     * @param array array to be added to
     * @param toAdd String to add to array
     * @return String array with the added String
     */
    private String[] addToStringArray(String[] array, String toAdd){
        String[] results = new String[array.length+1];
        for(int i = 0; i < array.length; i++){
            results[i] = array[i];
        }
        results[array.length] = toAdd;
        return results;
    }

    /**
     * adds a boolean to a boolean array
     * @param array array to be added to
     * @param toAdd boolean to add
     * @return boolean array with the added boolean
     */
    private boolean[] addToBooleanArray(boolean[] array, boolean[] toAdd){
        boolean[] results = new boolean[array.length];
        for(int i = 0; i < array.length; i++){
            if(array[i]==false && toAdd[i] == true){
                results[i] = toAdd[i];
            }else{
                results[i] = array[i];
            }
        }
        return results;
    }

    /**
     * checks if two boolean arrays are equivalent
     * @param one first array to compare
     * @param two second array to compare
     * @return true if they are the same, false if they aren't
     */
    private boolean checkArrayEquivalency(boolean[] one, boolean[] two){
        for(int i =0; i <one.length; i++){
            if(one[i] != two[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * creates a boolean array of a given length that only
     * holds the value true
     * @param size the size to make the array
     * @return boolean array of given size that only holds the value true
     */
    private boolean[] createAllTrueArray(int size){
      boolean[] a = new boolean[size];
      for(int i = 0; i <size; i++){
          a[i] = true;
      }
      return a;
    }

    /**
     * takes in a String array of ids, loops through ArrayList of furniture items
     * to match the given ids with the ids of the furniture pieces, takes the corresponding
     * furnitures' prices and sums them to get the total price
     * @param ids String array of furniture ids
     * @return the amount the given furniture would cost
     */
    private int calculateCostFromIds(String[] ids){
        int cost = 0;
        for(int i = 0; i < furniture.size(); i++){
            for(int j = 0; j < ids.length; j++){
                if(furniture.get(i).getID().equals(ids[j])){
                    cost += furniture.get(i).getPrice();
                }
            }
        }
        return cost;
    }

    /**
     * converts a String ArrayList to a String array
     * @param s String ArrayList to convert
     * @return converted String Array
     */
    private String[] toStringArray(ArrayList<String> s){
        String[] array = new String[s.size()];
        for(int i = 0; i < s.size();i++){
            array[i] = s.get(i);
        }
        return array;
    }

    public ArrayList<Furniture> getFurnitureArray(){
        return this.furniture;
    }

    /**
     * checks to see if a given String is in a given String array
     * @param array array to check against
     * @param toCheck String to check if in array
     * @return true if String is in array, false if it isn't
     */
    private boolean isInArray(String[] array, String toCheck){
        for(int i =0; i < array.length; i++){
            if(array[i].equals(toCheck)){
                return true;
            }
        }
        return false;
    }

    /**
     * this performs a check of the 
     * @param increase the offset to use in the calculations
     */
    private void secondCheck(int increase){
        boolean[] b = createAllTrueArray(furniture.get(0).getPiecesAvailable().length);
        for(int i = 0; i <furniture.size();i++){
            String[] idCombo = {furniture.get(i).getID()};
            boolean[] parts = furniture.get(i).getPiecesAvailable();
            ArrayList<boolean[]> partsUsed = new ArrayList<boolean[]>();
            partsUsed.add(parts);
            for(int j=i+increase; j<furniture.size();j++){
                if(i+increase < furniture.size()){
                    if(!checkArrayEquivalency(parts, furniture.get(j).getPiecesAvailable()) && 
                    !checkArrayEquivalency(parts, b)&& !isInArray(idCombo, furniture.get(j).getID())){
                        parts = addToBooleanArray(parts, furniture.get(j).getPiecesAvailable());
                        idCombo = addToStringArray(idCombo, furniture.get(j).getID());
                        partsUsed.add(furniture.get(j).getPiecesAvailable());
                    }
                }
            }
            if(checkArrayEquivalency(parts, b)){
                 costOptions.add(calculateCostFromIds(idCombo));
                 idsForEachCostOption.add(idCombo);
                 piecesPerOption.add(partsUsed);
            }
        }
    }
}