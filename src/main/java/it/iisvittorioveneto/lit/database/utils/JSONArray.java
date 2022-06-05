package it.iisvittorioveneto.lit.database.utils;

import org.json.JSONException;

/**
 * This class is a clone of the org.json.JSONArray class with the
 * implementation of the JSONContent interface.
 */
public class JSONArray extends org.json.JSONArray implements JSONContent {

    @Override
    public JSONObject getJSONObject(int index) throws JSONException {
        return (JSONObject) super.getJSONObject(index);
    }

    @Override
    public JSONArray getJSONArray(int index) throws JSONException {
        return (JSONArray) super.getJSONArray(index);
    }
}
