package fr.yncrea.carnulator.adapter;

import android.os.Build;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.yncrea.carnulator.ApplicationActivity;
import fr.yncrea.carnulator.CarnutesBottle;
import fr.yncrea.carnulator.Container;
import fr.yncrea.carnulator.MainActivity;
import fr.yncrea.carnulator.R;
import fr.yncrea.carnulator.model.Beer;

public class ContainerAdapter extends BaseAdapter {
    public static List<Container> mContainerList = new ArrayList<Container>();;
    public List<Beer> mCarnuteList;
    public static double totalPrice;

    private final LayoutInflater mInflater;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ContainerAdapter() {
        this.mCarnuteList = MainActivity.beerList;
        this.mInflater = LayoutInflater.from(ApplicationActivity.getContext());
    }




    @Override
    public int getCount() {
        return null != mCarnuteList ? mCarnuteList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null != mCarnuteList ? mCarnuteList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //"ViewHolder holder;" be like : we set the holder to display our data
        Container carnutesTypeTest;

        boolean isSet = false;
        int index = -1;
        //scrolling

        //inflate the list with an item
        convertView = mInflater.inflate(R.layout.carnutes_item,null);
        final Beer carnutesBottle = (Beer) getItem(position);
        carnutesTypeTest = new Container(convertView);
        carnutesTypeTest.itemTextNameBottle.setText(carnutesBottle.getName()+' '+String.valueOf(carnutesBottle.getSize())+" cL");

        //check if we already set the container
        for (int i = 0; i < ContainerAdapter.mContainerList.size(); i++  ) {
            String a =  String.valueOf(ContainerAdapter.mContainerList.get(i).itemTextNameBottle.getText());
            String b =  String.valueOf(carnutesTypeTest.itemTextNameBottle.getText());
            if(a.equals(b)){
                isSet = true;
                index = i;
            }
        }

        Container carnutesType= new Container(convertView);
        if(isSet == true){
            final int idx = index;
            Container carnutesTypeSet = ContainerAdapter.mContainerList.get(idx);
            convertView.setTag(carnutesTypeSet);
            carnutesType.itemNumberBottle.setText(String.valueOf(carnutesTypeSet.itemNumberBottle.getText()));
            carnutesType.itemNumberBottle.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);

            //set variables of the holder
            carnutesType.itemTextNameBottle.setText(String.valueOf(carnutesTypeSet.itemTextNameBottle.getText()));
            carnutesType.setPrice(carnutesTypeSet.getPrice());

            //set the tag of every component
            carnutesType.m_ok.setTag(position);
            carnutesType.m_minus.setTag(position);
            carnutesType.m_plus.setTag(position);
            carnutesType.m_seekBar.setTag(position);

            if(!carnutesBottle.getImage().isEmpty()){
                Picasso.get().load("https://carnulator-b911.restdb.io/media/"+carnutesBottle.getImage()).fit().into(carnutesType.imgItem);
            }

            carnutesType.m_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ContainerAdapter.mContainerList.get(idx).itemNumberBottle.setText(String.valueOf(Integer.valueOf(String.valueOf(carnutesType.itemNumberBottle.getText()))+1));
                    carnutesType.itemNumberBottle.setText(String.valueOf(Integer.valueOf(String.valueOf(carnutesType.itemNumberBottle.getText()))+1));
                }
            }
            );
            carnutesType.m_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ContainerAdapter.mContainerList.get(idx).itemNumberBottle.setText(String.valueOf(Integer.valueOf(String.valueOf(carnutesType.itemNumberBottle.getText()))-1));
                    carnutesType.itemNumberBottle.setText(String.valueOf(Integer.valueOf(String.valueOf(carnutesType.itemNumberBottle.getText()))-1));
                }
            }
            );
            carnutesType.m_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    ContainerAdapter.mContainerList.get(idx).itemNumberBottle.setText(String.valueOf(progress));
                    carnutesType.itemNumberBottle.setText(String.valueOf(progress));

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            }
            );
            //when "ok" is clicked for an element, updates the total price
            carnutesType.m_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Integer.valueOf(String.valueOf(carnutesType.itemNumberBottle.getText())) < 0){
                        CharSequence text = "La valeur entree est negative, merci de rentrer un nombre superieur a 0";
                        Toast toast = Toast.makeText(ApplicationActivity.getContext(), text,Toast.LENGTH_SHORT);
                        toast.show();

                        ContainerAdapter.mContainerList.get(idx).itemNumberBottle.setText(Integer.toString(0));
                        carnutesType.itemNumberBottle.setText(Integer.toString(0));

                    }

                    calculate();
                    displayTotal(parent.getRootView());
                }
            });


            return convertView;

        }
        else{


            convertView.setTag(carnutesType);
            carnutesType.itemNumberBottle.setText(Integer.toString(0));
            carnutesType.itemNumberBottle.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);

            //set variables of the holder
            carnutesType.itemTextNameBottle.setText(carnutesBottle.getName()+' '+String.valueOf(carnutesBottle.getSize())+" cL");
            carnutesType.setPrice(carnutesBottle.getPrice());

            //set the tag of every component
            carnutesType.m_ok.setTag(position);
            carnutesType.m_minus.setTag(position);
            carnutesType.m_plus.setTag(position);
            carnutesType.m_seekBar.setTag(position);

            //get image
            if(!carnutesBottle.getImage().isEmpty()){
                Picasso.get().load("https://carnulator-b911.restdb.io/media/"+carnutesBottle.getImage()).fit().into(carnutesType.imgItem);
            }

            carnutesType.m_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    carnutesType.itemNumberBottle.setText(String.valueOf(Integer.valueOf(String.valueOf(carnutesType.itemNumberBottle.getText()))+1));
                    }
                }
            );
            carnutesType.m_minus.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     carnutesType.itemNumberBottle.setText(String.valueOf(Integer.valueOf(String.valueOf(carnutesType.itemNumberBottle.getText()))-1));

                 }
            }
            );
            carnutesType.m_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    carnutesType.itemNumberBottle.setText(String.valueOf(progress));

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            }
            );
            //when "ok" is clicked for an element, updates the total price
            carnutesType.m_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Integer.valueOf(String.valueOf(carnutesType.itemNumberBottle.getText())) < 0){
                        CharSequence text = "La valeur entree est negative, merci de rentrer un nombre superieur a 0";
                        Toast toast = Toast.makeText(ApplicationActivity.getContext(), text,Toast.LENGTH_SHORT);
                        toast.show();

                        carnutesType.itemNumberBottle.setText(Integer.toString(0));

                    }

                    calculate();
                    displayTotal(parent.getRootView());
                }
            });
            ContainerAdapter.mContainerList.add(carnutesType);

            return convertView;
        }

    }

    //Calculate total price of all bottles

    public static void calculate(){
        double total = 0;
        if(ContainerAdapter.mContainerList != null ){
            if (ContainerAdapter.mContainerList.size() !=0){
                for(Container container : ContainerAdapter.mContainerList){
                    total += Integer.valueOf(String.valueOf(container.itemNumberBottle.getText()))*container.getPrice();
                }

            }

        }
        totalPrice = total;


    }

//Display the total price
    public void displayTotal(View view){
        TextView display = (TextView) view.findViewById(R.id.displayTotal);
        display.setText("Prix total : "+ ContainerAdapter.totalPrice + "€");
    }


}
