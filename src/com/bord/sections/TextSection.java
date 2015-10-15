package com.bord.sections;

import com.bord.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class TextSection extends SheetSection {
    public TextSection(String identifier, String name, int page, int width, int height)
    {
        super(identifier, name, page);

        JTextField textfield = new JTextField();
        textfield.setPreferredSize(new Dimension(width, height));
        Component = textfield;
    }
}
