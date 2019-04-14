package com.revolut.appsByPravin.MoneyApp.Exception;

import com.sun.net.ssl.internal.www.protocol.https.HttpsURLConnectionOldImpl;
import sun.net.www.protocol.https.HttpsURLConnectionImpl;

import java.net.HttpURLConnection;
import java.util.function.Supplier;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException(String message) {
        super(message, HttpURLConnection.HTTP_NO_CONTENT);
    }
}
