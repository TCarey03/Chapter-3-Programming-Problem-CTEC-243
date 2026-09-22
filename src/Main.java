public class Main {

    public static void main(String[] args) {

        // Create folders
        Folder root = new Folder("Root");
        Folder documents = new Folder("Documents");
        Folder pictures = new Folder("Pictures");
        Folder vacations = new Folder("Vacations");

        // Create files
        FileItem resume = new FileItem("Resume.pdf", 500);
        FileItem notes = new FileItem("Notes.txt", 100);
        FileItem photo1 = new FileItem("Beach.jpg", 2000);
        FileItem photo2 = new FileItem("Mountain.jpg", 2500);
        FileItem photo3 = new FileItem("Hotel.jpg", 1500);

        // Add files to folders
        documents.addItem(resume);
        documents.addItem(notes);

        vacations.addItem(photo1);
        vacations.addItem(photo2);
        vacations.addItem(photo3);

        // Create the folder hierarchy
        pictures.addItem(vacations);
        root.addItem(documents);
        root.addItem(pictures);

        // Count files recursively
        int fileCount = FileSystemAnalyzer.countFilesRecursive(root);

        System.out.println("Total files: " + fileCount);
    }
}