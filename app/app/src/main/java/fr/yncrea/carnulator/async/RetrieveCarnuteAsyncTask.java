package fr.yncrea.carnulator.async;

import android.os.AsyncTask;

import java.util.List;

import fr.yncrea.carnulator.CarnutesBottle;
import fr.yncrea.carnulator.interfaces.ICarnuteChangeListener;

public class RetrieveCarnuteAsyncTask extends AsyncTask<String, Void, List<CarnutesBottle>>  {
    private ICarnuteChangeListener mListener;
    //BALEC for now
    public RetrieveCarnuteAsyncTask(ICarnuteChangeListener mListener){
        this.mListener = mListener;
    }

    @Override
    protected List<CarnutesBottle> doInBackground(String... strings) {
        //TO DO next
        //List<Beer> beers = db.BeersDao().getAllBeers();
        //if((strings != null) && (strings.length > 0)){
        //    return CarnutesBottle.getListOfBottle();
        //return TwitterHelper.getTweetsOfUser(strings[0]);
        //}
        return CarnutesBottle.getListOfBottle();
    }

    @Override
    protected void onPostExecute(List<CarnutesBottle> carnutesBottleList) {
        if (mListener != null && carnutesBottleList != null){
            mListener.onRetrieveBottle(carnutesBottleList);
        }
        super.onPostExecute(carnutesBottleList);
    }
}
