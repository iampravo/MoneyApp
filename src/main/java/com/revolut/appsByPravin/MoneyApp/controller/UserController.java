package com.revolut.appsByPravin.MoneyApp.controller;

import com.revolut.appsByPravin.MoneyApp.exception.ApiException;
import com.revolut.appsByPravin.MoneyApp.exception.EntityNotFoundException;
import com.revolut.appsByPravin.MoneyApp.exception.MalformedRequestException;
import com.revolut.appsByPravin.MoneyApp.utils.ResponseMapper;
import com.revolut.appsByPravin.MoneyApp.model.User;
import com.revolut.appsByPravin.MoneyApp.service.UserService;
import com.revolut.appsByPravin.MoneyApp.service.UserServiceImpl;
import com.revolut.appsByPravin.MoneyApp.utils.ResponseBuilder;
import com.revolut.appsByPravin.MoneyApp.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Optional;

public class UserController {
    private static final UserController userController = new UserController(UserServiceImpl.getInstance());

    private  UserController(final UserServiceImpl transactionServiceImpl) {
        this.userService = transactionServiceImpl;
    }

    public static UserController getInstance() {
        return userController;
    }


    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService ;

    public ResponseMapper getAllUsers(final Request request, final Response response) {
        log.info("Started method = getAllUsers, class = UserController");
        try {
            final List<User> users = userService.getAllUsers();
            if (users == null) {
                throw new EntityNotFoundException("Users not found");
            }
            return ResponseBuilder.buildSuccessResponse(users, response);
        } catch (final ApiException e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        } catch (final Exception e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        }
    }


    public ResponseMapper getUserById(final Request request, final Response response) {
        log.info("Started method = getUserById, class = UserController");
        try {
            final String userIdParam = request.params(":userId");

            final Optional<Long> userId = Utils.parseLong(userIdParam);
            if (!userId.isPresent()) {
                throw new MalformedRequestException("The given UserId '" + userIdParam + "' could not be parsed from request Parameter");
            }

            final Optional<User> user = userService.getUserById(userId.get());

            if (!user.isPresent()) {
                throw new EntityNotFoundException("The given User Id '" + userId.get() + "' could not be retrieved");
            }
            return ResponseBuilder.buildSuccessResponse(user, response);

        } catch (final ApiException e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        } catch (final Exception e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        }
    }
}
