import java.util.Scanner;

public class TextUserInterface {
    static final String mainmenu = "Hello and welcome to Fantastix(tm) sports app 420" +
            "\nThese are your choices write desired number then return\n" +
            "1: Create a new session\n" +
            "2: Copy from existing session\n" +
            "3: Modify a previous session\n" +
            "4: get monthly best\n" +
            "5: Exit this abomination\n" +
            "Write your choice here: ";

    public static void main (String[] args){
        boolean running = true;
        while (running) {
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println(mainmenu);
            int n = reader.nextInt();
            switch (n) {
                case 1:
                    //TODO: call proper function
                    System.out.println("Not yet implemented");
                    break;
                case 2:
                    //TODO: call proper function
                    System.out.println("Not yet implemented");
                    break;
                case 3:
                    //TODO: call proper function
                    System.out.println("Not yet implemented");
                    break;
                case 4:
                    //TODO: call proper function
                    System.out.println("Not yet implemented");
                    break;
                case 5:
                    System.out.println("goodbye!");
                    running = false;
                    break;
            }
        }
    }
}
