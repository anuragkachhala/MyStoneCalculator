package com.tekit.software.mystonecalculator.Model;

public class StoneDimention {

    private String noOfNug;
    private String lengthOfNug;
    private String heightOfNug;
    private String squreFeet;

    public StoneDimention(String noOfNug, String lengthOfNug, String heightOfNug,String squreFeet) {
        this.noOfNug = noOfNug;
        this.lengthOfNug = lengthOfNug;
        this.heightOfNug = heightOfNug;
        this.squreFeet = squreFeet;
    }


    public String getSqureFeet() {
        return squreFeet;
    }

    public void setSqureFeet(String squreFeet) {
        this.squreFeet = squreFeet;
    }

    public String getNoOfNug() {
        return noOfNug;
    }

    public void setNoOfNug(String noOfNug) {
        this.noOfNug = noOfNug;
    }

    public String getLengthOfNug() {
        return lengthOfNug;
    }

    public void setLengthOfNug(String lengthOfNug) {
        this.lengthOfNug = lengthOfNug;
    }

    public String getHeightOfNug() {
        return heightOfNug;
    }

    public void setHeightOfNug(String heightOfNug) {
        this.heightOfNug = heightOfNug;
    }

    // overriding toString() method
    @Override
    public String toString() {
        return "Stone Dim ["
                + "No of Nug=" + noOfNug
                + ", lengthOfNug=" + lengthOfNug
                + ", heightOfNug=" + heightOfNug
                + ", squreFeet= " +squreFeet + "]";
    }

}
