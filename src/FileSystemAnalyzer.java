public class FileSystemAnalyzer {

    public static int countFilesRecursive(FileSystemItem item) {

        // Base case
        if (item instanceof FileItem) {
            return 1;
        }

        // General case
        if (item instanceof Folder) {
            Folder folder = (Folder) item;
            int count = 0;

            for (FileSystemItem child : folder.getItems()) {
                count += countFilesRecursive(child);
            }

            return count;
        }

        return 0;
    }
}