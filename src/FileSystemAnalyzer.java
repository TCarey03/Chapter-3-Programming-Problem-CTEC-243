import java.util.Stack;

public class FileSystemAnalyzer {

    // Phase 1: Count files recursively
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

    // Phase 2: Calculate total size recursively
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

    // Phase 2: Find largest file recursively
    public static FileItem findLargestFileRecursive(FileSystemItem item) {

        if (item instanceof FileItem) {
            return (FileItem) item;
        }

        if (item instanceof Folder) {
            Folder folder = (Folder) item;
            FileItem largest = null;

            for (FileSystemItem child : folder.getItems()) {

                FileItem childLargest =
                        findLargestFileRecursive(child);

                if (childLargest != null &&
                        (largest == null ||
                        childLargest.getSizeInKB()
                                > largest.getSizeInKB())) {

                    largest = childLargest;
                }
            }

            return largest;
        }

        return null;
    }

    // Phase 3: Count files iteratively
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

    // Phase 4: Print hierarchy
    public static void printHierarchy(
            FileSystemItem item, String indent) {

        if (item instanceof FileItem) {

            FileItem file = (FileItem) item;

            System.out.println(
                    indent + "- " +
                    file.getName() +
                    " (" +
                    file.getSizeInKB() +
                    " KB)");
        }

        else if (item instanceof Folder) {

            Folder folder = (Folder) item;

            System.out.println(
                    indent + folder.getName() + "/");

            for (FileSystemItem child : folder.getItems()) {

                printHierarchy(
                        child,
                        indent + "  ");
            }
        }
    }

    // Phase 4: Find folder by name
    public static Folder findFolder(
            Folder current,
            String targetName) {

        if (current.getName()
                .equalsIgnoreCase(targetName)) {

            return current;
        }

        for (FileSystemItem item : current.getItems()) {

            if (item instanceof Folder) {

                Folder found = findFolder(
                        (Folder) item,
                        targetName);

                if (found != null) {
                    return found;
                }
            }
        }

        return null;
    }
}
