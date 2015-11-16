package md.vmacari.webserver;

import io.netty.handler.codec.http.HttpMethod;
import md.vmacari.common.Constants;
import org.restexpress.RestExpress;

public abstract class Routes
{
	public static void define(Configuration config, RestExpress server)
    {

		server.uri("/", config.getHomeController())
				.action("readAll", HttpMethod.GET)
				.method(HttpMethod.POST)
				.name(Constants.Routes.HOME_ROUTE);

		//---------------------------------------------------------------------
		server.uri("/node.{format}", config.getNodeController())
				.action("readAll", HttpMethod.GET)
				.method(HttpMethod.POST)
				.name(Constants.Routes.NODE_ROUTE);

		server.uri("/node/{id}.{format}", config.getNodeController())
				.action("read", HttpMethod.GET)
				.method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
				.name(Constants.Routes.NODE_ROUTE_ID);

		//---------------------------------------------------------------------
		// http://localhost:8081/data/100/3
		server.uri("/data/{node}/{sensor}.{format}", config.getDataController())
				.action("readAllSensorData", HttpMethod.GET)
				.method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
				.name(Constants.Routes.DATA_ROUTE);

		// http://localhost:8081/data/100/3/10
		server.uri("/data/{node}/{sensor}/{limit}.{format}", config.getDataController())
				.action("readAllSensorData", HttpMethod.GET)
				.method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
				.name(Constants.Routes.DATA_ROUTE_LIMIT);


		//---------------------------------------------------------------------
		server.uri("/sensor/{node}.{format}", config.getSensorController())
				.action("readAll", HttpMethod.GET)
				.method(HttpMethod.POST)
				.name(Constants.Routes.SENSOR_ROUTE);

		server.uri("/sensor/{node}/{sensor}.{format}", config.getSensorController())
				.action("read", HttpMethod.GET)
				.method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
				.name(Constants.Routes.SENSOR_ROUTE_ID);
    }
}
