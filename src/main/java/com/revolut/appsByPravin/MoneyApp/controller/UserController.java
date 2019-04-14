package com.revolut.appsByPravin.MoneyApp.controller;

import com.revolut.appsByPravin.MoneyApp.Exception.ApiException;
import com.revolut.appsByPravin.MoneyApp.Exception.UserNotFoundException;
import com.revolut.appsByPravin.MoneyApp.Exception.MalformedRequestException;
import com.revolut.appsByPravin.MoneyApp.model.ResponseMapper;
import com.revolut.appsByPravin.MoneyApp.model.User;
import com.revolut.appsByPravin.MoneyApp.service.UserService;
import com.revolut.appsByPravin.MoneyApp.service.UserServiceImpl;
import com.revolut.appsByPravin.MoneyApp.utils.ResponseBuilder;
import com.revolut.appsByPravin.MoneyApp.utils.Utils;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Optional;

public class UserController {
    private UserService userService = new UserServiceImpl();

/*    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }*/

    public ResponseMapper getAllUsers(Request request, Response response) {
        try {
            List<User> users = userService.getAllUsers();
            if (users == null) {
                throw new UserNotFoundException("Users not found");
            }
            return ResponseBuilder.buildSuccessResponse(users, response);
        } catch (ApiException e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        } catch (Exception e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        }
    }


    public ResponseMapper getUserById(Request request, Response response) {
        try {
            String userID = request.params(":userId");

            Optional<Long> userId = Utils.parseLong(userID);
            if (!userId.isPresent()) {
                throw new MalformedRequestException("The given UserId " + userID + "could not be parsed from request Parameter");
            }

            return userId.flatMap(userService::getUserById)
                    .map(user -> ResponseBuilder.buildSuccessResponse(user, response))
                    .orElseGet(() -> ResponseBuilder.buildFailedResponse(new UserNotFoundException("The given User Id " + userId.get() + "could not be retrieved"), response));
        } catch (ApiException e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        } catch (Exception e) {
            return ResponseBuilder.buildFailedResponse(e, response);
        }
    }
}
