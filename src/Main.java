import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create initial file system
        Folder root = createSampleFileSystem();

        boolean running = true;

        while (running) {

            displayMenu();

            String input = scanner.nextLine().trim();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 6.");
                continue;
            }

            switch (choice) {

                case 1:
                    displayFileSystem(root);
                    break;

                case 2:
                    addFile(scanner, root);
                    break;

                case 3:
                    addSubfolder(scanner, root);
                    break;

                case 4:
                    runRecursiveAudit(root);
                    break;

                case 5:
                    runIterativeAudit(root);
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please choose 1-6.");
            }
        }

        scanner.close();
    }

    // Create the initial file system
    public static Folder createSampleFileSystem() {

        Folder root = new Folder("Root");

        Folder documents = new Folder("Documents");
        Folder pictures = new Folder("Pictures");
        Folder vacations = new Folder("Vacations");

        FileItem resume =
                new FileItem("Resume.pdf", 500);

        FileItem notes =
                new FileItem("Notes.txt", 100);

        FileItem beach =
                new FileItem("Beach.jpg", 2000);

        FileItem mountain =
                new FileItem("Mountain.jpg", 2500);

        FileItem hotel =
                new FileItem("Hotel.jpg", 1500);

        documents.addItem(resume);
        documents.addItem(notes);

        vacations.addItem(beach);
        vacations.addItem(mountain);
        vacations.addItem(hotel);

        pictures.addItem(vacations);

        root.addItem(documents);
        root.addItem(pictures);

        return root;
    }

    // Display menu
    public static void displayMenu() {

        System.out.println();
        System.out.println("==============================");
        System.out.println("   VIRTUAL FILE SYSTEM");
        System.out.println("==============================");
        System.out.println("1. Display File System Structure");
        System.out.println("2. Add File to a Folder");
        System.out.println("3. Add Subfolder");
        System.out.println("4. Run Recursive Audit");
        System.out.println("5. Run Iterative Audit & Verification");
        System.out.println("6. Exit");
        System.out.println("==============================");
        System.out.print("Enter your choice: ");
    }

    // Option 1
    public static void displayFileSystem(Folder root) {

        System.out.println();
        System.out.println("File System Structure:");
        System.out.println("----------------------");

        FileSystemAnalyzer.printHierarchy(root, "");
    }

    // Option 2
    public static void addFile(
            Scanner scanner,
            Folder root) {

        System.out.println();
        System.out.println("Add File");
        System.out.println("--------");

        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine().trim();

        if (fileName.isEmpty()) {
            System.out.println("File name cannot be empty.");
            return;
        }

        System.out.print("Enter file size in KB: ");

        int size;

        try {
            size = Integer.parseInt(
                    scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(
                    "Invalid size. Please enter a whole number.");
            return;
        }

        if (size < 0) {
            System.out.println(
                    "File size cannot be negative.");
            return;
        }

        System.out.print(
                "Enter target folder name: ");

        String folderName =
                scanner.nextLine().trim();

        Folder target =
                FileSystemAnalyzer.findFolder(
                        root,
                        folderName);

        if (target == null) {

            System.out.println(
                    "Folder '" +
                    folderName +
                    "' was not found.");

            return;
        }

        FileItem newFile =
                new FileItem(fileName, size);

        target.addItem(newFile);

        System.out.println(
                "File added successfully.");
    }

    // Option 3
    public static void addSubfolder(
            Scanner scanner,
            Folder root) {

        System.out.println();
        System.out.println("Add Subfolder");
        System.out.println("-------------");

        System.out.print("Enter new folder name: ");

        String newFolderName =
                scanner.nextLine().trim();

        if (newFolderName.isEmpty()) {

            System.out.println(
                    "Folder name cannot be empty.");

            return;
        }

        System.out.print(
                "Enter parent folder name: ");

        String parentName =
                scanner.nextLine().trim();

        Folder parent =
                FileSystemAnalyzer.findFolder(
                        root,
                        parentName);

        if (parent == null) {

            System.out.println(
                    "Parent folder '" +
                    parentName +
                    "' was not found.");

            return;
        }

        Folder newFolder =
                new Folder(newFolderName);

        parent.addItem(newFolder);

        System.out.println(
                "Subfolder added successfully.");
    }

    // Option 4
    public static void runRecursiveAudit(
            Folder root) {

        System.out.println();
        System.out.println("Recursive Audit");
        System.out.println("----------------");

        int fileCount =
                FileSystemAnalyzer.countFilesRecursive(root);

        int totalSize =
                FileSystemAnalyzer
                        .calculateTotalSizeRecursive(root);

        FileItem largestFile =
                FileSystemAnalyzer
                        .findLargestFileRecursive(root);

        System.out.println(
                "Total files: " + fileCount);

        System.out.println(
                "Total storage: " +
                totalSize + " KB");

        if (largestFile != null) {

            System.out.println(
                    "Largest file: " +
                    largestFile.getName());

            System.out.println(
                    "Largest file size: " +
                    largestFile.getSizeInKB() +
                    " KB");

        } else {

            System.out.println(
                    "No files exist.");
        }
    }

    // Option 5
    public static void runIterativeAudit(
            Folder root) {

        System.out.println();
        System.out.println("Iterative Audit");
        System.out.println("----------------");

        int iterativeCount =
                FileSystemAnalyzer
                        .countFilesIterative(root);

        int recursiveCount =
                FileSystemAnalyzer
                        .countFilesRecursive(root);

        System.out.println(
                "Iterative file count: " +
                iterativeCount);

        System.out.println(
                "Recursive file count: " +
                recursiveCount);

        if (iterativeCount == recursiveCount) {

            System.out.println(
                    "Verification successful!");

            System.out.println(
                    "Both methods returned " +
                    iterativeCount + " files.");

        } else {

            System.out.println(
                    "Verification failed!");

            System.out.println(
                    "The two methods returned different counts.");
        }
    }
}
