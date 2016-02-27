package com.diploma.rest;

import com.diploma.entity.TestEntity;
import com.diploma.service.TestService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by boubdyk on 27.02.2016.
 */

@Path("/general")
public class GeneralREST {

    private static ApplicationContext context;
    private static TestService testService;

    static {
        context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        testService = context.getBean(TestService.class);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response testCreate(final String input) throws ParseException {
        System.out.println("\n\n\n\n\nGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGggg\n\n\n\n\n\n\n\n\n");
        JSONObject inputObj = (JSONObject) new JSONParser().parse(input);
        String field = inputObj.get("field").toString();
        TestEntity te = new TestEntity();
        te.setField(field);
        Long id = testService.testCreate(te);
        if (id == null) {
            return Response.status(Constants.CODE_NOT_MODIFIED).build();
        }
        JSONObject returnObj = new JSONObject();
        returnObj.put("id", id);
        return Response.status(Constants.CODE_CREATED).entity(returnObj.toJSONString()).build();
    }
}
