package md.vmacari.webserver;

import md.vmacari.webserver.serialization.SerializationProvider;
import org.restexpress.RestExpress;
import org.restexpress.pipeline.SimpleConsoleLogMessageObserver;

/**
 * Created by vmacari on 10/19/15.
 */
public class RestWebServer {

    private static final String SERVICE_NAME = "datalogger interface";
    private RestExpress server;

    public RestWebServer (String [] args) {

        RestExpress.setDefaultSerializationProvider(new SerializationProvider());

        Configuration config = new Configuration();
        config.initializeWithDefaultValues();

        RestExpress server = new RestExpress()
                .setName(SERVICE_NAME)
                .setBaseUrl(config.getBaseUrl())
                .setExecutorThreadCount(config.getExecutorThreadPoolSize())
                .setEnforceHttpSpec(true)
                .addMessageObserver(new SimpleConsoleLogMessageObserver());

        Routes.define(config, server);
        server.bind(config.getPort());
    }

}
