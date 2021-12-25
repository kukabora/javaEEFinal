package com.javaeefinal.itse1908r.javaeefinal.Controllers;


import com.javaeefinal.itse1908r.javaeefinal.Entities.User;
import com.javaeefinal.itse1908r.javaeefinal.Entities.Userdetail;
import com.javaeefinal.itse1908r.javaeefinal.Logged;
import com.javaeefinal.itse1908r.javaeefinal.Security.JWTService;
import com.javaeefinal.itse1908r.javaeefinal.Services.UserService;
import com.javaeefinal.itse1908r.javaeefinal.Services.UserdetailService;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.ArrayList;

@Path("/auth")

public class AuthController implements ExceptionMapper {

    @EJB
    JWTService jwtService;

    @EJB
    UserdetailService userdetailService;

    @EJB
    UserService userService;



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @Logged
    @Path("/generate")
    public Response JWTAuthorization(User authenticationData) {
        User user  = userService.authenticate(authenticationData.getLogin(), authenticationData.getPassword());
        if(user == null) {
            return Response.status(500)
                    .entity("Something went wrong")
                    .build();
        }
        else{
            String JWT_User = jwtService.generateJWTToken(user);
            return Response.ok().entity(JWT_User).build();
        }

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/profile")
    @Logged
    @PermitAll
    public Response JWTCheck(String JWT) {
        String JWT_check = jwtService.valid(JWT);
        if(!(JWT_check == "not valid")) {
            String[] parts = JWT_check.split(",");
            String username = parts[0].split(":")[1].substring(1, parts[0].split(":")[1].length() - 1);
            User user = userService.getUserByLogin(username);
            Userdetail userdetail = userdetailService.getUserdetailById(user.getId().longValue());
            ArrayList<Object> info = new ArrayList<Object>();
            info.add(userdetail);
            return Response.ok().entity(info).build();
        }
        else{
            return Response.status(500)
                    .entity("Wrong Token")
                    .build();
        }
    }



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    @Logged
    @PermitAll
    public Response register(User user) {
        User user1 = userService.createNewUser(user.getLogin(), user.getPassword());
        if(user1.getLogin() == null) {
            return Response.status(500)
                    .entity("User with this login has been already registered!")
                    .build();
        }
        User JWT = userService.authenticate(user1.getLogin(), user1.getPassword());
        if(JWT == null) {
            return Response.status(500)
                    .entity("Wrong Data was entered!")
                    .build();
        }
        else{
            String JWT_User = jwtService.generateJWTToken(user);
            return Response.ok().entity("User has been successfully created!\nJWT: "+ JWT_User).build();
        }

    }

    @Override
    public Response toResponse(Throwable throwable) {
        return Response.status(500)
                .entity("ERROR!")
                .build();
    }
}