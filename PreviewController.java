package cvbuilder.Controller;

import cvbuilder.View.CoreTabs;
import cvbuilder.View.UserSectionPanel;
import cvbuilder.View.UserSectionRow;
import cvbuilder.View.UserSectionTabs;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class PreviewController {

    // Generate a text preview of selected radio button values
    public void generatePreview(JTabbedPane tabbedPane) {
        try {
            // Build the preview text
            StringBuilder preview = new StringBuilder();
            preview.append("                      CURRICULUM VITAE\n");
            preview.append("=======================================================\n\n");

            // User section
            UserSectionTabs userTabs = (UserSectionTabs) tabbedPane.getComponentAt(0);
            addUserSection(preview, userTabs);

            // Core section
            CoreTabs coreTabs = (CoreTabs) tabbedPane.getComponentAt(1);
            addCoreSection(preview, coreTabs);

            // Show preview dialog
            showPreview(preview.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error generating preview: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showPreview(String previewText) {
        // Create dialog
        JDialog dialog = new JDialog((Frame) null, "CV Preview", true);
        dialog.setSize(700, 500);
        dialog.setLocationRelativeTo(null);

        // Create text area
        JTextArea textArea = new JTextArea(previewText);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setMargin(new Insets(20, 20, 20, 20));

        // Add to scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        // Set layout
        dialog.setLayout(new BorderLayout());
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Show dialog
        dialog.setVisible(true);
    }

    // Add user section to preview
    private void addUserSection(StringBuilder preview, UserSectionTabs userTabs) {
        if (userTabs != null) {
            preview.append("PERSONAL INFORMATION\n");
            preview.append("-------------------\n");

            // Add name
            if (userTabs.getNamePanel() != null) {
                String name = getSelectedText(userTabs.getNamePanel());
                if (name != null) {
                    preview.append("Name: ").append(name).append("\n");
                }
            }

            // Add title
            if (userTabs.getTitlePanel() != null) {
                String title = getSelectedText(userTabs.getTitlePanel());
                if (title != null) {
                    preview.append("Title: ").append(title).append("\n");
                }
            }

            // Add email
            if (userTabs.getEmailPanel() != null) {
                String email = getSelectedText(userTabs.getEmailPanel());
                if (email != null) {
                    preview.append("Email: ").append(email).append("\n");
                }
            }

            preview.append("\n");
        }
    }

    // Add core section to preview
    private void addCoreSection(StringBuilder preview, CoreTabs coreTabs) {
        if (coreTabs != null) {
            // Add profile statement
            if (coreTabs.getProfilePanel() != null) {
                String profile = getSelectedText(coreTabs.getProfilePanel());
                if (profile != null) {
                    preview.append("PROFILE STATEMENT\n");
                    preview.append("----------------\n");
                    preview.append(profile).append("\n\n");
                }
            }

            // Add skills
            if (coreTabs.getSkillsPanel() != null) {
                String skills = getSelectedText(coreTabs.getSkillsPanel());
                if (skills != null) {
                    preview.append("SKILLS\n");
                    preview.append("------\n");
                    preview.append(skills).append("\n\n");
                }
            }
        }
    }

    // Helper method to get the text of the selected radio button
    private String getSelectedText(UserSectionPanel panel) {
        if (panel == null || panel.getButtonGroup() == null) {
            return null;
        }

        Enumeration<AbstractButton> buttons = panel.getButtonGroup().getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                // Find the SectionRow that contains this button
                Component parent = button.getParent();
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