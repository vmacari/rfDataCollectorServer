package md.vmacari.webserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.restexpress.Request;
import org.restexpress.Response;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


public class HomeController
{
    public static Map<String, Object> object2map (Object object) {
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, object);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public class Product {
        public String url;
        public String name;
    }

    public HomeController()
	{
		super();
	}

	public Object create(Request request, Response response)
	{
		//TODO: Your 'POST' logic here...
		return null;
	}

	public Object read(Request request, Response response)
	{
		//TODO: Your 'GET' logic here...
		return null;
	}

	public Object readAll(Request request, Response response)
	{
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

        try {
            cfg.setTemplateLoader( new FileTemplateLoader());

            //Load template from source folder
            Template template = cfg.getTemplate("resources/templates/home/index.html");

            // Build the data-model
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("user", "TestUeser");

            Product pr = new Product();
            pr.url = "new product url";
            pr.name = "new prouct name";

            data.put("latestProduct", object2map(pr));

            StringWriter file = new StringWriter();
            template.process(data, file);
            file.flush();

//            String htmlOut = file.toString();
//            System.out.println(htmlOut);
//            return htmlOut;
            return file; //.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }


        return null; //"This is home page!";
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
