package cvbuilder.View;

import javax.swing.*;

import cvbuilder.Controller.EditDeleteController;

import java.awt.*;

public class UserSectionRow extends JPanel {
    private JRadioButton radioButton;
    private JButton editButton;
    private JButton deleteButton;
    private String currentText; // Store the current text

    // SECTION PANEL eg. User Names (tab)
    // SECTION ROWS eg. SJ Taylor | 0 | EDIT | DELETE

    public UserSectionRow(String text) {
        // Set layout to center the components
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.currentText = text; // Initialize text

        // create Controller instance
        EditDeleteController controller = new EditDeleteController(this);

        // Create a radio button with the provided text
        radioButton = new JRadioButton(text);
        this.add(radioButton);

        // Create an "Edit" button
        editButton = new JButton("Edit");
        editButton.addActionListener(controller);
        editButton.setActionCommand("edit");
        this.add(editButton);

        // Create a "Delete" button
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(controller);
        deleteButton.setActionCommand("delete");
        this.add(deleteButton);
    }

    public JRadioButton getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(JRadioButton radioButton) {
        this.radioButton = radioButton;
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setText(String newText) {
        this.currentText = newText;
        radioButton.setText(newText);
    }
}
