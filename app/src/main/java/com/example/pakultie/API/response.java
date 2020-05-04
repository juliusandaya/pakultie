package com.example.pakultie.API;

public class response {
    String id;
    String details;
    String error;

    public response(String id, String details, String error) {
        this.id = id;
        this.details = details;
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
