//////////////////////////////
// JSONResponse
//
//
//
// autor:
// inhalt: parsen von den erhaltenen Retrofit Daten
// zugriffsdatum: 11.12.19
//
//
//
//
//
//
//////////////////////////////

package com.Fachhochschulebib.fhb.pruefungsplaner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonResponse {

    @SerializedName("Erstpruefer")
    @Expose
    private String Erstpruefer;


    @SerializedName("Zweitpruefer")
    @Expose
    private String Zweitpruefer;


    @SerializedName("Pruefform")
    @Expose
    private String Pruefform;


    @SerializedName("Semester")
    @Expose
    private String Semester;


    @SerializedName("Datum")
    @Expose
    private String Datum;


    @SerializedName("Modul")
    @Expose
    private String Modul;


    @SerializedName("Studiengang")
    @Expose
    private String Studiengang;


    @SerializedName("Termin")
    @Expose
    private String Termin;


    @SerializedName("ID")
    @Expose
    private String ID;

    @SerializedName("Raum")
    @Expose
    private String Raum;

    // Start Merlin Gürtler
    @SerializedName("StudiengangId")
    @Expose
    private String StudiengangId;

    @SerializedName("Status")
    @Expose
    private String Status;

    @SerializedName("Hint")
    @Expose
    private String Hint;

    @SerializedName("Color")
    @Expose
    private String Color;
    // Ende Merlin Gürtler


    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getHint() {
        return Hint;
    }

    public void setHint(String hint) {
        Hint = hint;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getErstpruefer() {
        return Erstpruefer;
    }

    public void setErstpruefer(String erstpruefer) {
        this.Erstpruefer = erstpruefer;
    }

    public String getRaum() {
        return Raum;
    }

    public void setRaum(String raum) {
        this.Raum = raum;
    }

    public String getDatum() {
        return Datum;
    }

    public void setDatum(String datum) {
        this.Datum = datum;
    }

    public String getZweitpruefer() {
        return Zweitpruefer;
    }

    public void setZweitpruefer(String zweitpruefer) {
        Zweitpruefer = zweitpruefer;
    }

    public String getPruefform() {
        return Pruefform;
    }

    public void setPruefform(String pruefform) {
        Pruefform = pruefform;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getModul() {
        return Modul;
    }

    public void setModul(String modul) {
        Modul = modul;
    }

    public String getStudiengang() {
        return Studiengang;
    }

    public void setStudiengang(String studiengang) {
        Studiengang = studiengang;
    }

    public String getTermin() {
        return Termin;
    }

    public void setTermin(String termin) {
        Termin = termin;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStudiengangId() {
        return StudiengangId;
    }

    public void setStudiengangId(String studiengangId) {
        StudiengangId = studiengangId;
    }

}
