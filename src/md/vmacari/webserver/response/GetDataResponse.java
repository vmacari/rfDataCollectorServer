package md.vmacari.webserver.response;

import md.vmacari.data.Data;

import java.util.List;

/**
 * Created by vmacari on 10/21/15.
 */
public class GetDataResponse extends BaseResponse {
    public List<Data> data;

    public GetDataResponse(List<Data> data) {
        this.data = data;
    }

    public GetDataResponse(String errorMessage) {
        super(errorMessage);
    }
}
