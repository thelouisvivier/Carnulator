package fr.yncrea.carnulator;

import java.util.ArrayList;
import java.util.List;

public class CarnutesBottle {

    private String m_name;
    private double m_price;
    private String m_imageUrl;



    public CarnutesBottle(String name, double price, String imageUrl){
        this.m_imageUrl = imageUrl;
        this.m_name = name;
        this.m_price = price;
    }

    public double getM_price() {
        return m_price;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_imageUrl() {
        return m_imageUrl;
    }

    public static List<CarnutesBottle> getListOfBottle(){
        ArrayList<CarnutesBottle> listOfCarnutesBottles = new ArrayList<>() ;

        ArrayList<String> cl= new ArrayList<>();
        cl.add("33");
        cl.add("75");
        ArrayList<String> type= new ArrayList<>();
        type.add("rousse");
        type.add("blonde");
        type.add("ambrée");
        type.add("triple");
        type.add("brune");

        for(String c : cl){
            for(String t : type){
                listOfCarnutesBottles.add(new fr.yncrea.carnulator.CarnutesBottle("Carnutes " + t + " " + c + "cL",2,"https://carnutes.pagesperso-orange.fr/WEFiles/Image/WEImage/ambree33-b97fe29b.png"));
            }
        }

        return listOfCarnutesBottles;
    }
}
