package com.bord;

import com.google.gson.*;

/**
 * Created by TAMANDL on 14.10.2015.
 */
public interface ConfigStoreable {
    JsonElement SaveObject();

    void LoadObject(JsonElement json);
}
