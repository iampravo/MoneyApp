package com.revolut.appsByPravin.MoneyApp.utils;

import com.google.gson.Gson;
import com.revolut.appsByPravin.MoneyApp.controller.AccountController;
import com.revolut.appsByPravin.MoneyApp.exception.ApiException;
import com.revolut.appsByPravin.MoneyApp.model.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Response;

import java.net.HttpURLConnection;

public class ResponseBuilder {
    private final static Logger log = LoggerFactory.getLogger(ResponseBuilder.class);

    public static ResponseMapper buildSuccessResponse(Object object, Response response) {
        response.status(HttpURLConnection.HTTP_OK);
        return new ResponseMapper(ResultStatus.SUCCESS, "OK", new Gson().toJsonTree(object));
    }

    public static ResponseMapper buildFailedResponse(ApiException exception, Response response) {
        log.error(exception.getMessage());
        response.status(exception.getHttpURLConnection());
        return new ResponseMapper(ResultStatus.ERROR, exception.getMessage());

    }

    public static ResponseMapper buildFailedResponse(Exception exception, Response response) {
        log.error(exception.getMessage());
        response.status(HttpURLConnection.HTTP_BAD_REQUEST);
        return new ResponseMapper(ResultStatus.ERROR, exception.getMessage());

    }
}
