package it.iisvittorioveneto.lit.database.utils;

import org.json.JSONException;

/**
 * This class is a clone of the org.json.JSONObject class with the
 * implementation of the JSONContent interface.
 */
public class JSONObject extends org.json.JSONObject implements JSONContent {

    @Override
    public JSONObject getJSONObject(String key) throws JSONException {
        return (JSONObject) super.getJSONObject(key);
    }

    @Override
    public JSONArray getJSONArray(String key) throws JSONException {
        return (JSONArray) super.getJSONArray(key);
    }
}
