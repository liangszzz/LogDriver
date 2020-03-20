import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class TestSQL {


    public static void main(String[] args) {

        try {
            Connection connect = DriverManager.getConnection("logSQL:jdbc:mysql://mysql.server:3306/sys?" +
                            "useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC", "root",
                    "123456");

            //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
            PreparedStatement stmt = connect.prepareStatement("select * from sys_config where value=?");
            stmt.setInt(1, 64);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    System.out.println(rs.getObject(i + 1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
