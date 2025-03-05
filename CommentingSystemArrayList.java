/*ArrayList is efficient to use when the user wants to access or remove 
 * any comment by their index but not their name as they store the 
 * elements in the same order as they were entered..and
 * the time complexity of the arrayList is "N" as it does linear search..
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Comment {
    String Name;
    String Comment;

    public Comment(String name, String comment) {
        this.Name = name;
        this.Comment = comment;
    }

    public String getName() {
        return Name;
    }

    public String getComment() {
        return Comment;
    }

    @Override
    public String toString() {
        return Name + " says: " + Comment;
    }
}

public class CommentingSystemArrayList{
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        List<Comment> CommentSet = new ArrayList<>();
        int choice;

        while (true) {
            System.out.println("Select your action");
            System.out.println("1. Comment on the post");
            System.out.println("2. Remove a comment");
            System.out.println("3. View comments");
            System.out.println("4. Remove all comments");
            System.out.println("5. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left in the buffer

            switch (choice) {
                case 1:
                    System.out.println("COMMENT HERE..!");
                    System.out.print("Name: ");
                    String Name = scanner.nextLine();
                    System.out.print("Comment: ");
                    String Comment = scanner.nextLine();
                    Comment newComment = new Comment(Name, Comment);
                    CommentSet.add(newComment);
                    break;

                case 2:
                    System.out.println("Enter the name of the comment to remove: ");
                    String nameToRemove = scanner.nextLine();
                    Iterator<Comment> iterator = CommentSet.iterator();
                    while (iterator.hasNext()) {
                        Comment comment = iterator.next();
                        if (comment.getName().equals(nameToRemove)) {
                            iterator.remove();
                            System.out.println("Comment removed: " + comment);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Comments:");
                    for (Comment comment : CommentSet) {
                        System.out.println(comment);
                    }
                    break;

                case 4:
                    CommentSet.clear(); // Remove all comments
                    System.out.println("All comments have been removed.");
                    break;

                default:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
            }
        }
    }
    
}
