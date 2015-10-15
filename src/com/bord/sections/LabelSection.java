package com.bord.sections;

import com.bord.SheetSection;

import javax.swing.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class LabelSection extends SheetSection {
    public LabelSection(String identifier, String name, int page)
    {
        super(identifier, name, page);
        Component = new JLabel(name);
    }
}
