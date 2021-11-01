import java.util.*;

public class Quit {
  
  public static void userQuit() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Are you sure you want to quit?\n1 is yes\n2 is no\nEnter choice: ");
    int userInput = sc.nextInt();
    
    if(userInput == 1) {
      System.exit(0);
    }
  }
  
  public static void main(String[] args) {
    userQuit();
  }
}
