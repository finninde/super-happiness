import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import java.util.Random;

public class TextUserInterface {
    static final String mainmenu =
            "Hello and welcome to Fantastix(tm) sports app 420\n" +
                    "These are your choices write desired number then return\n" +
                    "1: Create a new session\n" +
                    "2: Show existing templates\n" +
                    "3: Modify a previous session\n" +
                    "4: Compare last result with best\n" +
                    "5: Exit this abomination\n" +
                    "Write your choice here: ";
    Database database = null;
    // Here we generate IDs for sessions
    public TextUserInterface(Database database){
        this.database = database;
    }
    public void menu() {
        boolean running = true;
        while (running) {
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println(mainmenu);
            int n = reader.nextInt();
            switch (n) {
                case 1:
                    if (createSession(reader, this.database) == true) {
                        System.out.println("Entry created successfull");
                    } else {
                        System.out.println("Entry already exists");
                    }
                    break;
                case 2:
                    showTemplate(this.database);
                    break;
                case 3:
                    //TODO: call proper function
                    System.out.println("Not yet implemented");
                    break;
                case 4:
                    compareResult(reader, this.database);
                    break;
                case 5:
                    System.out.println("goodbye!");
                    running = false;
                    break;
            }
        }
    }
    public static void compareResult(Scanner reader, Database database){
        database.compareResult();
    }
	
	public void getNotes(Scanner reader,Database database){
    	database.getNotes();
    }
    public static void showTemplate(Database database){
        System.out.println(database.getTemplates());
    }


    public static boolean createSession(Scanner reader, Database database) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("You need to make ze key");
        int sessionID = reader.nextInt();
        System.out.println("Insert date: ");
        String date = scnr.nextLine();
        System.out.println("Insert durationInMinutes: ");
        int durationInMinutes = reader.nextInt();
        System.out.println("Insert form[0-10]: ");
        int form = reader.nextInt();
        System.out.println("Insert performance: ");
        int performance = reader.nextInt();
        System.out.println("Is this a template? [true/false]: ");
        boolean isTemplate = reader.nextBoolean();
        System.out.println("Was it outdoor? [1/0]: ");
        boolean isOutdoor = reader.nextBoolean();
        System.out.println("What was the temperature in celsius: ?");
        int temperature = reader.nextInt();
        System.out.println("Describe the weather for me [50 chars]: ");
        String weather = scnr.nextLine();
        System.out.println("Describe the airquality: ");
        int airQuality = reader.nextInt();
        System.out.println("Describe the ventilation: ");
        int ventilation = reader.nextInt();
        System.out.println("How many people watched: ");
        int peopleWatchingMe = reader.nextInt();
        System.out.println("What exercise did you do. Possible exercises are these");
        System.out.println(database.getExercises());
        String exerciseName  = scnr.nextLine();
        if (database.createSession(sessionID, date, durationInMinutes, form, performance,
                isTemplate, isOutdoor, temperature, weather, airQuality, ventilation,
                peopleWatchingMe)){
        return true;}
        else {return false;}
    }
}

