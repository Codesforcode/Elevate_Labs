import java.util.ArrayList;
import java.util.Scanner;

class Book{
    private String name;
    private String author;
    private boolean isIssued;
    private int price;


    Book( String name, String author,int price ){
        this.name = name;
        this.author= author;
        this.isIssued =false;
        this.price= price;

    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public int getPrice() {
        return price;
    }

        public void issue() {
        isIssued = true;
    }
        public void returnBook() {
        isIssued = false;
    }
    public String toString() {
        return name + " by " + author + (isIssued ? " [Issued]" : " [Available]") + "Price"+price;
    }

    }


 class Library{
    private ArrayList<Book> books;

      public Library(){
          books = new ArrayList<>();
     }
     public void addBook( Book book){
          books.add(book);
     }
     public void showBook(){
         for (Book book:books){
        System.out.println(book);
    }


         }
     public void issueBook(String name ){
          for (Book b: books){
              if (b.getName().equalsIgnoreCase(name) && !b.isIssued()) {
                  b.issue();
                  System.out.println(name + " has been issued.");
                  return;
              }
          }
         System.out.println("Book not available or already issued.");
   }
   public void returnBook(String name) {
         for (Book b : books) {
             if (b.getName().equalsIgnoreCase(name) && b.isIssued()) {
                 b.returnBook();
                 System.out.println(name + " has been returned.");
                 return;
             }
         }
         System.out.println("Invalid return attempt.");
     }
 }

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}



public class LibraryManagementSystem {
    public static void main(String[] args) {

        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        library.addBook(new Book("The Alchemist", "Paulo Coelho",500));
        library.addBook(new Book("1984", "George Orwell",500));
        library.addBook(new Book("Clean Code", "Robert C. Martin",600));
        System.out.println("Welcome to the Library System");

        while (true) {
            System.out.println("\n1. Show Books\n2. Issue Book\n3. Return Book\n4. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    library.showBook();
                    break;
                case 2:
                    System.out.print("Enter book title to issue: ");
                    String issueTitle = sc.nextLine();
                    library.issueBook(issueTitle);
                    break;
                case 3:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = sc.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
