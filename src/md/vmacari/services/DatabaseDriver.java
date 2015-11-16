package md.vmacari.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import md.vmacari.common.Constants;
import md.vmacari.data.Data;
import md.vmacari.data.Log;
import md.vmacari.data.Node;
import md.vmacari.data.Sensor;
import md.vmacari.message.GwLogger;

import java.sql.SQLException;

/**
 * Created by vmacari on 9/27/15.
 */
public class DatabaseDriver {

    private Dao<Node, String>  nodeDao;
    private Dao<Sensor, String>  sensorDao;
    private Dao<Log, String>  logDao;
    private Dao<Data, String> dataDao;

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    private ConnectionSource connectionSource;


    private static DatabaseDriver _instance = null;

    public void flush () {
//        try {
//            _instance.connectionSource.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public static DatabaseDriver getInstance () {

        if (_instance == null) {
            _instance = new DatabaseDriver();
            try {
                _instance.connectionSource =
                        new JdbcConnectionSource(Constants.Dtabase.DATABASE_URL,
                                Constants.Dtabase.USERNAME, Constants.Dtabase.PASSWORD);
                if (_instance.connectionSource.isOpen() ) {
                    GwLogger.i("Connection is opened!");
                } else {
                    GwLogger.e("Connection is NOT opened!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (_instance == null | _instance.connectionSource == null) {
                return null;
            }

            try {
                _instance.logDao= DaoManager.createDao(_instance.connectionSource, Log.class);
                _instance.sensorDao= DaoManager.createDao(_instance.connectionSource, Sensor.class);
                _instance.nodeDao= DaoManager.createDao(_instance.connectionSource, Node.class);
                _instance.dataDao= DaoManager.createDao(_instance.connectionSource, Data.class);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return _instance;
    }

    public Dao<Node, String>  getNodeDao() {
        return nodeDao;
    }

    public  Dao<Sensor, String>  getSensorDao() {
        return sensorDao;
    }

    public  Dao<Log, String>  getLogsDao() {
        return logDao;
    }

    public  Dao<Data, String>  getDataDao() {
        return dataDao;
    }
}
