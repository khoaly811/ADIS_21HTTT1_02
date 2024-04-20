package adis02;

public class App {
    public static void main(String[] args) {
        // Database connection details
        MainDatabase db = new MainDatabase();
        try {
            db.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
