package com.example.pccp.model.request;

public class UpdateAudioUrReq {
    private Integer id;

    private Integer pipeId;

    private String measureUrl;

    private String getUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPipeId() {
        return pipeId;
    }

    public void setPipeId(Integer pipeId) {
        this.pipeId = pipeId;
    }

    public String getMeasureUrl() {
        return measureUrl;
    }

    public void setMeasureUrl(String measureUrl) {
        this.measureUrl = measureUrl;
    }

    public String getGetUrl() {
        return getUrl;
    }

    public void setGetUrl(String getUrl) {
        this.getUrl = getUrl;
    }
}
