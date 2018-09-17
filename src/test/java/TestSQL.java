import com.github.yiyan1992.driver.MyDriver;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/***
 * @author liang-shan@outlook.com
 * @createTime 2018-09-03
 * @DESC:
 */
public class TestSQL {

    /**
     * mysql8  url: log-mysql8  driver: com.mysql.cj.jdbc.Driver
     * mysql5  url: log-mysql5  driver: com.mysql.jdbc.Driver
     */
    @Test
    public void test() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");     //加载MYSQL JDBC驱动程序
            DriverManager.registerDriver(new MyDriver());
            //Class.forName("org.gjt.mm.mysql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection connect = DriverManager.getConnection("log-mysql8:jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC", "root", "123456");
            //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
            PreparedStatement stmt = connect.prepareStatement("select * from sys_user");
            ResultSet rs = stmt.executeQuery();
            //user 为你表的名称
            while (rs.next()) {
               System.out.println(rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
