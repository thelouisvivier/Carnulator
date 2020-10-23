package fr.yncrea.carnutes;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Container {
    protected EditText m_numberDisplay;
    protected SeekBar m_seekBar;
    protected int m_nb;
    protected int m_price;
    protected Button m_plus;
    protected Button m_minus;
    protected Button m_ok;

    public Container(EditText number, Button plus, Button minus, Button ok, SeekBar seekBar){
        this.m_numberDisplay = number;
        this.m_minus = minus;
        this.m_plus = plus;
        this.m_ok = ok;
        this.m_seekBar = seekBar;
        this.m_nb = 0;

        ok.setOnClickListener(onClickOk());
        plus.setOnClickListener(onClickPlus(1));
        minus.setOnClickListener(onClickPlus(-1));
        seekBar.setOnSeekBarChangeListener(onClickSeekBar());
    }


    public EditText getM_numberDisplay() {
        return m_numberDisplay;
    }

    public void setM_nb(int m_nb) {
        this.m_nb = m_nb;
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
            total += container.m_nb*container.m_price;
        }
        return total;
    }

    public void displayTotal(double total, EditText totalEditText){
        totalEditText.setText(String.valueOf(total));
    }



}
