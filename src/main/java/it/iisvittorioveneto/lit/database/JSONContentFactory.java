package it.iisvittorioveneto.lit.database;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONContentFactory {

    public static <T> T create(T content){
        if (content instanceof JSONObject){
            return (T) new JSONObject();
        }
        if (content instanceof JSONArray) {
            return (T) new JSONArray();
        }
        return null;
    }
}
