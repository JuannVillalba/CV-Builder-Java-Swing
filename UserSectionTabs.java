package cvbuilder.View;

import cvbuilder.Models.CVData;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

public class UserSectionTabs extends JTabbedPane {

    UserSectionPanel namePanel;
    UserSectionPanel titlePanel;
    UserSectionPanel emailPanel;

    // CREATES TAB of TABs called "USER" and adds the panels to it
    public UserSectionTabs(CVData cvData) {
        super();

        namePanel = new UserSectionPanel(cvData.getUserNames());
        this.addTab("User Names", createTabPanel(namePanel));
        cvData.getObservers().add((Observer) namePanel);

        titlePanel = new UserSectionPanel(cvData.getUserTitles());
        this.addTab("User Titles", createTabPanel(titlePanel));
        cvData.getObservers().add((Observer) titlePanel);

        emailPanel = new UserSectionPanel(cvData.getUserEmails());
        this.addTab("User Emails", createTabPanel(emailPanel));
        cvData.getObservers().add((Observer) emailPanel);

    }

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
    public UserSectionPanel getNamePanel() {
        return namePanel;
    }

    public void setNamePanel(UserSectionPanel namePanel) {
        this.namePanel = namePanel;
    }

    public UserSectionPanel getEmailPanel() {
        return emailPanel;
    }

    public void setEmailPanel(UserSectionPanel emailPanel) {
        this.emailPanel = emailPanel;
    }

    public UserSectionPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(UserSectionPanel titlePanel) {
        this.titlePanel = titlePanel;
    }
}