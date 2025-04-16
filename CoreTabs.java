package cvbuilder.View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CoreTabs extends javax.swing.JTabbedPane {

    UserSectionPanel skillsPanel;
    UserSectionPanel profilePanel;

    // CREATES TAB of TABs called "CORE" and adds the panels to it
    public CoreTabs(cvbuilder.Models.CVData cvData) {
        super();

        // CREATE TABS and add, for skills and profile statements
        skillsPanel = new SkillsPanel(cvData.getCoreSkills());
        this.addTab("Skills", createTabPanel(skillsPanel));
        cvData.getObservers().add((cvbuilder.View.Observer) skillsPanel);

        profilePanel = new ProfilePanel(cvData.getCoreProfileStatement());
        this.addTab("Profile Statement", createTabPanel(profilePanel));
        cvData.getObservers().add((cvbuilder.View.Observer) profilePanel);

    }

    // create styled panel containers
    private JPanel createTabPanel(JComponent content) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add scrolling capability
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        panel.add(scrollPane, BorderLayout.CENTER);

        // Create navigation buttons in a panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton prevButton = new JButton("Previous");
        prevButton.addActionListener(e -> navigatePrevious());

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> navigateNext());

        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        // Add the navigation panel to the bottom
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Navigate to previous tab
    public void navigatePrevious() {
        int currentIndex = this.getSelectedIndex();
        if (currentIndex > 0) {
            this.setSelectedIndex(currentIndex - 1);
        }
    }

    // Navigate to next tab
    public void navigateNext() {
        int currentIndex = this.getSelectedIndex();
        if (currentIndex < this.getTabCount() - 1) {
            this.setSelectedIndex(currentIndex + 1);
        }
    }

    // getters and setters for panels
    public UserSectionPanel getSkillsPanel() {
        return skillsPanel;
    }

    public void setSkillsPanel(UserSectionPanel skillsPanel) {
        this.skillsPanel = skillsPanel;
    }

    public UserSectionPanel getProfilePanel() {
        return profilePanel;
    }

    public void setProfilePanel(UserSectionPanel profilePanel) {
        this.profilePanel = profilePanel;
    }
}
