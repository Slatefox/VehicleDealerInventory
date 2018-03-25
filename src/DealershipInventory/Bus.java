/***************************************************************
*** Class Name: Bus
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Extends Vehicle class and creates
*** bus objects. Contains methods to interact with bus object
*** properties.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
package DealershipInventory;

import java.awt.Color;
import java.io.Serializable;

public class Bus extends Vehicle implements Serializable
{
    // Holds number of buses available
    private static int busCount = 0;
    // Holds length of the bus
    private int length;
    // Holds number of passengers
    private int passCount;
    // Holds if bus is diesel or not
    private boolean diesel;
    

/***************************************************************
*** Method Name: Bus
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Constructor that builds bus objects
*** inheriting vehicle attributes
*** Method Inputs: int length, int passCount, boolean diesel,
*** String make, String model, int purchasePrice,
*** int askingPrice, Color color
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public Bus( int length, int passCount, boolean diesel, int v, String make, String model, int purchasePrice, int askingPrice, Color color )
    {
        // Inherit attributes from vehicle class
        super(v, make, model, purchasePrice, askingPrice, color);
        // Set bus length
        this.length = length;
        // Set number of passengers
        this.passCount = passCount;
        // Set diesel
        this.diesel = diesel;
        // Increment bus counter
        busCount++;
    }
/***************************************************************
*** Method Name: getLength
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the length of the bus
*** Method Inputs: N/A
*** Return Value: int length
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public int getLength()
    {
        // Return bus length
        return length;
    }
/***************************************************************
*** Method Name: setLength
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Sets the length of the bus
*** Method Inputs: int length
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public void setLength( int length )
    {
        // Set bus length
        this.length = length;
    }
/***************************************************************
*** Method Name: getPassCount
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the number of passengers
*** Method Inputs: N/A
*** Return Value: int passCount
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/ 
    public int getPassCount()
    {
        // Return number of passengers
        return passCount;
    }
/***************************************************************
*** Method Name: setPassCount
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Sets number of passenger for the bus
*** Method Inputs: int passCount
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public void setPassCount( int passCount )
    {
        // Set number of passengers
        this.passCount = passCount;
    }
/***************************************************************
*** Method Name: getDiesel
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the diesel choice
*** Method Inputs: N/A
*** Return Value: boolean diesel
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/ 
    public boolean getDiesel()
    {
        // Return diesel choice
        return diesel;
    }
/***************************************************************
*** Method Name: setDiesel
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Sets the diesel choice
*** Method Inputs: boolean diesel
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public void setDiesel( boolean diesel )
    {
        // Set diesel choice
        this.diesel = diesel;
    }
/***************************************************************
*** Method Name: remove
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Overriding method to decrement the
*** number of buses available
*** Method Inputs: N/A
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    @Override
    public void remove()
    {
        // Decremenr number of buses
        busCount--;
    }

/***************************************************************
*** Method Name: printableString
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Overriding method to return a
*** printable string containing attributes of the bus
*** Method Inputs: Vehicle bus
*** Return Value: String myBus
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/ 
    @Override
    public String printableString(Vehicle bus)
    {
        // Holds the bus attributes
        String myBus = "";
        // Holds if diesel or not
        String diesel = "";
        // Cast bus of type bus to access methods
        Bus b;
        b = (Bus)bus;
        // Set diesel
        if( b.getDiesel())
        {
            // Yes diesel
            diesel = "Yes";
        }
        else
        {   
            // Not diesel
            diesel = "No";
        }
        // Get bus attributes and store in myBus string
        myBus = "**Bus**"+ '\n'+
                "Vehicle ID: " +b.getVehicleID()+'\n'+
                "Make: " + b.getMake()+ '\n'+
                "Model: "+ b.getModel()+ '\n'+
                "Color: " + b.sColor(b.getColor())+ '\n'+
                "Length: "+ b.getLength()+ '\n'+
                "Passenger Count: "+ b.getPassCount()+ '\n'+
                "Diesel: "+diesel+ '\n'+
                "Purchase Price: $"+ b.getPurchasePrice()+'\n'+
                "Asking Price: $"+ b.getAskingPrice()+'\n';
        // Return printable string myBus
        return myBus;
    }
/***************************************************************
*** Method Name: getBusCount
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the number of buses available
*** Method Inputs: N/A
*** Return Value: int busCount
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public int getBusCount()
    {
        // Return number of buses
        return busCount;
    }
/***************************************************************
*** Method Name: calculateCommission
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Calculate the commission earned on
*** the bus sold
*** Method Inputs: int custOffer
*** Return Value: int commission
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    @Override
    public int calculateCommission( int custOffer )
    {
        // Holds commission calculation
        int commission = 0;
        // Calculate commission
        commission = (int)(custOffer * 0.1);
        // Return commssion result
        return commission;
    }
}
