/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md.vmacari.services;

import md.vmacari.data.Data;
import md.vmacari.data.Log;
import md.vmacari.data.Node;
import md.vmacari.data.Sensor;
import md.vmacari.message.GwLogger;
import md.vmacari.messages.MessageGeneric;
import md.vmacari.messages.MessagePresentationSubtypes;
import md.vmacari.messages.MessageSensorValues;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vmacari
 */
public class DatabaseService {

    public static void addLogMessage(String message) {

       // Logger.getGlobal().info("Adding log entry : " + message);

        // add new sensor
        Log logEntry = new Log();
        logEntry.setMessage(message);
        logEntry.setTime( new Date());

        try {
            DatabaseDriver.getInstance().getLogsDao().create(logEntry);
            //DatabaseDriver.getInstance().flush();
//            DatabaseDriver.getInstance().getLogsDao().commit(DatabaseDriver.getInstance().getConnectionSource().getReadWriteConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //private  EntityManager entityManager;
    private static DatabaseService _instance = null;
    
    private DatabaseService () {
//        entityManager = Persistence.createEntityManagerFactory(
//                "JavaTestRFGatewayPU").createEntityManager();
    }
    
    /**
     * 
     * @return 
     */
    private static DatabaseService getInstance () {
        if (_instance == null) {
            _instance = new DatabaseService();
        }
        return _instance;
    }
    
    /**
     * Retrieve all nodes from database
     * @return 
     */
    public static List<Node> getAllNodes () {
//        Query query = getInstance().entityManager.createNamedQuery("Node.findAll");
//        return query.getResultList();

        //Node  node = DatabaseDriver.getInstance().getNodeDao().queryForId(String.valueOf(nodeId));
        try {
            return  DatabaseDriver.getInstance().getNodeDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Node getNode (String nodeId) {
        try {
            return DatabaseDriver.getInstance().getNodeDao().queryForId(nodeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *  Get next available node Id 
     * TODO: Implement some logic to delete old nodes (somehow) ...
     * @return 
     */
    public static  short getNextAvailabelNodId() {

//        try {
//            Query query = getInstance().entityManager.createNamedQuery("Node.findAll");
//            List<Node> resultList = query.getResultList();
//            List<Integer> existingNodeIds = new ArrayList<Integer> ();
//
//            for (Node c : resultList) {
//                existingNodeIds.add(new Integer(c.getId()));
//            }
//
//            for (short i = 1; i< 255; i ++) {
//                if (!existingNodeIds.contains(new Integer(i))) {
//                    return i;
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        return -1;
    }
    
    /**
     * 
     * @param nodeId
     * @param name
     * @return 
     */    
    public static Node addOrUpdateNewNodeId (short nodeId, String name, String version) {
    
//        getInstance().entityManager.getTransaction().begin();
//
//        Query query = getInstance().entityManager.createNamedQuery("Node.findById");
//        query.setParameter("id", nodeId);
//        List<Node> resultList = query.getResultList();

        Node nodeEntity = null;

//        if (resultList == null || resultList.size() == 0) {
//            nodeEntity = new Node();
//            nodeEntity.setId(nodeId);
//        } else {
//            nodeEntity = resultList.get(0);
//        }
 
        nodeEntity.setName(name);
        nodeEntity.setVersion(version);
        
        
//        getInstance().entityManager.persist(nodeEntity);
//        getInstance().entityManager.getTransaction().commit();
        return nodeEntity;
    }
    
    /**
     * 
     * @param nodeId
     * @param childSensorId
     * @param payload 
     */
    public static void saveBatteryLevel(int nodeId, int childSensorId, String payload) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void sendConfig(int nodeId, int childSensorId, String payload) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void saveSketchName(int nodeId, int childSensorId, String payload) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GwLogger.i("saving sketch name : NodeId %d, sensor %d, payload  %s", nodeId, 
                childSensorId,  payload);

        try {
            Node  node = DatabaseDriver.getInstance().getNodeDao().queryForId(String.valueOf(nodeId));
            if (childSensorId == 255) {
                node.setName(payload);

            }

            DatabaseDriver.getInstance().getNodeDao().update(node);
        //    DatabaseDriver.getInstance().getNodeDao().commit(DatabaseDriver.getInstance().getConnectionSource().getReadWriteConnection());
            //DatabaseDriver.getInstance().flush();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void saveSketchVersion(int nodeId, int childSensorId, String payload) {
       //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GwLogger.i("saving sketch version : NodeId %d, sensor %d, payload  %s", nodeId, 
                childSensorId,  payload);

        // find node

        try {
            Node  node = DatabaseDriver.getInstance().getNodeDao().queryForId(String.valueOf(nodeId));
            if (childSensorId == 255) {
                node.setVersion(payload);

            }

            DatabaseDriver.getInstance().getNodeDao().update(node);
            //DatabaseDriver.getInstance().getNodeDao().commit(DatabaseDriver.getInstance().getConnectionSource().getReadWriteConnection());
            //DatabaseDriver.getInstance().flush();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void saveValue(int nodeId, int childSensorId, MessageSensorValues subType, String payload) {

        GwLogger.i("Save value for NodeId %d, sensorId %d, ValueType %s, Payload %s",
                nodeId, childSensorId, subType, payload);

        try {

            Data dataEntity = new Data();
            //Node  node = DatabaseDriver.getInstance().getNodeDao().queryForId(String.valueOf(nodeId));
            //Sensor sensor =  DatabaseDriver.getInstance().getSensorDao().queryForId(String.valueOf(childSensorId));

            dataEntity.setNodeId(nodeId);
            dataEntity.setSensorId(childSensorId);

            dataEntity.setDataType(subType.toString());
            dataEntity.setTime(new Date());
            dataEntity.setData(payload);

            DatabaseDriver.getInstance().getDataDao().create(dataEntity);
            //DatabaseDriver.getInstance().getDataDao().commit(DatabaseDriver.getInstance().getConnectionSource().getReadWriteConnection());
            //DatabaseDriver.getInstance().flush();

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public static void saveProtocol(int nodeId, int sensorId, String payload) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GwLogger.i("saving protocol for node: NodeId %d, Senosr ID %d, Protocol %s", nodeId, sensorId,  payload);

        try {
            Node  node = DatabaseDriver.getInstance().getNodeDao().queryForId(String.valueOf(nodeId));
            if (sensorId == 255) {
                node.setProtocol(payload);

            }

            DatabaseDriver.getInstance().getNodeDao().update(node);
            //DatabaseDriver.getInstance().flush();
            //DatabaseDriver.getInstance().getNodeDao().commit(DatabaseDriver.getInstance().getConnectionSource().getReadWriteConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public static List<Node> getNodes () {
//        List<Node> nodes = new ArrayList<Node>();
//        return nodes;
//    }


    public static void saveNodeSensor(int nodeId, int childSensorId, MessageGeneric.MessageTypes messageType, MessagePresentationSubtypes subType,
                                      String payload) {
        GwLogger.i("saving node sensor [NodeId: %d,   sensorId : %d,  MessageType: %s, MessageSubtype %s, Name %s]",
                nodeId, childSensorId, messageType, subType, payload);

        // node info
        if (childSensorId == 255) {

            try {
                Node  node = DatabaseDriver.getInstance().getNodeDao().queryForId(String.valueOf(nodeId));

                if (node != null) {
                    node.setLastUpdateTime(new Date());
                    DatabaseDriver.getInstance().getNodeDao().update(node);
                } else {
                    node = new Node();
                    node.setId(nodeId);
                    //node.setName(payload);
                    node.setLastUpdateTime(new Date());
                    DatabaseDriver.getInstance().getNodeDao().create(node);
                }

                DatabaseDriver.getInstance().flush();

            } catch (SQLException e) {
                e.printStackTrace();
            }


            return;
        }

        try {
            List<Sensor> results =
                    DatabaseDriver.getInstance().getSensorDao().queryBuilder()
                            .where().
                            eq("node_id", String.valueOf(nodeId))
                            .and()
                            .eq("id", String.valueOf(childSensorId))
                            .query();

            if (results!=null && results.size() > 0 ){
                // update existing sensor info
                Sensor firstFoundSensor = results.get(0);

                firstFoundSensor.setType(subType);

                DatabaseDriver.getInstance().getSensorDao().update(firstFoundSensor);

            } else {

                GwLogger.i("Add new sensor requested");
               //Node node =  DatabaseDriver.getInstance().getNodeDao().queryForId(String.valueOf(nodeId));

                // add new sensor
                Sensor sensor = new Sensor();
                sensor.setType(subType);
                sensor.setId(childSensorId);
                sensor.setName(payload);
                //sensor.setNode(node);
                sensor.setNodeId(nodeId);

                int cnt = DatabaseDriver.getInstance().getSensorDao().create(sensor);

                GwLogger.i("Sensor added %d, with ID ", cnt, sensor.getId());
                //DatabaseDriver.getInstance().getSensorDao().commit(DatabaseDriver.getInstance().getConnectionSource().getReadWriteConnection());

            }

            //DatabaseDriver.getInstance().getSensorDao().commit(DatabaseDriver.getInstance().getConnectionSource().getReadWriteConnection());
            //DatabaseDriver.getInstance().flush();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void sendFirmwareConfigResponse(int nodeId, short fwType, short fwVersion) {
        GwLogger.e("[sendFirmwareConfig] firmware operations not supported");
    }

    public static void sendFirmwareResponse(int nodeId, short fwType, short fwVersion, short fwBlock) {
        GwLogger.e("[sendFirmware] firmware operations not supported");
    }

    public static void deleteNode(String nodeId) {
        try {
            int cnt = DatabaseDriver.getInstance().getNodeDao().deleteById(nodeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<Data> getData(String nodeId, String sensorId) {

        try {
            return DatabaseDriver.getInstance().getDataDao().queryBuilder()
                            .where().
                            eq("node_id", String.valueOf(nodeId))
                            .and()
                            .eq("sensor_id", String.valueOf(sensorId))
                            .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Data> getData(String nodeId, String sensorId, Long limit) {

        try {
            return DatabaseDriver.getInstance().getDataDao().queryBuilder()
                    .limit(limit)
                    .where().
                            eq("node_id", String.valueOf(nodeId))
                    .and()
                    .eq("sensor_id", String.valueOf(sensorId))


                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Sensor getSensor(String nodeId, String sensorId) {

        try {
            List<Sensor> sensors = DatabaseDriver.getInstance().getSensorDao().queryBuilder()
                    .where().
                            eq("node_id", String.valueOf(nodeId))
                    .and()
                    .eq("sensor_id", String.valueOf(sensorId))
                    .query();


           if (sensors.size() > 0) {
               return sensors.get(0);
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Sensor> getSensors(String nodeId) {
        try {
            return DatabaseDriver.getInstance().getSensorDao().queryBuilder()
                    .where().
                            eq("node_id", String.valueOf(nodeId))
                    .query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
