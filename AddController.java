package cvbuilder.Controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import cvbuilder.View.UserSectionPanel;

public class AddController implements ActionListener {
    // This class is responsible for handling the logic of adding a user to the CV
    // data.
    // It will interact with the CVData model and update the view accordingly.

    UserSectionPanel view; // The view that this controller will manage
    // The view will be passed to the constructor of this controller
    private String name;
    private String title;
    private String email;

    public AddController(UserSectionPanel view) {

        this.view = view;
    }

    public void addUser() {
        // Logic to add user to the CV data
        // This will involve updating the CVData model and notifying observers

        System.out.println("User added: " + name + ", " + title + ", " + email);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get parent container's name to determine which section we're in
        String sectionName = getSectionName();

        // Create a simple dialog based on the section
        JTextField inputField = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter new " + sectionName + ":"));
        panel.add(inputField);

        int result = JOptionPane.showConfirmDialog(
                null, panel, "Add " + sectionName,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        // Process the result
        if (result == JOptionPane.OK_OPTION && !inputField.getText().isEmpty()) {
            String newValue = inputField.getText();

            // Add the new value to the data
            view.getData().add(newValue);

            // Update the view
            view.update();

            System.out.println("Added new " + sectionName + ": " + newValue);
        }
    }

    private String getSectionName() {
        Container parent = view.getParent();
        while (parent != null && !(parent instanceof JTabbedPane)) {
            parent = parent.getParent();
        }

        if (parent != null) {
            JTabbedPane tabbedPane = (JTabbedPane) parent;
            int selectedIndex = tabbedPane.indexOfComponent(view);
            if (selectedIndex != -1) {
                String title = tabbedPane.getTitleAt(selectedIndex);
                // Extract section name (e.g., "User Names" -> "Name")
                if (title.contains("Names"))
                    return "Name";
                if (title.contains("Titles"))
                    return "Title";
                if (title.contains("Emails"))
                    return "Email";
            }

        }

        // Fallback if we can't determine the section
        return "value";

        // System.out.println("Add button clicked!" + view.getData());

        /*
         * System.out.println("Add button clicked!" + view.getData());
         * String newValue = JOptionPane.showInputDialog("Enter new value:");
         * if (newValue != null && !newValue.isEmpty()) {
         * // Add the new value to the data
         * view.getData().add(newValue);
         * 
         * // Update the view to show the new data
         * view.update();
         * 
         * System.out.println("Added: " + newValue);
         * 
         * 
         * }
         */
    }

}
