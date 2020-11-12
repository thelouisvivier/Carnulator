package fr.yncrea.carnulator.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.yncrea.carnulator.model.Beer;

@Dao
public interface BeersDao {
    @Query("select * from Beer")
    List<Beer> getAllBeers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Beer beer);
}
