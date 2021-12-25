package com.javaeefinal.itse1908r.javaeefinal.Controllers;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Building;
import com.javaeefinal.itse1908r.javaeefinal.Logged;
import com.javaeefinal.itse1908r.javaeefinal.Services.BuildingService;
import com.javaeefinal.itse1908r.javaeefinal.Services.CategoryService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;

@Path("/student")
@RolesAllowed({"User","Administrator"})
public class Student implements ExceptionMapper {

    @EJB
    BuildingService buildingService;

    @EJB
    CategoryService categoryService;

    @GET
    @Path("/getAllBuildings")
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Administrator", "User"})
    public Response getAllBuildings() {
        List<Building> buildings = buildingService.getAllBuildingsByCategoryName("Student");
        return Response.ok().entity(buildings).build();
    }

    @GET
    @Path("/getCategoryInfo")
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Administrator", "User"})
    public Response getCategory() {
        return Response.ok().entity(categoryService.getCategoryByName("Student").getDescription()).build();
    }

    @Override
    public Response toResponse(Throwable throwable) {
        return Response.status(500)
                .entity("ERROR!")
                .build();
    }
}
