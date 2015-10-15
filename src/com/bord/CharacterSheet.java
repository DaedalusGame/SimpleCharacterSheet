package com.bord;

import java.util.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class CharacterSheet {
    public ArrayList<SheetSection> Sections = new ArrayList<>();
    public HashSet<String> UsedIdentifiers = new HashSet<>();

    public void AddSection(SheetSection section)
    {
        Sections.add(section);
        section.Parent = this;
    }
}
