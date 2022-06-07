package it.iisvittorioveneto.lit.database.utils;

import org.json.JSONException;

/**
 * This class is a clone of the org.json.JSONArray class with the
 * implementation of the JSONContent interface.
 */
public class JSONArray extends org.json.JSONArray implements JSONContent {

    /**
     * This constructor generates a new JSONArray
     */
    public JSONArray() {
        super();
    }

    public JSONArray(org.json.JSONArray jsonObject) {
        super(jsonObject.toString());
    }

    /**
     * This constructor generates a new JSONArray in order to
     * fill the JSONDocument content with it.
     * @param jsonTokener The JSONTokener to read the JSONArray from
     * @throws JSONException If the JSONArray is not valid
     */
    public JSONArray(JSONTokener jsonTokener) throws JSONException {
        super(jsonTokener);
    }

    @Override
    public JSONObject getJSONObject(int index) throws JSONException {
        return new JSONObject(super.getJSONObject(index));
    }

    @Override
    public JSONArray getJSONArray(int index) throws JSONException {
        return new JSONArray(super.getJSONArray(index));
    }

    public boolean hasJSONObject(JSONObject object) {
        for (int i = 0; i < this.length(); i++)
            if (this.getJSONObject(i).equals(object)) return true;
        return false;
    }
}
