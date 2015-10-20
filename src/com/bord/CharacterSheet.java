package com.bord;

import com.google.gson.*;

import java.util.*;

/**
 * Created by TAMANDL on 15.10.2015.
 */
public class CharacterSheet implements ConfigStoreable {
    public ArrayList<SheetSection> Sections = new ArrayList<>();
    public HashMap<String,SheetSection> SortedSections = new HashMap<>();

    public void AddSection(SheetSection section)
    {
        Sections.add(section);
        SortedSections.put(section.Identifier, section);
        section.Parent = this;
    }

    @Override
    public JsonElement SaveObject() {
        JsonObject cooljson = new JsonObject();

        for(SheetSection section : SortedSections.values())
        {
            cooljson.add(section.Identifier,section.SaveObject());
        }

        return cooljson;
    }

    @Override
    public void LoadObject(JsonElement json) {
        JsonObject cooljson = (JsonObject)json;

        for(Map.Entry<String, JsonElement> elem : cooljson.entrySet())
        {
            String identifier = elem.getKey();

            if(SortedSections.containsKey(identifier))
            {
                SheetSection section = SortedSections.get(identifier);
                section.LoadObject(elem.getValue());
            }
        }
    }
}
