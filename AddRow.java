package cvbuilder.View;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JPanel;

import cvbuilder.Controller.AddController;
import cvbuilder.Models.User;

public class AddRow extends JPanel {
    private String name;
    private String title;
    private String email;
    private JButton addButton = new JButton("Add");

    public AddRow(UserSectionPanel panel) {
        // Set layout to center the components

        // create add button
        addButton = new JButton("Add New Item");

        // layout for the add button
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create a new UserAddController and pass the panel to it
        // This controller will handle the logic for adding a new user
        AddController controller = new AddController(panel);
        addButton.addActionListener(controller);
        addButton.setActionCommand("add");
        this.add(addButton);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
