package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import filters.SecureFilter;
import models.User;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.Router;
import ninja.session.FlashScope;
import services.AuthenticationService;

import java.util.Optional;


/**
 * Created by Charl on 2015-01-16.
 */
@Singleton
public class AuthenticationController {
    @Inject
    AuthenticationService authenticationService;
    @Inject
    Router router;

    public Result login(FlashScope flashScope, Context context) {
        String httpMethod = context.getMethod();
        switch (httpMethod) {
            case "GET":
                return Results.html();

            case "POST":
                String username = context.getParameter("username");
                String password = context.getParameter("password");
                if (authenticationService.authenticateUser(username, password)) {
                    context.getSession().put(SecureFilter.USERNAME, username);

                    //generatedReverseRoute = context.getRequestPath();

                    return Results.html().redirect(router.getReverseRoute(GameController.class, "index"));
                } else {
                    flashScope.error("Login Failed");
                }

                return Results.html();

        }

        return Results.html();


    }

    public Result register(FlashScope flashScope, Context context) {
        String httpMethod = context.getMethod();
        switch (httpMethod) {
            case "GET":
                return Results.html();

            case "POST":
                String username = context.getParameter("username");
                String password = context.getParameter("password");

                Optional<User> user = authenticationService.register(username, password);
                if (user.isPresent()) {
                    context.getSession().put(SecureFilter.USERNAME, username);

                    return  Results.html().redirect(router.getReverseRoute(GameController.class, "index"));
                } else {
                    flashScope.error("Registration Failed");
                }

                return Results.html();
        }

        return Results.html();

    }

    public Result logout(Context context) {
        context.getSession().clear();
        return Results.html();
    }
}
