
package fr.yncrea.carnulator;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.yncrea.carnulator.adapter.ContainerAdapter;
import fr.yncrea.carnulator.fragment.ContainerFragment;

public class MainActivity extends AppCompatActivity {

    private double prixCarnutesAmbree33 = 2;
    private int nbCarnutesAmbree33 = 0;
    private double prixCarnutesAmbree75 = 2.7;
    private int nbCarnutesAmbree75 = 0;
    private double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            Toast.makeText(this,"PAUUUUUUUUUUUUUL" , Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().add(R.id.containerCarnutes,new ContainerFragment()).commit();
        }
        //setContentView(R.layout.carnutes_item);
        //initialise();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void initialise(){
        EditText textOfAmbreeCarnutes33 = findViewById(R.id.numberOfBottles);
        textOfAmbreeCarnutes33.setInputType(InputType.TYPE_CLASS_NUMBER);
        textOfAmbreeCarnutes33.setText( (String.valueOf(0)));


        final Button ok = findViewById(R.id.okButton);
        final Button plus = findViewById(R.id.plusButton);
        final Button minus = findViewById(R.id.minusButton);
        final SeekBar seekBar = findViewById(R.id.seekBarNumberOfBottle);

        //Container CA33 = new Container(textOfAmbreeCarnutes33,plus,minus,ok,seekBar);


        ok.setOnClickListener(onClickOk());
        plus.setOnClickListener(onClickPlus(1));
        minus.setOnClickListener(onClickPlus(-1));
        seekBar.setOnSeekBarChangeListener(onClickSeekBar());

    }

    private SeekBar.OnSeekBarChangeListener onClickSeekBar() {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                EditText textOfAmbreeCarnutes33 = findViewById(R.id.numberOfBottles);
                textOfAmbreeCarnutes33.setText( (String.valueOf(progress)));

                nbCarnutesAmbree33 = Integer.valueOf(String.valueOf(progress));
                calculate();
                displayTotal();
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
        final EditText textOfAmbreeCarnutes33 = findViewById(R.id.numberOfBottles);

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText textOfAmbreeCarnutes33 = findViewById(R.id.numberOfBottles);
                textOfAmbreeCarnutes33.setText( String.valueOf(Integer.valueOf(String.valueOf(textOfAmbreeCarnutes33.getText()))+plusOrMinus));

                nbCarnutesAmbree33 = Integer.valueOf(String.valueOf(textOfAmbreeCarnutes33.getText()));

                calculate();
                displayTotal();
            }
        };
    }

    protected View.OnClickListener onClickOk(){
        final EditText textOfAmbreeCarnutes33 = findViewById(R.id.numberOfBottles);
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nbCarnutesAmbree33 = Integer.valueOf(String.valueOf(textOfAmbreeCarnutes33.getText()));

                calculate();
                displayTotal();
            }
        };
    }

    protected void calculate(){
        total = nbCarnutesAmbree33*prixCarnutesAmbree33 + nbCarnutesAmbree75*prixCarnutesAmbree75;

    }

    protected void displayTotal(){
        getSupportActionBar().setSubtitle("TOTAL : " + String.valueOf(ContainerAdapter.calculate()) + " €");
    }
}