package com.bow3n.learn.concurrency.class02;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author bowen
 */
public class DBPool {

    // 数据库连接池容器
    private static LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(SqlConnectionImpl.fetchConnection());
            }
        }
    }

    // 在超时前返回数据库连接
    public Connection getConn(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills < 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long overTime = System.currentTimeMillis() + mills;
                long remain = mills;
                while (pool.isEmpty() && remain > 0) {
                    pool.wait(remain);
                    remain = overTime - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }

    public void releaseConn(Connection connection){
        if(connection!=null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }
}
