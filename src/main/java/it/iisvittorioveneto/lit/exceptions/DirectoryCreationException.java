package it.iisvittorioveneto.lit.exceptions;

public class DirectoryCreationException extends RuntimeException {
    public DirectoryCreationException(String path, String s) {
        super(path + ": " + s);
    }
}
