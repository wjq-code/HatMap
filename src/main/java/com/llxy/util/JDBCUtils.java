package com.llxy.util;

import com.llxy.config.ConfigurationManager;
import com.llxy.constant.Constants;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 类名称：${class}
 * 类描述：
 *
 * @author 武建谦
 * @Time 2018/11/23 15:37
 */
public class JDBCUtils {
    //通过读取配置信息获取到JDBC相关配置
    static {
        //通过配置管理组件来读取常量接口中的常量，从而获取到配置文件的value值
        String driver = ConfigurationManager.getProperty(Constants.JDBC_DRIVER);

        //加载驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建当前类的单例对象
     * 因为该工具类中要构建一个数据库连接池，连接池比较消耗资源，
     * 所要保证在整个操作过程中，只生成一个数据库连接池
     */
    private static JDBCUtils instance = null;

    public static JDBCUtils getInstance() {
        if (instance == null) {
            synchronized (JDBCUtils.class) {
                if (instance == null) {
                    instance = new JDBCUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 3、构建数据库连接池
     * 关于数据库连接池：现在已经有完全封装好的第三方数据库连接池，
     * 这种连接池一般在J2EE项目中直接可以嵌入使用当前我们采用自己封装的方式来完成
     * 数据结构  LinkedList 单向链表
     * 特点：后边的元素都持有前面元素的所有
     */
    LinkedList<Connection> dataSource = new LinkedList<Connection>();

    /**
     * 构建私有化构造器
     */
    private JDBCUtils() {
        /**
         * 1、获取数据库连接池中连接的数量
         * 一般情况，连接池的线程数是在编码之前就已近设计好的，所以把他作为配置信息存储在配置文件中
         */
        Integer count = ConfigurationManager.getInteger(Constants.JDBC_DATASOURCE_SIZE);
        //构建指定数量的数据库连接并且放入到数据量连接池
        for (int i = 1; i < count; i++) {
            String url = ConfigurationManager.getProperty(Constants.JDBC_URL);
            String user = ConfigurationManager.getProperty(Constants.JDBC_USER);
            String password = ConfigurationManager.getProperty(Constants.JDBC_PASSWORD);

            try {
                Connection conn = DriverManager.getConnection(url, user, password);
                dataSource.add(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 封装对外提供数据库连接的方法
     * 原因：有时候可能获取数据连接池中的连接时，连接池中的连接正好用光，所以会出现暂时无法获取连接的情况
     * 所以需要通过自定设置等待的时间，来等待获取数据库的连接
     * 注意：需要上同步锁
     */
    public synchronized Connection getConnection() {
        //如果连接池中的连接数等于0时，让其休眠1秒钟
        while (dataSource.size() == 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return dataSource.poll();
    }

    /**
     * 业务操作 CRUD
     */

    /**
     * 执行增删改操作
     *
     * @param sql    传入SQL语句
     * @param params 把SQL中执行的参数封装到一个数组中
     * @return 更新状态结果
     */
    public int executorUpdate(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int status = 0;
        try {
            //获取连接
            conn = getConnection();
            //取消自动处理对象
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            //那穿进来的数组
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            status = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                dataSource.push(conn);
            }
        }
        return status;
    }

    /**
     * 查询操作
     */
    /**
     * @param sql
     * @param params
     * @param queryCallBack 回调函数处理rs
     * @return
     */
    public void executorQurey(String sql, Object[] params, QueryCallBack queryCallBack) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //获取连接
        conn = getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            //那穿进来的数组
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            //执行sql
            rs = pstmt.executeQuery();
            queryCallBack.process(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                dataSource.push(conn);
            }
        }
    }

    /**
     * 回调函数：封装到一个方法中，当该方法被外籍调用时，触发回调函数执行：
     * 功能：处理sql查询结果集
     */
    public static interface QueryCallBack {
        public void process(ResultSet rs);
    }

    /**
     * 功能:jdbc高级功能，批量执行，好处：减少频繁创建连接，降低数据库压力；
     *
     * @param sql
     * @param paramsList
     * @return
     */
    public int[] executeBatch(String sql, List<Object[]> paramsList) {
        int[] ints = null;
        Connection conn = null;
        PreparedStatement preps = null;
        try {
            conn = getConnection();
            //开启手动提交事物
            conn.setAutoCommit(false);
            preps = conn.prepareStatement(sql);
            if (paramsList.size() > 0 && paramsList != null) {
                for (Object[] params : paramsList) {
                    for (int i = 0; i < params.length; i++) {
                        preps.setObject(i + 1, params[i]);
                    }
                    preps.addBatch();
                }
            }
            // 第三步：使用PreparedStatement.executeBatch()方法，执行批量的SQL语句
            ints = preps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                dataSource.push(conn);
            }
        }
        return ints;
    }
}