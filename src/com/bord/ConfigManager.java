package com.bord;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import java.io.*;
import java.util.*;

/**
 * Created by TAMANDL on 14.10.2015.
 */
public class ConfigManager {
    public void Save(String filepath)
    {
        try {
            FileWriter coolwriter = new FileWriter(filepath);
            JsonWriter jsonwriter = Json.createWriter(coolwriter);
        }
        catch(IOException e) {

        }
    }

    public void Load(String filepath)
    {
        try {
            FileReader coolreader = new FileReader(filepath);
            JsonReader jsonreader = Json.createReader(coolreader);
        }
        catch(IOException e) {

        }
    }
}
