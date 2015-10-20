package com.bord.sections;

import com.bord.*;

import com.google.gson.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class TextSection extends SheetSection {
    JTextField TextField;

    public TextSection(String identifier, String name, int page, int width, int height)
    {
        super(identifier, name, page);

        TextField = new JTextField();
        TextField.setPreferredSize(new Dimension(width, height));
        Component = TextField;
    }

    @Override
    public JsonElement SaveObject()
    {
        return new JsonPrimitive(TextField.getText());
    }

    @Override
    public void LoadObject(JsonElement json) {
        TextField.setText(json.getAsString());
    }
}
