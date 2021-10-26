package com.company;

public class ClimateInformation {
    private String headline;
    private String description;

    public ClimateInformation(String headline, String description) {
        this.headline = headline;
        this.description = description;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
