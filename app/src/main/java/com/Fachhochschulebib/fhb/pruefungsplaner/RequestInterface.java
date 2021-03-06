//////////////////////////////
// Requestinterface
//
//
//
// autor:
// inhalt: Interface damit Prüfplanobjekte von der Json Modelklasse zur lokalen Datenbank hinzugefügt werden können
// zugriffsdatum: 11.12.1992
//
//
//
//
//
//
//////////////////////////////

package com.Fachhochschulebib.fhb.pruefungsplaner;

import java.util.List;

import com.Fachhochschulebib.fhb.pruefungsplaner.model.JsonResponse;
import com.Fachhochschulebib.fhb.pruefungsplaner.model.JsonCourse;
import com.Fachhochschulebib.fhb.pruefungsplaner.model.JsonUuid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;


public interface RequestInterface {
    @GET(" ")
    Call<List<JsonResponse>> getJSON();

    //Start Merlin Gürtler
    @GET(" ")
    Call<JsonUuid> getJsonUuid();

    @PUT(" ")
    Call<Void> anotherStart();

    @POST(" ")
    Call<Void> sendFeedBack();

    @GET(" ")
    Call<List<JsonCourse>> getStudiengaenge();

    @POST(" ")
    Call<Void> sendCourses();
    //Ende Merlin Gürtler
}