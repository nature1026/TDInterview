
package com.td.td.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Icon_ {

    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("Height")
    @Expose
    private String height;
    @SerializedName("Width")
    @Expose
    private String width;

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

}
