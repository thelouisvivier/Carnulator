package fr.yncrea.carnulator.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import fr.yncrea.carnulator.dao.BeersDao;
import fr.yncrea.carnulator.model.Beer;

@Database(entities = {Beer.class}, version = 4)
public abstract class BeersDatabase extends RoomDatabase {
    public abstract BeersDao BeersDao();
}
