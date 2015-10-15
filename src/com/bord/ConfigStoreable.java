package com.bord;

import javax.json.*;

/**
 * Created by TAMANDL on 14.10.2015.
 */
public interface ConfigStoreable {
    JsonObject SaveObject();

    void LoadObject(JsonObject json);
}
