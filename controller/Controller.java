package controller;

import model.Arrangement;
import model.Hold;
import model.Tutor;
import model.Uddannelse;
import storage.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Controller {

    public static Uddannelse createUddannelse(String navn){
        Uddannelse uddannelse = new Uddannelse(navn);
        Storage.addUddannelse(uddannelse);
        return uddannelse;
    }

    public static Tutor createTutor(String navn, String Email){
        Tutor tutor = new Tutor(navn,Email);
        Storage.addTutor(tutor);
        return tutor;
    }
    public static void removeTutor(Tutor tutor){
        Storage.removeTutor(tutor);
    }

    public static ArrayList<Tutor> getTutors(){
        return Storage.getTutors();
    }


    public static void addTutorToArrangement(Tutor tutor, Arrangement arrangement){
        if(!tutor.getHold().harTidsOverlap(arrangement)){
            tutor.addArrangement(arrangement);
        }else {
            throw new RuntimeException("Tutor already has an arrangement scheduled");
        }
    }

    public static Arrangement createArrangement(String titel, LocalDate date, LocalTime startTid, LocalTime slutTidn, double pris){
        Arrangement arrangement = new Arrangement(titel,date,startTid,slutTidn,pris);
        Storage.addArrangement(arrangement);
        return arrangement;
    }

    public static ArrayList<Arrangement> getArrangementer(){
        return Storage.getArrangementer();
    }

    public static ArrayList<Hold> holdUdenTutorer(){
        ArrayList<Hold> udenTutor = new ArrayList<>();
        for(Uddannelse u : Storage.getUddannelser()){
            for(Hold h : u.getHolds()){
                if(h.getTutors().isEmpty()){
                    udenTutor.add(h);
                }
            }
        }
        return udenTutor;
    }

    public static void tutorOversigtTilFil(String filnavn) throws FileNotFoundException{
    PrintWriter pw = new PrintWriter(filnavn);

    for(Uddannelse u : Storage.getUddannelser()){
        for(String s : u.tutorOversigt()){
            pw.println(s);
        }
    }
    pw.close();
    }

    public static void initStorage(){
        Uddannelse ud1 = createUddannelse("DMU");
        Uddannelse ud2 = createUddannelse("FINØ");

        Hold hold1 = ud1.createHold("1dmE17S","Magrethe Dybdahl");
        Hold hold2 = ud1.createHold("1dmE17T","Kristian Dorland");
        Hold hold3 = ud1.createHold("1dmE17U","Kell Ørhøj");
        Hold hold4 = ud2.createHold("1fiE17B","Karen Jensen");

        Tutor tutor1 = createTutor("Anders Hansen", "aaa@students.eaaa.dk");
        Tutor tutor2 = createTutor("Peter Jensen", "ppp@students.eaaa.dk");
        Tutor tutor3 = createTutor("Niels Madsen", " nnn@students.eaaa.dk");
        Tutor tutor4 = createTutor("Lone Andersen", "lll@students.eaaa.dk");
        Tutor tutor5 = createTutor("Mads Miller", "mmm@students.eaaa.dk");

        Arrangement a1 = createArrangement("Rusfest", LocalDate.of(2017,8,31),LocalTime.of(18,0),LocalTime.of(23,30),250);
        Arrangement a2 = createArrangement("Fodbold",LocalDate.of(2017,8,30),LocalTime.of(14,0),LocalTime.of(17,30),100);
        Arrangement a3 = createArrangement("Brætspil",LocalDate.of(2017,8,29),LocalTime.of(12,0),LocalTime.of(16,30),25);
        Arrangement a4 = createArrangement("Mindeparken",LocalDate.of(2017,8,30),LocalTime.of(18,0),LocalTime.of(22,0),25);

        hold1.addTutor(tutor1);
        hold1.addTutor(tutor4);

        hold2.addTutor(tutor2);
        hold2.addTutor(tutor3);

        hold3.addTutor(tutor5);

        addTutorToArrangement(tutor1,a1);
        addTutorToArrangement(tutor2,a1);
        addTutorToArrangement(tutor5,a1);

        addTutorToArrangement(tutor3,a2);

        addTutorToArrangement(tutor4,a3);
        addTutorToArrangement(tutor3,a3);

        addTutorToArrangement(tutor5,a4);
        addTutorToArrangement(tutor1,a4);

    }
    public static void init(){
        initStorage();
    }

}
