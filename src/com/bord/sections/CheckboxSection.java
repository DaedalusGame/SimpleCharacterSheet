package com.bord.sections;

import com.bord.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class CheckboxSection extends SheetSection {
    public CheckboxSection(String identifier, String name, int page)
    {
        super(identifier, name, page);
        Component = new JCheckBox();
    }
}
