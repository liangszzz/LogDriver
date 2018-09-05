package com.ls.Log;

import java.sql.PreparedStatement;
import java.util.Vector;

/***
 * @author liang-shan@outlook.com
 * @createTime 2018-09-04
 * @DESC:
 */
public class LoggerManager {

    private static Vector<MyLogger> loggers=new Vector<>();

    public static void register(MyLogger logger) {
        if(!loggers.contains(logger)){
            loggers.add(logger);
        }
    }

    public static void debug(String sql) {
        loggers.forEach(e -> {
            e.debug(sql);
        });
    }

    public static void debug(PreparedStatement preparedStatement) {
        loggers.forEach(e -> {
            e.debug(preparedStatement);
        });
    }

    public static void debug(PreparedStatement preparedStatement, String sql) {
        loggers.forEach(e -> {
            e.debug(preparedStatement, sql);
        });
    }

    public static void debug(PreparedStatement preparedStatement, String sql, int autoGeneratedKeys) {
        loggers.forEach(e -> {
            e.debug(preparedStatement, sql, autoGeneratedKeys);
        });
    }

    public static void debug(PreparedStatement preparedStatement, String sql, int[] columnIndexes) {
        loggers.forEach(e -> {
            e.debug(preparedStatement, sql, columnIndexes);
        });
    }

    public static void debug(PreparedStatement preparedStatement, String sql, String[] columnNames) {
        loggers.forEach(e -> {
            e.debug(preparedStatement, sql, columnNames);
        });
    }

}
