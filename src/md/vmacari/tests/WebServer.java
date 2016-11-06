package md.vmacari.tests;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import md.vmacari.Main;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vmacari on 10/17/15.
 */
public class WebServer {

    public void startWebInterface () {
        HttpServer server;
        try {

            server = HttpServer.create(new InetSocketAddress(8001), 0);
            server.createContext("/", new IndexPageContext());
            server.createContext("/about", new AboutPageContext());
            server.createContext("/nodes", new NodesPageContext());

            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static class IndexPageContext implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "Started <b>SmartSensorsServer</b>, welcome";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class AboutPageContext implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "<html> <body>About <b>SmartSensorsServer</b>, welcome </body> </html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class NodesPageContext implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {
            Headers requestHeaders  =  t.getRequestHeaders();

            String requestMethod =  t.getRequestMethod();
            String protocol =  t.getProtocol();
            URI requestUri = t.getRequestURI();

            Object idAtt = t.getAttribute("id");


            //t.getAttribute("id")


            String response = "<html> <body>About <b>SmartSensorsServer</b>, welcome </body> </html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }


    public static Map<String, String> queryToMap(HttpExchange t){

        String query = t.getRequestURI().getQuery();

        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }


}
