package com.revolut.appsByPravin.MoneyApp.utils;

import com.google.gson.Gson;
import com.revolut.appsByPravin.MoneyApp.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Response;

import java.net.HttpURLConnection;

public class ResponseBuilder {
    private static final Logger log = LoggerFactory.getLogger(ResponseBuilder.class);

    public static ResponseMapper buildSuccessResponse(final Object object, final Response response) {
        response.status(HttpURLConnection.HTTP_OK);
        return new ResponseMapper(ResultStatus.SUCCESS, "OK", new Gson().toJsonTree(object));
    }

    public static ResponseMapper buildFailedResponse(final ApiException exception, final Response response) {
        log.error(exception.getMessage());
        response.status(exception.getHttpURLConnection());
        return new ResponseMapper(ResultStatus.ERROR, exception.getMessage());

    }

    public static ResponseMapper buildFailedResponse(final Exception exception, final Response response) {
        log.error(exception.getMessage());
        response.status(HttpURLConnection.HTTP_BAD_REQUEST);
        return new ResponseMapper(ResultStatus.ERROR, "Internal Server Error");

    }
}
