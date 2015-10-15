package com.bord;

import javax.swing.*;
import java.awt.*;

public class CharacterSheetForm implements Runnable {
    JFrame Form;

    @Override
    public void run() {
        // Create the window
        Form = new JFrame("Hello, !");
        JTabbedPane PaneList = new JTabbedPane();

        // Sets the behavior for when the window is closed
        Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add a layout manager so that the button is not placed on top of the label
        JPanel Page1 = new JPanel();
        JPanel Page2 = new JPanel();
        JPanel Page3 = new JPanel();

        Page1.setLayout(new BoxLayout(Page1,1));
        Page2.setLayout(new BoxLayout(Page2,1));
        Page3.setLayout(new BoxLayout(Page3,1));

        PaneList.addTab("Page1", Page1);
        PaneList.addTab("Page2",Page2);
        PaneList.addTab("Page2",Page3);

        Form.add(PaneList);

        // Add a label and a button
        AddSection(Page1,"Name");
        AddSection(Page1,"Gender");
        AddSection(Page1,"Age");
        AddSection(Page1,"Race");
        AddSection(Page1,"Civilian Clothing");
        AddSection(Page1,"Uniform");

        // Arrange the components inside the window
        Form.pack();
        // By default, the window is not visible. Make it visible.
        Form.setVisible(true);
    }

    public void AddSection(Container putitin,String label)
    {
        JTextField shit = new JTextField();
        shit.setPreferredSize(new Dimension(200, 30));

        JPanel thebest = new JPanel();
        thebest.setLayout(new BorderLayout(0,0));
        thebest.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.red),
                thebest.getBorder()));
        thebest.add(new JLabel(label + ":"),BorderLayout.WEST);
        thebest.add(Box.createHorizontalGlue());
        thebest.add(shit,BorderLayout.EAST);
        putitin.add(thebest);
    }

    public static void main(String[] args) {
        CharacterSheetForm se = new CharacterSheetForm();
        // Schedules the application to be run at the correct time in the event queue.
        SwingUtilities.invokeLater(se);
    }
}
