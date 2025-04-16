package cvbuilder.Controller;

import cvbuilder.View.CoreTabs;
import cvbuilder.View.UserSectionPanel;
import cvbuilder.View.UserSectionRow;
import cvbuilder.View.UserSectionTabs;

import javax.swing.*;

import java.awt.Container;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;

public class SaveController {

    // Save selected radio button values to a CSV file
    public void saveToFile(JTabbedPane tabbedPane) {
        try (FileWriter writer = new FileWriter("data/saved_CV.csv")) {
            writer.write("Section,Sub-section,Selected Item\n");

            // Save User selections
            UserSectionTabs userTabs = (UserSectionTabs) tabbedPane.getComponentAt(0);
            saveUserSection(writer, userTabs);

            // Save Core selections
            CoreTabs coreTabs = (CoreTabs) tabbedPane.getComponentAt(1);
            saveCoreSection(writer, coreTabs);

            JOptionPane.showMessageDialog(null, "CV saved to saved_CV.csv");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Save user section selections
    private void saveUserSection(FileWriter writer, UserSectionTabs userTabs) throws IOException {
        if (userTabs != null) {
            // Save Name
            if (userTabs.getNamePanel() != null) {
                String selectedName = getSelectedText(userTabs.getNamePanel());
                if (selectedName != null) {
                    writer.write("User,Name," + selectedName + "\n");
                }
            }

            // Save Title
            if (userTabs.getTitlePanel() != null) {
                String selectedTitle = getSelectedText(userTabs.getTitlePanel());
                if (selectedTitle != null) {
                    writer.write("User,Title," + selectedTitle + "\n");
                }
            }

            // Save Email
            if (userTabs.getEmailPanel() != null) {
                String selectedEmail = getSelectedText(userTabs.getEmailPanel());
                if (selectedEmail != null) {
                    writer.write("User,Email," + selectedEmail + "\n");
                }
            }
        }
    }

    // Save core section selections
    private void saveCoreSection(FileWriter writer, CoreTabs coreTabs) throws IOException {
        if (coreTabs != null) {
            // Save Profile Statement
            if (coreTabs.getProfilePanel() != null) {
                String selectedProfile = getSelectedText(coreTabs.getProfilePanel());
                if (selectedProfile != null) {
                    writer.write("Core competencies,Profile Statement," + selectedProfile + "\n");
                }
            }

            // Save Skills
            if (coreTabs.getSkillsPanel() != null) {
                String selectedSkills = getSelectedText(coreTabs.getSkillsPanel());
                if (selectedSkills != null) {
                    writer.write("Core competencies,Skills," + selectedSkills + "\n");
                }
            }
        }
    }

    // Get the text of the selected button
    private String getSelectedText(UserSectionPanel panel) {
        if (panel == null || panel.getButtonGroup() == null) {
            return null;
        }

        Enumeration<AbstractButton> buttons = panel.getButtonGroup().getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                // Find the SectionRow that contains this button
                Container parent = button.getParent();
                while (parent != null && !(parent instanceof UserSectionRow)) {
                    parent = parent.getParent();
                }

                if (parent instanceof UserSectionRow) {
                    return ((UserSectionRow) parent).getCurrentText();
                }
            }
        }
        return null;
    }
}