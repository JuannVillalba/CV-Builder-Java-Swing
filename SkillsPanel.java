package cvbuilder.View;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;

public class SkillsPanel extends UserSectionPanel {

    public SkillsPanel(ArrayList<String> skills) {
        super(skills);
    }

    @Override
    public final void update() {
        this.removeAll();
        setButtonGroup(new ButtonGroup());

        // Create skill rows for each item
        for (String skill : getData()) {
            // Create a new SkillRow for each skill and add it to the button group
            SkillRow skillRow = new SkillRow(skill);
            getButtonGroup().add(skillRow.getRadioButton());
            this.add(skillRow);
        }

        // Add the "Add" button row
        this.add(new AddRow(this));

        this.revalidate();
        this.repaint();
    }
}