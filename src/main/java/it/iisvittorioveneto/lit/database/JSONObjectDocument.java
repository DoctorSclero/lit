package it.iisvittorioveneto.lit.database;

import it.iisvittorioveneto.lit.database.utils.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONObjectDocument extends JSONDocument<JSONObject> {
    /**
     * This constructor generates a new JSONObject in order to
     * fill the JSONDocument content with it.
     * @param path The path to the JSONDocument
     * @throws IOException If the file cannot be read/write.
     */
    public JSONObjectDocument(Path path) throws IOException {
        // Try to read from the JSONDocument
        super(path);
        // If file is not present or not readable a new JSONObject is generated
        if (super.content == null) super.content = new JSONObject();
        // After creating the document save it
        super.save();
    }
}
