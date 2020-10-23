package fr.yncrea.carnutes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.yncrea.carnutes.ApplicationActivity;
import fr.yncrea.carnutes.Container;
import fr.yncrea.carnutes.MainActivity;

public class ContainerAdapter /*extends BaseAdapter implements View.OnClickListener*/ {
    List<Container> mCarnutesList;
    private final LayoutInflater mInflater;
    //private itemListener mListener;

    public ContainerAdapter(LayoutInflater mInflater) {
        this.mInflater = LayoutInflater.from(ApplicationActivity.getContext());
    }

   /* public void setmListener(TweetListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getCount() {
        return null != mTweets ? mTweets.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null != mTweets ? mTweets.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //scrolling
        if(null == convertView){
            convertView = mInflater.inflate(R.layout.tweet_listitem,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        final Tweet tweet = (Tweet) getItem(position);

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

    private class ViewHolder{
        public ImageView image;
        public TextView name;
        public TextView alias;
        public TextView text;
        public Button button;

        public ViewHolder(View view){
            image = (ImageView) view.findViewById(R.id.tweetListItemImageView);
            name = (TextView) view.findViewById(R.id.tweetListItemNameTextView);
            alias = (TextView) view.findViewById(R.id.textListItemAliasTextView);
            text = (TextView) view.findViewById(R.id.tweetListItemTextTextView);
            button = (Button) view.findViewById(R.id.tweetListItemButtonTextView);
        }

    }*/


}
