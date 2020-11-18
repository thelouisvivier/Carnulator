package fr.yncrea.carnulator.adapter;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.yncrea.carnulator.ApplicationActivity;
import fr.yncrea.carnulator.CarnutesBottle;
import fr.yncrea.carnulator.Container;
import fr.yncrea.carnulator.R;

public class ContainerAdapter extends BaseAdapter {
    public static List<Container> mContainerList = new ArrayList<Container>();;
    public List<CarnutesBottle> mCarnuteList;
    public static double totalPrice;

    private final LayoutInflater mInflater;

    public ContainerAdapter(List<CarnutesBottle> carnuteList) {
        this.mCarnuteList = carnuteList;
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


        //ViewHolder holder;
        Container carnutesType;
        //scrolling
        if(null == convertView){
            convertView = mInflater.inflate(R.layout.carnutes_item,null);
            carnutesType= new Container(convertView);

            convertView.setTag(carnutesType);
            carnutesType.itemNumberBottle.setText(Integer.toString(0));
            carnutesType.itemNumberBottle.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);

        }
        else {
            carnutesType = (Container)convertView.getTag();

        }
        final CarnutesBottle carnutesBottle = (CarnutesBottle) getItem(position);

        carnutesType.itemTextNameBottle.setText(carnutesBottle.getM_name());
        carnutesType.setPrice(carnutesBottle.getM_price());

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

        carnutesType.m_ok.setTag(position);
        carnutesType.m_minus.setTag(position);
        carnutesType.m_plus.setTag(position);
        carnutesType.m_seekBar.setTag(position);

        if(!carnutesBottle.getM_imageUrl().isEmpty()){
            Picasso.get().load(carnutesBottle.getM_imageUrl()).fit().into(carnutesType.imgItem);
        }



        for (int i = 0; i < ContainerAdapter.mContainerList.size(); i++  ) {
            if(ContainerAdapter.mContainerList.get(i).itemTextNameBottle == carnutesType.itemTextNameBottle){
                ContainerAdapter.mContainerList.get(i).itemNumberBottle = carnutesType.itemNumberBottle;
                return convertView;
            }
        }

        ContainerAdapter.mContainerList.add(carnutesType);

        return convertView;
    }

    //Calculate total price of all bottles

    public static void calculate(){
        double total = 0;
        if(ContainerAdapter.mContainerList != null ){
            if (ContainerAdapter.mContainerList.size() !=0){
                for(Container container : ContainerAdapter.mContainerList){
                    Log.d("TAG", "calculate:"+String.valueOf(container.itemNumberBottle.getText()));
                    total += Integer.valueOf(String.valueOf(container.itemNumberBottle.getText()))*container.getPrice();
                }

            }

        }
        totalPrice = total;


    }


    public void displayTotal(View view){
        TextView display = (TextView) view.findViewById(R.id.displayTotal);
        display.setText("Prix total : "+ ContainerAdapter.totalPrice + "€");
    }


}
