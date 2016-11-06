package md.vmacari.common;

/**
 * Created by vmacari on 10/17/15.
 */
public class Constants {
    public final static class Dtabase {
        public static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
        public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/nodes";
        public static final String USERNAME = "root";
        public static final String PASSWORD = "123123123";
        public static final String MAX_POOL = "250"; // set your own limit
    }

    /**
     * These define the URL parmaeters used in the route definition strings (e.g. '{userId}').
     */
    public final static class Url
    {
        //TODO: Your URL parameter names here...
        public static final String SAMPLE_ID = "sampleId";
    }

    /**
     * These define the route names used in naming each route definitions.  These names are used
     * to retrieve URL patterns within the controllers by name to create links in responses.
     */
    public final static class Routes
    {
        //TODO: Your Route names here...
        public static final String SINGLE_SAMPLE = "sample.single.route";
        public static final String SAMPLE_COLLECTION = "sample.collection.route";
        public static final String HOME_ROUTE = "home";
        public static final String NODE_ROUTE = "node";
        public static final String NODE_ROUTE_ID = "node.id";
        public static final String SENSOR_ROUTE = "sensor";
        public static final String DATA_ROUTE_LIMIT = "data.node.sensor.limit";
        public static final String SENSOR_ROUTE_ID = "sensor.id";
        public static String DATA_ROUTE = "data.node.sensor";
    }
}
