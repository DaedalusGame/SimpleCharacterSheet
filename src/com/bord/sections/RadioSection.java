package com.bord.sections;

import com.bord.SheetSection;

import javax.swing.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class RadioSection extends SheetSection {
    public JRadioButtonMenuItem RadioButton;

    public RadioSection(String identifier, String name, int page)
    {
        super(identifier, name, page);
        Component = RadioButton = new JRadioButtonMenuItem();
    }
}
