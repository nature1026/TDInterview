
package com.td.td.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Topic {

    @SerializedName("Result")
    @Expose
    private String result;
    @SerializedName("Icon")
    @Expose
    private Icon_ icon;
    @SerializedName("FirstURL")
    @Expose
    private String firstURL;
    @SerializedName("Text")
    @Expose
    private String text;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Icon getRealtedTopicIcon(){
        Icon icon = new Icon();
        try {
            icon.setURL(icon.getURL());
            icon.setHeight(icon.getHeight());
            icon.setWidth(icon.getWidth());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return icon;
    }
    public Icon_ getIcon() {
        return icon;
    }

    public void setIcon(Icon_ icon) {
        this.icon = icon;
    }

    public String getFirstURL() {
        return firstURL;
    }

    public void setFirstURL(String firstURL) {
        this.firstURL = firstURL;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
