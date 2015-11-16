package md.vmacari.webserver.response;

import md.vmacari.data.Sensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmacari on 10/21/15.
 */
public class GetSensorResponse extends BaseResponse {
    public List<Sensor> data;

    public GetSensorResponse(List<Sensor> data) {
        this.data = data;
    }

    public GetSensorResponse() {
        super();
    }

    public GetSensorResponse(String errorMessage) {
        super(errorMessage);
    }

    public GetSensorResponse(Sensor data) {
        this.data = new ArrayList<Sensor>();
        this.data.add(data);
    }
}
