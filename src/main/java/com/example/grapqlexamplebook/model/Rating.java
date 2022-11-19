package com.example.grapqlexamplebook.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Rating {
    BEST("*****"),
    GOOD("****"),
    NORMAL("***"),
    BAD("**"),
    VERYBAD("*");

    private String star;

    Rating(String star) {
        this.star = star;
    }

    @JsonValue
    public String getStar() {
        return star;
    }
}
