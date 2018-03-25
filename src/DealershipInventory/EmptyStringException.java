/***************************************************************
*** Class Name: EmptyStringException
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Handles exception for empty string
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
package DealershipInventory;

public class EmptyStringException extends Exception {

    /**
     * Creates a new instance of <code>EmptyStringException</code> without
     * detail message.
     */
    public EmptyStringException() {
    }

    /**
     * Constructs an instance of <code>EmptyStringException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmptyStringException(String msg) {
        super(msg);
    }
}
