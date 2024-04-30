package Model;

public class FileRW { //File Writer/Reader class

    public FileRW() {
    }

    public void writeToFile(String fileName, String text) {
        // Skriv till fil
    }

    public String readFromFile(String fileName) {
        // Läs från fil
        return "text";
    }

    public void appendToFile(String fileName, String text) {
        // Lägg till i fil
    }

    public void deleteFile(String fileName) {
        // Ta bort fil
    }

    public void createFile(String fileName) {
        // Skapa fil
    }

    public void createDirectory(String directoryName) {
        // Skapa mapp
    }

    public void deleteDirectory(String directoryName) {
        // Ta bort mapp
    }

    public void listFiles(String directoryName) {
        // Lista filer i mapp
    }

    public void listDirectories(String directoryName) {
        // Lista mappar i mapp
    }

    public void copyFile(String sourceFileName, String destinationFileName) {
        // Kopiera fil
    }

    public void moveFile(String sourceFileName, String destinationFileName) {
        // Flytta fil
    }

    public void renameFile(String fileName, String newFileName) {
        // Byt namn på fil
    }

    public void renameDirectory(String directoryName, String newDirectoryName) {
        // Byt namn på mapp
    }

    public void copyDirectory(String sourceDirectoryName, String destinationDirectoryName) {
        // Kopiera mapp
    }

    public void moveDirectory(String sourceDirectoryName, String destinationDirectoryName) {
        // Flytta mapp
    }

    public void writeObjectToFile(String fileName, Object object) {
        // Skriv objekt till fil
    }

    public Object readObjectFromFile(String fileName) {
        // Läs objekt från fil
        return new Object();
    }

    public void appendObjectToFile(String fileName, Object object) {
        // Lägg till objekt i fil
    }

    public void deleteObjectFromFile(String fileName) {
        // Ta bort objekt från fil
    }

    public void createObjectFile(String fileName) {
        // Skapa objektfil
    }

    public void createObjectDirectory(String directoryName) {
        // Skapa objektmapp
    }

    public void deleteObjectDirectory(String directoryName) {
        // Ta bort objektmapp
    }
}
