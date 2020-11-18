
package fr.yncrea.carnulator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.yncrea.carnulator.adapter.ContainerAdapter;
import fr.yncrea.carnulator.fragment.ContainerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){

            setContentView(R.layout.activity_main);

            getSupportFragmentManager().beginTransaction().add(R.id.containerCarnutes,new ContainerFragment()).commit();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}