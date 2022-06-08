package model;

import java.util.ArrayList;

public class Hold {
    private String betegnelse;
    private String holdLeder;
    private Uddannelse uddannelse;
    private ArrayList<Tutor> tutors = new ArrayList<>();

    public Hold(String betegnelse, String holdLeder, Uddannelse uddannelse){
        this.betegnelse = betegnelse;
        this.holdLeder = holdLeder;
        this.uddannelse = uddannelse;
    }

    public ArrayList<Tutor> getTutors() {
        return new ArrayList<>(tutors);
    }

    public void addTutor(Tutor tutor){
        if(!tutors.contains(tutor)){
            tutors.add(tutor);
            tutor.setHold(this);
        }
    }
    public void removeTutor(Tutor tutor){
        if(tutors.contains(tutor)){
            tutors.remove(tutor);
            tutor.setHold(null);
        }
    }

    public void setTutors(ArrayList<Tutor> tutors) {
        this.tutors = tutors;
    }

    public String getBetegnelse() {
        return betegnelse;
    }

    public void setBetegnelse(String betegnelse) {
        this.betegnelse = betegnelse;
    }

    public String getHoldLeder() {
        return holdLeder;
    }

    public void setHoldLeder(String holdLeder) {
        this.holdLeder = holdLeder;
    }

    public double arrangementsPris(){
        double samletPrisForHold = 0;
        for(Tutor t : tutors){
            for(Arrangement a : t.getArrangementer()){
                samletPrisForHold += a.getPris();
            }
        }
        return samletPrisForHold;
    }

    public boolean harTidsOverlap(Arrangement arrangement){
        for(Tutor t : tutors){
            for (Arrangement a : t.getArrangementer()){
                if(a.getDate().equals(arrangement.getDate())){
                    if(overlap(a,arrangement)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean overlap(Arrangement a1, Arrangement a2){
        return a1.getStartTid().isBefore(a2.getSlutTidn()) && a1.getSlutTidn().isAfter(a2.getStartTid()) ;
    }

}
