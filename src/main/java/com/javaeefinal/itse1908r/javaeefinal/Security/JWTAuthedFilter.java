package com.javaeefinal.itse1908r.javaeefinal.Security;

import com.javaeefinal.itse1908r.javaeefinal.Entities.User;
import com.javaeefinal.itse1908r.javaeefinal.Services.UserService;
import io.jsonwebtoken.JwtParser;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts;
@Provider
public class JWTAuthedFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    @Inject
    UserService userService;

    @Inject
    private JWTService jwtService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        Method method = resourceInfo.getResourceMethod();
        if (!method.isAnnotationPresent(PermitAll.class)) {
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("Access blocked for all users !!").build());
                return;
            }
            String payload = "";
            try {
                token = token.split(" ")[1];
                payload = jwtService.valid(token);
            } catch (Exception e) {
                requestContext
                        .abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                .build());
            }
            String username = "";
            String password = "";
            try {
            String[] parts = payload.split(",");

            username = parts[0].split(":")[1].substring(1, parts[0].split(":")[1].length()-1);
            password = parts[1].split(":")[1].substring(1,parts[0].split(":")[1].length()-1);

            } catch (Exception e) {
                requestContext
                        .abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                .build());
            }
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                if (!isUserAllowed(username, password, rolesSet)) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You cannot access this resource").build());
                    return;
                }
            }

        }
    }


    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
        boolean isAllowed = false;
        User user = userService.authenticate(username, password);

        if (!(user == null)) {
            String userRole = user.getRole().getName().toString();
            System.out.println(userRole);
            System.out.println(rolesSet);
            if (rolesSet.contains(userRole)) {

                isAllowed = true;
                System.out.println(isAllowed);
            }
        }
        return isAllowed;
    }

}
