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
                listOfCarnutesBottles.add(new fr.yncrea.carnulator.CarnutesBottle("Carnutes " + t + " " + c + "cL",2,t+c));
            }
        }
        /**
        listOfCarnutesBottles.add(new CarnutesBottle("Carnutes Rousse 33cL",2,"rousse33"));
        listOfCarnutesBottles.add(new CarnutesBottle("Carnutes Rousse 75cL",4,"rousse75"));
        listOfCarnutesBottles.add(new CarnutesBottle("Carnutes Blonde 33cL",2,"blonde33"));
        listOfCarnutesBottles.add(new CarnutesBottle("Carnutes Blonde 75cL",4,"blonde75"));
        listOfCarnutesBottles.add(new CarnutesBottle("Carnutes Ambrée 33cL",2,"ambree33"));
        listOfCarnutesBottles.add(new CarnutesBottle("Carnutes Ambrée 75cL",4,"ambree75"));
        listOfCarnutesBottles.add(new CarnutesBottle("Carnutes Triple 33cL",2.5,"triple33"));
        listOfCarnutesBottles.add(new CarnutesBottle("Carnutes Triple 75cL",5,"triple75"));
        listOfCarnutesBottles.add(new CarnutesBottle("Carnutes Brune 33cL",2.5,"brune33"));
        listOfCarnutesBottles.add(new CarnutesBottle("Carnutes Brune 75cL",5,"brune75"));**/

        return listOfCarnutesBottles;
    }
}
