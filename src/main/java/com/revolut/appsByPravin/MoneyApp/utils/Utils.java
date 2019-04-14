package com.revolut.appsByPravin.MoneyApp.utils;

import java.util.Optional;

public class Utils {

    public static Optional<Long> parseLong(String str) {
        try {
            return Optional.of(Long.parseLong(str));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
