package md.vmacari.webserver.controllers;

import md.vmacari.data.Sensor;
import md.vmacari.services.DatabaseService;
import md.vmacari.webserver.response.GetSensorResponse;
import org.restexpress.Request;
import org.restexpress.Response;

public class SensorController
{
	private static final String NODE_ID_HEADER = "node";
	private static final String SENSOR_ID_HEADER = "sensor";

	public SensorController()
	{
		super();
	}

	public Object create(Request request, Response response)
	{
		//TODO: Your 'POST' logic here...
		return null;
	}

	public GetSensorResponse read(Request request, Response response)
	{
		String nodeId = request.getHeader(NODE_ID_HEADER, "node parameter is required ...");
		String sensorId = request.getHeader(SENSOR_ID_HEADER, "sensor parameter is required ...");
		response.setResponseCreated();

		if (nodeId == null) {
			return new GetSensorResponse("Node id parameter not specified in header!");
		}

		if (sensorId == null) {
			return new GetSensorResponse("Sensor id parameter not specified in header!");
		}

		Sensor sensor = DatabaseService.getSensor(nodeId, sensorId);
		if(sensor == null) {
			return new GetSensorResponse("Could not get sensor from DB");
		}

		return  new GetSensorResponse(sensor);
	}

	public GetSensorResponse readAll(Request request, Response response)
	{
		String nodeId = request.getHeader(NODE_ID_HEADER, "node parameter is required ...");
		if (nodeId == null) {
			return new GetSensorResponse("Node id parameter not specified in header!");
		}

		response.setResponseCreated();
		return new GetSensorResponse(DatabaseService.getSensors(nodeId));
	}


	public void update(Request request, Response response)
	{
		//TODO: Your 'PUT' logic here...
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		//TODO: Your 'DELETE' logic here...
		response.setResponseNoContent();
	}
}
