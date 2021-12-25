package com.javaeefinal.itse1908r.javaeefinal.Controllers;

import com.javaeefinal.itse1908r.javaeefinal.Entities.Building;
import com.javaeefinal.itse1908r.javaeefinal.Logged;
import com.javaeefinal.itse1908r.javaeefinal.Services.AtmService;
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

@Path("/tourism")
@RolesAllowed({"User", "Administrator"})
public class Tourist implements ExceptionMapper {

    @EJB
    BuildingService buildingService;

    @EJB
    CategoryService categoryService;

    @EJB
    AtmService atmService;

    @GET
    @Path("/getAllBuildings")
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Administrator", "User"})
    public Response getAllBuildings() {
        List<Building> buildings = buildingService.getAllBuildingsByCategoryName("Tourism");
        return Response.ok().entity(buildings).build();
    }

    @GET
    @Path("/getCategoryInfo")
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Administrator", "User"})
    public Response getCategory() {
        return Response.ok().entity(categoryService.getCategoryByName("Tourism").getDescription()).build();
    }

    @GET
    @Path("/getAtms")
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Administrator", "User"})
    public Response getAtms() {
        return Response.ok().entity(atmService.getAllAtms()).build();
    }


    @Override
    public Response toResponse(Throwable throwable) {
        return Response.status(500)
                .entity("ERROR!")
                .build();
    }
}
