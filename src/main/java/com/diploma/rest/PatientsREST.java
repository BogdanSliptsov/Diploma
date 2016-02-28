package com.diploma.rest;

import com.diploma.service.DeseaseService;
import com.diploma.service.GeneralService;
import com.diploma.service.PatientsService;
import com.diploma.service.YearService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by boubdyk on 28.02.2016.
 */

@Path("/patient")
public class PatientsREST {

    private static ApplicationContext context;
    private static DeseaseService deseaseService;
    private static PatientsService patientsService;
    private static YearService yearService;
    private static GeneralService generalService;

    static {
        context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        deseaseService = context.getBean(DeseaseService.class);
        patientsService = context.getBean(PatientsService.class);
        yearService = context.getBean(YearService.class);
        generalService = context.getBean(GeneralService.class);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response createPatientsRecord(final String input) throws ParseException {
        JSONObject inputObj = (JSONObject) new JSONParser().parse(input);

        String diseaseName = inputObj.get("diseaseName").toString();
        Integer numberOfPatients = Integer.valueOf(inputObj.get("numberOfPatients").toString());
        Integer yearNumber = Integer.valueOf(inputObj.get("yeraNumber").toString());

        Long id = generalService.fillDataByYear(diseaseName, yearNumber, numberOfPatients);

        if (id == null) {
            return Response.status(Constants.CODE_NOT_MODIFIED).build();
        }
        return Response.status(Constants.CODE_CREATED).build();
    }

    @POST
    @Path("/restore")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response restoreDataForYears(final String input) throws ParseException {
        JSONObject inputObj = (JSONObject) new JSONParser().parse(input);

        String diseaseName = inputObj.get("diseaseName").toString();

        List<Integer> restoredYears = generalService.restoreDataForYears(diseaseName);

        if (restoredYears == null) {
            return Response.status(Constants.CODE_NOT_MODIFIED).build();
        }
        return Response.status(Constants.CODE_CREATED).build();
    }
}
