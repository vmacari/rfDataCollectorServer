/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari;

import com.j256.ormlite.logger.LocalLog;
import com.j256.ormlite.logger.LoggerFactory;
import gnu.io.CommPortIdentifier;
import md.vmacari.comm.*;
import md.vmacari.data.Node;
import md.vmacari.message.MessageReader;
import md.vmacari.services.DatabaseDriver;
import md.vmacari.webserver.RestWebServer;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;


/**
 *
 * @author vmacari
 */
public class Main {


    private void startSerialLogger (final String portName) {
        Enumeration e = CommPortIdentifier.getPortIdentifiers();

        CommPortIdentifier selectedComPortId = null;
        System.out.println("Enumerate ports ...");
        while (e.hasMoreElements()) {
            CommPortIdentifier comPortId = (CommPortIdentifier) e.nextElement();
            System.out.println(comPortId.getName());

            if (comPortId.getName().equals(portName)) {
                selectedComPortId = comPortId;
            }

        }

        if (selectedComPortId != null) {
            System.out.print("Com port selected!");


            MessageReader reader = null;

            reader = new MessageReader(Arrays.asList(
                    new MessageInternalHadler(),
                    new MessagePresentationHandler(),
                    new MessageSetHandler(),
                    new MessageStreamHandler(),
                    new MessageRequestHandler()
            ),
                    selectedComPortId,
                    115200);

            reader.startCommunication();
        } else {
            System.out.println("Cannot select com port!");

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

    private void testDbNodeTable () {
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "trace");
        System.setProperty(LoggerFactory.LOG_TYPE_SYSTEM_PROPERTY, LoggerFactory.LogType.LOCAL.toString());

        //final String portName = "/dev/ttyUSB0";
        final String portName = "/dev/tty.wchusbserial1420";

        System.out.println("Application started!");

        Main mainApplication = new Main();
        //mainApplication.startSerialLogger(portName);

        new RestWebServer(args);

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




    
}
