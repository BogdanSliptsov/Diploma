package com.diploma.frontend;

import com.diploma.service.DeseaseService;
import com.diploma.service.GeneralService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Yevhenii on 2/29/2016.
 */

@WebServlet(urlPatterns = "/forecast")
public class ForecastController extends HttpServlet{

    private static ApplicationContext context;
    private static GeneralService generalService;

    static {
        context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        generalService = context.getBean(GeneralService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String disease = req.getParameter("disease");

        Map<Integer, Integer> allPatientsOfDisease = generalService.getAllPatientsOfDisease(disease);
        req.setAttribute("restoredValues", allPatientsOfDisease);
        req.getRequestDispatcher("forecast.jsp").forward(req, resp);

    }



}
