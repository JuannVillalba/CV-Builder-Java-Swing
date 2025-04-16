package cvbuilder;

import cvbuilder.View.MainView;
import cvbuilder.Models.CVData;
import cvbuilder.Models.User;
import cvbuilder.Controller.EditDeleteController;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class App {

    public static void main(String[] args) {

        // load from csv file
        CVData cvData = new CVData();

        // create main view and pass cvData
        new MainView(cvData);

    }

}
