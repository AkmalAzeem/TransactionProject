import java.sql.SQLException;
import java.util.Date;

public class AppInitializer {
    public static void main(String[] args) {
        CrudManager crudManager = new CrudManager();
        Account account = new Account("A-1", "Nimal", 25000);
        Payment payment = new Payment("P-1", new Date(),25000);
        try {
            System.out.println(
                    crudManager.saveData(account,payment)
            );
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
