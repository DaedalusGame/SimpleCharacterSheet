package com.bord.sections;

import com.bord.BasicTable;
import com.bord.ConfigStoreable;
import com.bord.SheetSection;
import com.bord.TableModellable;
import com.google.gson.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class TableSection extends SheetSection implements ActionListener {
    JTable UITable;
    BasicTable BaseTable;
    JButton AddButton;
    JButton RemoveButton;

    public TableSection(String identifier, String name, int page, BasicTable table)
    {
        super(identifier, name, page);
        BaseTable = table;

        AddButton = new JButton("+");
        //AddButton.setPreferredSize(new Dimension(40,40));
        AddButton.addActionListener(this);
        AddButton.setActionCommand("add");
        RemoveButton = new JButton("-");
        //RemoveButton.setPreferredSize(new Dimension(40,40));
        RemoveButton.addActionListener(this);
        RemoveButton.setActionCommand("remove");

        JTable cooltable = UITable = new JTable(table);
        JPanel coolpanel = new JPanel();

        JScrollPane scrollpane = new JScrollPane(cooltable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        SpringLayout layout = new SpringLayout();
        coolpanel.setLayout(layout);
        coolpanel.add(scrollpane);
        coolpanel.add(AddButton);
        coolpanel.add(RemoveButton);
        coolpanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 200));
        coolpanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        layout.putConstraint(SpringLayout.WEST, scrollpane, 0, SpringLayout.WEST, coolpanel);
        layout.putConstraint(SpringLayout.NORTH, scrollpane, 0, SpringLayout.NORTH, coolpanel);
        layout.putConstraint(SpringLayout.EAST, coolpanel, 0, SpringLayout.EAST, scrollpane);
        layout.putConstraint(SpringLayout.SOUTH, coolpanel, 0, SpringLayout.SOUTH, AddButton);

        layout.putConstraint(SpringLayout.EAST, AddButton, 0, SpringLayout.WEST, RemoveButton);
        layout.putConstraint(SpringLayout.NORTH, AddButton, 0, SpringLayout.SOUTH, scrollpane);
        layout.putConstraint(SpringLayout.EAST, RemoveButton, 0, SpringLayout.EAST, coolpanel);
        layout.putConstraint(SpringLayout.NORTH, RemoveButton, 0, SpringLayout.SOUTH, scrollpane);
        layout.putConstraint(SpringLayout.SOUTH, RemoveButton, 0, SpringLayout.SOUTH, coolpanel);

        Component = coolpanel;
    }

    @Override
    public JsonElement SaveObject()
    {
        JsonArray cooljson = new JsonArray();

        for(Object tableval : BaseTable.Values)
        {
            ConfigStoreable storeval = (ConfigStoreable)tableval;

            cooljson.add(storeval.SaveObject());
        }

        return cooljson;
    }

    @Override
    public void LoadObject(JsonElement json) {
        JsonArray cooljson = json.getAsJsonArray();

        for(JsonElement elem : cooljson)
        {
            TableModellable tableobj = BaseTable.getDefaultObject();
            tableobj.LoadObject(elem);
            BaseTable.Values.add(tableobj);
        }

        BaseTable.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = UITable.getSelectionModel().getLeadSelectionIndex();
        String action = e.getActionCommand();

        if(index == -1) index = BaseTable.Values.size()-1;

        switch(action)
        {
            case("add"):
                BaseTable.Values.add(BaseTable.getDefaultObject());
                BaseTable.fireTableDataChanged();
                break;
            case("remove"):
                BaseTable.Values.remove(index);
                BaseTable.fireTableDataChanged();
                break;
        }
    }
}
