package it.iisvittorioveneto.lit.database;

import it.iisvittorioveneto.lit.database.utils.JSONContent;
import org.json.JSONTokener;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ? Are Generic useful in this class or not??
 */

/**
 * This class represents a JSON document.
 * @param <T> The type of the root element of the document.
 */
public abstract class JSONDocument<T extends JSONContent> {

    protected Path path;
    protected T content;

    /**
     * Initializes a new JSONDocument if file is not on disk,
     * a new one is created.
     * @throws IOException If an error occurs while creating the file.
     */
    protected JSONDocument(Path path) throws IOException {
        if (!(path.toFile().exists())) {
            path.toFile().createNewFile();
        }

        this.path = path;
        this.content = this.read();
    }

    /**
     * This method reads the JSONDocument from the disk.
     * @return the JSONContent of the file
     * @throws IOException If an error occurs while reading the file.
     */
    protected T read() throws IOException {
        T res = null;
        if (path.toFile().exists()) {
            try {
                FileReader reader = new FileReader(path.toFile());

                JSONTokener tokener = new JSONTokener(reader);
                if (tokener.more()) {
                    res = (T) tokener.nextValue();
                }
            } catch (FileNotFoundException ignored) {}
        }
        return res;
    }


    /**
     * This method saves the JSON Document.
     */
    public void save() {
        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(content.toString());
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Error while saving the file!");
            ioe.printStackTrace();
        }
    }

    /**
     * Get the path of the document.
     * @return The path of the document.
     */
    public Path getPath() {
        return path;
    }

    /**
     * Get the content of the document.
     * @return The content of the document.
     */
    public T getContent() {
        return content;
    }
}


