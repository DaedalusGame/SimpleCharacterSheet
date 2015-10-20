package com.bord.sections;

import com.bord.SheetSection;
import com.google.gson.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class BigTextSection extends SheetSection {
    JTextArea TextArea;

    public BigTextSection(String identifier, String name, int page, int width, int height)
    {
        super(identifier, name, page);

        JTextArea textarea = new JTextArea();
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);

        JScrollPane scrollpane = new JScrollPane(textarea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setPreferredSize(new Dimension(width, height));

        TextArea = textarea;
        Component = scrollpane;
    }

    @Override
    public JsonElement SaveObject()
    {
        return new JsonPrimitive(TextArea.getText());
    }

    @Override
    public void LoadObject(JsonElement json) {
        TextArea.setText(json.getAsString());
    }
}
