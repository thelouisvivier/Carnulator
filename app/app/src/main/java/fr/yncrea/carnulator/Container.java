package fr.yncrea.carnulator;

import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class Container {

    public SeekBar m_seekBar;
    //public CarnutesBottle m_bottle;
    public ImageView imgItem;
    public TextView itemTextNameBottle;
    public TextView itemNumberBottle;
    public Button m_plus;
    public Button m_minus;
    public Button m_ok;
    private double price;

    //See if we need it, else To delete
/*
    public Container(EditText number, CarnutesBottle bottle, Button plus, Button minus, Button ok, SeekBar seekBar){

        this.m_minus = minus;
        this.m_plus = plus;
        this.m_ok = ok;
        this.m_seekBar = seekBar;
        this.m_bottle = bottle;

        ok.setOnClickListener(onClickOk());
        plus.setOnClickListener(onClickPlus(1));
        minus.setOnClickListener(onClickPlus(-1));
        seekBar.setOnSeekBarChangeListener(onClickSeekBar());
    }
*/


    public Container(View view){
        m_seekBar = (SeekBar) view.findViewById(R.id.seekBarNumberOfBottle);
        imgItem = (ImageView) view.findViewById(R.id.imageViewItem);
        itemTextNameBottle = (TextView) view.findViewById(R.id.itemTextNameBottle);
        itemNumberBottle = (TextView) view.findViewById(R.id.numberOfBottles);
        m_ok = (Button) view.findViewById(R.id.okButton);
        m_plus = (Button) view.findViewById(R.id.plusButton);
        m_minus = (Button) view.findViewById(R.id.minusButton);

    }

    public Container(Container c){
        m_seekBar = c.m_seekBar;
        imgItem = c.imgItem;
        itemTextNameBottle = c.itemTextNameBottle;
        itemNumberBottle = c.itemNumberBottle;
        m_ok = c.m_ok;
        m_plus = c.m_plus;
        m_minus = c.m_minus;

    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    private SeekBar.OnSeekBarChangeListener onClickSeekBar() {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                itemNumberBottle.setText(String.valueOf(progress));
                //calculate();
                //displayTotal();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }

    private View.OnClickListener onClickPlus(final int plusOrMinus) {

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemNumberBottle.setText(String.valueOf(Integer.valueOf(String.valueOf(itemNumberBottle.getText()))+plusOrMinus));

                //calculate();
                //displayTotal();
            }
        };
    }

    protected View.OnClickListener onClickOk(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                itemNumberBottle.setText(Integer.valueOf(String.valueOf(itemNumberBottle.getText())));
                // double total = calculate();
                //displayTotal(total,totalDisplay);
            }
        };
    }

    //Calculate total price of all bottles
    /*
    public double calculate(ArrayList<Container> containers){
        double total = 0;
        for(Container container : containers){
            total += container.m_nbBeer*container.m_bottle.getM_price();
        }
        return total;
    }

    public void displayTotal(double total, EditText totalEditText){
        totalEditText.setText(String.valueOf(total));
    }
    */




}
