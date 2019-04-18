package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makePreparedStatement(Object object, Connection connection) throws SQLException {
        Long id = (Long) object;

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM userinfo WHERE id=?;");
        preparedStatement.setLong(1, id);

        return preparedStatement;
    }
}
