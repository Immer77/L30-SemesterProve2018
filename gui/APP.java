package gui;

import controller.Controller;
import model.Arrangement;
import model.Hold;
import model.Tutor;
import model.Uddannelse;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;

public class APP {
    public static void main(String[] args) {
        Arrangement arrangement1 = new Arrangement("Skøjter", LocalDate.of(2021,4,4), LocalTime.of(15,0),LocalTime.of(20,0),200);

        Arrangement arrangement2 = new Arrangement("Skøjter", LocalDate.of(2021,4,4), LocalTime.of(13,0),LocalTime.of(17,0),200);

        Tutor tutor = new Tutor("Peter", "Prutskid.com");
        Uddannelse uddannelse = new Uddannelse("Datamatiker");
        Hold hold = new Hold("Datamatioker", "Kasper",uddannelse );

        hold.addTutor(tutor);
        tutor.addArrangement(arrangement1);
//        tutor.addArrangement(arrangement2);
        System.out.println(hold.harTidsOverlap(arrangement2));
        String filnavn = ".\\semesteprojekt2018\\src\\controller\\tutorOversigt.txt";
        Controller.init();
        try {
            Controller.tutorOversigtTilFil(filnavn);
        }catch (FileNotFoundException fe){
            // Do nothing
        }

    }
}
