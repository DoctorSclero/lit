package it.iisvittorioveneto.lit.database;

import org.json.JSONStringer;
import org.json.JSONTokener;

import java.io.*;

/**
 * * Check
 * * Is it ok to use Java's Generics?
 */

/**
 * This class represents a JSON document.
 * @param <T> The type of the root element of the document.
 */
public class JSONDocument<T> {

    public String path;
    public Object content;

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
            content = tokener.nextValue();

        } catch (FileNotFoundException ignored) {}

    }

    public void saveDocument() throws IOException {

        FileWriter writer = new FileWriter(path);
        writer.write(JSONStringer.valueToString(content));

    }

    public String getPath() {
        return path;
    }

    public T getContent() {
        return (T) content;
    }

}
