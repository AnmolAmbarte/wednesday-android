package com.sample.wednesday.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "details")

public class Data {

    @PrimaryKey(autoGenerate = true)
    private int dataId;



    @Expose
    @SerializedName("avatar")
    private String avatar;
    @Expose
    @SerializedName("last_name")
    private String last_name;
    @Expose
    @SerializedName("first_name")
    private String first_name;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("id")
    private int id;



    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDataId() {
        return dataId;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }


}
