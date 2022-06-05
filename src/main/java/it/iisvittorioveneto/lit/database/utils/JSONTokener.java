package it.iisvittorioveneto.lit.database.utils;

import org.json.JSONException;

import java.io.FileReader;

public class JSONTokener extends org.json.JSONTokener {
    public JSONTokener(FileReader reader) {
        super(reader);
    }

    public JSONContent nextJSONContent() throws JSONException {
        char c = this.nextClean();
        switch (c) {
            case '[' -> {
                super.back();
                return (JSONContent) new JSONArray(this);
            }
            case '{' -> {
                super.back();
                return (JSONContent) new JSONObject(this);
            }
            default -> {
                return null;
            }
        }
    }
}
