package com.revolut.appsByPravin.MoneyApp.controller;

import com.google.gson.JsonElement;
import com.revolut.appsByPravin.MoneyApp.model.ResponseMapper;
import com.revolut.appsByPravin.MoneyApp.service.TransactionService;
import spark.Request;
import spark.Response;

public class TransactionController {
    private TransactionService transactionService;

    public ResponseMapper transfer(Request request, Response response) {
         transactionService.transfer();
         return null;
    }
}
