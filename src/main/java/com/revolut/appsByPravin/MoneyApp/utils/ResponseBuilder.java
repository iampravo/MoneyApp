package com.revolut.appsByPravin.MoneyApp.utils;

import com.google.gson.Gson;
import com.revolut.appsByPravin.MoneyApp.Exception.ApiException;
import com.revolut.appsByPravin.MoneyApp.model.ResponseMapper;
import org.eclipse.jetty.util.thread.ReservedThreadExecutor;
import spark.Response;

import java.net.HttpURLConnection;

public class ResponseBuilder {

    public static ResponseMapper buildSuccessResponse(Object object, Response response) {
        response.status(HttpURLConnection.HTTP_OK);
        return new ResponseMapper(ResultStatus.SUCCESS, "OK", new Gson().toJsonTree(object));
    }

    public static ResponseMapper buildFailedResponse(ApiException exception, Response response) {
        response.status(exception.getHttpURLConnection());
        return new ResponseMapper(ResultStatus.ERROR, exception.getMessage());

    }
    public static ResponseMapper buildFailedResponse(Exception exception, Response response) {
        response.status(HttpURLConnection.HTTP_BAD_REQUEST);
        return new ResponseMapper(ResultStatus.ERROR, exception.getMessage());

    }
}
