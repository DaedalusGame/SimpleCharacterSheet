package com.bord.sections;

import com.bord.SheetSection;
import com.bord.SpringUtilities;
import com.google.gson.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class GridSection extends SheetSection {
    private SpringLayout Layout;
    private JPanel Panel;
    public HashMap<String,SheetSection> SubSections = new HashMap<>();
    //public ArrayList<SheetSection> SubSections = new ArrayList<>();
    private int Columns;
    int DisplayedSections = 0;

    public GridSection(String identifier, String name, int page, int columns) {
        super(identifier, name, page);
        JPanel panel = new JPanel();
        Layout = new SpringLayout();

        panel.setLayout(Layout);

        Panel = panel;
        Component = panel;
        Columns = columns;
    }

    public void AddEntry(SheetSection section)
    {
        SubSections.put(section.Identifier, section);
        if(section.Component != null) {
            Panel.add(section.Component);
            DisplayedSections++;
        }
        //Layout.setColumns(Columns);
        //Layout.setRows(DisplayedSections/Columns);
        int rows = DisplayedSections/Columns;

        SpringUtilities.makeCompactGrid(Panel, //parent
                rows, Columns,
                3, 3,  //initX, initY
                3, 3); //xPad, yPad
    }

    @Override
    public JsonElement SaveObject() {
        JsonObject cooljson = new JsonObject();

        for(SheetSection section : SubSections.values())
        {
            cooljson.add(section.Identifier,section.SaveObject());
        }

        return cooljson;
    }

    @Override
    public void LoadObject(JsonElement json) {
        JsonObject cooljson = (JsonObject)json;

        for(Map.Entry<String, JsonElement> elem : cooljson.entrySet())
        {
            String identifier = elem.getKey();

            if(SubSections.containsKey(identifier))
            {
                SheetSection section = SubSections.get(identifier);
                section.LoadObject(elem.getValue());
            }
        }
    }
}
