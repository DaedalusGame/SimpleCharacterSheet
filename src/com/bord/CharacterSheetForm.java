package com.bord;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.*;
import com.bord.sections.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class CharacterSheetForm implements Runnable,ActionListener,ItemListener {
    JFrame Form;
    JTabbedPane PaneList;

    final JFileChooser FileChooser = new JFileChooser();

    public CharacterSheet CoolSheet;
    public ArrayList<Container> Pages = new ArrayList<>();

    ConfigManager Config = new ConfigManager();

    JMenuItem menuNew;
    JMenuItem menuSave;
    JMenuItem menuSaveAs;
    JMenuItem menuLoad;

    @Override
    public void run() {
        // Create the window
        Form = new JFrame("Hello, !");
        PaneList = new JTabbedPane();

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");

        menuBar.add(menuFile);

        /*menuNew = new JMenuItem("New");
        menuNew.addActionListener(this);
        menuNew.setActionCommand("new");
        menuFile.add(menuNew);*/
        menuLoad = new JMenuItem("Load");
        menuLoad.addActionListener(this);
        menuLoad.setActionCommand("load");
        menuFile.add(menuLoad);
        menuSave = new JMenuItem("Save");
        menuSave.addActionListener(this);
        menuSave.setActionCommand("save");
        menuFile.add(menuSave);
        menuSaveAs = new JMenuItem("Save-as");
        menuSaveAs.addActionListener(this);
        menuSaveAs.setActionCommand("saveas");
        menuFile.add(menuSaveAs);


        Form.setJMenuBar(menuBar);

        Form.setPreferredSize(new Dimension(800, 600));
        Form.setResizable(false);

        FileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return getExtension(f) == "json" || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Only charactersheets (.json)";
            }
        });

        // Sets the behavior for when the window is closed
        Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CoolSheet = new CharacterSheet();
        CoolSheet.AddSection(new TextSection("name","Name",1,200,20));
        CoolSheet.AddSection(new TextSection("gender","Gender",1,200,20));
        CoolSheet.AddSection(new TextSection("age","Age",1,200,20));
        CoolSheet.AddSection(new TextSection("race","Race",1,200,20));
        CoolSheet.AddSection(new TextSection("clothes","Civilian Clothes",1,200,20));
        CoolSheet.AddSection(new TextSection("uniform","Uniform",1,200,20));
        CoolSheet.AddSection(new BigTextSection("abilities","Abilities",1,200,100));
        CoolSheet.AddSection(new BigTextSection("relationships","Relationships",1,200,100));

        BasicTable<Influence> influences = new BasicTable<Influence>(new Influence("",false,false));

        //influences.Values.add(new Influence("",false,false));

        CoolSheet.AddSection(new TableSection("influence","Influence",1,influences));
        CoolSheet.AddSection(new BigTextSection("mentor","Mentor",1,200,100));
        CoolSheet.AddSection(new BigTextSection("class", "Class", 2, 200, 100));

        GridSection Labels = new GridSection("labels","Labels",2,7);
        // header
        Labels.AddEntry(new LabelSection("header","",0));
        Labels.AddEntry(new LabelSection("header-2","-2",0));
        Labels.AddEntry(new LabelSection("header-1","-1",0));
        Labels.AddEntry(new LabelSection("header0","0",0));
        Labels.AddEntry(new LabelSection("header+1","+1",0));
        Labels.AddEntry(new LabelSection("header+2","+2",0));
        Labels.AddEntry(new LabelSection("header+3","+3",0));
        // freak
        Labels.AddEntry(new LabelSection("freak_label","Freak",0));
        RadioGroupSection freak_group = new RadioGroupSection("freak",null,0,"freak0");
        Labels.AddEntry(freak_group);
        RadioSection freak_button_n2 = new RadioSection("freak-2",null,0);
        RadioSection freak_button_n1 = new RadioSection("freak-1",null,0);
        RadioSection freak_button_0 = new RadioSection("freak0",null,0);
        RadioSection freak_button_p1 = new RadioSection("freak+1",null,0);
        RadioSection freak_button_p2 = new RadioSection("freak+2",null,0);
        RadioSection freak_button_p3 = new RadioSection("freak+3",null,0);
        freak_group.AddButton(freak_button_n2);
        freak_group.AddButton(freak_button_n1);
        freak_group.AddButton(freak_button_0);
        freak_group.AddButton(freak_button_p1);
        freak_group.AddButton(freak_button_p2);
        freak_group.AddButton(freak_button_p3);
        Labels.AddEntry(freak_button_n2);
        Labels.AddEntry(freak_button_n1);
        Labels.AddEntry(freak_button_0);
        Labels.AddEntry(freak_button_p1);
        Labels.AddEntry(freak_button_p2);
        Labels.AddEntry(freak_button_p3);
        // danger
        Labels.AddEntry(new LabelSection("danger_label","Danger",0));
        RadioGroupSection danger_group = new RadioGroupSection("danger",null,0,"danger0");
        Labels.AddEntry(danger_group);
        RadioSection danger_button_n2 = new RadioSection("danger-2",null,0);
        RadioSection danger_button_n1 = new RadioSection("danger-1",null,0);
        RadioSection danger_button_0 = new RadioSection("danger0",null,0);
        RadioSection danger_button_p1 = new RadioSection("danger+1",null,0);
        RadioSection danger_button_p2 = new RadioSection("danger+2",null,0);
        RadioSection danger_button_p3 = new RadioSection("danger+3",null,0);
        danger_group.AddButton(danger_button_n2);
        danger_group.AddButton(danger_button_n1);
        danger_group.AddButton(danger_button_0);
        danger_group.AddButton(danger_button_p1);
        danger_group.AddButton(danger_button_p2);
        danger_group.AddButton(danger_button_p3);
        Labels.AddEntry(danger_button_n2);
        Labels.AddEntry(danger_button_n1);
        Labels.AddEntry(danger_button_0);
        Labels.AddEntry(danger_button_p1);
        Labels.AddEntry(danger_button_p2);
        Labels.AddEntry(danger_button_p3);
        // savior
        Labels.AddEntry(new LabelSection("savior_label","Savior",0));
        RadioGroupSection savior_group = new RadioGroupSection("savior",null,0,"savior0");
        Labels.AddEntry(savior_group);
        RadioSection savior_button_n2 = new RadioSection("savior-2",null,0);
        RadioSection savior_button_n1 = new RadioSection("savior-1",null,0);
        RadioSection savior_button_0 = new RadioSection("savior0",null,0);
        RadioSection savior_button_p1 = new RadioSection("savior+1",null,0);
        RadioSection savior_button_p2 = new RadioSection("savior+2",null,0);
        RadioSection savior_button_p3 = new RadioSection("savior+3",null,0);
        savior_group.AddButton(savior_button_n2);
        savior_group.AddButton(savior_button_n1);
        savior_group.AddButton(savior_button_0);
        savior_group.AddButton(savior_button_p1);
        savior_group.AddButton(savior_button_p2);
        savior_group.AddButton(savior_button_p3);
        Labels.AddEntry(savior_button_n2);
        Labels.AddEntry(savior_button_n1);
        Labels.AddEntry(savior_button_0);
        Labels.AddEntry(savior_button_p1);
        Labels.AddEntry(savior_button_p2);
        Labels.AddEntry(savior_button_p3);
        // superior
        Labels.AddEntry(new LabelSection("superior_label","Superior",0));
        RadioGroupSection superior_group = new RadioGroupSection("superior",null,0,"superior0");
        Labels.AddEntry(superior_group);
        RadioSection superior_button_n2 = new RadioSection("superior-2",null,0);
        RadioSection superior_button_n1 = new RadioSection("superior-1",null,0);
        RadioSection superior_button_0 = new RadioSection("superior0",null,0);
        RadioSection superior_button_p1 = new RadioSection("superior+1",null,0);
        RadioSection superior_button_p2 = new RadioSection("superior+2",null,0);
        RadioSection superior_button_p3 = new RadioSection("superior+3",null,0);
        superior_group.AddButton(superior_button_n2);
        superior_group.AddButton(superior_button_n1);
        superior_group.AddButton(superior_button_0);
        superior_group.AddButton(superior_button_p1);
        superior_group.AddButton(superior_button_p2);
        superior_group.AddButton(superior_button_p3);
        Labels.AddEntry(superior_button_n2);
        Labels.AddEntry(superior_button_n1);
        Labels.AddEntry(superior_button_0);
        Labels.AddEntry(superior_button_p1);
        Labels.AddEntry(superior_button_p2);
        Labels.AddEntry(superior_button_p3);
        // mundane
        Labels.AddEntry(new LabelSection("mundane_label","Mundane",0));
        RadioGroupSection mundane_group = new RadioGroupSection("mundane",null,0,"mundane0");
        Labels.AddEntry(mundane_group);
        RadioSection mundane_button_n2 = new RadioSection("mundane-2",null,0);
        RadioSection mundane_button_n1 = new RadioSection("mundane-1",null,0);
        RadioSection mundane_button_0 = new RadioSection("mundane0",null,0);
        RadioSection mundane_button_p1 = new RadioSection("mundane+1",null,0);
        RadioSection mundane_button_p2 = new RadioSection("mundane+2",null,0);
        RadioSection mundane_button_p3 = new RadioSection("mundane+3",null,0);
        mundane_group.AddButton(mundane_button_n2);
        mundane_group.AddButton(mundane_button_n1);
        mundane_group.AddButton(mundane_button_0);
        mundane_group.AddButton(mundane_button_p1);
        mundane_group.AddButton(mundane_button_p2);
        mundane_group.AddButton(mundane_button_p3);
        Labels.AddEntry(mundane_button_n2);
        Labels.AddEntry(mundane_button_n1);
        Labels.AddEntry(mundane_button_0);
        Labels.AddEntry(mundane_button_p1);
        Labels.AddEntry(mundane_button_p2);
        Labels.AddEntry(mundane_button_p3);
        // add
        CoolSheet.AddSection(Labels);

        GridSection Conditions = new GridSection("conditions","Conditions",2,3);
        Conditions.AddEntry(new CheckboxSection("afraid",null,0));
        Conditions.AddEntry(new LabelSection("afraid_label","AFRAID",0));
        Conditions.AddEntry(new LabelSection("afraid_desc","(-2 to directly engage)",0));
        Conditions.AddEntry(new CheckboxSection("angry",null,0));
        Conditions.AddEntry(new LabelSection("angry_label","ANGRY",0));
        Conditions.AddEntry(new LabelSection("angry_desc","(-2 to change self-image or pierce the mask)",0));
        Conditions.AddEntry(new CheckboxSection("guilty",null,0));
        Conditions.AddEntry(new LabelSection("guilty_label","GUILTY",0));
        Conditions.AddEntry(new LabelSection("guilty_desc","(-2 to provoke someone or assess the situation)",0));
        Conditions.AddEntry(new CheckboxSection("insecure",null,0));
        Conditions.AddEntry(new LabelSection("insecure_label","INSECURE",0));
        Conditions.AddEntry(new LabelSection("insecure_desc","(-2 to stand in defense or reject what others say)",0));
        Conditions.AddEntry(new CheckboxSection("hopeless",null,0));
        Conditions.AddEntry(new LabelSection("hopeless_label","HOPELESS",0));
        Conditions.AddEntry(new LabelSection("hopeless_desc","(-2 to unleash your powers)",0));

        CoolSheet.AddSection(Conditions);
        CoolSheet.AddSection(new BigTextSection("moves","Moves",2,200,30));

        MakeSheet();

        //Page1.setLayout(new BoxLayout(Page1,1));
        //Page2.setLayout(new BoxLayout(Page2,1));
        //Page3.setLayout(new BoxLayout(Page3,1));

        Form.add(PaneList);

        // Add a label and a button
        for(Container page : Pages) {
            SpringLayout layout = (SpringLayout)page.getLayout();

            Component lastcomp = null;
            //JSeparator seperator = new JSeparator(SwingConstants.HORIZONTAL);

            //page.add(seperator);

            for(Component comp : page.getComponents())
            {
                if(lastcomp != null)
                    layout.putConstraint(SpringLayout.NORTH,comp,5,SpringLayout.SOUTH,lastcomp);
                else
                    layout.putConstraint(SpringLayout.NORTH,comp,0,SpringLayout.NORTH,page);

                if(comp instanceof JLabel)
                {
                    layout.putConstraint(SpringLayout.WEST,comp,10,SpringLayout.WEST,page);
                    //layout.putConstraint(SpringLayout.WEST,seperator,0,SpringLayout.EAST,comp);
                }
                else
                {
                    for(Component innercomp : page.getComponents())
                    {
                        if(innercomp instanceof JLabel)
                            layout.putConstraint(SpringLayout.WEST,comp,40,SpringLayout.EAST,innercomp);
                    }
                    lastcomp = comp;
                    layout.putConstraint(SpringLayout.EAST,comp,0,SpringLayout.EAST,page);
                    //layout.putConstraint(SpringLayout.WEST,comp,0,SpringLayout.EAST,seperator);
                }
            }

            layout.putConstraint(SpringLayout.SOUTH, page, 0, SpringLayout.SOUTH, lastcomp);
            //page.add(Box.createVerticalGlue());
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
                JScrollPane scrollpage = new JScrollPane(page, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                page.setLayout(new SpringLayout());
                Pages.add(pageindex,page);
                PaneList.addTab("Page"+Integer.toString(pageindex+1), scrollpage);
            }

            AddSection(page,section);
        }
    }

    public void AddSection(Container putitin,SheetSection section)
    {
        //JPanel thebest = new JPanel();
        //thebest.setLayout(new BoxLayout(thebest,0));
        JLabel label = new JLabel(section.Name + ":");

        //thebest.add(Box.createHorizontalGlue());
        //thebest.add(label);

        //thebest.setLayout(new FlowLayout());

        //if(section.Component != null) {
        //    thebest.add(section.Component);
        //    AddBorder((JComponent) section.Component, Color.orange);
        //}

        putitin.add(label);

        if(section.Component != null) {
            putitin.add(section.Component);
            //AddBorder((JComponent) section.Component, Color.orange);
        }
    }

    public void AddBorder(JComponent comp, Color col)
    {
        comp.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(col),
                comp.getBorder()));
    }

    public static void main(String[] args) {
        CharacterSheetForm se = new CharacterSheetForm();
        // Schedules the application to be run at the correct time in the event queue.
        SwingUtilities.invokeLater(se);
    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        String action = e.getActionCommand();

        File currfile = FileChooser.getSelectedFile();

        switch(action)
        {
            case("save"):
            case("saveas"):
                if(currfile == null || action == "saveas") {
                    FileChooser.showSaveDialog(Form);
                    currfile = FileChooser.getSelectedFile();
                }
                if(currfile != null)
                    Config.Save(currfile.getAbsolutePath(),CoolSheet);
                break;
            case("load"):
                FileChooser.showOpenDialog(Form);
                currfile = FileChooser.getSelectedFile();
                if(currfile != null)
                    Config.Load(currfile.getAbsolutePath(), CoolSheet);
                break;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

    private class Influence implements TableModellable, ConfigStoreable {
        String Name;
        boolean ToMe;
        boolean ToOther;

        public Influence(String name, boolean tome, boolean toother)
        {
            Name = name;
            ToMe = tome;
            ToOther = toother;
        }

        @Override
        public Object getValue(int i) {
            switch(i) {
                case (0):
                    return Name;
                case (1):
                    return ToMe;
                case (2):
                    return ToOther;
            }

            return null;
        }

        @Override
        public void setValue(int i, Object o) {
            switch(i) {
                case (0):
                    Name = (String)o; break;
                case (1):
                    ToMe = (boolean)o; break;
                case (2):
                    ToOther = (boolean)o; break;
            }
        }

        @Override
        public int getColumns() {
            return 3;
        }

        @Override
        public String getColumnName(int i) {
            switch(i)
            {
                case(0):return "Name";
                case(1):return "To Me";
                case(2):return "To Other";
            }

            return null;
        }

        @Override
        public Object getDefaultCopy() {
            return new Influence("",false,false);
        }

        @Override
        public boolean canEdit(int i) {
            return true;
        }

        @Override
        public JsonElement SaveObject() {
            JsonObject cooljson = new JsonObject();

            cooljson.add("name",new JsonPrimitive(Name));
            cooljson.add("tome",new JsonPrimitive(ToMe));
            cooljson.add("toother",new JsonPrimitive(ToOther));

            return cooljson;
        }

        @Override
        public void LoadObject(JsonElement json) {
            JsonObject cooljson = json.getAsJsonObject();

            Name = cooljson.get("name").getAsString();
            ToMe = cooljson.get("tome").getAsBoolean();
            ToOther = cooljson.get("toother").getAsBoolean();
        }
    }
}
