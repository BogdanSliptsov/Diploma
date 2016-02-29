package com.diploma.rest;

import com.diploma.Point;
import com.diploma.service.DeseaseService;
import com.diploma.service.GeneralService;
import com.diploma.service.PatientsService;
import com.diploma.service.YearService;
import org.json.simple.JSONArray;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
    @Path("/get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response getPatients(final String input) throws ParseException {
        JSONObject inputObj = (JSONObject) new JSONParser().parse(input);

        String diseaseName = inputObj.get("diseaseName").toString();

        List<Point> points = generalService.getAllPatientsOfDisease(diseaseName);

        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.getX() > o2.getX()) return 1;
                if (o1.getX() < o2.getX()) return -1;
                return 0;
            }
        });

        if (points == null) {
            return Response.status(Constants.CODE_NOT_MODIFIED).build();
        }

        JSONObject returnObject = new JSONObject();
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        for (Point p: points) {
            jsonObject = new JSONObject();
            jsonObject.put("year", p.getX());
            jsonObject.put("numberOfPatients", p.getY());
            jsonArray.add(jsonObject);
        }
        returnObject.put("ResultList", jsonArray);
        return Response.status(Constants.CODE_CREATED).entity(returnObject.toJSONString()).build();
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

    @POST
    @Path("/forecast/smoothing")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response getForecastedPatientsSmoothing(final String input) throws ParseException {
        JSONObject inputObj = (JSONObject) new JSONParser().parse(input);

        String diseaseName = inputObj.get("diseaseName").toString();
        Integer years = Integer.valueOf(inputObj.get("years").toString());

        List<Point> points = generalService.getAllForecastedPatientsOfDiseaseSmoothing(diseaseName, years);

        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.getX() > o2.getX()) return 1;
                if (o1.getX() < o2.getX()) return -1;
                return 0;
            }
        });

        if (points == null) {
            return Response.status(Constants.CODE_NOT_MODIFIED).build();
        }
        JSONObject returnObject = new JSONObject();
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        for (Point p: points) {
            jsonObject = new JSONObject();
            jsonObject.put("year", p.getX());
            jsonObject.put("numberOfPatients", p.getY());
            jsonArray.add(jsonObject);
        }
        returnObject.put("ResultList", jsonArray);
        return Response.status(Constants.CODE_CREATED).entity(returnObject.toJSONString()).build();
    }

//    @POST
//    @Path("/forecast/fourier")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public final Response getForecastedPatientsFourierSeries(final String input) throws ParseException {
//        JSONObject inputObj = (JSONObject) new JSONParser().parse(input);
//
//        String diseaseName = inputObj.get("diseaseName").toString();
//
//        //TODO Change here to valid math method
//        Integer years = Integer.valueOf(inputObj.get("years").toString());
//
//        List<Point> points = generalService.getAllForecastedPatientsOfDiseaseSmoothing(diseaseName, years);
//
//        if (points == null) {
//            return Response.status(Constants.CODE_NOT_MODIFIED).build();
//        }
//        JSONArray returnJSON = new JSONArray();
//        JSONObject patientsJSON;
//        JSONObject yearJSON;
//
//        for (Point p : points) {
//            yearJSON = new JSONObject();
//            patientsJSON = new JSONObject();
//            yearJSON.put("year", p.getX());
//            patientsJSON.put("patients", p.getY());
//            returnJSON.add(patientsJSON);
//        }
//        return Response.status(Constants.CODE_CREATED).entity(returnJSON.toJSONString()).build();
//    }

    @POST
    @Path("/forecast/polinom")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response getForecastedPatientsPolinom(final String input) throws ParseException {
        JSONObject inputObj = (JSONObject) new JSONParser().parse(input);

        String diseaseName = inputObj.get("diseaseName").toString();

        //TODO Change here to valid math method
        Integer years = Integer.valueOf(inputObj.get("years").toString());

        List<Point> points = generalService.getAllForecastedPatientsOfDiseaseSmoothing(diseaseName, years);

        if (points == null) {
            return Response.status(Constants.CODE_NOT_MODIFIED).build();
        }
        JSONArray returnJSON = new JSONArray();
        JSONObject patientsJSON;
        JSONObject yearJSON;

        for (Point p : points) {
            yearJSON = new JSONObject();
            patientsJSON = new JSONObject();
            yearJSON.put("year", p.getX());
            patientsJSON.put("patients", p.getY());
            returnJSON.add(patientsJSON);
        }
        return Response.status(Constants.CODE_CREATED).entity(returnJSON.toJSONString()).build();
    }
}
