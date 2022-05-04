package it.iisvittorioveneto.lit.database;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import java.io.*;
import java.util.function.Supplier;

/**
 * * Check
 * * Is it ok to use Java's Generics?
 */

/**
 * This class represents a JSON document.
 * @param <T> The type of the root element of the document.
 */
public class JSONDocument<T> {

    private String path;
    private Object content;
    private Class<T> type;

    public JSONDocument(String path) throws IOException {

        this.path = path;

        // check if file exists and is readable
        if ( !(new File(path).exists())  ) {
            new File(path).createNewFile();
        }

        // Read the json file database at the given path
        try {

            // Read the JSON file
            FileReader reader = new FileReader(path);

            // Parse the JSON file
            JSONTokener tokener = new JSONTokener(reader);
            if (tokener.more()) {
                content = tokener.nextValue();
            }

        } catch (FileNotFoundException ignored) {}

    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(JSONStringer.valueToString(content));
        } catch (IOException ioe) {
            System.out.println("Error while saving the file!");
            ioe.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    public T getContent() {
        return (T) content;
    }

}
