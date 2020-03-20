package com.github.yiyan1992.driver;

import com.github.yiyan1992.log.MySQL5Logger;
import com.github.yiyan1992.log.MySQL8Logger;
import com.github.yiyan1992.log.LoggerManager;
import com.github.yiyan1992.log.MyLogger;

import java.sql.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class MyDriver implements Driver {

    private static final Map<String, MyLogger> loggerMap=new ConcurrentHashMap<>();

    static {

        try {
            java.sql.DriverManager.registerDriver(new MyDriver());
        } catch (SQLException E) {
            throw new RuntimeException("Can't register driver!");
        }

        loggerMap.put("log-mysql5", new MySQL5Logger());
        loggerMap.put("log-mysql8", new MySQL8Logger());
    }

    Driver driver;

    public Connection connect(String url, Properties info) throws SQLException {
        setDriver(url);
        Connection connection = driver == null ? null : driver.connect(url.substring(url.indexOf(":")+1), info);
        return new MyConnection(connection);
    }

    public boolean acceptsURL(String url) {
        return driver == null;
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
        return driver != null && driver.jdbcCompliant();
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
