package com.bord;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.bord.sections.*;

public class CharacterSheetForm implements Runnable {
    JFrame Form;
    JTabbedPane PaneList;

    public CharacterSheet CoolSheet;
    public ArrayList<Container> Pages = new ArrayList<>();

    @Override
    public void run() {
        // Create the window
        Form = new JFrame("Hello, !");
        PaneList = new JTabbedPane();

        // Sets the behavior for when the window is closed
        Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CoolSheet = new CharacterSheet();
        CoolSheet.AddSection(new TextSection("name","Name",1,200,30));
        CoolSheet.AddSection(new TextSection("gender","Gender",1,200,30));
        CoolSheet.AddSection(new TextSection("age","Age",1,200,30));
        CoolSheet.AddSection(new TextSection("race","Race",1,200,30));
        CoolSheet.AddSection(new TextSection("clothes","Civilian Clothes",1,200,30));
        CoolSheet.AddSection(new TextSection("uniform","Uniform",1,200,30));
        CoolSheet.AddSection(new BigTextSection("abilities","Abilities",1,200,30));
        CoolSheet.AddSection(new BigTextSection("relationships","Relationships",1,200,30));
        CoolSheet.AddSection(new BigTextSection("influence","Influence",1,200,30));
        CoolSheet.AddSection(new BigTextSection("mentor","Mentor",1,200,30));
        CoolSheet.AddSection(new BigTextSection("class", "Class", 2, 200, 30));

        GridSection Conditions = new GridSection("conditions","Conditions",2,3);
        Conditions.AddEntry(new CheckboxSection("afraid",null,0));
        Conditions.AddEntry(new LabelSection(null,"AFRAID",0));
        Conditions.AddEntry(new LabelSection(null,"(-2 to directly engage)",0));
        Conditions.AddEntry(new CheckboxSection("angry",null,0));
        Conditions.AddEntry(new LabelSection(null,"ANGRY",0));
        Conditions.AddEntry(new LabelSection(null,"(-2 to change self-image or pierce the mask)",0));
        Conditions.AddEntry(new CheckboxSection("guilty",null,0));
        Conditions.AddEntry(new LabelSection(null,"GUILTY",0));
        Conditions.AddEntry(new LabelSection(null,"(-2 to provoke someone or assess the situation)",0));
        Conditions.AddEntry(new CheckboxSection("insecure",null,0));
        Conditions.AddEntry(new LabelSection(null,"INSECURE",0));
        Conditions.AddEntry(new LabelSection(null,"(-2 to stand in defense or reject what others say)",0));
        Conditions.AddEntry(new CheckboxSection("hopeless",null,0));
        Conditions.AddEntry(new LabelSection(null,"HOPELESS",0));
        Conditions.AddEntry(new LabelSection(null,"(-2 to unleash your powers)",0));

        CoolSheet.AddSection(Conditions);
        CoolSheet.AddSection(new BigTextSection("moves","Moves",2,200,30));

        MakeSheet();

        //Page1.setLayout(new BoxLayout(Page1,1));
        //Page2.setLayout(new BoxLayout(Page2,1));
        //Page3.setLayout(new BoxLayout(Page3,1));

        Form.add(PaneList);

        // Add a label and a button
        for(Container page : Pages) {
            page.add(Box.createVerticalGlue());
        }

        // Arrange the components inside the window
        Form.pack();
        // By default, the window is not visible. Make it visible.
        Form.setVisible(true);
    }

    public void MakeSheet()
    {
        for(SheetSection section : CoolSheet.Sections)
        {
            Container page = null;
            int pageindex = section.Page-1;

            if(Pages.size() > pageindex)
            {
                page = Pages.get(pageindex);
            }

            if(page == null) {
                page = new JPanel();
                page.setLayout(new BoxLayout(page,1));
                Pages.add(pageindex,page);
                PaneList.addTab("Page"+Integer.toString(pageindex+1), page);
            }

            AddSection(page,section);
        }
    }

    public void AddSection(Container putitin,SheetSection section)
    {
        JPanel thebest = new JPanel();
        thebest.setLayout(new BorderLayout(0, 0));
        thebest.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.red),
                thebest.getBorder()));
        thebest.add(new JLabel(section.Name + ":"), BorderLayout.WEST);
        thebest.add(Box.createHorizontalGlue());
        if(section.Component != null) {
            thebest.add(section.Component,BorderLayout.EAST);
        }
        putitin.add(thebest);
        putitin.add(Box.createHorizontalGlue());
        //thebest.add(Box.createVerticalGlue(),BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        CharacterSheetForm se = new CharacterSheetForm();
        // Schedules the application to be run at the correct time in the event queue.
        SwingUtilities.invokeLater(se);
    }
}
