package cvbuilder.View;

import javax.swing.JPanel;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.util.ArrayList;

//Panel class to represent groups of cvbuilder User data, switching between
// Name
//-Title
//— Email
// uses observer pattern to update the user data

public class UserSectionPanel extends JPanel implements Observer {

    private ArrayList<String> data; // List of data to be displayed
    private ButtonGroup buttonGroup; // Group for radio buttons

    // SECTION PANEL eg. User Names (tab)
    // SECTION ROWS eg. SJ Taylor | 0 | EDIT | DELETE

    public UserSectionPanel(ArrayList<String> inputData) {
        super();
        this.data = inputData;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Vertical layout
        // Create rows for each item in the list
        update();

    }

    // getter for data arraylist
    public ArrayList<String> getData() {
        return data;
    }

    // getter for button group
    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    // setter for button group
    public void setButtonGroup(ButtonGroup buttonGroup) {
        this.buttonGroup = buttonGroup;
    }

    @Override
    // Refresh the rows when the data changes
    public void update() {
        this.removeAll(); // remove existing rows
        buttonGroup = new ButtonGroup(); // Create a new button group

        for (String item : data) {
            UserSectionRow userSectionRow = new UserSectionRow(item);
            buttonGroup.add(userSectionRow.getRadioButton()); // Add the radio button to the group
            this.add(userSectionRow); // Add the row to the panel
        }

        /**
         * ADD button is plcaed at the bottom of the section panel.
         * If the panel is completely empty (getComponentCount() == 0), or
         * If the last component in the panel is not an AddRow
         * display ADD button.
         */
        if (this.getComponentCount() == 0 || !(this.getComponent(this.getComponentCount() - 1) instanceof AddRow)) {
            this.add(new AddRow(this));
        }
        this.revalidate(); // Refresh the panel
        this.repaint();
    }
}
