package com.bord;

import com.google.gson.*;
import com.google.gson.stream.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by TAMANDL on 14.10.2015.
 */
public class ConfigManager {
    public static final String ConfigVersion = "1.0";

    public void Save(String filepath, ConfigStoreable storeable)
    {
        try {
            FileWriter coolwriter = new FileWriter(filepath);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
            JsonWriter jsonwrite = gson.newJsonWriter(coolwriter);
            JsonParser parser = new JsonParser();

            coolwriter.write(gson.toJson(storeable.SaveObject()));
            coolwriter.flush();

        }
        catch(IOException e) {
        }
    }

    public void Load(String filepath, ConfigStoreable storeable)
    {
        ConfigStoreable robject = null;

        try {
            FileReader coolreader = new FileReader(filepath);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            JsonParser parser = new JsonParser();

            storeable.LoadObject(parser.parse(coolreader));
        }
        catch(IOException e) {

        }
    }
}
