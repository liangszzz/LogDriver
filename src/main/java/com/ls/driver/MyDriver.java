package com.ls.driver;

import com.ls.Log.LoggerManager;
import com.ls.Log.MyLogger;
import com.ls.Log.MySQL5Logger;
import com.ls.Log.MySQL8Logger;

import java.sql.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/***
 * @author liang-shan@outlook.com
 * @createTime 2018-09-03
 * @DESC: 驱动
 */
public class MyDriver implements Driver {

    private static Map<String, MyLogger> loggerMap=new ConcurrentHashMap<>();

    static {
        loggerMap.put("log-mysql5", new MySQL5Logger());
        loggerMap.put("log-mysql8", new MySQL8Logger());
    }

    Driver driver;

    public Connection connect(String url, Properties info) throws SQLException {
        setDriver(url);
        Connection connection = driver == null ? null : driver.connect(url.substring(url.indexOf(":")+1), info);
        return new MyConnection(connection);
    }

    public boolean acceptsURL(String url) throws SQLException {
        return driver == null ? true : false;
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return driver == null ? new DriverPropertyInfo[0] : driver.getPropertyInfo(url, info);
    }

    public int getMajorVersion() {
        return driver == null ? 0 : driver.getMajorVersion();
    }

    public int getMinorVersion() {
        return driver == null ? 0 : driver.getMinorVersion();
    }

    public boolean jdbcCompliant() {
        return driver == null ? false : driver.jdbcCompliant();
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return driver == null ? null : driver.getParentLogger();
    }

    private void setDriver(String url) throws SQLException {
        String url_head=url.substring(0,url.indexOf(":"));
        if(loggerMap.containsKey(url_head)){
            String new_url = url.substring(url.indexOf(":")+1);
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver d = drivers.nextElement();
                if (d.acceptsURL(new_url)) {
                    driver = d;
                    LoggerManager.register(loggerMap.get(url_head));
                }
            }
        }

    }
}
