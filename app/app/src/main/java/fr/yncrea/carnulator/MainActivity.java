
package fr.yncrea.carnulator;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import fr.yncrea.carnulator.api.CarnutesApiService;
import fr.yncrea.carnulator.database.BeersDatabase;
import fr.yncrea.carnulator.model.Beer;
import fr.yncrea.carnulator.model.CarnutesAPIBeers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private double prixCarnutesAmbree33 = 2;
    private int nbCarnutesAmbree33 = 0;
    private double prixCarnutesAmbree75 = 2.7;
    private int nbCarnutesAmbree75 = 0;
    private double total = 0;

    private BeersDatabase db;
    private CarnutesApiService swapiService;
    private Executor backgroundExecutor = Executors.newSingleThreadExecutor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carnutes_item);

        //Create API Call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://carnulator-b911.restdb.io/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        swapiService = retrofit.create(CarnutesApiService.class);

        //New thread pour la bdd
        backgroundExecutor.execute(()-> {
            db = Room.databaseBuilder(getApplicationContext(), BeersDatabase.class, "beers_database.db").build();
        });

        backgroundExecutor.execute(()-> {
            loadFromApiAndSave();
            //Enregistrement bière en BDD
            //Affichage données en BDD
            updateBeersList();
        });

        initialise();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void initialise(){
        EditText textOfAmbreeCarnutes33 = findViewById(R.id.numberOfBottles);
        textOfAmbreeCarnutes33.setInputType(InputType.TYPE_CLASS_NUMBER);
        textOfAmbreeCarnutes33.setText( (String.valueOf(0)));


        final Button ok = findViewById(R.id.okCA33);
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
        getSupportActionBar().setSubtitle("TOTAL : " + String.valueOf(total) + " €");
    }

    // API
    private void loadFromApiAndSave(){
        // Recup bières depuis API
        try {
            Response<CarnutesAPIBeers> response = swapiService.getBeers().execute();
            if(response.isSuccessful()){
                List<Beer> beers = response.body().results;
                Log.w("Carnutes APP","Bières: "+ beers.size());

                // Save beer bdd
                for(Beer beer : beers){
                    db.BeersDao().insert(beer);
                }
            }
            else{
                Log.w("Carnutes APP","Il y a eu un soucis avec la requête");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateBeersList(){
        // Récupération
        List<Beer> beers = db.BeersDao().getAllBeers();
        //String text = beers.stream().map(Beer::toString).collect(Collectors.joining("\n"));
        /*runOnUiThread(()-> {
            TextView helloTextView = findViewById(R.id.helloTextView);
            helloTextView.setText(text);
        });*/
    }
}