import org.junit.Test;

import java.sql.*;


public class TestSQL {

    /**
     * mysql8  url: log-mysql8  driver: com.mysql.cj.jdbc.Driver
     * mysql5  url: log-mysql5  driver: com.mysql.jdbc.Driver
     */
    @Test
    public void test() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");     //加载MYSQL JDBC驱动程序
            Class.forName("com.github.yiyan1992.driver.MyDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection connect = DriverManager.getConnection("log-mysql8:jdbc:mysql://mysql.server:3306/sys?" +
                    "useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC", "root",
                    "123456");
            //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
            PreparedStatement stmt = connect.prepareStatement("select * from sys_config");
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            System.out.println(metaData.getColumnCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
