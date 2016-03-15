public class Main {
    public static void main(String args[]){
        Database database = new Database();
        database.createSession(80, "01/01/91", 20, 1, 1, true, true, 20, "fine", 2, 1, 1);
        TextUserInterface tui = new TextUserInterface(database);
        tui.menu();
        //System.out.println(database.getExercises());
    }
}