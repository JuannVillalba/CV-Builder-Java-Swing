package cvbuilder.View;

import cvbuilder.Controller.SaveController;
import cvbuilder.Controller.PreviewController;
import cvbuilder.Models.CVData;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// MainView class to represent the main view of the application
// This class is responsible for creating the main window of the application

public class MainView extends JFrame {
    // Instance variables
    private JTabbedPane tabbedPane;
    private SaveController saveController;
    private PreviewController previewController;

    // Constructor
    public MainView(CVData cvData) {
        super();
        this.setLayout(new BorderLayout());
        this.setTitle("User Profile Builder");
        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize menu controllers
        saveController = new SaveController();
        previewController = new PreviewController();

        // Create Tabbed Pane
        UserSectionTabs userTabs = new UserSectionTabs(cvData);
        CoreTabs coreTabs = new CoreTabs(cvData);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("User", userTabs);
        tabbedPane.addTab("Core", coreTabs);

        // Style tabbed pane
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setForeground(new Color(60, 60, 60));
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // Add a bit of padding around the main area
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(tabbedPane, BorderLayout.CENTER);

        // Add a title to the Window "CV Builder"
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("CV Builder");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);

        // Add the menu bar
        createMenuBar();

        this.setVisible(true);

        // Notify observers
        cvData.notifyObservers();
    }

    // Method to create the menu bar
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Create "File" menu
        JMenu fileMenu = new JMenu("File");

        // Add "Save File" menu item
        JMenuItem saveFileItem = new JMenuItem("Save File");
        saveFileItem.addActionListener(e -> saveController.saveToFile(tabbedPane));
        fileMenu.add(saveFileItem);

        // Add "Preview" menu item
        JMenuItem previewItem = new JMenuItem("Preview");
        previewItem.addActionListener(e -> previewController.generatePreview(tabbedPane));
        fileMenu.add(previewItem);

        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }
}
