package md.vmacari.webserver.response;

/**
 * Created by vmacari on 10/21/15.
 */
public class BaseResponse {

    boolean success = true;
    String message = "OK";

    public BaseResponse () {}



    public BaseResponse(String errorMessage) {
        success = false;
        message = errorMessage;

    }

}
