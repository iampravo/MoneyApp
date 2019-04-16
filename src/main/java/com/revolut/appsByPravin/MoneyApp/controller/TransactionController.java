package com.revolut.appsByPravin.MoneyApp.controller;

import com.google.gson.Gson;
import com.revolut.appsByPravin.MoneyApp.dto.TransactionDTO;
import com.revolut.appsByPravin.MoneyApp.exception.ApiException;
import com.revolut.appsByPravin.MoneyApp.exception.EntityNotFoundException;
import com.revolut.appsByPravin.MoneyApp.exception.TransactionException;
import com.revolut.appsByPravin.MoneyApp.utils.ResponseMapper;
import com.revolut.appsByPravin.MoneyApp.model.Transaction;
import com.revolut.appsByPravin.MoneyApp.service.TransactionService;
import com.revolut.appsByPravin.MoneyApp.service.TransactionServiceImpl;
import com.revolut.appsByPravin.MoneyApp.utils.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class TransactionController {

    private static final TransactionController transactionController = new TransactionController(TransactionServiceImpl.getInstance());

    private TransactionController(final TransactionServiceImpl transactionServiceImpl) {
        this.transactionServiceImpl = transactionServiceImpl;
    }

    public static TransactionController getInstance() {
        return transactionController;
    }


    private final Logger log = LoggerFactory.getLogger(TransactionController.class);
    private TransactionService transactionServiceImpl;// = new TransactionServiceImpl();


    public ResponseMapper transfer(final Request request, final Response response) {
        try {
            log.info("Started method = transfer, class = TransactionController " + request.body());
            final TransactionDTO transferDTO = new Gson().fromJson(request.body(), TransactionDTO.class);
            if (transferDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new TransactionException("The given amount " + transferDTO.getAmount().longValue() + " should be greater than 0");
            }
            final Optional<Transaction> transactionOptional = transactionServiceImpl.transfer(transferDTO);
            if (!transactionOptional.isPresent()) {
                throw new TransactionException("Transaction could not be completed");
            }
            return ResponseBuilder.buildSuccessResponse(transactionOptional.get(), response);
        } catch (final ApiException e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        } catch (final Exception e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        }
    }

    public ResponseMapper getAllTransactions(final Request request, final Response response) {
        log.info("Started method = getAllTransactions, class = TransactionController");
        try {
            final List<Transaction> transactions = transactionServiceImpl.getAllTransactions();

            if (transactions == null) {
                throw new EntityNotFoundException("transactions not found");
            }
            return ResponseBuilder.buildSuccessResponse(transactions, response);
        } catch (final ApiException e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        } catch (final Exception e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        }
    }
}
