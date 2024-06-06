package game605.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
@ConfigurationProperties(prefix = "mysql-properties")
public class DaoUtil {

    private static String url;
    private static String name;
    private static String username;
    private static String password;
    private static String driver;

    public static Connection connectionSql(){
        try {
            //连接JDBC驱动
            Class.forName(driver);

            Connection connection = null;
            //连接成功，Connection代表数据库对象
            /*
            *   url: jdbc:mysql://localhost:3306/test_db?useUnicode=true&characterEncoding=utf8&useSSL=true
                name: test_db
                username: root
                password: 123456
                driver: com.mysql.cj.jdbc.Driver
              */


            connection = DriverManager.getConnection(url,username,password);

            return connection;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    //释放资源
    public static void daoClose(ResultSet resultSet, Statement statement, PreparedStatement preparedStatement, Connection connection){
        try {
            if(resultSet!=null)
                resultSet.close();
            if(statement!=null)
                statement.close();
            if(preparedStatement!=null)
                preparedStatement.close();
            if(connection!=null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    //若要使用 ConfigurationProperties注解 set方法不能是静态的
    public void setUrl(String url) {
        DaoUtil.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        DaoUtil.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        DaoUtil.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        DaoUtil.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        DaoUtil.driver = driver;
    }
}
