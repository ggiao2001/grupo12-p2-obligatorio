import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CSV.getDrivers();
        CSV.getCsvInfo();
        FrontEnd frontEnd = new FrontEnd();
        frontEnd.Menu();

    }
}