package it.iisvittorioveneto.lit.database;

import it.iisvittorioveneto.lit.database.utils.JSONArray;

import java.io.IOException;
import java.nio.file.Path;

public class JSONArrayDocument extends JSONDocument<JSONArray> {

    /**
     * This constructor generates a new JSONObject in order to
     * fill the JSONDocument content with it.
     * @param path The path to the JSONDocument
     * @throws IOException If the file cannot be read/write.
     */
    public JSONArrayDocument(Path path) throws IOException {
        // Try to read from the JSONDocument
        super(path);
        // If file is not present or not readable a new JSONArray is generated
        if (super.content == null) super.content = new JSONArray();
        // After creating the document save it
        super.save();
    }

}
