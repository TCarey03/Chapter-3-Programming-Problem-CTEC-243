import java.util.Stack;

public class FileSystemAnalyzer {

    // Phase 1
    public static int countFilesRecursive(FileSystemItem item) {

        if (item instanceof FileItem) {
            return 1;
        }

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

    // Phase 2
    public static int calculateTotalSizeRecursive(FileSystemItem item) {

        if (item instanceof FileItem) {
            return item.getSizeInKB();
        }

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

    // Phase 2
    public static FileItem findLargestFileRecursive(FileSystemItem item) {

        if (item instanceof FileItem) {
            return (FileItem) item;
        }

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

    // Phase 3
    public static int countFilesIterative(Folder rootFolder) {

        Stack<FileSystemItem> stack = new Stack<>();

        stack.push(rootFolder);

        int fileCount = 0;

        while (!stack.isEmpty()) {

            FileSystemItem current = stack.pop();

            if (current instanceof FileItem) {
                fileCount++;
            }

            else if (current instanceof Folder) {
                Folder folder = (Folder) current;

                for (FileSystemItem child : folder.getItems()) {
                    stack.push(child);
                }
            }
        }

        return fileCount;
    }
}
