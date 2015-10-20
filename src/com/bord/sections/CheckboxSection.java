package com.bord.sections;

import com.bord.*;
import com.google.gson.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class CheckboxSection extends SheetSection {
    JCheckBox CheckBox;

    public CheckboxSection(String identifier, String name, int page)
    {
        super(identifier, name, page);
        Component = CheckBox = new JCheckBox();
    }

    @Override
    public JsonElement SaveObject()
    {
        return new JsonPrimitive(CheckBox.isSelected());
    }

    @Override
    public void LoadObject(JsonElement json) {
        CheckBox.setSelected(json.getAsBoolean());
    }
}
