public class FileSystemAnalyzer {

    // Phase 1
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

    // Phase 2 - Calculate total storage
    public static int calculateTotalSizeRecursive(FileSystemItem item) {

        // Base case
        if (item instanceof FileItem) {
            return item.getSizeInKB();
        }

        // General case
        if (item instanceof Folder) {
            Folder folder = (Folder) item;
            int totalSize = 0;

            for (FileSystemItem child : folder.getItems()) {
                totalSize += calculateTotalSizeRecursive(child);
            }

            return totalSize;
        }

        return 0;
    }

    // Phase 2 - Find largest file
    public static FileItem findLargestFileRecursive(FileSystemItem item) {

        // Base case
        if (item instanceof FileItem) {
            return (FileItem) item;
        }

        // General case
        if (item instanceof Folder) {
            Folder folder = (Folder) item;
            FileItem largest = null;

            for (FileSystemItem child : folder.getItems()) {
                FileItem childLargest = findLargestFileRecursive(child);

                if (childLargest != null &&
                        (largest == null ||
                         childLargest.getSizeInKB() > largest.getSizeInKB())) {

                    largest = childLargest;
                }
            }

            return largest;
        }

        return null;
    }
}
