package filters;

import com.google.inject.Inject;
import ninja.*;

/**
 * Created by Charl on 2015-01-16.
 */
public class SecureFilter implements Filter {

    @Inject
    Router router;
    /**
     * If a username is saved we assume the session is valid
     */
    public static final String USERNAME = "username";

    @Override
    public Result filter(FilterChain filterChain, Context context) {
        // no cookies --> break:
        if (context.getSession() == null || context.getSession().get(USERNAME) == null) {
            return Results.forbidden().html().template("/views/AuthenticationController/login.ftl.html");

        } else {
            return filterChain.next(context);
        }
    }
}
