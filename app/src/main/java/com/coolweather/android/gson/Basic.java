package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")//in the field of id which contain some chars:CN....
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
