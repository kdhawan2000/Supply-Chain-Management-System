package edu.ucalgary.ensf409;
/**
 * This class handles the storage of all the possible furniture
 * @version 1.0
 * @since 1.0
 * @author Kaitlin Culligan, Arindam Mishra, Kunal Dhawan
 */
public class Furniture {
    private String type;
    private boolean[] piecesAvailable;
    private String ID;
    private int price;

    /**
     * Constructor
     * @param type the furniture sub type
     * @param ID the furniture's id
     * @param price the price of the furniture
     * @param piecesAvailable a booolean array of the usable pieces
     */
    public Furniture(String type, String ID, int price, boolean[] piecesAvailable){
        this.type = type;
        this.ID = ID;
        this.price = price;
        this.piecesAvailable = piecesAvailable;
    }

    /**
     * getter for sub type
     * @return furniture's sub type
     */
    public String getType(){
        return this.type;
    }

    /**
     * getter for the array of usable pieces
     * @return boolean array of usable pieces
     */
    public boolean[] getPiecesAvailable(){
        return this.piecesAvailable;
    }

    /**
     * getter for furniture's id
     * @return furniture's id
     */
    public String getID(){
        return this.ID;
    }

    /**
     * getter for furniture's price
     * @return the furniture's price
     */
    public int getPrice(){
        return this.price;
    }
    //this class might make it easier to keep track of what the table contains
    //and help make the calculations less of a nightmare
}