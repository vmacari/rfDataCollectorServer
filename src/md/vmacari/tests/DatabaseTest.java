package md.vmacari.tests;

import md.vmacari.data.Node;
import md.vmacari.services.DatabaseDriver;

import java.sql.SQLException;

/**
 * Created by vmacari on 11/5/16.
 */
public class DatabaseTest {


    public static void main (String [] args ) {

        // new WebServer().startWebInterface();

//        try {
//            List<Sensor> results =
//                    DatabaseDriver.getInstance().getSensorDao().queryBuilder()
//                            .where().
//                            eq("node_id", String.valueOf(1))
//                            .and()
//                            .eq("id", String.valueOf(1))
//                            .query();
//
//            if (results != null && results.size() > 0) {
//                System.out.println("found nodes!");
//            } else {
//                System.out.println("nodes not found!");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }


    public void testDbNodeTable () {
        Node n  = new Node();
        n.setName("node 1");
        try {
            DatabaseDriver.getInstance().getNodeDao().create(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String nodeId = String.valueOf(n.getId());
        try {
            n = DatabaseDriver.getInstance().getNodeDao().queryForId(nodeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        n.setProtocol("testProtocol");

        try {
            DatabaseDriver.getInstance().getNodeDao().update(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    private void testDatabaseOperations() {
        //      int newNodeId = DatabaseService.getNextAvailabelNodId ();
//      System.out.println("New node ID " + String.valueOf(newNodeId));
//      Node entity = DatabaseService.addOrUpdateNewNodeId((short)newNodeId, "Test node ");
//      System.out.println("New node added ! " + String.valueOf(entity.getId()));

//      DatabaseService.addLogMessage("message 1 "  + UUID.randomUUID().toString());
//      DatabaseService.addLogMessage("message 2 "  + UUID.randomUUID().toString());


//        DatabaseService.saveNodeSensor(1, 255, MessageGeneric.MessageTypes.Internal, MessagePresentationSubtypes.S_ARDUINO_NODE);
//        DatabaseService.saveNodeSensor(1, 255, MessageGeneric.MessageTypes.Internal, MessagePresentationSubtypes.S_ARDUINO_NODE);


//        DatabaseService.addLogMessage("Test log message " + UUID.randomUUID().toString());

/*
        try {
            Dao<Log, String> logsDao =
                    DaoManager.createDao(DatabaseDriver.getInstance().getConnectionSource(), Log.class);

            // if you need to create the 'accounts' table make this call
            //TableUtils.createTable(DatabaseDriver.getInstance().getConnectionSource(), Log.class);

            Log lg = new Log();
            lg.setMessage("Test message " + UUID.randomUUID().toString());
            lg.setTime(new Date());

            logsDao.create(lg);

            System.out.println("log entry created " + lg.getId());

            DatabaseDriver.getInstance().getConnectionSource().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
    }
}
