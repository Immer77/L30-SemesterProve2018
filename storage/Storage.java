package storage;

import model.Arrangement;
import model.Tutor;
import model.Uddannelse;

import java.util.ArrayList;

public class Storage {
    public static ArrayList<Tutor> tutors = new ArrayList<>();
    public static ArrayList<Uddannelse> uddannelser = new ArrayList<>();
    public static ArrayList<Arrangement> arrangementer = new ArrayList<>();

    public static ArrayList<Tutor> getTutors() {
        return tutors;
    }

    public static void addTutor(Tutor tutor){
        tutors.add(tutor);
    }

    public static void removeTutor(Tutor tutor){
        tutors.remove(tutor);
    }
    //------------------------------------------------------------

    public static ArrayList<Uddannelse> getUddannelser() {
        return uddannelser;
    }

    public static void addUddannelse(Uddannelse uddannelse){
        uddannelser.add(uddannelse);
    }

    //------------------------------------------------------------

    public static ArrayList<Arrangement> getArrangementer() {
        return arrangementer;
    }

    public static void addArrangement(Arrangement arrangement){
        arrangementer.add(arrangement);
    }
}
