package kr.ac.jejunu.userdao;

public class DaoFactory {
    public ConnectionMaker getConnectionMaker() {
        return new JejuConnectionMaker();
    }

    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }
}
