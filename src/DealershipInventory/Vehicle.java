/***************************************************************
*** Class Name: Vehicle
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Creates a vehicle object and holds
*** vehicle properties. Contains abstract methods that are
*** overwritten by correct objects
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
package DealershipInventory;

import java.awt.Color;
import java.io.Serializable;

public class Vehicle implements Serializable
{
    // Holds total amount of vehicles
    private static int vehCount = 0;
    // Hold vehicle id
    private int vid;
    // Holds make
    private String make;
    // Holds model
    private String model;
    // Holds the color
    private Color color;
    // Holds purchase price
    private int purchasePrice;
    // Holds asking price
    private int askingPrice;

/***************************************************************
*** Method Name: Vehicle
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Constructor to set vehicle
*** properties
*** Method Inputs: String make, String model, int purchasePrice,
*** int askingPrice, Color color
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public Vehicle( int v, String make, String model, int purchasePrice, int askingPrice, Color color )
    {
        vid = v;
        // Set make
        this.make = make;
        // Set model
        this.model = model;
        // Set purchase price
        this.purchasePrice = purchasePrice;
        // Set asking price
        this.askingPrice = askingPrice;
        // Set the color
        this.color = color;
        // Increment vehicle counter
        vehCount++;
    }
/***************************************************************
*** Method Name: getMake
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the make of the vehicle
*** Method Inputs: N/A
*** Return Value: String make
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public String getMake()
    {
        // Return make
        return make;
    }
/***************************************************************
*** Method Name: setMake 
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Sets the make of the vehicle
*** Method Inputs: String make
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public void setMake( String make )
    {
        // Set the make
        this.make = make;
    }
/***************************************************************
*** Method Name: getModel 
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the model of the vehicle
*** Method Inputs: N/A
*** Return Value: String model
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public String getModel()
    {
        // Return the model
        return model;
    }
/***************************************************************
*** Method Name: setModel
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Sets the model of the vehicle
*** Method Inputs: String model
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public void setModel( String model )
    {
        // Set the model
        this.model = model;
    }
/***************************************************************
*** Method Name: getPurchasePrice 
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the purchase price of the
*** vehicle
*** Method Inputs: N/A
*** Return Value: int purchasePrice
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public int getPurchasePrice()
    {
        // Return purchase price
        return purchasePrice;
    }
/***************************************************************
*** Method Name: setPurchasePrice 
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Sets the purchase price of the
*** vehicle
*** Method Inputs: int purchasePric
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public void setPurchasePrice( int purchasePrice )
    {
        // Set the purchase price
        this.purchasePrice = purchasePrice;
    }
/***************************************************************
*** Method Name: getAskingPrice
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the asking price of the vehicle
*** Method Inputs: N/A
*** Return Value: int askingPrice
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/   
    public int getAskingPrice()
    {
        // Return vehicle asking price
        return askingPrice;
    }
/***************************************************************
*** Method Name: setAskingPrice
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Sets the asking price of the vehicle
*** Method Inputs: int askingPrice
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public void setAskingPrice( int askingPrice )
    {
        // Set asking price
        this.askingPrice = askingPrice;
    }
/***************************************************************
*** Method Name: deal
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Checks if the customers offer is
*** acceptable for a sale
*** Method Inputs: int custOffer
*** Return Value: boolean deal
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public boolean deal( int custOffer )
    {
        // Set deal to true
        boolean deal = true;
        // Determine if customer offer is equal to the asking price
        if( custOffer < askingPrice )
        {
            // Set deal to false if not equal
            deal = false;
        }
        // Return deal
        return deal;
    }

/***************************************************************
*** Method Name: remove
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Overridden method to decrement the
*** count of the respective vehicle
*** Method Inputs: N/A
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public void remove()
    {
        vehCount--;
    }

/***************************************************************
*** Method Name: calculateCommission
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Overridden method that will calculate
*** the commission of the respective vehicle sale
*** Method Inputs: int custOffer
*** Return Value: int commission
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/ 
    public int calculateCommission( int custOffer )
    {
        // Set commission to 0 for no reason
        int commission = 0;
        // Return commission
        return commission;
    }
/***************************************************************
*** Method Name: printableString
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Overridden method that prints the
*** respective vehicle information
*** Method Inputs: Vehicle veh
*** Return Value: String temp
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    String printableString(Vehicle veh)
    {
       // Temporary string
       String temp = ""; 
       // Return temp
       return temp;
    }
/***************************************************************
*** Method Name: getCount 
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the total vehicle count
*** Method Inputs: N/A
*** Return Value: int vehCount
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public int getCount()
    {
        // Return vehicle count
        return vehCount;
    }
/***************************************************************
*** Method Name: setColor 
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Sets the color of the vehicle
*** Method Inputs: N/A
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public void setColor( Color c )
    {
        // Set the color
        color = c;
    }
/***************************************************************
*** Method Name: getColor 
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the color of the vehicle
*** Method Inputs: N/A
*** Return Value: Color color
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public Color getColor()
    {
        // Return color
        return color;
    }
/***************************************************************
*** Method Name: setVehicleID
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Sets the vehicle ID
*** Method Inputs: int n
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/ 
    public void setVehicleID( int n )
    {
        // Set vehicle id
        vid = n;
    }
/***************************************************************
*** Method Name: getVehicleID
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Returns the vehicle id
*** Method Inputs: N/A
*** Return Value: int vid
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public int getVehicleID()
    {
        // Return vehicle id
        return vid;
    }
/***************************************************************
*** Method Name: sColor
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the Color class color for the
*** vehicle and returns a printable string
*** Method Inputs: Color carColor
*** Return Value: String userColor
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public String sColor( Color carColor )
    {
        // Holds printable string of the color
        String userColor;
        // Get the RGB integer equivalent for the color
        userColor = Integer.toString(carColor.getRGB());
        // Find the color
        switch( userColor )
        {
            case "-65536": userColor = "RED"; // If red
                        break;
            case "-16776961": userColor = "BLUE"; // If blue
                        break;
            case "-16711936": userColor = "GREEN"; // If green
                        break;
            case "-16777216": userColor = "BLACK"; // If black
                        break;
            case "-256": userColor = "YELLOW"; // If yellow
                        break;
            case "-14336": userColor = "ORANGE"; // If orange
                        break;
            case "-1": userColor = "WHITE"; // If white
                        break;
            case "-20561": userColor = "PINK"; // If pink
                        break;
            default: userColor = "OTHER"; // If other
                        break;
        }
        // Return userColor printable string of color chosen
        return userColor;
    }
/***************************************************************
*** Method Name: findColor
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Finds the user color of the class
*** Color
*** Method Inputs: String carColor
*** Return Value: Color userColor
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public Color findColor( String carColor )
    {
        // Instantiate userColor of type Color
        Color userColor;
        // Set user input color to lowercase
        carColor = carColor.toLowerCase();
        // Switch statement to find the valid color
        switch( carColor )
        {
            // Set userColor to red
            case "red": userColor = Color.RED;
                break;
            // Set userColor to green
            case "green": userColor = Color.GREEN;
                break;
            // Set userColor to blue
            case "blue": userColor = Color.BLUE;
                break;
            // Set userColor to black
            case "black": userColor = Color.BLACK;
                break;
            // Set userColor to yellow
            case "yellow": userColor = Color.YELLOW;
                break;
            // Set userColor to orange
            case "orange": userColor = Color.ORANGE;
                break;
            // Set userColor to white
            case "white": userColor = Color.WHITE;
                break;
            // Set userColor to pink
            case "pink": userColor = Color.PINK;
                break;
            // Set userColor to gray as other
            case "other": userColor = Color.gray;
                break;
            // Set userColor to darkGray as default
            default: userColor = Color.darkGray;
                break;
        }
        // Return userColor
        return userColor;
    }
}
