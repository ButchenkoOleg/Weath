package com.example.oleg.weath;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import Utils.Utils;
import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;


public class MainActivity extends AppCompatActivity {

    private TextView cityName;
    private TextView temp;
    private ImageView iconView;
    private TextView description;
    private TextView humididty;
    private TextView pressure;
    private TextView wind;
    private TextView sanrice;
    private TextView sanset;
    private TextView updated;

    Weather weather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cityName = (TextView)findViewById(R.id.cityText);
        iconView = (ImageView)findViewById(R.id.thumbnailIcon);
        temp = (TextView)findViewById(R.id.tempText);
        description = (TextView)findViewById(R.id.cloudText);
        humididty = (TextView)findViewById(R.id.humidText);
        pressure = (TextView)findViewById(R.id.pressureText);
        wind = (TextView)findViewById(R.id.windText);
        sanrice = (TextView)findViewById(R.id.riseText);
        sanset = (TextView)findViewById(R.id.setText);
        updated = (TextView)findViewById(R.id.updateText);

        renderWeatherData("Kiev, UA" );

    }

    public void renderWeatherData (String city) {

        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[] {city + "&units=metric" });

    }

    private class DownloadImageAsyncTask extends AsyncTask <String, Void, Bitmap>{
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return null;
        }

        private Bitmap downloadImage(String code){

            return null;
        }
    }

    private  class  WeatherTask extends AsyncTask<String, Void, Weather>{

        @Override
        protected Weather doInBackground(String... params) {

            String data = null;
            try {
                 data = ((new WeatherHttpClient()).getWeatherData(params[0]));



            } catch (IOException e) {
                e.printStackTrace();
            }


           weather = JSONWeatherParser.getWeather(data);
                Log.v("Data: ", weather.currentCondition.getDescription());

            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            DateFormat df = DateFormat.getTimeInstance();
            String sunrise = df.format(new Date(weather.place.getSanrice()));
            String sunset = df.format(new Date(weather.place.getSanset()));
            String lastUpdate = df.format(new Date(weather.place.getLastupdate()));
            DecimalFormat decimalFormat = new DecimalFormat("#.#");

            String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature());

            cityName.setText(weather.place.getCity()+", "+weather.place.getCountry());
            temp.setText("" + weather.currentCondition.getTemperature()+"'C");
            humididty.setText("Humidity: "+ weather.currentCondition.getHumidity() + "%");
            pressure.setText("Pressure: " + weather.currentCondition.getHumidity() + "hPa");
            wind.setText("Wind: " + weather.wind.getSpeed() + "m/s");
            sanrice.setText("Sanrice: " + sunrise);
            sanset.setText("Sanset: " + sunset);
            updated.setText("Last updated: " + lastUpdate);
            description.setText("Condition: " + weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescription() + " )" );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
