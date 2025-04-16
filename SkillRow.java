package cvbuilder.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import cvbuilder.Controller.EditDeleteController;

public class SkillRow extends UserSectionRow {
    private JTextArea textArea;

    public SkillRow(String text) {
        super(text);

        // Reset the layout
        this.removeAll();
        this.setLayout(new BorderLayout(10, 0));

        // Create a panel for the radio button and text area (left side)
        JPanel leftPanel = new JPanel(new BorderLayout(5, 0));
        leftPanel.setBackground(this.getBackground());

        // Keep the radio button (for selection)
        JRadioButton radioButton = getRadioButton();
        radioButton.setText(""); // Clear the text from the radio button
        leftPanel.add(radioButton, BorderLayout.WEST);

        // Add a text area for wrapped text display
        textArea = new JTextArea(text);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        textArea.setRows(2); // Show up to 2 rows of text
        leftPanel.add(textArea, BorderLayout.CENTER);

        this.add(leftPanel, BorderLayout.CENTER);

        // Create button panel (right side)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Create controller
        EditDeleteController controller = new EditDeleteController(this);

        // Create buttons
        JButton editButton = new JButton("Edit");
        editButton.setActionCommand("edit");
        editButton.addActionListener(controller);
        editButton.setBackground(new Color(230, 230, 250));

        JButton deleteButton = new JButton("Delete");
        deleteButton.setActionCommand("delete");
        deleteButton.addActionListener(controller);
        deleteButton.setBackground(new Color(255, 230, 230));

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        this.add(buttonPanel, BorderLayout.EAST);

        // size constraints for wrapped text need
        this.setPreferredSize(new Dimension(750, 60));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    @Override
    public void setText(String newText) {
        super.setText(newText);
        if (textArea != null) {
            textArea.setText(newText); // Update the text area
        }
    }

    @Override
    public String getCurrentText() {
        if (textArea != null) {
            return textArea.getText();
        } else {
            return null;
        }
    }
}
