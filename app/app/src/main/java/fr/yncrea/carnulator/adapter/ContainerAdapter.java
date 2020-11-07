package fr.yncrea.carnulator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import fr.yncrea.carnulator.ApplicationActivity;
import fr.yncrea.carnulator.CarnutesBottle;
import fr.yncrea.carnulator.Container;
import fr.yncrea.carnulator.R;

public class ContainerAdapter extends BaseAdapter /*implements View.OnClickListener*/ {
    List<Container> mCarnutesList;
    private final LayoutInflater mInflater;
    //private itemListener mListener;

    public ContainerAdapter(List<Container> carnutesList) {
        mCarnutesList = carnutesList;
        this.mInflater = LayoutInflater.from(ApplicationActivity.getContext());
    }

   /* public void setmListener(TweetListener mListener) {
        this.mListener = mListener;
    }
    */


    @Override
    public int getCount() {
        return null != mCarnutesList ? mCarnutesList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null != mCarnutesList ? mCarnutesList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Container carnutesType;
        //scrolling
        if(null == convertView){
            convertView = mInflater.inflate(R.layout.carnutes_item,null);
            carnutesType= new Container(convertView);
            convertView.setTag(carnutesType);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        final CarnutesBottle carnutesBottle = (CarnutesBottle) getItem(position);

        carnutesType.m_bottle.setName(carnutesBottle.getM_name());

        holder.name.setText(tweet.user.name);
        holder.alias.setText(tweet.user.screenName);
        holder.text.setText(tweet.text);
        holder.button.setTag(position);
        holder.button.setOnClickListener(this);

        if(!tweet.user.profileImageUrl.isEmpty()){
            Picasso.get().load(tweet.user.profileImageUrl).fit().into(holder.image);
        }

        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        if(null != mListener){
            final Tweet tweet = (Tweet) getItem(position);
            mListener.onRetweet(tweet);
        }
    }

   


}
