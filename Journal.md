Phase 1

The base case in my countFilesRecursive method is when the item is a FileItem. Since a file is one individual file, the method returns 1.

The general case is when the item is a Folder. The method loops through everything inside the folder and recursively calls countFilesRecursive on each child. The results are added together to get the total number of files.

The Smaller-Caller rule guarantees that each recursive call works on a smaller part of the file system. Instead of processing the entire tree again, each call processes one child item inside the current folder. Eventually, the method reaches a FileItem, which is the base case, so the recursion stops.

-------------------

Phase 2

The findLargestFileRecursive method returns null when a folder does not contain any files anywhere in its hierarchy. This is useful because null represents that no file was found.

When checking the results from each child, I first make sure that childLargest is not null. If it is not null, I compare its size to the current largest file. If there is no current largest file or the child file is larger, I update largest.

This prevents a NullPointerException because I never call getSizeInKB() on a null reference. Empty folders can therefore be handled safely by the recursive method.

---------------------

Phase 3

The recursive solution is more intuitive to me because it matches the structure of the file system. A folder contains other items, so the method can call itself on each child folder.

The iterative solution uses an explicit Stack instead of the runtime call stack. It takes a little more code because I have to manually push and pop items, but it avoids recursive method calls.

For overhead space, both approaches need space to keep track of folders that still need to be processed. The recursive version uses the runtime call stack, while the iterative version uses a Stack data structure.

I find the recursive version easier to read and maintain for this problem because the recursive call naturally follows the folder hierarchy. The iterative version is useful because it shows how recursion can be replaced with an explicit data structure.

-----------------------

Phase 4

If a folder contained a reference to itself or to one of its ancestor folders, the recursive method could continue calling itself forever because the problem would never become smaller. Eventually, the program would run out of stack space and produce a StackOverflowError.

The iterative stack method would also have a problem. It would keep finding the same folders and pushing them onto the stack repeatedly. The stack would continue growing because there would be no point where the circular reference ends.

This relates to the Smaller-Caller rule because recursion needs each call to move toward a smaller problem and eventually reach a base case. A circular reference violates that rule because the traversal can return to a folder that was already visited instead of moving toward a smaller problem.

A possible solution for a real file system would be to keep track of folders that have already been visited. That would prevent the program from processing the same folder repeatedly.
