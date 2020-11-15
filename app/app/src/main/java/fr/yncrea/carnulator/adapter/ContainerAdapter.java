package fr.yncrea.carnulator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.yncrea.carnulator.ApplicationActivity;
import fr.yncrea.carnulator.CarnutesBottle;
import fr.yncrea.carnulator.Container;
import fr.yncrea.carnulator.MainActivity;
import fr.yncrea.carnulator.R;

public class ContainerAdapter extends BaseAdapter /*implements View.OnClickListener*/ {
    public static List<Container> mContainerList;
    public List<CarnutesBottle> mCarnuteList;

    private final LayoutInflater mInflater;
    //private itemListener mListener;

    public ContainerAdapter(List<CarnutesBottle> carnuteList) {
        this.mCarnuteList = carnuteList;
        this.mInflater = LayoutInflater.from(ApplicationActivity.getContext());
    }



   /* public void setmListener(TweetListener mListener) {
        this.mListener = mListener;
    }
    */


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
        }
        else {
            //holder = (ViewHolder)convertView.getTag();
            carnutesType = (Container)convertView.getTag();

        }

        final CarnutesBottle carnutesBottle = (CarnutesBottle) getItem(position);

        carnutesType.itemTextNameBottle.setText(carnutesBottle.getM_name());
        carnutesType.setPrice(carnutesBottle.getM_price());
        carnutesType.itemNumberBottle.setText(Integer.toString(0));
        carnutesType.m_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                carnutesType.itemNumberBottle.setText(String.valueOf(Integer.valueOf(String.valueOf(carnutesType.itemNumberBottle.getText()))+1));
                //calculate();
                }
            }
        );
        carnutesType.m_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnutesType.itemNumberBottle.setText(String.valueOf(Integer.valueOf(String.valueOf(carnutesType.itemNumberBottle.getText()))-1));
                //calculate();
                }
            }
        );
        carnutesType.m_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                carnutesType.itemNumberBottle.setText(String.valueOf(progress));
                //calculate();
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
                carnutesType.itemNumberBottle.setText(Integer.valueOf(String.valueOf(carnutesType.itemNumberBottle.getText())));
                //calculate();
            }
        });

        carnutesType.m_ok.setTag(position);
        carnutesType.m_minus.setTag(position);
        carnutesType.m_plus.setTag(position);
        carnutesType.m_seekBar.setTag(position);

        if(!carnutesBottle.getM_imageUrl().isEmpty()){
            Picasso.get().load(carnutesBottle.getM_imageUrl()).fit().into(carnutesType.imgItem);
        }

        //carnutesType.setPrice(carnutesBottle.getM_price());

        //carnutesType.m_bottle.setName(carnutesBottle.getM_name());

        //holder.name.setText(tweet.user.name);
        //holder.alias.setText(tweet.user.screenName);
        //holder.text.setText(tweet.text);
        //holder.button.setTag(position);
        //holder.button.setOnClickListener(this);

        //if(!tweet.user.profileImageUrl.isEmpty()){
        //    Picasso.get().load(tweet.user.profileImageUrl).fit().into(holder.image);
        //}

        return convertView;
    }

    //Calculate total price of all bottles
    /*
    public static double calculate(){
        double total = 0;
        for(Container container : mContainerList){
            total += Integer.valueOf(String.valueOf(container.itemNumberBottle.getText()))*container.getPrice();
        }
        return total;
    }

     */
/*
    public void displayTotal(){
        .setSubtitle("TOTAL : " + String.valueOf(calculate()) + " €");
        totalEditText.setText(String.valueOf(total));
    }

 */



/*
    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        if(null != mListener){
            final Tweet tweet = (Tweet) getItem(position);
            mListener.onRetweet(tweet);
        }
    }

   */


}
