package fr.yncrea.carnulator.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value={"name"})})
public class Beer {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name="name")
    public String name;
    public Integer size;
    public Float price;
    public String image;
}
