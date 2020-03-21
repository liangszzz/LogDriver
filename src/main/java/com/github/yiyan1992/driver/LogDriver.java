package com.github.yiyan1992.driver;

import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

public class LogDriver implements Driver {


    private static final String HEAD = "logSQL";

    static {
        try {
            java.sql.DriverManager.registerDriver(new LogDriver());
        } catch (SQLException E) {
            throw new RuntimeException("Can't register driver!");
        }
    }

    Driver driver;

    public Connection connect(String url, Properties info) throws SQLException {
        setDriver(url);
        Connection connection = driver == null ? null : driver.connect(url.substring(url.indexOf(":") + 1), info);
        return new LogConnection(connection);
    }

    public boolean acceptsURL(String url) {
        return url.startsWith(HEAD);
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
        if (url.startsWith(HEAD)) {
            String new_url = url.substring(HEAD.length() + 1);
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver d = drivers.nextElement();
                if (d.acceptsURL(new_url)) {
                    driver = d;
                }
            }
        }

    }
}
