package cvbuilder.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDialog extends javax.swing.JDialog {

    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;

    private boolean confirmed = false; // To track if the user confirmed the dialog

    public AddDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        nameLabel = new javax.swing.JLabel("Name:");
        nameTextField = new javax.swing.JTextField(20);
        titleLabel = new javax.swing.JLabel("Title:");
        titleTextField = new javax.swing.JTextField(20);
        emailLabel = new javax.swing.JLabel("Email:");
        emailTextField = new javax.swing.JTextField(20);
        addButton = new javax.swing.JButton("Add");
        cancelButton = new javax.swing.JButton("Cancel");

        setLayout(new java.awt.GridLayout(4, 2));
        add(nameLabel);
        add(nameTextField);
        add(titleLabel);
        add(titleTextField);
        add(emailLabel);
        add(emailTextField);
        add(addButton);
        add(cancelButton);

        setTitle("Add New User");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the dialog on the screen

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true; // Mark as confirmed
                dispose(); // close
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = false; // Mark as not confirmed
                dispose();
            }
        });
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getNameInput() {
        return nameTextField.getText();
    }

    public String getTitleInput() {
        return titleTextField.getText();
    }

    public String getEmailInput() {
        return emailTextField.getText();
    }
}
