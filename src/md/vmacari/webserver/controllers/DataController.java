package md.vmacari.webserver.controllers;

import io.netty.handler.codec.http.HttpResponseStatus;
import md.vmacari.services.DatabaseService;
import md.vmacari.webserver.response.GetDataResponse;
import org.restexpress.Request;
import org.restexpress.Response;

public class DataController
{
    private static final String NODE_ID_HEADER = "node";
    private static final String SENSOR_ID_HEADER = "sensor";
    private static final String LIMIT_HEADER = "limit";

    public DataController()
	{
		super();
	}

	public Object create(Request request, Response response)
	{
		//TODO: Your 'POST' logic here...
		return null;
	}

//	public GetDataResponse read(Request request, Response response)
//	{
//		//TODO: Your 'GET' logic here...
//		return null;
//	}
//
//	public GetDataResponse readAll(Request request, Response response)
//	{
//		response.setResponseCreated();
//		return new GetDataResponse(DatabaseService.get());
//
//		//TODO: Your 'GET collection' logic here...
//		return Collections.emptyList();
//	}


	public GetDataResponse readAllSensorData(Request request, Response response)
	{


        String nodeId = request.getHeader(NODE_ID_HEADER, "node id parameter is required ...");
        String sensorId = request.getHeader(SENSOR_ID_HEADER, "sensor id parameter is required ...");
        String limit = request.getHeader(LIMIT_HEADER);

        if (nodeId == null) {
            response.setResponseStatus(HttpResponseStatus.BAD_REQUEST);
            return new GetDataResponse("Node ID is required to get data");
        }

        if (sensorId == null) {
            response.setResponseStatus(HttpResponseStatus.BAD_REQUEST);
            return new GetDataResponse("Sensor ID is required to get data");
        }

        if (limit == null) {
            return new GetDataResponse(DatabaseService.getData(nodeId, sensorId));
        } else {
            return new GetDataResponse(DatabaseService.getData(nodeId, sensorId, Long.valueOf(limit)));
        }
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
