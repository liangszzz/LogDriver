package com.ls.Log;

import java.sql.PreparedStatement;
import java.util.Arrays;

/***
 * @author liang-shan@outlook.com
 * @createTime 2018-09-04
 * @DESC:
 */
public class MySQL5Logger implements MyLogger {

    @Override
    public void debug(String info) {
        printInfo(info);
    }

    @Override
    public void debug(PreparedStatement preparedStatement) {
        printInfo(getAsSqlByPreparedStatement(preparedStatement, getclazzName()));
    }

    @Override
    public void debug(PreparedStatement preparedStatement, String sql) {
        printInfo(getAsSqlByPreparedStatement(preparedStatement, getclazzName()));
        printInfo(sql);
    }

    @Override
    public void debug(PreparedStatement preparedStatement, String sql, int autoGeneratedKeys) {
        printInfo(getAsSqlByPreparedStatement(preparedStatement, getclazzName()));
        printInfo(sql);
        printInfo(autoGeneratedKeys + "");
    }

    @Override
    public void debug(PreparedStatement preparedStatement, String sql, int[] columnIndexes) {
        printInfo(getAsSqlByPreparedStatement(preparedStatement, getclazzName()));
        printInfo(sql);
        printInfo(Arrays.toString(columnIndexes));
    }

    @Override
    public void debug(PreparedStatement preparedStatement, String sql, String[] columnNames) {
        printInfo(getAsSqlByPreparedStatement(preparedStatement, getclazzName()));
        printInfo(sql);
        printInfo(Arrays.toString(columnNames));
    }

    @Override
    public String getclazzName() {
        return "com.mysql.jdbc.PreparedStatement";
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(obj.hashCode()==this.hashCode()){
//            return true;
//        }
//        return false;
//    }
//    @Override
//    public int hashCode(){
//        return this.getclazzName().hashCode();
//    }

}
