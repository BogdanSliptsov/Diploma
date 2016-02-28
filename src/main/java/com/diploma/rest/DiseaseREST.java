package com.diploma.rest;


import com.diploma.entity.DeseaseEntity;
import com.diploma.service.DeseaseService;
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
 * Created by boubdyk on 28.02.2016.
 */

@Path("/disease")
public class DiseaseREST {

    private static ApplicationContext context;
    private static DeseaseService deseaseService;

    static {
        context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        deseaseService = context.getBean(DeseaseService.class);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public final Response createDesease(final String input) throws ParseException {
        JSONObject inputObj = (JSONObject) new JSONParser().parse(input);
        String diseaseName = inputObj.get("diseaseName").toString();
        Long id = deseaseService.addDiseaseByName(diseaseName);
        if (id == null) {
            return Response.status(Constants.CODE_NOT_MODIFIED).build();
        }
           return Response.status(Constants.CODE_CREATED).build();
    }
}
