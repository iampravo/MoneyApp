package com.revolut.appsByPravin.MoneyApp.controller;

import com.revolut.appsByPravin.MoneyApp.exception.ApiException;
import com.revolut.appsByPravin.MoneyApp.exception.MalformedRequestException;
import com.revolut.appsByPravin.MoneyApp.exception.EntityNotFoundException;
import com.revolut.appsByPravin.MoneyApp.model.Account;
import com.revolut.appsByPravin.MoneyApp.model.ResponseMapper;
import com.revolut.appsByPravin.MoneyApp.service.AccountService;
import com.revolut.appsByPravin.MoneyApp.service.AccountServiceImpl;
import com.revolut.appsByPravin.MoneyApp.utils.ResponseBuilder;
import com.revolut.appsByPravin.MoneyApp.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Optional;

public class AccountController {
    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService = new AccountServiceImpl();

    public ResponseMapper getAllAccounts(Request request, Response response) {
        log.info("Started method = getAllAccounts, class = AccountController");
        try {
            String userIdParam = request.params(":userId");

            Optional<Long> userId = Utils.parseLong(userIdParam);
            if (!userId.isPresent()) {
                throw new MalformedRequestException("The given UserId '" + userIdParam + "' could not be parsed from request Parameter");
            }

            List<Account> accounts = accountService.getAllAccounts(userId.get());

            if (accounts.isEmpty()) {
                throw new EntityNotFoundException("No accounts details found for the given User Id '" + userId + "'.");
            }
            return ResponseBuilder.buildSuccessResponse(accounts, response);

        } catch (ApiException e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        } catch (Exception e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        }
    }
}
