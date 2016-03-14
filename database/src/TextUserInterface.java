import java.util.Scanner;
import java.util.Random;

public class TextUserInterface {
    static final String mainmenu =
            "Hello and welcome to Fantastix(tm) sports app 420\n" +
                    "These are your choices write desired number then return\n" +
                    "1: Create a new session\n" +
                    "2: Copy from existing session\n" +
                    "3: Modify a previous session\n" +
                    "4: get monthly best\n" +
                    "5: Exit this abomination\n" +
                    "Write your choice here: ";
    // Here we generate IDs for sessions

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println(mainmenu);
            int n = reader.nextInt();
            switch (n) {
                case 1:
                    if (createSession(reader) == true) {
                        System.out.println("Entry created successfull");
                    } else {
                        System.out.println("Entry already exists");
                    }
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

    public static boolean createSession(Scanner reader) {
        //TODO:Take values by scanner reader as above
        Random rand = null;
        int sessionID = rand.nextInt((20000 - 0) + 1);
        System.out.println("Insert date: ");
        String date = reader.nextLine();
        System.out.println("Insert durationInMinutes: ");
        int durationInMinutes = reader.nextInt();
        System.out.println("Insert form[0-10]: ");
        int form = reader.nextInt();
        System.out.println("Insert performance: ");
        int performance = reader.nextInt();
        System.out.println("Is this a template? [1/0]: ");
        boolean isTemplate = reader.nextBoolean();
        System.out.println("Was it outdoor? [1/0]: ");
        boolean isOutdoor = reader.nextBoolean();
        System.out.println("What was the temperature in celsius: ?");
        int temperature = reader.nextInt();
        System.out.println("Describe the weather for me [50 chars]: ");
        String weather = reader.next();
        System.out.println("Describe the airquality: ");
        int airQuality = reader.nextInt();
        System.out.println("Describe the ventilation: ");
        int ventilation = reader.nextInt();
        System.out.println("How many people watched: ");
        int peopleWatchingMe = reader.nextInt();
        //TODO: insert these values into db
        Database.createSession(SessionID, date, durationInMinutes, form, performance,
                isTemplate, isOutdoor, temperature, weather, airQuality, ventilation,
                peopleWatchingMe);
        return true;
    }
}

