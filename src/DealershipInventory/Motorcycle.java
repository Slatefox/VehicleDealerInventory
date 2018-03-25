/***************************************************************
*** Class Name: Motorcycle
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Extends Vehicle class and creates
*** motorcycle objects. Contains methods to interact with 
*** motorcycle object properties.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
package DealershipInventory;

import java.awt.Color;
import java.io.Serializable;

public class Motorcycle extends Vehicle implements Serializable
{
    // Holds number of motorcycles available
    private static int motorcycleCount = 0;
    // Holds motorcycle weight
    private int weight;
    // Holds motorcycle type
    private String type;
    // Holds engine size
    private int engineSize;
    

/***************************************************************
*** Method Name: Bus
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Constructor to build new motorcycle
*** objects inheriting attributes from the vehicle class
*** Method Inputs: int weight, String type, int engineSize,
*** String make, String model, int purchasePrice,
*** int askingPrice, Color color
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public Motorcycle( int weight, String type, int engineSize, int v, String make, String model, int purchasePrice, int askingPrice, Color color )
    {
        // Inherit attributes from vehicle class
        super(v, make, model, purchasePrice, askingPrice, color);
        // Set the weight
        this.weight = weight;
        // Set the type
        this.type = type;
        // Set the engine size
        this.engineSize = engineSize;
        // Increment motorcycle counter
        motorcycleCount++;
        
    }
/***************************************************************
*** Method Name: getWeight
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Get the motorcycle weight
*** Method Inputs: N/A
*** Return Value: int weight
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public int getWeight()
    {
        // Return motorcycle weight
        return weight;
    }
/***************************************************************
*** Method Name: setWeight
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Set the motorcycle weight
*** Method Inputs: int weight
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public void setWeight( int weight )
    {
        // Set motorcycle weight
        this.weight = weight;
    }
/***************************************************************
*** Method Name: getType
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Get the motorcycle type
*** Method Inputs: N/A
*** Return Value: String type
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public String getType()
    {
        // Return motorcycle type
        return type;
    }
/***************************************************************
*** Method Name: setType
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Set the motorcycle type
*** Method Inputs: String type
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public void setType( String type )
    {
        // Set the motorcycle type
        this.type = type;
    }
/***************************************************************
*** Method Name: getEngineSize
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Get the motorcycle engine size
*** Method Inputs: N/A
*** Return Value: int engineSize
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public int getEngineSize()
    {
        // Return int engineSize
        return engineSize;
    }
/***************************************************************
*** Method Name: setEngineSize
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Set the engine size of the motorcycle
*** Method Inputs: int engineSize
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public void setEngineSize( int engineSize )
    {
        // Set engine size of the motorcycle
        this.engineSize = engineSize;
    }
/***************************************************************
*** Method Name: remove
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Overriding method to decrement the
*** number of motorcycles available
*** Method Inputs: N/A
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
    @Override
    public void remove()
    {
        // Decrement number of vehicles
        motorcycleCount--;
    }

/***************************************************************
*** Method Name: printableString
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Overriding method to return a
*** printable string of attributes for the motorcycle
*** Method Inputs: Vehicle bike
*** Return Value: String myBike
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
    @Override
    public String printableString(Vehicle bike)
    {
        // Holds string of motorcycle attributes
        String myBike = "";
        // Cast bike of type motorcycle to access methods
        Motorcycle m;
        m = (Motorcycle)bike;
        // Get attributes and put into myBike
        myBike = "**Motorcycle**"+ '\n'+
                 "Vehicle ID: " +m.getVehicleID()+'\n'+
                 "Make: "+ m.getMake()+ '\n'+
                 "Model: "+ m.getModel()+ '\n'+
                 "Color: " + m.sColor(m.getColor())+ '\n'+
                 "Type: "+ m.getType()+ '\n'+
                 "Engine Size: "+ m.getEngineSize()+ '\n'+
                 "Weight: "+ m.getWeight()+'\n'+
                 "Purchase Price: $"+ m.getPurchasePrice()+'\n'+
                 "Asking Price: $"+m.getAskingPrice()+'\n';
        // Return printable string myBike
        return myBike;
    }
/***************************************************************
*** Method Name: getMotorcycleCount
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Gets the number of motorcycles
*** available
*** Method Inputs: N/A
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public int getMotorcycleCount()
    {
        // Return number of motorcycles
        return motorcycleCount;
    }
/***************************************************************
*** Method Name: calculateCommission
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Overriding method that calculates
*** the commission made on the motorcycle based on customer
*** offer
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
        // Calculate commission
        commission = (int)(custOffer * 0.02);
        // Return commission result
        return commission;
    }
}
