package com.bord;

import javax.json.*;
import java.awt.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by TAMANDL on 14.10.2015.
 */
public class SheetSection {
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
}
