/***************************************************************
*** Class Name: InvalidCharacterException
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Handles exception for invalid input
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
package DealershipInventory;

public class InvalidCharacterException extends Exception {

    /**
     * Creates a new instance of <code>InvalidCharacterException</code> without
     * detail message.
     */
    public InvalidCharacterException() {
    }

    /**
     * Constructs an instance of <code>InvalidCharacterException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidCharacterException(String msg) {
        super(msg);
    }
}
