package fr.yncrea.carnulator.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import fr.yncrea.carnulator.CarnutesBottle;
import fr.yncrea.carnulator.MainActivity;
import fr.yncrea.carnulator.R;
import fr.yncrea.carnulator.adapter.ContainerAdapter;
import fr.yncrea.carnulator.async.RetrieveCarnuteAsyncTask;
import fr.yncrea.carnulator.interfaces.ICarnuteChangeListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContainerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContainerFragment extends Fragment implements ICarnuteChangeListener, AdapterView.OnItemClickListener  {

    private ListView mListOfContainer;
    private RetrieveCarnuteAsyncTask mCarnuteAsyncTask;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ContainerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContainerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContainerFragment newInstance(String param1, String param2) {
        ContainerFragment fragment = new ContainerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_container, container, false);
        mListOfContainer = (ListView) rootView.findViewById(R.id.containerListView);
        //mListOfContainer.setOnItemClickListener(this);

        return rootView;
    }

    //BALEC not so sure
    @Override
    public void onStart() {
        super.onStart();
        mCarnuteAsyncTask = new RetrieveCarnuteAsyncTask(this);
        mCarnuteAsyncTask.execute();



    }

    @Override
    public void onRetrieveBottle(List<CarnutesBottle> carnutesBottles) {
        if( carnutesBottles != null){
            // final ArrayAdapter<Tweet> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, tweets);
            // mListView.setAdapter(adapter);
            final ContainerAdapter adapter = new ContainerAdapter(carnutesBottles);
            //adapter.setmListener(mTweetListener);
            mListOfContainer.setAdapter(adapter);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    //@Override
    //public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    //}
    /*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tweet tweet = (Tweet) mListView.getItemAtPosition((position));
        if(mTweetListener != null) {
            mTweetListener.onViewTweet(tweet);
        }
    }

     */
    /*
    //BALEC
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TweetListener){
            mTweetListener = (TweetListener) context;
        }
    }

     */

}