/***************************************************************
*** Class Name: LayoutManager
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

    

public class LayoutManager extends JFrame implements Serializable
{
    // Database connection string
    static final String DATABASE_URL = "jdbc:sqlserver://CTASV20r2drw:1433;databaseName= al029;user=al029;password=CISK2016al;instanceName=mssqlserver2012";

    // Create inventory array to hold vehicle objects
    private Vehicle[] inventory = new Vehicle[500];
    // Index counter
    private static int index = 0;
    // Instantiate panel1 of type Jpanel
    private JPanel panel1 = new JPanel();
    // Instantiate panel2 of type Jpanel
    private JPanel panel2 = new JPanel();
    // Instantiate panel3 of type Jpanel
    private JPanel panel3 = new JPanel();
    // Instantiate buttonPanel of type Jpanel
    private JPanel buttonPanel = new JPanel();
    // Instantiate textArea of type JTextArea
    private JTextArea textArea = new JTextArea();
    // Instantiate new button group
    private ButtonGroup buttonsGrp;
    // Instantiate new radio button group
    private ButtonGroup radButtonsGrp;
    // Instatiate new radio button for car
    private JRadioButton radBtnCar = new JRadioButton("Car");
    // Instatiate new radio button for bus
    private JRadioButton radBtnBus = new JRadioButton("Bus");
    // Instatiate new radio button for motorcycle
    private JRadioButton radBtnBike = new JRadioButton("Motorcycle");
    // Instantiate new button for Previous
    private JButton prevVeh = new JButton("Previous");
    // Instantiate new button for display inventory
    private JButton displayInv = new JButton("Display Inventory");
    // Instantiate new button for Next
    private JButton nextVeh = new JButton("Next");
    // Instantiate new button for Add vehicle
    private JButton addVeh = new JButton("Add Vehicle");
    // Instantiate new button for Offer
    private JButton btnCustOffer = new JButton("Offer");
    // Instantiate new button for display vehicles
    private JButton displayVehicles = new JButton("Display Vehicles");
    // Instantiate new button for saving inventory to file
    private JButton saveInventory = new JButton("Save To Database");
    // Instantiate new button for opening a file to the inventory
    private JButton openInventory = new JButton("Load From Database");
    // Instantiate new button for Reset
    private JButton resetBtn = new JButton("Reset");
    // Instantiate new button for Close
    private JButton closeBtn = new JButton("Close");
    // Remove current vehicle
    private JButton deleteVeh = new JButton("Delete Current Vehicle");
    // Vehicle ID text field
    private JTextField vehID = new JTextField(15);
    // Instantiate new textfield for make
    private JTextField textMake = new JTextField(15);
    // Instantiate new textfield for model
    private JTextField textModel = new JTextField(15);
    // Instantiate new textfield for color
    private JTextField textColor = new JTextField(15);
    // Instantiate new textfield for engine size / passenger count 
    private JTextField textEngPassCount = new JTextField(15);
    // Instantiate new textfield for motorcycle weight / number of doors / bus length
    private JTextField textWtDoorLng = new JTextField(15);
    // Instantiate new textfield for average mpg / motorcycle type / bus fuel type
    private JTextField textMpgTypeDis = new JTextField(15);
    // Instantiate new textfield for purchase price
    private JTextField textPurchasePrice = new JTextField(15);
    // Instantiate new textfield for asking price
    private JTextField textAskingPrice = new JTextField(15);
    // Instantiate new textfield for customer offer
    private JTextField textCustOffer = new JTextField(15);
    // Veh ID label
    private JLabel vehIdL = new JLabel("Vehicle ID");
    // Instantiate new label for make
    private JLabel lblmake = new JLabel("Make");
    // Instantiate new label for model
    private JLabel lblmodel = new JLabel("Model");
    // Instantiate new label for Color
    private JLabel lblcolor = new JLabel("<html>Color (Red, Green, Blue, Black, Yellow, Orange, White, Pink, Other)</html>");
    // Instantiate new label for engine size / passenger limit
    private JLabel lblEngPassCnt = new JLabel("Engine Size/Passenger Limit");
    // Instantiate new label for motorcycle weight / number of doors / bus length
    private JLabel lblWtDrCntLgth = new JLabel("Weight/Doors/Length");
    // Instantiate new label for average mpg / motorcycle type / bus fuel type
    private JLabel lblMpgTypeDis = new JLabel("Mpg/Type/Diesel");
    // Instantiate new label for purchase price
    private JLabel lblPurchasePrice = new JLabel("Purchase Price");
    // Instantiate new label for asking price
    private JLabel lblAskingPrice = new JLabel("Asking Price");
    // Instantiate new label for customer offer
    private JLabel lblCustOffer = new JLabel("Customer Offer");
    // Instantiate new label for successful add
    private JLabel lblSelect = new JLabel("**Select a Vehicle**");
    // Instantiate new label for total vehicles
    private JLabel totalVeh = new JLabel("Total Vehicles: 0");
    // Instantiate new label for number of cars
    private JLabel numCars = new JLabel("Cars: 0");
    // Instantiate new label for number of buses
    private JLabel numBuses = new JLabel("Buses: 0");
    // Instantiate new label for number of motorcycles
    private JLabel numBikes = new JLabel("Motorcycles: 0");
    // Instantiate blackline of type border for borders
    private Border blackline = BorderFactory.createLineBorder(Color.black);
    // Instantiate empty radio button border
    private Border radButtonBorder = BorderFactory.createEmptyBorder();
    // Instantiate titled border for textfield section
    private TitledBorder textFieldTitle;
    // Instantiate titled border for radio buttons section
    private TitledBorder radButtonTitle;
    // Set color to dark gray as default
    private Color color = Color.darkGray;
    // Hold vehicle ID
    private int vid = 0;
    // Hold string vid
    private String sVid = "";
    // Holds make
    private String make = "";
    // Holds model
    private String model = "";
    // Holds string vehicle color
    private String vehColor = "";
    // Holds bus fuel type
    private String fuel = "";
    // Holds motorcycle type
    private String bikeType = "";
    // Holds passenger limit
    private int passCount = 0;
    // Holds number of doors
    private int doorCount = 0;
    // Holds purchase price
    private int purchasePrice = 0;
    // Holds asking price
    private int askingPrice = 0;
    // Holds bus length
    private int length = 0;
    // Holds motorcycle engine size
    private int engineSize = 0;
    // Holds motorcycle weight
    private int bikeWeight = 0;
    // Holds customer offer
    private int custOffer = 0;
    // Holds commission made
    private int commission = 0;
    // Holds average car mpg
    private double mpg = 0;
    // Holds boolean fuel option
    private boolean diesel;
    // Status check
    private boolean st;
/***************************************************************
*** Method Name: LayoutManager (constructor)
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Builds the LayoutManager graphical
*** user interface.
*** Method Inputs: N/A
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
    public LayoutManager()
    {
        // Inherit from JFrame and set window title
        super("Dealer Inventory");
        // Create a new border layout
        setLayout( new BorderLayout());
        // Set panel1 for left side of the window
        panel1.setLayout( new BoxLayout(panel1, BoxLayout.Y_AXIS) );
        // Set panel1 size
        panel1.setPreferredSize( new Dimension( 300, 400));
        // Set panel 2 for middle of the window
        panel2.setLayout( new BoxLayout(panel2, BoxLayout.Y_AXIS) );
        // Set panel2 size
        panel2.setPreferredSize( new Dimension( 200, 400 ));
        // Set panel3 to right side of the window
        panel3.setLayout( new BoxLayout(panel3, BoxLayout.Y_AXIS));
        // Set panel3 size
        panel3.setPreferredSize( new Dimension( 300, 400 ));
        // Create a panel for buttons for alignment
        buttonPanel.setLayout( new FlowLayout());
        // Display empty inventory message in text area
        textArea.setText("**Inventory Empty**");
        // Set text area editable to false
        textArea.setEditable(false);
        // Add text area to panel1
        panel1.add(textArea);
        // Create new button group
        buttonsGrp = new ButtonGroup();
        // Add previous vehicle button
        buttonsGrp.add(prevVeh);
        // Add display inventory button
        buttonsGrp.add(displayInv);
        // Add next vehicle button
        buttonsGrp.add(nextVeh);
        // Create a box for alignment
        Box buttonBox = Box.createHorizontalBox();
        // Add previous button
        buttonBox.add(prevVeh);
        // Add display inventory button
        buttonBox.add(displayInv);
        // Add next vehicle button
        buttonBox.add(nextVeh);
        // Align total vehicles label
        totalVeh.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Align number of cars label
        numCars.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Align number of buses label
        numBuses.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Align number of bikes label
        numBikes.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Align display vehicles button
        displayVehicles.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Create radio buttons group
        radButtonsGrp = new ButtonGroup();
        // Add car radio button
        radButtonsGrp.add(radBtnCar);
        // Add bus radio button
        radButtonsGrp.add(radBtnBus);
        // Add motorcycle radio button
        radButtonsGrp.add(radBtnBike);
        // Create box for radio buttons
        Box radBox = Box.createVerticalBox();
        // Add car radio button
        radBox.add(radBtnCar);
        // Add bus radio button
        radBox.add(radBtnBus);
        // Add motorcycle radio button
        radBox.add(radBtnBike);
        // Add add vehicle button
        radBox.add(addVeh);
        // Create a titled border
        radButtonTitle = BorderFactory.createTitledBorder(radButtonBorder, "Vehicles");
        // Set the border
        radBox.setBorder(radButtonTitle);
        // Align the box
        radBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Align customer offer label
        lblCustOffer.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Align customer offer textfield
        textCustOffer.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Set textfield size
        textCustOffer.setMaximumSize(textCustOffer.getPreferredSize());
        // Align customer offer button
        btnCustOffer.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Align remove button
        deleteVeh.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Align the save inventory button centered
        saveInventory.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Align the open inventory button centered
        openInventory.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Create vehicle info box for textfields
        Box info = Box.createVerticalBox();
        // Add vehicle id label
        info.add(vehIdL);
        // Add vehicle id textfield
        info.add(vehID);
        // Add make label
        info.add(lblmake);
        // Add make textfield
        info.add(textMake);
        // Add model label
        info.add(lblmodel);
        // Add model textfield
        info.add(textModel);
        // Add color label
        info.add(lblcolor);
        // Add color textfield
        info.add(textColor);
        // Add engine size / passenger count label
        info.add(lblEngPassCnt);
        // Add engine size / passenger count textfield
        info.add(textEngPassCount);
        // Add motorcycle weight / door count / bus length label
        info.add(lblWtDrCntLgth);
        // Add motorcycle weight / door count / bus length textfield
        info.add(textWtDoorLng);
        // Add average MPG / motorcycle type / bus fuel type label
        info.add(lblMpgTypeDis);
        // Add average MPG / motorcycle type / bus fuel type textfield
        info.add(textMpgTypeDis);
        // Add purchase price label
        info.add(lblPurchasePrice);
        // Add purchase price textfield
        info.add(textPurchasePrice);
        // Add asking price label
        info.add(lblAskingPrice);
        // Add asking price textfield
        info.add(textAskingPrice);
        // Create titled border
        textFieldTitle = BorderFactory.createTitledBorder(blackline, "Vehicle Info");
        // Add border
        info.setBorder(textFieldTitle);
        // Add button box to panel 1
        panel1.add(buttonBox);
        // Add total vehicle label to panel 1
        panel1.add(totalVeh);
        // Add number of cars label to panel 1
        panel1.add(numCars);
        // Add bumber of buses label to panel 1
        panel1.add(numBuses);
        // Add number of motorcycles label to panel 1
        panel1.add(numBikes);
        // Add display vehicles button to panel 1
        panel1.add(displayVehicles);
        // create spacing from the top of the window
        panel2.add(Box.createRigidArea(new Dimension(0, 30)));
        // Align label select
        lblSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add added label to panel2
        panel2.add(lblSelect);
        // Create space between label and radio button box
        panel2.add(Box.createRigidArea(new Dimension(0, 5)));
        // Add radio button box to panel 2
        panel2.add(radBox);
        // Create spacing from radio button box
        panel2.add(Box.createRigidArea(new Dimension(0, 20)));
        // Add customer offer label to panel 2
        panel2.add(lblCustOffer);
        // Create spacing between above customer offer textfield
        panel2.add(Box.createRigidArea(new Dimension(0, 10)));
        // Disable textfield
        textCustOffer.setEnabled(false);
        // Add customer offer textfield
        panel2.add(textCustOffer);
        // Create spacing above customer offer button
        panel2.add(Box.createRigidArea(new Dimension(0, 10)));
        // Disable offer button
        btnCustOffer.setEnabled(false);
        // Add customer offer button to panel2
        panel2.add(btnCustOffer);
        // Create spacing above customer save inventory button
        panel2.add(Box.createRigidArea(new Dimension(0, 10)));
        // Add remove vehicle button
        panel2.add(deleteVeh);
        // Add delete vehicle tooltip
        deleteVeh.setToolTipText("Deletes current vehicle from inventory");
        // Disable delete button for empty inv
        deleteVeh.setEnabled(false);
        // Empty box for spacing
        panel2.add(Box.createRigidArea(new Dimension(0, 10)));
        // Add save inventory button to panel2
        panel2.add(saveInventory);
        // Create spacing above customer open inventory button
        panel2.add(Box.createRigidArea(new Dimension(0, 10)));
        // Add open inventory button to panel2
        panel2.add(openInventory);
        // Add vehicle info box to panel 3
        panel3.add(info);
        // Create spacing above reset and close button
        panel3.add(Box.createRigidArea(new Dimension(0, 5)));
        // Add reset button to button panel
        buttonPanel.add(resetBtn);
        // Create space between reset and close button
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        // Add close button to button panel
        buttonPanel.add(closeBtn);
        // Add button panel to panel3
        panel3.add(buttonPanel);
        
        // Set all textfields and vehicle add button to false
        vehID.setEnabled(false);
        textMake.setEnabled(false);
        textModel.setEnabled(false);
        textColor.setEnabled(false);
        textEngPassCount.setEnabled(false);
        textWtDoorLng.setEnabled(false);
        textMpgTypeDis.setEnabled(false);
        textPurchasePrice.setEnabled(false);
        textAskingPrice.setEnabled(false);
        addVeh.setEnabled(false);
        
        // Add panel 1 to left side of window
        add(panel1, BorderLayout.WEST);
        // Add panel 2 to center of the window
        add(panel2, BorderLayout.CENTER);
        // Add panel 3 to right side of the window
        add(panel3, BorderLayout.EAST);
        // Add actionlistener for car radio button
        radBtnCar.addActionListener( new VehicleTypeListener() );
        // Add actionlistener for bus radio button
        radBtnBus.addActionListener( new VehicleTypeListener() );
        // Add actionlistener for motorcycle radio button
        radBtnBike.addActionListener( new VehicleTypeListener() );
        // Add actionlistener for add vehicle button
        addVeh.addActionListener( new AddVehicleListener() );
        // Add actionlistener for display inventory button
        displayInv.addActionListener( new DisplayInventoryListener() );
        // Add actionlistener for previous vehicle button
        prevVeh.addActionListener( new PreviousVehicleListener() );
        // Add actionlistener for next vehicle button
        nextVeh.addActionListener( new NextVehicleListener() );
        // Add actionlistener for customer offer button
        btnCustOffer.addActionListener( new OfferButtonListener() );
        // Add actionlistener for display vehicles button
        displayVehicles.addActionListener( new DisplayVehiclesButtonListener() );
        // Add actionlistener for save inventory button
        saveInventory.addActionListener( new SaveButtonListener() );
        // Add actionlistener for the open inventory button
        openInventory.addActionListener( new OpenInventoryButtonListener() );
        // Add actionlistener for reset button
        resetBtn.addActionListener( new ResetButtonListener() );
        // Add actionlistener for close button
        closeBtn.addActionListener( new CloseButtonListener() );
        // Add actionlistener for delete button
        deleteVeh.addActionListener( new DeleteButtonListener() );
        // Pack the layout
        pack();
    }
/***************************************************************
*** Method Name: addCar
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Adds a car object in the next 
*** available inventory element.
*** Method Inputs: Vehicle[] inv, Vehicle car
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/       
    public void addCar( Vehicle[] inv, Vehicle car )
    {
        // Searches for the next available open element
        for( int i = 0; i < inv.length; i++ )
        {   
            // Add new car if empty cell
            if( inv[i] == null )
            {
                // Add car
                inv[i] = car;
                // Stop the loop
                break;
            }
        }
    }
/***************************************************************
*** Method Name: addBus
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Adds a new bus object into the next
*** available inventory element.
*** Method Inputs: Vehicle bus, Vehicle[] inv
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public void addBus( Vehicle bus, Vehicle[] inv )
    {
        // Search for empty location to add new bus
        for( int i = 0; i < inv.length; i++ )
        {    
            // Add new bus if empty cell
            if( inv[i] == null )
            {
                // Add bus
                inv[i] = bus;
                // Stop the loop
                break;
            }
        }
    }
/***************************************************************
*** Method Name: addMotorcycle
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Adds a new motorcycle object to the
*** next available inventory element.
*** Method Inputs: Vehicle bike, Vehicle[] inv
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public void addMotorcycle( Vehicle bike, Vehicle[] inv )
    {
        // Search for next available empty cell
        for( int i = 0; i < inv.length; i++ )
        {    
            // Add new bike object to empty cell
            if( inv[i] == null )
            {
                // Add new bike
                inv[i] = bike;
                // Stop the loop
                break;
            }
        }
    }
/***************************************************************
*** Method Name: displayCurVehicle
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Displays the first vehicle in the
*** inventory, if available.
*** Method Inputs: Vehicle[] inv, int pos, JTextArea textArea
*** Return Value: String vehicle
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public String displayCurVehicle(Vehicle[] inv, int pos, JTextArea textArea)
    {
        // Holds printable string of the vehicle
        String vehicle = "";
        // Check for empty inventory
        if( inv[pos] == null )
        {
            // Display empty inventory in text area
            textArea.setText("**Inventory Empty**");
            // Return empty string
            return vehicle;
        }
        else
        {
            // Get first vehicle info
            vehicle = inv[pos].printableString(inv[pos]);
        }
        // Return vehicle information
        return vehicle;
    }
/***************************************************************
*** Method Name: moveToPrevVehicle
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Displays the previous vehicle in the
*** inventory, if available.
*** Method Inputs: Vehicle[] inv, int pos, JTextArea textArea
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public void moveToPrevVehicle( Vehicle[] inv, int pos, JTextArea textArea)
    {
        // Holds the vehicle information
        String vehicle = "";
        // Get and store the vehicle information
        vehicle = inv[pos].printableString(inv[pos]);
        // Print the vehicle information
        textArea.setText(vehicle);
    }
/***************************************************************
*** Method Name: moveToNextVehicle
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Displays the next vehicle in the
*** inventory, if available.
*** Method Inputs: Vehicle[] inv, int pos, JTextArea textArea
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public void moveToNextVehicle( Vehicle[] inv, int pos, JTextArea textArea )
    {
        // Holds vehicle information
        String vehicle = "";
        // Get vehicle information and store in vehicle
        vehicle = inv[pos].printableString(inv[pos]);
        // Display vehicle information
        textArea.setText(vehicle);
    }
/***************************************************************
*** Method Name: removeCurVehicle
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Removes a vehicle from the inventory
*** if the vehicle is sold and update correct record from
*** the database.
*** Method Inputs: Vehicle[] inv, int pos, JTextArea textArea, 
*** int com
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    public void removeCurVehicle( Vehicle[] inv, int pos, JTextArea textArea, int com )
    {
        // Insantiate temp object of type vehicle for method access
        Vehicle temp = new Vehicle(vid, make, model, purchasePrice, askingPrice, color);
        // Decrement vehicle counter after temporary object is created
        temp.remove();
        // Decrement correct vehicle counter
        inv[pos].remove();
        // Decrement vehicle counter
        temp.remove();
        // Locate object at position
        for( int i = pos; i < inv.length; i++ )
        {
            // Check if next element is null
            if( inv[i + 1] == null )
            {
                // If next element is null, set object at position to null
                inv[i] = null;
                // Set i to inventory length to stop the loop
                i = inv.length;
            }
            else
            {
                // Set object at next element to current position to replace and remove it
                inv[i] = inv[i + 1];
            }
        }
        // Display successful sale
        textArea.setText("Vehicle Sold!" +'\n'+ "Commission made: " +com + "\n");
        textArea.append("The inventory databse has " + "\n" + "been automatically updated!");
    }
/***************************************************************
*** Method Name: deleteCurVehicle
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Allows use to remove vehicles before
*** saving to database.
*** Method Inputs: Vehicle[] inv, int pos, JTextArea textArea.
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/   
    public void deleteCurVehicle( Vehicle[] inv, int pos, JTextArea textArea )
    {
        // Let user know delete is from loaded file, not from database
        int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete current vehicle?"
                                             + "\n" + "*Note: Only sales will delete current vehicle"
                                             + "\n" + "from loaded inventory database. Current vehicle will only" 
                                             + "\n" + "be deleted from temporary file loaded. No changes "
                                             + "\n" + " will be made to the database.", "Warning!", JOptionPane.YES_NO_OPTION);
        // Return if cancelled
        if( n == JOptionPane.NO_OPTION )
        {
            return;
        }
        // Insantiate temp object of type vehicle for method access
        Vehicle temp = new Vehicle(vid, make, model, purchasePrice, askingPrice, color);
        // Decrement vehicle counter after temporary object is created
        temp.remove();
        // Decrement correct vehicle counter
        inv[pos].remove();
        // Decrement vehicle counter
        temp.remove();
        // Locate object at position
        for( int i = pos; i < inv.length; i++ )
        {
            // Check if next element is null
            if( inv[i + 1] == null )
            {
                // If next element is null, set object at position to null
                inv[i] = null;
                // Set i to inventory length to stop the loop
                i = inv.length;
            }
            else
            {
                // Set object at next element to current position to replace and remove it
                inv[i] = inv[i + 1];
            }
        }
        
        textArea.setText("");
    }
/***************************************************************
*** Method Name: acceptCustOfferOnCurrVehicle
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Accepts offer on current vehicle if
*** customer offer is equal to or greater than asking price.
*** Method Inputs: Vehicle[] veh, int i, int custOffer
*** Return Value: boolean deal
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public boolean acceptCustOfferOnCurrVehicle( Vehicle[] veh, int i, int custOffer )
    {
        // Set deal boolean to false
        boolean deal = false;
        // Cal deal method to see if offer meets asking price
        deal = veh[i].deal(custOffer);
        // If deal not made
        if( deal == false )
        {
            // Display message for unaccepted
            JOptionPane.showMessageDialog(null, "Offer not accepted!", "No Deal!", JOptionPane.WARNING_MESSAGE);
        }
        // Return deal
        return deal;
    }
/***************************************************************
*** Method Name: salesCommission
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Calculates the sales commission made
*** on the sale of a vehicle.
*** Method Inputs: Vehicle[] inv, int i, int custOffer
*** Return Value: int commission
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public int salesCommission( Vehicle[] inv, int i, int custOffer )
    {
        // Holds commission
        int commission = 0;
        // Call calculateCommission method to determine commission amount
        commission = inv[i].calculateCommission(custOffer);
        // Return commission
        return commission;
    }
/***************************************************************
*** Method Name: displayNumberOfVehicles
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Displays the total number of
*** vehicles, cars, buses, and motorcycles, if available.
*** Method Inputs: Vehicle[] inv, JLabel v, JLabel c, JLabel b,
*** JLabel m
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public void displayNumberOfVehicles( Vehicle[] inv, JLabel v, JLabel c, JLabel b, JLabel m )
    {
        // Display error for empty inventory
        if( inv[0] == null )
        {   
            // Set all labels to 0
            v.setText("Total Vehicles: 0" );
            c.setText("Cars available: 0");
            b.setText("Buses available: 0");
            m.setText("Motorcycles available: 0");
            // Display error and return
            JOptionPane.showMessageDialog(null, "No vehicles available!", "Error!", JOptionPane.ERROR_MESSAGE);
            // Return back to method call
            return;
        }
        // Instantiate temporary objects for access to member methods
        Vehicle temp = new Vehicle(vid, make, model, purchasePrice, askingPrice, color);
        // Decrement vehicle for temp object creation
        temp.remove();
        // Instantiate car of type Car
        Car car = new Car(passCount, doorCount, mpg, vid, make, model, purchasePrice, askingPrice, color);
        // Decrement car counter after temporary object is created
        car.remove();
        // Decrement vehicle counter after temporary object is created
        temp.remove();
        // Instantiate bus of type Bus
        Bus bus = new Bus(passCount, doorCount, diesel, vid, make, model, purchasePrice, askingPrice, color);
        // Decrement bus counter after temporary object is created
        bus.remove();
        // Decrement vehicle counter after temporary object is created
        temp.remove();
        // Instantiate bike of type Motorcycle
        Motorcycle bike = new Motorcycle(bikeWeight, bikeType, engineSize, vid, make, model, purchasePrice, askingPrice, color);
        // Decrement motorcycle counter after temporary object is created
        bike.remove();
        // Decrement vehicle counter after temporary object is created
        temp.remove();
        // Display total number of vehicles
        v.setText("Total Vehicles: " +Integer.toString(inv[0].getCount()));
        // Display total number of cars
        c.setText("Cars available: " +Integer.toString(car.getCarCount()));
        // Display total number of buses
        b.setText("Buses available: " +Integer.toString(bus.getBusCount()));
        // Display total number of motorcycles
        m.setText("Motorcycles available: " +Integer.toString(bike.getMotorcycleCount()));
    }
/***************************************************************
*** Method Name: vehicleIDCheck
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Checks if vehicle id exists.
*** Method Inputs: Vehicle[] inv, JLabel v, int i
*** Return Value: boolean
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    public boolean vehicleIDCheck( Vehicle[] inv, int i )
    {
        // Step through inventory to see if vehicle exists
        for( int a = 0; a < inv.length; a++ )
        {
            if( inv[a] == null )
            {
                return false;
            }
            // Check for ID match
            else if( i == inv[a].getVehicleID() )
            {
                return true;
            }
        }
        
        return false;
    }
/***************************************************************
*** Class Name: VehicleTypeListener
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Implements ActionListener that invokes
*** action performed on vehicle radio buttons selected.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/        
    private class VehicleTypeListener implements ActionListener
    {
/***************************************************************
*** Method Name: actionPerformed
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Performs action on radio button
*** selected, change the label types associated with the
*** selected option, and enable the vehicle info text fields.
*** Method Inputs: ActionEvent e
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
        public void actionPerformed( ActionEvent e )
        {
            // If car radio button selected
            if( radBtnCar.isSelected() )
            {
                // Set passenger limit label
                lblEngPassCnt.setText("Passenger Limit");
                // Set number of doors label
                lblWtDrCntLgth.setText("Number of Doors");
                // Set mpg label
                lblMpgTypeDis.setText("Average MPG");
                // Enable all text fields once option selected
                vehID.setEnabled(true);
                textMake.setEnabled(true);
                textModel.setEnabled(true);
                textColor.setEnabled(true);
                textEngPassCount.setEnabled(true);
                textWtDoorLng.setEnabled(true);
                textMpgTypeDis.setEnabled(true);
                textPurchasePrice.setEnabled(true);
                textAskingPrice.setEnabled(true);
                addVeh.setEnabled(true);
            }
            // If button radio button is selected
            else if( radBtnBus.isSelected() )
            {
                // Set passenger limit label
                lblEngPassCnt.setText("Passenger Limit");
                // Set bus length label
                lblWtDrCntLgth.setText("Length");
                // Set diesel fuel option label
                lblMpgTypeDis.setText("Diesel (Y/N)");
                // Enable all text fields once option selected
                vehID.setEnabled(true);
                textMake.setEnabled(true);
                textModel.setEnabled(true);
                textColor.setEnabled(true);
                textEngPassCount.setEnabled(true);
                textWtDoorLng.setEnabled(true);
                textMpgTypeDis.setEnabled(true);
                textPurchasePrice.setEnabled(true);
                textAskingPrice.setEnabled(true);
                addVeh.setEnabled(true);
            }
            // If motorcycle button is selected
            else if( radBtnBike.isSelected() )
            {
                // Set engine size label
                lblEngPassCnt.setText("Engine Size");
                // Set bike weight label
                lblWtDrCntLgth.setText("Weight");
                // Set bike type label
                lblMpgTypeDis.setText("Type (I.e. Street, Cruiser)");
                // Enable all text fields once option selected
                vehID.setEnabled(true);
                textMake.setEnabled(true);
                textModel.setEnabled(true);
                textColor.setEnabled(true);
                textEngPassCount.setEnabled(true);
                textWtDoorLng.setEnabled(true);
                textMpgTypeDis.setEnabled(true);
                textPurchasePrice.setEnabled(true);
                textAskingPrice.setEnabled(true);
                addVeh.setEnabled(true);
            }
        }
    }
/***************************************************************
*** Class Name: AddVehicleListener
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Implements ActionListener that invokes
*** action performed on add vehicle button.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    private class AddVehicleListener implements ActionListener
    {
/***************************************************************
*** Method Name: actionPerformed
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Performs action on add vehicle button
*** that adds the selected vehicle to the dealer inventory.
*** Method Inputs: ActionEvent e
*** Return Value: N/A
****************************************************************
*** Date: 10/21/2016
****************************************************************
***************************************************************/        
        public void actionPerformed( ActionEvent e )
        {   
            // Instantiate ese object of type ErrorExceptions
            ErrorExceptions ese = new ErrorExceptions();
            // Instantiate ic object of type ErrorExceptions
            ErrorExceptions ic = new ErrorExceptions();
            // Instantiate ce object of type ErrorExceptions
            ErrorExceptions ec = new ErrorExceptions();
            // Instantiate tempLM of type LayoutManager for method access
            LayoutManager tempLM = new LayoutManager();
            // Check if car radio button is selected
            try
            {
                if( radBtnCar.isSelected() )
                {   
                    // Instantiate temp of type Vehicle for method access
                    Vehicle temp = new Vehicle(vid, make, model, purchasePrice, askingPrice, color);
                    // Decrement vehicle counter after temporary object is created
                    temp.remove();
                    // Get vehicle id
                    sVid = vehID.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(sVid);
                    //Convert to int
                    vid = Integer.parseInt(sVid);
                    // Check if vehicle ID exists
                    st = tempLM.vehicleIDCheck(inventory, vid);
                    // Throw exception if exists
                    if( st == true )
                    {
                        throw new Exception();
                    }
                    // Get make
                    make = textMake.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(make);
                    // Get the model
                    model = textModel.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(model);
                    // Get the string color
                    vehColor = textColor.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(vehColor);
                    // Find the color
                    color = temp.findColor(vehColor);
                    // Check for valid color
                    ec.ColorValidation(color);
                    // Get number of passengers
                    passCount = Integer.parseInt(textEngPassCount.getText());
                    // Get number of doors
                    doorCount = Integer.parseInt(textWtDoorLng.getText());
                    // Get the mpg
                    mpg = Double.parseDouble(textMpgTypeDis.getText());
                    // Get purchase price
                    purchasePrice = Integer.parseInt(textPurchasePrice.getText());
                    // Get asking price
                    askingPrice = Integer.parseInt(textAskingPrice.getText());
                    //Instantiate a new car of type car
                    Vehicle car = new Car(passCount, doorCount, mpg, vid, make, model, purchasePrice, askingPrice, color);
                    // Add car to inventory
                    tempLM.addCar(inventory, car);
                    // Display successful added
                    JOptionPane.showMessageDialog(null, "Car has been successfully added to the inventory", "Successful!", JOptionPane.PLAIN_MESSAGE);
                    // Set customer text field to enabled
                    textCustOffer.setEnabled(true);
                    // Set customer offer button enabled
                    btnCustOffer.setEnabled(true);
                    // Reset all text fields
                    vehID.setText("");
                    textMake.setText("");
                    textModel.setText("");
                    textColor.setText("");
                    textEngPassCount.setText("");
                    textWtDoorLng.setText("");
                    textMpgTypeDis.setText("");
                    textPurchasePrice.setText("");
                    textAskingPrice.setText("");
                    deleteVeh.setEnabled(true);
                }
                // If bus is selected
                else if( radBtnBus.isSelected() )
                {
                  // Set diesel to false
                    diesel = false;
                    // Instantiate temp of type Vehicle for method access
                    Vehicle temp = new Vehicle( vid, make, model, purchasePrice, askingPrice, color);
                    // Decrement vehicle counter after temporary object is created
                    temp.remove();
                    // Get vehicle id
                    sVid = vehID.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(sVid);
                    //Convert to int
                    vid = Integer.parseInt(sVid);
                    // Check if vehicle ID exists
                    st = tempLM.vehicleIDCheck(inventory, vid);
                    // Throw exception if exists
                    if( st == true )
                    {
                        throw new Exception();
                    }
                    // Get make
                    make = textMake.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(make);
                    // Get the model
                    model = textModel.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(model);
                    // Get the string color
                    vehColor = textColor.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(vehColor);
                    // Find the color
                    color = temp.findColor(vehColor);
                    // Check for valid color
                    ec.ColorValidation(color);
                    // Get passenger count
                    passCount = Integer.parseInt(textEngPassCount.getText());
                    // Get bus length
                    length = Integer.parseInt(textWtDoorLng.getText());
                    // Get the fuel type
                    fuel = textMpgTypeDis.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(fuel);
                    // Set input to uppercase
                    fuel = fuel.toUpperCase();
                    // Validate correct option Y or N
                    ic.FuelTypeValidation(fuel);
                    // Set fuel type
                    if( Objects.equals(fuel, new String("Y")) )
                    {
                        // Set diesel to true
                        diesel = true;
                    }
                    else
                    {
                        // Set diesel to false
                        diesel = false;
                    }
                    // Get purchase price
                    purchasePrice = Integer.parseInt(textPurchasePrice.getText());
                    // Get asking price
                    askingPrice = Integer.parseInt(textAskingPrice.getText());
                    // Instantiate new bus vehicle of type bus
                    Vehicle bus = new Bus(length, passCount, diesel, vid, make, model, purchasePrice, askingPrice, color);
                    // Add bus to inventory
                    tempLM.addBus(bus, inventory);
                    // Display successful add
                    JOptionPane.showMessageDialog(null, "Bus has been successfully added to the inventory", "Successful!", JOptionPane.PLAIN_MESSAGE);
                    // Set customer text field to enabled
                    textCustOffer.setEnabled(true);
                    // Set customer offer button enabled
                    btnCustOffer.setEnabled(true);
                    // Reset all text fields
                    vehID.setText("");
                    textMake.setText("");
                    textModel.setText("");
                    textColor.setText("");
                    textEngPassCount.setText("");
                    textWtDoorLng.setText("");
                    textMpgTypeDis.setText("");
                    textPurchasePrice.setText("");
                    textAskingPrice.setText("");
                    deleteVeh.setEnabled(true);
                }
                // If motorcycle is selected
                else if( radBtnBike.isSelected() )
                {
                    // Instantiate temp of type Vehicle for method access
                    Vehicle temp = new Vehicle(vid, make, model, purchasePrice, askingPrice, color);
                    // Decrement vehicle counter after temporary object is created
                    temp.remove();
                    // Get vehicle id
                    sVid = vehID.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(sVid);
                    //Convert to int
                    vid = Integer.parseInt(sVid);
                    // Check if vehicle ID exists
                    st = tempLM.vehicleIDCheck(inventory, vid);
                    // Throw exception if exists
                    if( st == true )
                    {
                        throw new Exception();
                    }
                    // Get make
                    make = textMake.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(make);
                    // Get the model
                    model = textModel.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(model);
                    // Get the string color
                    vehColor = textColor.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(vehColor);
                    // Find the color
                    color = temp.findColor(vehColor);
                    // Check for valid color
                    ec.ColorValidation(color);
                    // Get engine size
                    engineSize = Integer.parseInt(textEngPassCount.getText());
                    // Get weight
                    bikeWeight = Integer.parseInt(textWtDoorLng.getText());
                    // Get the motorcycle type
                    bikeType = textMpgTypeDis.getText();
                    // Check for empty string
                    ese.EmptyStringValidation(bikeType);
                    // Get purchase price
                    purchasePrice = Integer.parseInt(textPurchasePrice.getText());
                    // Get asking price
                    askingPrice = Integer.parseInt(textAskingPrice.getText());
                    // Instantiate a new bike of type Motorcycle
                    Vehicle bike = new Motorcycle(bikeWeight, bikeType, engineSize, vid, make, model, purchasePrice, askingPrice, color);
                    // Add motorcycle to inventory
                    tempLM.addMotorcycle(bike, inventory);
                    // Display successful add
                    JOptionPane.showMessageDialog(null, "Motorcycle has been successfully added to the inventory", "Successful!", JOptionPane.PLAIN_MESSAGE);
                    // Set customer text field to enabled
                    textCustOffer.setEnabled(true);
                    // Set customer offer button enabled
                    btnCustOffer.setEnabled(true);
                    // Reset all text fields
                    vehID.setText("");
                    textMake.setText("");
                    textModel.setText("");
                    textColor.setText("");
                    textEngPassCount.setText("");
                    textWtDoorLng.setText("");
                    textMpgTypeDis.setText("");
                    textPurchasePrice.setText("");
                    textAskingPrice.setText("");
                    deleteVeh.setEnabled(true);
                }   // End if bike selected
                else
                {
                    // Display error for no vehicle choice selected
                    JOptionPane.showMessageDialog(null, "Please make a vehicle type selection!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
            // Catch empty text field
            catch( EmptyStringException str )
            {
                // Display error message
                JOptionPane.showMessageDialog(null, "All fields are required!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            // Catch invalid number
            catch( NumberFormatException ex )
            {
                // Display error message
                JOptionPane.showMessageDialog(null, "Please enter valid required numbers!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            // Catch invalid option for fuel
            catch( InvalidCharacterException ice )
            {
                // Display error message
                JOptionPane.showMessageDialog(null, "Fuel type must be Y or N!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            catch(ColorException ce) // Catch invalid color
            {
                // Display error that color choice was invalid
                JOptionPane.showMessageDialog(null, "Please enter a valid color!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            catch( Exception ex )
            {
                JOptionPane.showMessageDialog(null, "Vehicle ID already exists!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }  
    }
/***************************************************************
*** Class Name: DisplayInventoryListener
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Implements ActionListener that invokes
*** action performed on display inventory button.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    private class DisplayInventoryListener implements ActionListener
    {
/***************************************************************
*** Method Name: actionPerformed
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Performs action on display vehicle
*** button that displays the first vehicle in the dealer
*** inventory.
*** Method Inputs: ActionEvent e
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
        public void actionPerformed( ActionEvent e )
        {
            // Set index to 0
            index = 0;
            // Instantiate es of type Err
            ErrorExceptions es = new ErrorExceptions();
            // Try catch begin
            try
            {
                //Set index to 0 for first vehicle
                index = 0;
                // Hold vehicle string
                String temp = "";
                // Instantiate tempLM of type LayoutManager
                LayoutManager tempLM = new LayoutManager();
                // Get printable string of first vehicle in inventory
                temp = tempLM.displayCurVehicle(inventory, index, textArea);
                // Check for empty
                es.EmptyStringValidation(temp);
                // Display vehicle
                textArea.setText(temp);
            }
            // Check for empty string
            catch( EmptyStringException str )
            {
                deleteVeh.setEnabled(false);
                // Display error message
                JOptionPane.showMessageDialog(null, "Inventory is currently empty!", "Error!", JOptionPane.ERROR_MESSAGE);
            }// End try catch
        }
    }
/***************************************************************
*** Class Name: PreviousVehicleListener
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Implements ActionListener that invokes
*** action performed on previous vehicle button.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    private class PreviousVehicleListener implements ActionListener
    {
/***************************************************************
*** Method Name: actionPerformed
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Performs action on previous vehicle
*** button that displays the vehicle previous to the current in 
*** the dealer inventory.
*** Method Inputs: ActionEvent e
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/        
        public void actionPerformed( ActionEvent e )
        {
            // Hold vehicle string
            String temp = "";
            // Instantiate tempLM of type LayoutManager
            LayoutManager tempLM = new LayoutManager();
            // Instantiate ee of type ErrorExceptions
            ErrorExceptions ee = new ErrorExceptions();
            // Try catch begin
            try
            {
                // Check if next index is empty
                if( inventory[index] == null || index == 0 )
                {
                    // Throw error for empty inventory
                    ee.EmptyStringValidation(temp);
                    // Set index to 0
                    index = 0;
                    // Display first vehicle
                    temp = tempLM.displayCurVehicle(inventory, index, textArea);
                    // Check for vehicle
                    ee.EmptyStringValidation(temp);
                    // Display vehicle info
                    textArea.setText(temp);
                    // Set temp to null
                    temp = "";
                    // Throw error for no previous vehicle
                    ee.EmptyStringValidation(temp);
                }
                else
                {
                    // Decrement index
                    index--;
                    // Display previous vehicle
                    tempLM.moveToPrevVehicle(inventory, index, textArea);
                }
            }
            // Catch empty string
            catch( EmptyStringException str )
            {
                // Display error message
                JOptionPane.showMessageDialog(null, "No vehicle previous to current!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            // End try catch
        }
    }
/***************************************************************
*** Class Name: NextVehicleListener
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Implements ActionListener that invokes
*** action performed on next vehicle button.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    private class NextVehicleListener implements ActionListener
    {
/***************************************************************
*** Method Name: actionPerformed
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Performs action on next vehicle
*** button that displays the next vehicle after the current in 
*** the dealer inventory.
*** Method Inputs: ActionEvent e
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
        public void actionPerformed( ActionEvent e )
        {
            // Hold vehicle string
            String temp = "";
            // Instantiate tempLM of type LayoutManager
            LayoutManager tempLM = new LayoutManager();
            // Instantiate ee of type ErrorExceptions
            ErrorExceptions ee = new ErrorExceptions();
            // Try catch begin
            try
            {
                // Check if next index is empty
                if( inventory[index + 1] == null )
                {
                    // Throw error for empty element
                    ee.EmptyStringValidation(temp);
                    // Display current vehicle
                    temp = tempLM.displayCurVehicle(inventory, index, textArea);
                    // Check for empty string
                    ee.EmptyStringValidation(temp);
                    // Display vehicle info
                    textArea.setText(temp);
                }
                else
                {   
                    // holds vehicle string
                    String vehicle = "";
                    // Increment index
                    index++;
                    // Display next vehicle
                    tempLM.moveToNextVehicle(inventory, index, textArea);
                    //textArea.setText(vehicle);
                }
            }
            // Catch empty string
            catch( EmptyStringException str )
            {
                // Display error message
                JOptionPane.showMessageDialog(null, "Reached end of inventory!", "Error!", JOptionPane.ERROR_MESSAGE);
            }// End try catch
        }
    }
/***************************************************************
*** Class Name: OfferButtonListener
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Implements ActionListener that invokes
*** action performed on customer offer button.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    private class OfferButtonListener implements ActionListener
    {
/***************************************************************
*** Method Name: actionPerformed
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Performs action on customer offer
*** button that submits a customers offer to purchase a vehicle
*** and deletes it from the inventory if sold. The database is
*** update automatically.
*** Method Inputs: ActionEvent e
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
        public void actionPerformed( ActionEvent e )
        {
            // Try catch begin
            try
            {
                // Instantiate tempLM of type LayoutManager for method access
                LayoutManager tempLM = new LayoutManager();
                // Instantiate temp of type Vehicle for method access
                Vehicle temp = new Vehicle( vid, make, model, purchasePrice, askingPrice, color );
                // Decrement vehicle counter after temporary object is created
                temp.remove();
                // Get the customer offer
                custOffer = Integer.parseInt(textCustOffer.getText());
                // Check if offer is accepted
                if( tempLM.acceptCustOfferOnCurrVehicle( inventory, index, custOffer ) )
                {
                    // Calculate commission for sale
                    commission = tempLM.salesCommission( inventory, index, custOffer );
                    // Set temp to current vehicle
                    temp = inventory[index];
                    // Remove the vehicle from inventory
                    tempLM.removeCurVehicle(inventory, index, textArea, commission);
                    // Create connection
                    Connection connection = null;
                    // Create statement
                    Statement statement = null;
                    // Create result set
                    ResultSet set = null;
                    // Clear offer textfield
                    textCustOffer.setText("");
                    // Load driver
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    // Connect to database with connections tring
                    connection = DriverManager.getConnection(DATABASE_URL);
                    // Create a statement
                    statement = connection.createStatement();
                    // Execute deletion update
                    set = statement.executeQuery("DELETE FROM INVENTORY WHERE VEH_ID = " + temp.getVehicleID() );
                    
                    // If last vehicle sold
                    if( inventory[0] == null )
                    {
                        // Disable customer offer textfield
                        textCustOffer.setEnabled(false);
                        // Disable customer offer button
                        btnCustOffer.setEnabled(false);
                    }
                }
                // Set customer offer text field to null
                textCustOffer.setText("");
            }
            // Catch invalid number
            catch( NumberFormatException ex )
            {
                // Reset customer offer textfield
                textCustOffer.setText("");
                // Display error message
                JOptionPane.showMessageDialog(null, "Please enter valid customer offer!", "Error!", JOptionPane.ERROR_MESSAGE);
            }// End try catch
            catch( Exception eq )
            {
                
            }
        }
    }
/***************************************************************
*** Class Name: DisplayVehiclesButtonListener
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Implements ActionListener that invokes
*** action performed on display vehicles button.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    private class DisplayVehiclesButtonListener implements ActionListener
    {
/***************************************************************
*** Method Name: actionPerformed
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Performs action on display vehicles
*** button that displays the current number of vehicles, cars,
*** buses, and motorcycles in the inventory.
*** Method Inputs: ActionEvent e
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
        public void actionPerformed( ActionEvent e )
        {
            // Instantiate tempLM of type LayoutManager for method access
            LayoutManager tempLM = new LayoutManager();
            // Display the total number of vehicles
            tempLM.displayNumberOfVehicles(inventory, totalVeh, numCars, numBuses, numBikes);
        }
    }
/***************************************************************
*** Class Name: ResetButtonListener
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Implements ActionListener that invokes
*** action performed on reset button.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
    private class ResetButtonListener implements ActionListener
    {
/***************************************************************
*** Method Name: actionPerformed
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Performs action on reset button that
*** sets all text fields to null.
*** Method Inputs: ActionEvent e
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
        public void actionPerformed( ActionEvent e )
        {
            // Set all textfields to null
            vehID.setText("");
            textMake.setText("");
            textModel.setText("");
            textColor.setText("");
            textEngPassCount.setText("");
            textWtDoorLng.setText("");
            textMpgTypeDis.setText("");
            textPurchasePrice.setText("");
            textAskingPrice.setText("");
            textCustOffer.setText("");
            textArea.setText("**Vehicle Info Reset**");
        }
    }
/***************************************************************
*** Class Name: CloseButtonListener
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Implements ActionListener that invokes
*** action performed on close button.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    private class CloseButtonListener implements ActionListener
    {
/***************************************************************
*** Method Name: actionPerformed
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Performs action on close button that
*** closes the application.
*** Method Inputs: ActionEvent e
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
        public void actionPerformed( ActionEvent e )
        {
            // Close the application
            System.exit(0);
        }
    }
/***************************************************************
*** Class Name: SaveButtonListener
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Implements ActionListener that invokes
*** action performed on save inventory button.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/     
    private class SaveButtonListener implements ActionListener
    {
/***************************************************************
*** Method Name: actionPerformed
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Performs action on save inventory
*** button that allows the user to save the inventory to a the
*** database.
*** Method Inputs: ActionEvent e
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
        public void actionPerformed( ActionEvent e )
        {
            // Create connection
            Connection connection = null;
            // Create statement
            Statement statement = null;
            // Create update
            PreparedStatement update;
            // Create resultset
            ResultSet resultSet = null;
            // Hold string vehicle id
            String vehID = "";
            // Hold string vehicle type
            String vehType = "";
            // Hold not applicable
            String notApp = "N/A";
            // Hold string engine size
            String eSize = "";
            // Hold string passenger limit
            String pLimit = "";
            // Hold string bike weight
            String bWt = "";
            // Hold string number of doors
            String nDoors = "";
            // Hold string length
            String lgt = "";
            // Hold string mpg
            String sMPG = "";
            // Hold string purchase price
            String pPrice = "";
            // Hold string asking price
            String aPrice = "";
            
            // Try catch begin
            try
            {
                // Load driver
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                // Get connection thru connection string
                connection = DriverManager.getConnection(DATABASE_URL);
                // Create statement
                statement = connection.createStatement();
                // Prepare update statement
                update = connection.prepareStatement("INSERT INTO INVENTORY(VEH_ID, VEH_TYPE, MODEL, MAKE,"
                                                    + " COLOR, ENG_SIZE, PASS_LIMIT, WEIGHT_, DOORS, "
                                                    + "LENGTH_, MPG, TYPE_, DIESEL, PUR_PRICE, ASK_PRICE, STATUS_)" 
                                                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                // Instantiate temporary objects for access to member methods
                Vehicle temp = new Vehicle( vid, make, model, purchasePrice, askingPrice, color);
                // Decrement vehicle for temp object creation
                temp.remove();
                // Loop counter
                int loop = 0;
                // Check for empty inventory
                if( inventory[loop] == null )
                {
                    JOptionPane.showMessageDialog(null, "Inventory empty! Nothing to save!", "Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // While loop to write objects to database
                while( inventory[loop] != null )
                {
                    // Get the next inventory item and set to temp
                    temp = inventory[loop];
                    // Check database to ensure vehicle ID does not already exist
                    resultSet = statement.executeQuery("SELECT VEH_ID FROM INVENTORY");
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int numberOfCol = metaData.getColumnCount();
                    while( resultSet.next() )
                    {    
                        for( int a = 1; a <= numberOfCol; a++ )
                        {

                            if( resultSet.getObject(a).toString().trim() == null )
                            {
                                break;
                            }
                            else
                            {
                                // If vehicle ID exists, let user know to re add vehicle
                                // to inventory before saving
                                vehID = resultSet.getObject(a).toString().trim();
                                vid = Integer.parseInt(vehID);
                                if( vid == temp.getVehicleID() )
                                {
                                    throw new Exception();
                                }
                            }
                        }
                    }
                    //Check if vehicle is a car
                    if( temp instanceof Car )
                    {
                        // Get vehicle id
                        vid = temp.getVehicleID();
                        // Convert to string
                        sVid = Integer.toString(vid);
                        // Set column update
                        update.setString(1, sVid);
                        // Set vehicle type
                        vehType = "Car";
                        // Set column update
                        update.setString(2, vehType);
                        // Get make
                        make = temp.getMake();
                        // Set column update
                        update.setString(3, make);
                        // Get model
                        model = temp.getModel();
                        // Set column update
                        update.setString(4, model);
                        // Get color
                        color = temp.getColor();
                        // Convert to string
                        vehColor = temp.sColor(color);
                        // Set column update
                        update.setString(5, vehColor);
                        // Not applicable column
                        update.setString(6, notApp);
                        // Get passenger count
                        passCount = ((Car) temp).getPassCount();
                        // Convert to string
                        pLimit = Integer.toString(passCount);
                        // Set column update
                        update.setString(7, pLimit);
                        // Not applicable column
                        update.setString(8, notApp);
                        // Get door count
                        doorCount = ((Car) temp).getDoorCount();
                        // Convert to string
                        nDoors = Integer.toString(doorCount);
                        // Set column update
                        update.setString(9, nDoors);
                        // Not applicable column
                        update.setString(10, notApp);
                        // Get mpg
                        mpg = ((Car) temp).getMpg();
                        // Convert to string
                        sMPG = Double.toString(mpg);
                        // Set column update
                        update.setString(11, sMPG);
                        // Not applicable columns
                        update.setString(12, notApp);
                        update.setString(13, notApp);
                        // Get purchase price
                        purchasePrice = temp.getPurchasePrice();
                        // Convert to string
                        pPrice = Integer.toString(purchasePrice);
                        // Set column update
                        update.setString(14, pPrice);
                        // Get asking price
                        askingPrice = temp.getAskingPrice();
                        // Convert to string
                        aPrice = Integer.toString(askingPrice);
                        // Set column update
                        update.setString(15, aPrice);
                        // Set on hand
                        update.setString(16, "On Hand");
                    }
                    // Check if bus
                    else if( temp instanceof Bus )
                    {
                        // Get vehicle id
                        vid = temp.getVehicleID();
                        // Convert to string
                        sVid = Integer.toString(vid);
                        // Set column update
                        update.setString(1, sVid);
                        // Set vehicle type
                        vehType = "Bus";
                        // Set column update
                        update.setString(2, vehType);
                        // Get make
                        make = temp.getMake();
                        // Set column update
                        update.setString(3, make);
                        // Get model
                        model = temp.getModel();
                        // Set column update
                        update.setString(4, model);
                        // Get color
                        color = temp.getColor();
                        // Convert to string
                        vehColor = temp.sColor(color);
                        // Set column update
                        update.setString(5, vehColor);
                        // Not applicable
                        update.setString(6, notApp);
                        // Get passenger count
                        passCount = ((Bus) temp).getPassCount();
                        // Convert to string
                        pLimit = Integer.toString(passCount);
                        // Set column update
                        update.setString(7, pLimit);
                        // Not applicable
                        update.setString(8, notApp);
                        update.setString(9, notApp);
                        // Get bus length
                        length = ((Bus) temp).getLength();
                        // Convert to string
                        lgt = Integer.toString(length);
                        // Set column update
                        update.setString(10, lgt);
                        // Not applicable
                        update.setString(11, notApp);
                        update.setString(12, notApp);
                        // Get diesel
                        diesel = ((Bus) temp).getDiesel();
                        // Convert to string
                        if( diesel == true )
                        {
                            // Set diesel to true
                            fuel = "Y";
                        }
                        else
                        {
                            // Set diesel to false
                            fuel = "N";
                        }
                        // Set column update
                        update.setString(13, fuel);
                        // Get purchase price
                        purchasePrice = temp.getPurchasePrice();
                        // Convert to string
                        pPrice = Integer.toString(purchasePrice);
                        // Set column update
                        update.setString(14, pPrice);
                        // Get asking price
                        askingPrice = temp.getAskingPrice();
                        // Convert to string
                        aPrice = Integer.toString(askingPrice);
                        // Set column update
                        update.setString(15, aPrice);
                        // Set on hand
                        update.setString(16, "On Hand");
                    }
                    // Check if motorcycle
                    else if( temp instanceof Motorcycle )
                    {
                        // Get vehicle ID
                        vid = temp.getVehicleID();
                        // Convert to string and update
                        sVid = Integer.toString(vid);
                        update.setString(1, sVid);
                        // Set vehicle type
                        vehType = "Motorcycle";
                        update.setString(2, vehType);
                        // Set make
                        make = temp.getMake();
                        update.setString(3, make);
                        // Set model
                        model = temp.getModel();
                        update.setString(4, model);
                        // Set color
                        color = temp.getColor();
                        vehColor = temp.sColor(color);
                        update.setString(5, vehColor);
                        // Set engine size
                        engineSize = ((Motorcycle) temp).getEngineSize();
                        eSize = Integer.toString(engineSize);
                        update.setString(6, eSize);
                        // Not applicable
                        update.setString(7, notApp);
                        // Set bike weight
                        bikeWeight = ((Motorcycle) temp).getWeight();
                        bWt = Integer.toString(bikeWeight);
                        update.setString(8, bWt);
                        // Not applicable
                        update.setString(9, notApp);
                        update.setString(10, notApp);
                        update.setString(11, notApp);
                        // Set bike type
                        bikeType = ((Motorcycle) temp).getType();
                        update.setString(12, bikeType);
                        update.setString(13, notApp);
                        // Set purcahse price
                        purchasePrice = temp.getPurchasePrice();
                        pPrice = Integer.toString(purchasePrice);
                        update.setString(14, pPrice);
                        // Set asking price
                        askingPrice = temp.getAskingPrice();
                        aPrice = Integer.toString(askingPrice);
                        update.setString(15, aPrice);
                        update.setString(16, "On Hand");
                    }
                    // Execute update
                    update.executeUpdate();
                    // Increment loop counter
                    loop++;
                }
                // Display message data write complete
                JOptionPane.showMessageDialog(null, "Write to database complete!", "Complete", JOptionPane.PLAIN_MESSAGE);
            }
            // Catch error if writing fails
            catch( Exception ex )
            {
                // Display error
                JOptionPane.showMessageDialog(null, "Duplicate ID error! Ending Save and returning!", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            finally
            {
                try
                {
                    // Close connections
                    resultSet.close();
                    statement.close();
                    connection.close();
                }
                catch(Exception ex)
                {
                    
                }
            }
            // End try catch
        }
    }
    
/***************************************************************
*** Class Name: OpenInventoryButtonListener
*** Class Author: Austin LeBel
****************************************************************
*** Purpose of the class: Implements ActionListener that invokes
*** action performed on open inventory button.
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/    
    private class OpenInventoryButtonListener implements ActionListener
    {
/***************************************************************
*** Method Name: actionPerformed
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Performs action on open inventory
*** button that allows the user to open an inventory file to the
*** application.
*** Method Inputs: ActionEvent e
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
        public void actionPerformed( ActionEvent e )
        {
            // Create connection
            Connection connection = null;
            // Create result set
            ResultSet resultSet = null;
            // Create statement
            Statement statement = null;
            // Hold car info
            String carTemp = "";
            // Hold bus info
            String busTemp = "";
            // Hold bike info
            String bikeTemp = "";
            // Hold column name
            String colName = "";
            // Hold color
            Color cTemp;
            // Hold status for booleans
            String stat = "";
            // Loop counter
            int loop = 0;
            // Try catch begin
            try
            {   
                // If an inventory is already loaded
                if( inventory[0] != null )
                {
                    // Ask the user if they want to continue and overwrite current inventory
                    int overWrite = JOptionPane.showConfirmDialog(null, "An inventory is already loaded." + '\n' + "Would you like to continue?", "Warning!", JOptionPane.YES_NO_CANCEL_OPTION);
                    // If user clicks no or cancel
                    if( overWrite != 0)
                    {
                        // Cancel load operation
                        return;
                    }
                }
                // Load driver
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                // Get database connection
                connection = DriverManager.getConnection(DATABASE_URL);
                // Create statement connection
                statement = connection.createStatement();
                // Query the database
                resultSet = statement.executeQuery("SELECT * FROM INVENTORY");
                // Get the metadata
                ResultSetMetaData metaData = resultSet.getMetaData();
                // Get number of columns
                int numberOfCol = metaData.getColumnCount();
                // Create new inventory
                inventory = new Vehicle[500];
                
                // Read in data
                while( resultSet.next() )
                {
                    for(int i = 1; i<=numberOfCol; i++)
                    {                        
                        // Enable offer textfield and button
                        textCustOffer.setEnabled(true);
                        btnCustOffer.setEnabled(true);
                        // Get first object read
                        String tmp = resultSet.getObject(i).toString().trim();
                        tmp = tmp.toUpperCase();
                        // If temp is a car
                        if( Objects.equals(tmp, new String("CAR")) )
                        {
                            // Instantiate new car of type car
                            Car car = new Car(passCount, doorCount, mpg, vid, make, model, purchasePrice, askingPrice, color);
                            for( int a = 1; a <= numberOfCol; a++ )
                            {
                                
                                // Column name
                                colName = metaData.getColumnName(a).toString().trim();
                                // Next object read
                                carTemp = resultSet.getObject(a).toString().trim();
                                // Convert to uppercase
                                carTemp = carTemp.toUpperCase();
                                // Check which column is read
                                if( Objects.equals(colName, new String("VEH_TYPE")) )
                                {
                                    continue;
                                }
                                // Skip if column is not applicable
                                else if( Objects.equals(carTemp, new String("N/A")))
                                {
                                    continue;
                                }
                                // Gets vehicle id
                                else if( Objects.equals(colName, new String("VEH_ID")) )
                                {
                                    vid = Integer.parseInt(carTemp);
                                    car.setVehicleID(vid);
                                    continue;
                                }
                                // Gets model
                                else if( Objects.equals(colName, new String("MODEL")) )
                                {
                                    car.setModel(carTemp);
                                    continue;
                                }
                                // Gets make
                                else if( Objects.equals(colName, new String("MAKE")) )
                                {
                                    car.setMake(carTemp);
                                    continue;
                                }
                                // Gets color
                                else if( Objects.equals(colName, new String("COLOR")) )
                                {
                                    cTemp = car.findColor(carTemp);
                                    car.setColor(cTemp);
                                    continue;
                                }
                                // Gets passenger limit
                                else if( Objects.equals(colName, new String("PASS_LIMIT")) )
                                {
                                    passCount = Integer.parseInt(carTemp);
                                    car.setPassCount(passCount);
                                    continue;
                                }
                                // Gets number of doors
                                else if( Objects.equals(colName, new String("DOORS")) )
                                {
                                    doorCount = Integer.parseInt(carTemp);
                                    car.setDoorCount(doorCount);
                                    continue;
                                }
                                // Gets mpg
                                else if( Objects.equals(colName, new String("MPG")) )
                                {
                                    mpg = Double.parseDouble(carTemp);
                                    car.setMpg(mpg);
                                    continue;
                                }
                                // Gets purchase price
                                else if( Objects.equals(colName, new String("PUR_PRICE")) )
                                {
                                    purchasePrice = Integer.parseInt(carTemp);
                                    car.setPurchasePrice(purchasePrice);
                                    continue;
                                }
                                // Gets asking price
                                else if( Objects.equals(colName, new String("ASK_PRICE")) )
                                {
                                    askingPrice = Integer.parseInt(carTemp);
                                    car.setAskingPrice(askingPrice);
                                    continue;
                                }
                                //Add Car to inventory
                                inventory[loop] = car;
                                loop++;
                                break;
                            }
                        } // If temp is a bus
                        else if( Objects.equals(tmp, new String("BUS")) )
                        {
                            // Instantiate new bus of type bus
                            Bus bus = new Bus(length, passCount, diesel, vid, make, model, purchasePrice, askingPrice, color);
                            for( int a = 1; a <= numberOfCol; a++ )
                            {
                                // Get column name
                                colName = metaData.getColumnName(a).toString().trim();
                                // Read next object
                                busTemp = resultSet.getObject(a).toString().trim();
                                // Convert to uppercase
                                busTemp = busTemp.toUpperCase();
                                // Skip vehicle type column
                                if( Objects.equals(colName, new String("VEH_TYPE")) )
                                {
                                    continue;
                                }
                                // Skip non applicable column
                                else if( Objects.equals(busTemp, new String("N/A")))
                                {
                                    continue;
                                }
                                // Get vehicle id
                                else if( Objects.equals(colName, new String("VEH_ID")) )
                                {
                                    vid = Integer.parseInt(busTemp);
                                    bus.setVehicleID(vid);
                                    continue;
                                }
                                // Get model
                                else if( Objects.equals(colName, new String("MODEL")) )
                                {
                                    bus.setModel(busTemp);
                                    continue;
                                }
                                // Get make
                                else if( Objects.equals(colName, new String("MAKE")) )
                                {
                                    bus.setMake(busTemp);
                                    continue;
                                }
                                // Get color
                                else if( Objects.equals(colName, new String("COLOR")) )
                                {
                                    cTemp = bus.findColor(busTemp);
                                    bus.setColor(cTemp);
                                    continue;
                                }
                                // Get passenger limit
                                else if( Objects.equals(colName, new String("PASS_LIMIT")) )
                                {
                                    passCount = Integer.parseInt(busTemp);
                                    bus.setPassCount(passCount);
                                    continue;
                                }
                                // Get length
                                else if( Objects.equals(colName, new String("LENGTH_")) )
                                {
                                    length = Integer.parseInt(busTemp);
                                    bus.setLength(length);
                                    continue;
                                }
                                // Get diesel info
                                else if( Objects.equals(colName, new String("DIESEL")) )
                                {
                                    
                                    if( Objects.equals(busTemp, new String("Y")) )
                                    {
                                        diesel = true;
                                    }
                                    else
                                    {
                                        diesel = false;
                                    }
                                    bus.setDiesel(diesel);
                                    continue;
                                }
                                // Get purchase price
                                else if( Objects.equals(colName, new String("PUR_PRICE")) )
                                {
                                    purchasePrice = Integer.parseInt(busTemp);
                                    bus.setPurchasePrice(purchasePrice);
                                    continue;
                                }
                                // Get asking price
                                else if( Objects.equals(colName, new String("ASK_PRICE")) )
                                {
                                    askingPrice = Integer.parseInt(busTemp);
                                    bus.setAskingPrice(askingPrice);
                                    continue;
                                }
                                // Add bus and done
                                inventory[loop] = bus;
                                loop++;
                                break;
                            }
                        } // If temp is a motorcycle
                        else if( Objects.equals(tmp, new String("MOTORCYCLE")) )
                        {
                            // Instantiate new bike of type motorcycle
                            Motorcycle bike = new Motorcycle(bikeWeight, bikeType, engineSize, vid, make, model, purchasePrice, askingPrice, color);
                            for( int a = 1; a <= numberOfCol; a++ )
                            {
                                // Get column name
                                colName = metaData.getColumnName(a).toString().trim();
                                // Read next bike info
                                bikeTemp = resultSet.getObject(a).toString().trim();
                                // Convert to uppercase
                                bikeTemp = bikeTemp.toUpperCase();
                                // Skip vehicle type column
                                if( Objects.equals(colName, new String("VEH_TYPE")) )
                                {
                                    continue;
                                }
                                // Skip non applicable columns
                                else if( Objects.equals(bikeTemp, new String("N/A")))
                                {
                                    continue;
                                }
                                // Get vehicle id
                                else if( Objects.equals(colName, new String("VEH_ID")) )
                                {
                                    vid = Integer.parseInt(bikeTemp);
                                    bike.setVehicleID(vid);
                                    continue;
                                }
                                // Get model
                                else if( Objects.equals(colName, new String("MODEL")) )
                                {
                                    bike.setModel(bikeTemp);
                                    continue;
                                }
                                // Get make
                                else if( Objects.equals(colName, new String("MAKE")) )
                                {
                                    bike.setMake(bikeTemp);
                                    continue;
                                }
                                // Get color
                                else if( Objects.equals(colName, new String("COLOR")) )
                                {
                                    cTemp = bike.findColor(bikeTemp);
                                    bike.setColor(cTemp);
                                    continue;
                                }
                                // Get engine size
                                else if( Objects.equals(colName, new String("ENG_SIZE")) )
                                {
                                    engineSize = Integer.parseInt(bikeTemp);
                                    bike.setEngineSize(engineSize);
                                    continue;
                                }
                                // Get weight
                                else if( Objects.equals(colName, new String("WEIGHT_")) )
                                {
                                    bikeWeight = Integer.parseInt(bikeTemp);
                                    bike.setWeight(bikeWeight);
                                    continue;
                                }
                                // Get type
                                else if( Objects.equals(colName, new String("TYPE_")) )
                                {
                                    bikeType = bikeTemp;
                                    bike.setType(bikeType);
                                    continue;
                                }
                                // Get purchase price
                                else if( Objects.equals(colName, new String("PUR_PRICE")) )
                                {
                                    purchasePrice = Integer.parseInt(bikeTemp);
                                    bike.setPurchasePrice(purchasePrice);
                                    continue;
                                }
                                // Get asking price
                                else if( Objects.equals(colName, new String("ASK_PRICE")) )
                                {
                                    askingPrice = Integer.parseInt(bikeTemp);
                                    bike.setAskingPrice(askingPrice);
                                    continue;
                                }
                                // Add bike and done
                                inventory[loop] = bike;
                                loop++;
                                break;
                            }
                        }
                    
                    }
                }
            }
            catch( Exception ex )
            {
                
            }
        }
    }
    
    private class DeleteButtonListener implements ActionListener
    {
/***************************************************************
*** Method Name: actionPerformed
*** Method Author: Austin LeBel
****************************************************************
*** Purpose of the Method: Performs action on customer offer
*** button that submits a customers offer to purchase a vehicle
*** and deletes it from the inventory if sold. Does not delete
*** from database.
*** Method Inputs: ActionEvent e
*** Return Value: N/A
****************************************************************
*** Date: 12/5/2016
****************************************************************
***************************************************************/
        public void actionPerformed( ActionEvent e )
        {
            // Try catch begin
            try
            {
                // Instantiate tempLM of type LayoutManager for method access
                LayoutManager tempLM = new LayoutManager();
                // Instantiate temp of type Vehicle for method access
                Vehicle temp = new Vehicle( vid, make, model, purchasePrice, askingPrice, color );
                // Decrement vehicle counter after temporary object is created
                temp.remove();
                
                tempLM.deleteCurVehicle(inventory, index, textArea);
                // If last vehicle sold
                if( inventory[0] == null )
                {
                    deleteVeh.setEnabled(false);
                    textArea.setText("");
                }
            }
            // Catch invalid number
            catch( NumberFormatException ex )
            {
                // Display error message
                JOptionPane.showMessageDialog(null, "Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
            }// End try catch
        }
    }
}


