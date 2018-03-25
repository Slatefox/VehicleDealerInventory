/***************************************************************
*** Class Name: DealershipInventory
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Builds a graphical user interface for
*** a user to add, sell, and / or display a dealership inventory
*** of cars, buses, and motorcycles. The user may also save the
*** inventory to a database or load an inventory to sell
*** vehicles. Selling vehicles will automatically update the
*** database.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
package DealershipInventory;

import javax.swing.JFrame;

public class DealershipInventory {

   
    public static void main(String[] args) 
    {
        // Instantiate gui of type LayoutManager
        LayoutManager gui = new LayoutManager();
        // Set default close operation
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set window size
        gui.setSize(800, 500);
        // Set set fixed size
        gui.setResizable(false);
        // Set visible
        gui.setVisible(true);
        
        
    }
    
}
