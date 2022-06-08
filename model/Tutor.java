package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Tutor {
    private String navn;
    private String email;
    private Hold hold;
    private ArrayList<Arrangement> arrangementer = new ArrayList<>();

    public Tutor(String navn, String email){
        this.navn = navn;
        this.email = email;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getEmail() {
        return email;
    }

    public Hold getHold() {
        return hold;
    }

    public ArrayList<Arrangement> getArrangementer() {
        return new ArrayList<>(arrangementer);
    }

    public void addArrangement(Arrangement arrangement){
        if(!arrangementer.contains(arrangement)){
            arrangementer.add(arrangement);
        }
    }

    public void removeArrangement(Arrangement arrangement){
        if(arrangementer.contains(arrangement)){
            arrangementer.remove(arrangement);
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHold(Hold hold) {
        if(this.hold != hold){
            Hold oldHold = this.hold;
            if(oldHold != null){
                oldHold.removeTutor(this);
            }
            this.hold = hold;
            if(hold != null){
                hold.addTutor(this);
            }
        }
    }

    public double arrangementspris(){
        double samletPris = 0.0;
        for(Arrangement a : arrangementer){
            samletPris += a.getPris();
        }
        return samletPris;
    }

    @Override
    public String toString(){
        return navn + " " + email;
    }

}
