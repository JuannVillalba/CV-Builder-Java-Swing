package cvbuilder.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import cvbuilder.Models.CVData;
import cvbuilder.View.UserSectionRow;
import cvbuilder.View.MainView;
import cvbuilder.View.Observer;
import cvbuilder.View.UserSectionPanel;

public class EditDeleteController implements ActionListener {

    UserSectionRow view;

    public EditDeleteController(UserSectionRow view) {
        this.view = view;
    }

    public UserSectionRow getView() {
        return view;
    }

    public void setView(UserSectionRow view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent s) {

        /**
         * Gets the parent component of the current view and we cast it to SectionPanel.
         * we retrieve the section panel that contains the current view component,
         * allowing access to Section properties.
         * so then we can print the all the values in the section to the user
         */
        UserSectionPanel sectionPanel = (UserSectionPanel) view.getParent();

        switch (s.getActionCommand()) {
            case "edit":
                String rowText = view.getCurrentText();
                System.out.println("Edit clicked for: " + rowText);
                String newText = JOptionPane.showInputDialog("Enter new value for", rowText);
                if (newText != null && !newText.isEmpty()) {
                    System.out.println("BEFORE: " + sectionPanel.getData());

                    // Get the index of the old text and replace it with the new text
                    // in the data arraylist, then update the panel
                    int oldTextIndex = sectionPanel.getData().indexOf(rowText);
                    sectionPanel.getData().set(oldTextIndex, newText);

                    // Update the radio button text
                    view.setText(newText);

                    // Force panel update
                    sectionPanel.update();

                    System.out.println("AFTER: " + sectionPanel.getData());
                    System.out.println("Updated to: " + newText);
                }
                break;

            case "delete":
                String textToDelete = view.getCurrentText();
                System.out.println("Delete clicked for: " + view.getRadioButton().getText());
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        " Are you sure you want to delete: " + textToDelete + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    sectionPanel.getData().remove(textToDelete);
                    sectionPanel.update();
                }
                break;

            default:
                throw new UnsupportedOperationException("unknown action command");

        }
    }
}
