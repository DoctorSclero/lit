package it.iisvittorioveneto.lit.database.utils;

import org.json.JSONException;

/**
 * This class is a clone of the org.json.JSONObject class with the
 * implementation of the JSONContent interface.
 */
public class JSONObject extends org.json.JSONObject implements JSONContent {

    /**
     * This constructor generates a new JSONObject
     */
    public JSONObject() {
        super();
    }

    public JSONObject(org.json.JSONObject jsonObject) {
        super(jsonObject.toString());
    }

    /**
     * This constructor generates a new JSONObject in order to
     * fill the JSONDocument content with it.
     * @param jsonTokener The JSONTokener to read the JSONObject from
     * @throws JSONException If the JSONObject is not valid
     */
    public JSONObject(JSONTokener jsonTokener) throws JSONException {
        super(jsonTokener);
    }

    @Override
    public JSONObject getJSONObject(String key) throws JSONException {
        return new JSONObject(super.getJSONObject(key));
    }

    @Override
    public JSONArray getJSONArray(String key) throws JSONException {
        return new JSONArray(super.getJSONArray(key));
    }
}
