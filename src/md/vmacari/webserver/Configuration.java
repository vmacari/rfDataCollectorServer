package md.vmacari.webserver;

import md.vmacari.webserver.controllers.DataController;
import md.vmacari.webserver.controllers.HomeController;
import md.vmacari.webserver.controllers.NodeController;
import md.vmacari.webserver.controllers.SensorController;
import org.restexpress.RestExpress;
import org.restexpress.util.Environment;

import java.util.Properties;

public class Configuration extends Environment
{
	private static final String DEFAULT_EXECUTOR_THREAD_POOL_SIZE = "20";

	private static final String PORT_PROPERTY = "port";
	private static final String BASE_URL_PROPERTY = "base.url";
	private static final String EXECUTOR_THREAD_POOL_SIZE = "executor.threadPool.size";

	private int port;
	private String baseUrl;
	private int executorThreadPoolSize;

	private HomeController homeController;
	private NodeController nodeController;
	private SensorController sensorController;
	private DataController dataController;

	public void initializeWithDefaultValues ()
    {
		this.port =   RestExpress.DEFAULT_PORT;
		this.baseUrl =  "http://localhost:" + String.valueOf(port);
		this.executorThreadPoolSize =  Integer.valueOf(DEFAULT_EXECUTOR_THREAD_POOL_SIZE);
		initialize();
	}

	@Override
	protected void fillValues(Properties p)
	{
		this.port = Integer.parseInt(p.getProperty(PORT_PROPERTY, String.valueOf(RestExpress.DEFAULT_PORT)));
		this.baseUrl = p.getProperty(BASE_URL_PROPERTY, "http://localhost:" + String.valueOf(port));
		this.executorThreadPoolSize = Integer.parseInt(p.getProperty(EXECUTOR_THREAD_POOL_SIZE, DEFAULT_EXECUTOR_THREAD_POOL_SIZE));
		initialize();
	}

	private void initialize()
	{
		homeController = new HomeController();
		nodeController = new NodeController();
		sensorController = new SensorController();
		dataController = new DataController();
	}

	public int getPort()
	{
		return port;
	}
	
	public String getBaseUrl()
	{
		return baseUrl;
	}
	
	public int getExecutorThreadPoolSize()
	{
		return executorThreadPoolSize;
	}

	public HomeController getHomeController()
	{
		return homeController;
	}

	public NodeController getNodeController() {
		return nodeController;
	}

	public SensorController getSensorController() {
		return sensorController;
	}

	public DataController getDataController() {
		return dataController;
	}
}
