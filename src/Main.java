import javax.xml.parsers.ParserConfigurationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Scanner;
import java.sql.*;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParserConfigurationException {
        String connectionUrl="jdbc:mysql://localhost:3306/test";
        String userName="root";
        String password="1234";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection collection= DriverManager.getConnection(connectionUrl, userName,password);
             Statement stat=collection.createStatement()){
            stat.executeUpdate("drop table books");
            stat.executeUpdate("create table if not exists books (id mediumint unsigned not null auto_increment,surname varchar(30) not null, experience mediumint unsigned not null , primary key (id)) DEFAULT CHARSET utf8");
            stat.executeUpdate("insert into books (surname, experience) values ('Иванов','10' )");
            stat.executeUpdate("insert into books (surname, experience) values ('Петров','12' )");
            stat.executeUpdate("insert into books (surname, experience) values ('Сидоров','14' )");
            ResultSet rez=stat.executeQuery("select surname from books where experience=(select max(experience) from books where experience<(select max(experience) from books))");
            while (rez.next()) {
                System.out.println(rez.getString("surname"));
            }
        }
    }
}