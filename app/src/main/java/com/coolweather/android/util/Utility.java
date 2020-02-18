package com.coolweather.android.util;

import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;

import com.coolweather.android.db.City;
import com.coolweather.android.db.Country;
import com.coolweather.android.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for(int i = 0;i<allProvinces.length();i++){
                    JSONObject provinceObjext = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObjext.getString("name"));
                    province.setProvinceCode(provinceObjext.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 解析市级数据
     */
    public static boolean handleCityResponse(String response , int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);//exception
                for(int i = 0 ; i < allCities.length();i++ ){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city =new City();
                   city.setCityName(cityObject.getString("name"));
                   city.setCityCode(cityObject.getInt("id"));
                   city.setProvinceId(provinceId);
                   city.save();
                }

                return true;
            }catch(JSONException e){
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * 解析县级数据
     */
    public static boolean handleCountryResponse(String response , int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCountries = new JSONArray(response);//exception
                for(int i = 0 ; i < allCountries.length();i++ ){
                        JSONObject countryObject = allCountries.getJSONObject(i);
                        Country country = new Country();
                        country.setCountryName(countryObject.getString("name"));
                        country.setWeatherId(countryObject.getString("weather_id"));
                        country.setCityId(cityId);
                        country.save();
                }

                return true;
            }catch(JSONException e){
                e.printStackTrace();
            }

        }
        return false;
    }

}
