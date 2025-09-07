package com.example.uiruclinic.src.softmeth.project2;

/**
 * Represents the different types of radiology scans.
 * This enum defines specific scan types available in radiology.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarthy
 */
public enum Radiology {
    CATSCAN("catscan"),
    ULTRASOUND("ultrasound"),
    XRAY("xray");

    private final String scanType;

    /**
     * Constructs a Radiology enum constant with the specified scan type.
     *
     * @param scanType The string representation of the radiology scan type.
     */
    Radiology(String scanType){
        this.scanType=scanType;
    }

    /**
     * Gets the string representation of the scan type.
     *
     * @return The scan type as a string.
     */
    public String getScanType(){
        return scanType;
    }
}