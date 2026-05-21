package com.auction.ee.web;

import com.auction.ee.ejb.session.UserSessionBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@RequestScoped
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class LoginResource {

    @Inject
    private UserSessionBean userSession;

    @POST
    @Path("/login")
    public Response login(@FormParam("userId") Long userId,
                          @FormParam("username") String username) {
        try {
            userSession.setUserId(userId);
            userSession.setUsername(username);

            return Response.status(Response.Status.FOUND)
                    .header("Location", "../place-bid.html")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(" Login failed: " + e.getMessage())
                    .build();
        }
    }

}
