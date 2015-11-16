package md.vmacari.webserver.controllers;

import md.vmacari.data.Node;
import md.vmacari.services.DatabaseService;
import md.vmacari.webserver.response.GetNodeResponse;
import org.restexpress.Request;
import org.restexpress.Response;

public class NodeController
{
    private static final java.lang.String NODE_ID_HEADER = "id";

    public NodeController()
	{
		super();
	}

	public Object create(Request request, Response response)
	{
		//TODO: Your 'POST' logic here...
		return null;
	}

	public GetNodeResponse read(Request request, Response response)
	{
        String nodeId = request.getHeader(NODE_ID_HEADER, "id parameter is required ...");
        response.setResponseCreated();

        GetNodeResponse getNoderesponse = new GetNodeResponse();


        if (nodeId == null) {
            return new GetNodeResponse("Node id parameter not specified in header!");
        }

        Node  node = DatabaseService.getNode(nodeId);
        if(node == null) {
            return new GetNodeResponse("Could not get node from DB");
        }

        return  new GetNodeResponse(node);
	}

	public GetNodeResponse readAll(Request request, Response response)
	{
        response.setResponseCreated();
        return new GetNodeResponse(DatabaseService.getAllNodes());
	}

	public void update(Request request, Response response)
	{
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
        String nodeId = request.getHeader(NODE_ID_HEADER, "id parameter is required ...");
        response.setResponseCreated();
        if (nodeId == null) {

            //response.setBody();
            return;
        }


        DatabaseService.deleteNode(nodeId);

        //TODO: Your 'DELETE' logic here...
		// response.setResponseNoContent();
	}
}
