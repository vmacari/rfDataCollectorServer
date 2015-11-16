package md.vmacari.webserver.response;

import md.vmacari.data.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmacari on 10/21/15.
 */
public class GetNodeResponse extends BaseResponse {
    public List<Node> data;

    public GetNodeResponse(List<Node> data) {
        this.data = data;
    }

    public GetNodeResponse() {
        super();
    }

    public GetNodeResponse(String errorMessage) {
        super(errorMessage);
    }

    public GetNodeResponse(Node node) {
        data = new ArrayList<Node>();
        data.add(node);
    }
}
