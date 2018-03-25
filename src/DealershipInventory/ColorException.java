/***************************************************************
*** Class Name: ColorException
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Handles exception for invalid color
*** choice
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
package DealershipInventory;


public class ColorException extends Exception {

    /**
     * Creates a new instance of <code>ColorException</code> without detail
     * message.
     */
    public ColorException() {
    }

    /**
     * Constructs an instance of <code>ColorException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ColorException(String msg) {
        super(msg);
    }
}
