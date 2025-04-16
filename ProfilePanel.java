package cvbuilder.View;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;

public class ProfilePanel extends UserSectionPanel {

    public ProfilePanel(ArrayList<String> profileStatements) {
        super(profileStatements);
    }

    @Override
    public final void update() {
        this.removeAll();
        setButtonGroup(new ButtonGroup());

        // Create profile statement rows for each item
        for (String statement : getData()) {
            ProfileRow profileRow = new ProfileRow(statement);
            getButtonGroup().add(profileRow.getRadioButton());
            this.add(profileRow);
        }

        // Add the "Add" button row
        this.add(new AddRow(this));

        this.revalidate();
        this.repaint();
    }
}