package fr.yncrea.carnulator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.ArrayList;


public class Container {
    public EditText m_numberDisplay;
    public SeekBar m_seekBar;
    public CarnutesBottle m_bottle;
    public Button m_plus;
    public Button m_minus;
    public Button m_ok;
    public int m_nbBeer;

    public Container(EditText number, CarnutesBottle bottle, Button plus, Button minus, Button ok, SeekBar seekBar){
        this.m_numberDisplay = number;
        this.m_minus = minus;
        this.m_plus = plus;
        this.m_ok = ok;
        this.m_seekBar = seekBar;
        this.m_nbBeer = 0;
        this.m_bottle = bottle;

        ok.setOnClickListener(onClickOk());
        plus.setOnClickListener(onClickPlus(1));
        minus.setOnClickListener(onClickPlus(-1));
        seekBar.setOnSeekBarChangeListener(onClickSeekBar());
    }

    public Container(View view){
        m_ok = (Button) view.findViewById(R.id.okButton);
    }


    public EditText getM_numberDisplay() {
        return m_numberDisplay;
    }

    public void setM_nb(int m_nb) {
        this.m_nbBeer = m_nb;
    }

    private SeekBar.OnSeekBarChangeListener onClickSeekBar() {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                getM_numberDisplay().setText( (String.valueOf(progress)));

                setM_nb(Integer.valueOf(String.valueOf(progress)));
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
                getM_numberDisplay().setText( String.valueOf(Integer.valueOf(String.valueOf(getM_numberDisplay().getText()))+plusOrMinus));

                setM_nb(Integer.valueOf(String.valueOf(getM_numberDisplay().getText())));

                //calculate();
                //displayTotal();
            }
        };
    }

    protected View.OnClickListener onClickOk(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setM_nb(Integer.valueOf(String.valueOf(getM_numberDisplay().getText())));

               // double total = calculate();
                //displayTotal(total,totalDisplay);
            }
        };
    }

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



}
