package server.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.sql.DataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 */
public class ConnectionManager {

  private static volatile ConnectionManager MANAGER;
  private final int SIZE;
  private static final String DRIVER = "com.mysql.jdbc.Driver";
  private static final String URL = "jdbc:mysql://localhost:3306/timetable";
  private static final String USERNAME = "scoreit";
  private static final String PASSWORD = "scoreit";

  private final DataSource dataSource;

  private ConnectionManager(int size) {
    SIZE = size;
    dataSource = initConnections();
  }

  public static ConnectionManager getInstance(int size) {
    if (MANAGER == null) {
      synchronized (ConnectionManager.class) {
        if (MANAGER == null) {
          MANAGER = new ConnectionManager(size);
        }
      }
    }
    return MANAGER;
  }


  private DataSource initConnections() {
    try {
      Class.forName(DRIVER);

      GenericObjectPool connectionPool = new GenericObjectPool();
      connectionPool.setMaxActive(10);

      ConnectionFactory cf = new DriverManagerConnectionFactory(
          URL,
          USERNAME,
          PASSWORD);

      PoolableConnectionFactory pcf =
          new PoolableConnectionFactory(cf, connectionPool,
              null, null, false, true);

      return new PoolingDataSource(connectionPool);

    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
      throw new RuntimeException("Can not initialize the connection pool.");
    }
  }


  public Connection getConnection(){
    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      throw new RuntimeException("Some problem with connection.");
    }
  }

}
