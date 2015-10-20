package com.bord;

import com.google.gson.*;
import java.awt.*;

/**
 * Created by TAMANDL on 14.10.2015.
 */
public class SheetSection implements ConfigStoreable {
    public CharacterSheet Parent;

    public String Identifier;
    public String Name;
    public int Page;

    public Component Component;

    public SheetSection(String identifier, String name, int page)
    {
        Identifier = identifier;
        Name = name;
        Page = page;
    }

    @Override
    public JsonElement SaveObject() {
        return null;
    }

    @Override
    public void LoadObject(JsonElement json) {
    }
}
