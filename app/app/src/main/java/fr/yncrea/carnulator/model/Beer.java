package fr.yncrea.carnulator.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(indices = {@Index(value={"name"})})
public class Beer {
    @PrimaryKey
    @NonNull
    @SerializedName("_id")
    public String id;

    @ColumnInfo(name="name")
    public String name;
    public Integer size;
    public Float price;
    public String image;

    public Float getPrice() {
        return price;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Integer getSize() {
        return size;
    }


}
