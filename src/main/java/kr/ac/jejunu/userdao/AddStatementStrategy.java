package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makePreparedStatement(Object object, Connection connection) throws SQLException {
        User user = (User) object;

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userinfo(name, password) VALUES (?, ?);");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        return preparedStatement;
    }
}
