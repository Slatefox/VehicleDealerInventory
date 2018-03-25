/***************************************************************
*** Class Name: Car
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Extends Vehicle class and creates
*** car objects. Contains methods to interact with car object
*** properties.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
package DealershipInventory;

import java.awt.Color;
import java.io.Serializable;

public class Car extends Vehicle implements Serializable
{
    // Holds number of cars
    private static int carCount = 0;
    // Holds passenger limit
    private int passCount;
    // Holds number of doors
    private int doorCount;
    // Holds miles per gallon
    private double mpg;
    

/***************************************************************
*** Method Name: Car
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Constructor to build new car objects
*** with attributes inherited from the vehicle class
*** Method Inputs: int passCount, int doorCount, double mpg,
*** String make, String model, int purchasePrice,
*** int askingPrice, Color color
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/      
    public Car( int passCount, int doorCount, double mpg, int v, String make, String model, int purchasePrice, int askingPrice, Color color )
    {
        // Inherit vehicle class attributes
        super(v, make, model, purchasePrice, askingPrice, color);
        // Set number of passengers
        this.passCount = passCount;
        // Set number of doors
        this.doorCount = doorCount;
        // Set miles per gallon
        this.mpg = mpg;
        // Increment car counter
        carCount++;
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
*** Purpose of the Method: Sets the number of passengers allowed
*** for the vehicle
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
*** Method Name: getDoorcount
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Get the number of doors on the car
*** Method Inputs: N/A
*** Return Value: int doorCount
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/      
    public int getDoorCount()
    {
        // Return number of doors
        return doorCount;
    }
/***************************************************************
*** Method Name: setDoorCount
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Sets the number of doors for the car.
*** Method Inputs: int doorCOunt
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/      
    public void setDoorCount( int doorCount )
    {
        // Set number of doors for car
        this.doorCount = doorCount;
    }
/***************************************************************
*** Method Name: getMpg
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Get the miles per gallon average for
*** the vehicle
*** Method Inputs: N/A
*** Return Value: double mpg
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/      
    public double getMpg()
    {
        // Return miles per gallon
        return mpg;
    }
/***************************************************************
*** Method Name: setMpg
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Sets the miles per gallon average for
*** the car
*** Method Inputs: double mpg
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/      
    public void setMpg( double mpg )
    {
        // Set miles per gallon
        this.mpg = mpg;
    }
/***************************************************************
*** Method Name: remove
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Overriding method to decrement car
*** counter if the vehicle is a car
*** Method Inputs: N/A
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/      
    @Override
    public void remove()
    {
        // Decrement car counter
        carCount--;
    }
/***************************************************************
*** Method Name: printableString
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Overridden method that returns the
*** respective car object attributes
*** Method Inputs: Vehicle car
*** Return Value: String myCar
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/  
    @Override
    public String printableString(Vehicle car)
    {
        // Holds vehicle attributes
        String myCar = "";
        // Cast vehicle car as a car for access to methods
        Car c;
        c = (Car)car;
        // Get all attributes of the car
        myCar = "**Car**"+ '\n'+
                "Vehicle ID: " +c.getVehicleID()+'\n'+
                "Make: " +c.getMake()+ '\n'+
                "Model: " + c.getModel()+ '\n' +
                "Color: " + c.sColor(c.getColor())+ '\n'+
                "Passanger Count: "+ c.getPassCount() + '\n'+
                "Door Count: " + c.getDoorCount() + '\n' +
                "MPG: " +c.getMpg() + '\n' +
                "Purchase Price: $" +c.getPurchasePrice() +'\n'+ 
                "Asking Price: $" +c.getAskingPrice()+'\n';
        // Return printable string of car attributes
        return myCar;
    }

/***************************************************************
*** Method Name: getCarCount
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the number of cars available
*** Method Inputs: N/A
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/  
    public int getCarCount()
    {
        // Returns number of cars
        return carCount;
    }
/***************************************************************
*** Method Name: calculateCommission
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: An overriding method to calculate
*** the commission for the car sold
*** Method Inputs: int custOffer
*** Return Value: int commission
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/  
    @Override
    public int calculateCommission( int custOffer )
    {
        // Holds commission result
        int commission = 0;
        // Calculate the commission
        commission = (int)(custOffer * 0.05);
        // Return commission result
        return commission;
    }
}
