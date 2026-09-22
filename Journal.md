Phase 1

The base case in my countFilesRecursive method is when the item is a FileItem. Since a file is one individual file, the method returns 1.

The general case is when the item is a Folder. The method loops through everything inside the folder and recursively calls countFilesRecursive on each child. The results are added together to get the total number of files.

The Smaller-Caller rule guarantees that each recursive call works on a smaller part of the file system. Instead of processing the entire tree again, each call processes one child item inside the current folder. Eventually, the method reaches a FileItem, which is the base case, so the recursion stops.
