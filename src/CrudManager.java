import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudManager {
    public boolean saveData(Account account, Payment payment) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO account VALUES(?,?,?)"
            );
            preparedStatement.setString(1,account.getAccountId());
            preparedStatement.setString(2,account.getName());
            preparedStatement.setDouble(3,account.getAmount());

            boolean isSaved = preparedStatement.executeUpdate()>0;
            if (isSaved){
                if (true){
                    connection.rollback();
                    return false;
                }
                PreparedStatement preparedStatement1 = connection.prepareStatement(
                        "INSERT INTO payment VALUES(?,?,?,?)"
                );
                preparedStatement1.setString(1,payment.getPayId());
                preparedStatement1.setObject(2,payment.getPayDate());
                preparedStatement1.setDouble(3,payment.getPayment());
                preparedStatement1.setString(4,account.getAccountId());

                if (preparedStatement1.executeUpdate()>0){
                    connection.commit();
                    return true;
                }
                connection.rollback();
            }
            return false;

        } catch (SQLException e){
            connection.rollback();
            throw e;
        }
        finally {
            connection.setAutoCommit(true);
        }
    }
}
