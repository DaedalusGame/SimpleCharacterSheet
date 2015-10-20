package com.bord.sections;

import com.bord.SheetSection;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import javax.swing.*;
import java.util.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class RadioGroupSection extends SheetSection {
    public HashMap<String,RadioSection> ButtonSections = new HashMap<>();
    ButtonGroup ButtonGroup;
    String DefaultButton;

    public RadioGroupSection(String identifier, String name, int page, String defaultbutton)
    {
        super(identifier, name, page);
        ButtonGroup = new ButtonGroup();
        Component = null;
        DefaultButton = defaultbutton;
    }

    public void AddButton(RadioSection button)
    {
        ButtonSections.put(button.Identifier,button);
        ButtonGroup.add(button.RadioButton);
        if(button.Identifier == DefaultButton)
        {
            button.RadioButton.setSelected(true);
        }
    }

    @Override
    public JsonElement SaveObject()
    {
        for(RadioSection section : ButtonSections.values())
        {
            if(ButtonGroup.isSelected(section.RadioButton.getModel()))
                return new JsonPrimitive(section.Identifier);
        }

        return null;
    }

    @Override
    public void LoadObject(JsonElement json) {
        String key = json.getAsString();
        if(ButtonSections.containsKey(key) || ButtonSections.containsKey(key = DefaultButton)) {
            RadioSection section = ButtonSections.get(key);
            section.RadioButton.setSelected(true);
        }
    }
}
