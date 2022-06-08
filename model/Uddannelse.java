package model;

import java.util.ArrayList;

public class Uddannelse {
    private String navn;
    private ArrayList<Hold> holds = new ArrayList<>();

    public Uddannelse(String navn){
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public ArrayList<Hold> getHolds() {
        return new ArrayList<>(holds);
    }



    public Hold createHold(String betegnelse, String holdLeder){
        Hold hold = new Hold(betegnelse,holdLeder, this);
        holds.add(hold);
        return hold;
    }

    /**
     * Adds the hold to this uddannelse
     *
     * @param hold
     * Pre: the hold isnt already assosiated with this uddannelse
     */
    public void addHold(Hold hold){
        if(!holds.contains(hold)){
            holds.add(hold);
        }
    }

    public void removeHold(Hold hold){
        if(holds.contains(hold)){
            holds.remove(hold);
        }
    }

    public ArrayList<String> tutorOversigt(){
        ArrayList<String> tutorIndholdsOversigt = new ArrayList<>();
        for(Hold h : holds){
            for(Tutor t : h.getTutors()){
                String s = t.getNavn() + " " + getNavn() + " " + h.getBetegnelse() + " " + t.getEmail() + " " + h.getHoldLeder();
                tutorIndholdsOversigt.add(s);
            }
        }
        return tutorIndholdsOversigt;
    }

    @Override
    public String toString(){
        return navn;
    }
}
