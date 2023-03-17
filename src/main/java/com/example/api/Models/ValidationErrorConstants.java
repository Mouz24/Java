package com.example.api.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationErrorConstants {
    public static String ServerError = "Internal Server Error. Something went wrong";

    @JsonProperty
    public static String BadRequest = "You did not pass the params";
}
