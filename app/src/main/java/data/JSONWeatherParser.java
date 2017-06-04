package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Utils.Utils;
import model.Place;
import model.Weather;

/**
 * Created by oleg on 01.06.17.
 */

public class JSONWeatherParser {

    public static Weather getWeather(String date){
        Weather weather = new Weather();

        //create JsonObject from data

        try {
            JSONObject jsonObject = new JSONObject(date);
            Place place = new Place();

            JSONObject cordObject = Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getFloat("lat", cordObject));
            place.setLon(Utils.getFloat("lon", cordObject));

            //Get the sys obj
            JSONObject sysObj = Utils.getObject("sys", jsonObject);
            place.setCountry(Utils.getString("country", sysObj));
            place.setLastupdate(Utils.getInt("dt", jsonObject));
            place.setSanrice(Utils.getInt("sunrise", sysObj));
            place.setSanset(Utils.getInt("sunset", sysObj));
            place.setCity(Utils.getString("name", jsonObject));
            weather.place = place;


            //Get weather info

            JSONArray jsonArray = jsonObject. getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Utils.getInt("id", jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description", jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main", jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon", jsonWeather));

            

            JSONObject windObject = Utils.getObject("wind", jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed", windObject));
            weather.wind.setDeg(Utils.getFloat("deg", windObject));

            JSONObject cloudsObject = Utils.getObject("clouds", jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all", cloudsObject));

            return weather;

        }catch (JSONException e){

            e.printStackTrace();

            return null;
        }


    }
}
