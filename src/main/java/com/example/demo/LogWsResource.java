package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/logs")
public class LogWsResource {

    @Context
    private HttpServletRequest request;

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @GET
    @Path("/test")
    public String test() {
        return "Hello, World!";
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createLog(LogWsJson logWsJson) {
        try {
            LogWsService logWsService = new LogWsService(em, request);
            logWsService.createLog(logWsJson);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.CREATED).build();
    }
}