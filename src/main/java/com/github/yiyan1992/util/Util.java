package com.github.yiyan1992.util;

import org.slf4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;

public class Util {

    private final static String MYSQL5 = "com.mysql.jdbc.JDBC4PreparedStatement";
    private final static String MYSQL6 = "com.mysql.cj.jdbc.ClientPreparedStatement";

    public static void log(Logger logger, PreparedStatement preparedStatement) {
        if (logger.isDebugEnabled()) {
            logger.debug("[{}]", getSQL(preparedStatement));
        }
    }

    private static String getSQL(PreparedStatement preparedStatement) {

        String name = preparedStatement.getClass().getName();

        if (MYSQL5.equals(name) || MYSQL6.equals(name)) {
            return getMySQLSQL(preparedStatement);
        }

        return null;
    }

    private static String getMySQLSQL(PreparedStatement preparedStatement) {
        try {
            Method asSql = preparedStatement.getClass().getMethod("asSql", boolean.class);
            return asSql.invoke(preparedStatement, false).toString();
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return preparedStatement.toString();
        }
    }
}
