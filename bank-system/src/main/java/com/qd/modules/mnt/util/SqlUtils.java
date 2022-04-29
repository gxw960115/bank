package com.qd.modules.mnt.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import com.qd.utils.CloseUtil;
import com.qd.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: bank
 * @BelongsPackage: com.qd.modules.mnt.util
 * @Author: GXW
 * @CreateTime: 2022-04-29  11:22
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
public class SqlUtils {

    private static DataSource getDataSource(String jdbcUrl, String userName, String password) {
        DruidDataSource druidDataSource = new DruidDataSource();
        String className;
        try {
            className = DriverManager.getDriver(jdbcUrl.trim()).getClass().getName();
        } catch (SQLException e) {
            throw new RuntimeException("Get class name error:=" + jdbcUrl);
        }
        if (StringUtils.isEmpty(className)) {
            DataTypeEnum dataTypeEnum = DataTypeEnum.urlOf(jdbcUrl);
            if (null == dataTypeEnum) {
                throw new RuntimeException("Not supported data type: jdbcUrl=" + jdbcUrl);
            }
            druidDataSource.setDriverClassName(dataTypeEnum.getDriver());
        } else {
            druidDataSource.setDriverClassName(className);
        }

        druidDataSource.setUrl(jdbcUrl);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        // 配置获取链接等待超时时间
        druidDataSource.setMaxWait(3000);
        // 配置初始化大小、最小、最大
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxActive(1);

        // 如果连接出现异常则直接判定为失败而不是一直重试
        druidDataSource.setBreakAfterAcquireFailure(true);
        try {
            druidDataSource.init();
        } catch (SQLException e) {
            log.error("Exception during pool initialization", e);
            throw new RuntimeException(e.getMessage());
        }
        return druidDataSource;
    }

    public static Connection getConnection(String jdbcUrl, String userName, String password) {
        DataSource dataSource = getDataSource(jdbcUrl, userName, password);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (Exception ignored) {

        }
        try {
            int timeOut = 5;
            if (null == connection || connection.isClosed() || !connection.isValid(timeOut)) {
                log.info("connection is closed or invalid,retry get connection!");
                connection = dataSource.getConnection();
            }
        } catch (Exception e) {
            log.error("create connection error,jdbcUrl:{}", jdbcUrl);
            throw new RuntimeException("create connection error,jdbcUrl:" + jdbcUrl);
        } finally {
            CloseUtil.close(connection);
        }
        return connection;
    }

    private static void releaseConnection(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                log.error("connection close error:" + e.getMessage());
            }
        }
    }

    public static boolean testConnection(String jdbcUrl, String userName, String password) {
        Connection connection = null;
        try {
            connection = getConnection(jdbcUrl, userName, password);
            if (null != connection) {
                return true;
            }
        } catch (Exception e) {
            log.info("Get connection failed:" + e.getMessage());
        } finally {
            releaseConnection(connection);
        }
        return false;
    }

    public static String executeFile(String jdbcUrl, String uerName, String password, File sqlFile) {
        Connection connection = getConnection(jdbcUrl, uerName, password);
        try {
            batchExecute(connection, readSqlList(sqlFile));
        } catch (Exception e) {
            log.error("sql脚本执行发生异常:{}", e.getMessage());
            return e.getMessage();
        } finally {
            releaseConnection(connection);
        }
        return "success";
    }


    /**
     * @methodname: batchExecute
     * @description: 批量执行sql
     * @author: GXW
     * @date: 2022/4/29 12:36
     * @param: connection /
     * @return: sqlList /
     **/
    private static void batchExecute(Connection connection, List<String> sqlList) {
        Statement st = null;
        try {
            st = connection.createStatement();
            for (String sql : sqlList) {
                if (sql.endsWith(";")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                st.addBatch(sql);
            }
            st.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            CloseUtil.close(st);
        }
    }

    /**
     * @methodname: readSqlList
     * @description:
     * @author: GXW
     * @date: 2022/4/29 12:45
     * @param: sqlFile
     * @return: sqlList
     **/
    private static List<String> readSqlList(File sqlFile) throws Exception {
        List<String> sqlList = Lists.newArrayList();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(sqlFile), StandardCharsets.UTF_8))) {
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                log.info("line:{}", tmp);
                if (tmp.endsWith(";")) {
                    sb.append(tmp);
                    sqlList.add(sb.toString());
                    sb.delete(0, sb.length());
                } else {
                    sb.append(tmp);
                }
            }
            if (!"".endsWith(sb.toString().trim())) {
                sqlList.add(sb.toString());
            }
        }
        return sqlList;
    }
}
