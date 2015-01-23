package controllers;

import com.google.inject.Singleton;
import filters.SecureFilter;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;

/**
 * Created by Charl on 2015-01-23.
 */
@Singleton
@FilterWith(SecureFilter.class)
public class MultiplayerController {

    public Result index() {
        Result result = Results.html();

        return result;
    }
}
