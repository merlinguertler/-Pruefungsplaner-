package com.Fachhochschulebib.fhb.pruefungsplaner;

//////////////////////////////
// TerminefragmentSuche
//
//
//
// autor:
// inhalt:  Prüfungen aus der Klasse Prüfplaneintrag werden abgefragt und zur darstelllung an den Recycleview adapter übergeben
// zugriffsdatum: 20.2.20
//
//
//
//
//
//
//////////////////////////////


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Fachhochschulebib.fhb.pruefungsplaner.data.AppDatabase;
import com.Fachhochschulebib.fhb.pruefungsplaner.data.PruefplanEintrag;

import java.util.ArrayList;
import java.util.List;


import static com.Fachhochschulebib.fhb.pruefungsplaner.Terminefragment.validation;


public class TerminefragmentSuche extends Fragment {


    private RecyclerView recyclerView;
    private CalendarView calendar;
    private Button btnsuche;
    private String date;

    List<Boolean> checkList = new ArrayList<>();
    List<String> modulUndStudiengangsList = new ArrayList<>();
    List<String> prueferUndSemesterList = new ArrayList<>();
    List<String> datumsList = new ArrayList<>();
    List<String> modulList = new ArrayList<>();
    List<String> idList = new ArrayList<>();
    List<String> pruefFormList = new ArrayList<>();
    List<String> raumList = new ArrayList<>();

    private String month2;
    private String day2;
    // private int position2 = 0;
    private String year2;
    private RecyclerView.LayoutManager mLayout;
    com.Fachhochschulebib.fhb.pruefungsplaner.MyAdapter mAdapter;
    List<Integer> WerteZumAnzeigenList = new ArrayList<>();



    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.terminefragment, container, false);



        //hinzufügen von recycleview
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView4);
        recyclerView.setVisibility(View.VISIBLE);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mLayout = recyclerView.getLayoutManager();



        //Userinterface Komponenten Initialiseren
        // recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView4);
        // recyclerView.setVisibility(View.VISIBLE);
        calendar = (CalendarView) v.findViewById(R.id.caCalender);
        btnsuche = (Button) v.findViewById(R.id.btnDatum);

        Adapteruebergabe();

        calendar.setVisibility(View.GONE);
        //btnsuche clicklistener überprüft, ob der "Kalender öffnen" - Button angetippt wurde
        /*  Es werden bei eingeschaltetem Kalender nur Prüfobjekte mit übereinstimmenden
            Datum angezeigt.
         */
        btnsuche.setOnClickListener(new View.OnClickListener() {
            boolean speicher = true;
            @Override
            public void onClick(View v) {
                if (!speicher) {
                    calendar.setVisibility(View.GONE);
                    Adapteruebergabe();
                    speicher = true;
                } else {
                    //Kalender ist geöffnet, nur übereinstimmende Prüfungen anzeigen
                    calendar.setVisibility(View.VISIBLE);
                    calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                            com.Fachhochschulebib.fhb.pruefungsplaner.data.AppDatabase roomdaten = com.Fachhochschulebib.fhb.pruefungsplaner.data.AppDatabase.getAppDatabase(getContext());
                            List<com.Fachhochschulebib.fhb.pruefungsplaner.data.PruefplanEintrag> ppeList = roomdaten.userDao().getAll(validation);

                            ClearLists();

                            //Creating editor to store uebergebeneModule to shared preferences
                            if (month < 10) {
                                month2 = "0" + String.valueOf(month + 1);
                            } else {
                                month2 = String.valueOf(month);
                            }
                            if (dayOfMonth < 10) {
                                day2 = "0" + String.valueOf(dayOfMonth);
                            } else {
                                day2 = String.valueOf(dayOfMonth);
                            }
                            year2 = String.valueOf(year);
                            date = year2 + "-" + month2 + "-" + day2;
                            for (int i = 0; i < ppeList.size(); i++) {
                                String[] date2 = ppeList.get(i).getDatum().split(" ");
                                if (date2[0].equals(date)) {
                                    modulUndStudiengangsList.add(
                                            ppeList.get(i).getModul() + "\n "
                                                    + ppeList.get(i).getStudiengang());
                                    prueferUndSemesterList.add(
                                            ppeList.get(i).getErstpruefer()
                                                    + " " + ppeList.get(i).getZweitpruefer()
                                                    + " " + ppeList.get(i).getSemester() + " ");
                                    datumsList.add(ppeList.get(i).getDatum());
                                    modulList.add(ppeList.get(i).getModul());
                                    idList.add(ppeList.get(i).getID());
                                    pruefFormList.add(ppeList.get(i).getPruefform());
                                    raumList.add(ppeList.get(i).getRaum());
                                }
                            }
                            // define an adapter
                            //Werte an den Adapter übergeben
                            mAdapter = new com.Fachhochschulebib.fhb.pruefungsplaner.MyAdapter(
                                    modulUndStudiengangsList,
                                    prueferUndSemesterList,
                                    datumsList,
                                    modulList,
                                    idList,
                                    pruefFormList,
                                    mLayout,
                                    raumList);
                            //Anzeigen von recyclerview
                            recyclerView.setAdapter(mAdapter);
                        }
                    });
                    speicher = false;
                }
            }
        });

        recyclerView.addOnItemTouchListener(
                new com.Fachhochschulebib.fhb.pruefungsplaner.RecyclerItemClickListener(getActivity(), new   RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final TextView txtSecondScreen
                                = (TextView) view.findViewById(R.id.txtSecondscreen);
                        View viewItem
                                = recyclerView.getLayoutManager().findViewByPosition(position);
                        LinearLayout layout1
                                =(LinearLayout) viewItem.findViewById(R.id.linearLayout);

                        layout1.setOnClickListener(new  View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.e("@@@@@", "" + position);
                                if (txtSecondScreen.getVisibility() == v.VISIBLE) {
                                    txtSecondScreen.setVisibility(v.GONE);
                                    checkList.set(position,false);
                                } else {
                                    txtSecondScreen.setVisibility(v.VISIBLE);
                                    txtSecondScreen.setText(mAdapter.giveString(position));
                                }
                            }
                        });

                        if(checkList.get(position)) {
                            txtSecondScreen.setVisibility(v.VISIBLE);
                            txtSecondScreen.setText(mAdapter.giveString(position));
                        }
                    }
                })
        );
        return v;
    }

    public void Adapteruebergabe()
    {

        //Datenbank initialisieren
        com.Fachhochschulebib.fhb.pruefungsplaner.data.AppDatabase datenbank = AppDatabase.getAppDatabase(getContext());

        // Änderung Merlin Gürtler
        // List<Pruefplan> pruefplandaten = datenbank.userDao().getAll(validation);
        // Für die Suche von Modulen
        List<PruefplanEintrag> ppeList = datenbank.userDao().getAll2();
        // Ende Änderung Merlin Gürtler

        ClearLists();

        for (int i =0;i<ppeList.size();i++) {
            System.out.println(ppeList.get(i).getAusgewaehlt());
            if(ppeList.get(i).getAusgewaehlt()) {
                WerteZumAnzeigenList.add(i);
            }
        }

        //Variablen mit Werten aus der lokalen Datenbank füllen
        for (int i = 0; i < WerteZumAnzeigenList.size(); i++) {
            modulUndStudiengangsList.add(ppeList.get(Integer.valueOf(WerteZumAnzeigenList.get(i))).getModul() + "\n " + ppeList.get(Integer.valueOf(WerteZumAnzeigenList.get(i))).getStudiengang());
            prueferUndSemesterList.add(ppeList.get(Integer.valueOf(WerteZumAnzeigenList.get(i))).getErstpruefer() + " " + ppeList.get(Integer.valueOf(WerteZumAnzeigenList.get(i))).getZweitpruefer() + " " + ppeList.get(Integer.valueOf(WerteZumAnzeigenList.get(i))).getSemester() + " ");
            datumsList.add(ppeList.get(Integer.valueOf(WerteZumAnzeigenList.get(i))).getDatum());
            modulList.add(ppeList.get(Integer.valueOf(WerteZumAnzeigenList.get(i))).getModul());
            idList.add(ppeList.get(Integer.valueOf(WerteZumAnzeigenList.get(i))).getID());
            pruefFormList.add(ppeList.get(Integer.valueOf(WerteZumAnzeigenList.get(i))).getPruefform());
            raumList.add(ppeList.get(Integer.valueOf(WerteZumAnzeigenList.get(i))).getRaum());
            checkList.add(true);
        }

        // define an adapter
        mAdapter = new MyAdapter(
                modulUndStudiengangsList,
                prueferUndSemesterList,
                datumsList,
                modulList,
                idList,
                pruefFormList,
                mLayout,
                raumList);
        recyclerView.setAdapter(mAdapter);
    }

    public void ClearLists()
    {
        modulUndStudiengangsList.clear();
        prueferUndSemesterList.clear();
        datumsList.clear();
        modulList.clear();
        idList.clear();
        pruefFormList.clear();
        raumList.clear();
    }

}






