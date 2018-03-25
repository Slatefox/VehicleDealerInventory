/***************************************************************
*** Class Name: ErrorExceptions
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Validates user input and throws an
*** exception if invalid input.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
package DealershipInventory;

import java.awt.Color;
import java.util.Objects;


public class ErrorExceptions 
{
/***************************************************************
*** Method Name: EmptyStringValidation
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Checks for empty string input from
*** the user.
*** Method Inputs: String input
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
    public void EmptyStringValidation ( String input ) throws EmptyStringException
    {
        // If string is empty
        if( input.length() == 0 )
            {
                // Instantiate str of type EmptyString
                EmptyStringException str = new EmptyStringException();
                // Throw EmptyString exception
                throw str;
            }
    }
/***************************************************************
*** Method Name: FuelTypeValidation
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Validates users input for fuel type.
*** Method Inputs: String input
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
    public void FuelTypeValidation ( String input ) throws InvalidCharacterException
    {
        // If fuel choice is not Y or N
        if( !(Objects.equals(input, new String("Y")) || Objects.equals(input, new String("N"))) )
        {
            // Instantiate ice of type InvalidCharacterException
            InvalidCharacterException ice = new InvalidCharacterException();
            // Throw exception
            throw ice;
        }
    }
/***************************************************************
*** Method Name: ColorValidation
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Validates user input for vehicle
*** color.
*** Method Inputs: Color color
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    

    public void ColorValidation( Color color ) throws ColorException
    {
        // If color is the default which is not a valid choice
        if( color == Color.darkGray )
        {
            // Instantiate ce of type ColorException
            ColorException ce = new ColorException();
            // Throw ColorException
            throw ce;
        }
    }
}
