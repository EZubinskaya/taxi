package com.util;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;


public class GoogleFindDistanceAndDuration extends AbstractSample {

    public static Path infoDurationDistanse(String userLocation, String taxiDestination) throws IOException, JSONException {

        System.setProperty("file.encoding", "UTF-8");
        final String baseUrl = "http://maps.googleapis.com/maps/api/distancematrix/json";// путь к Geocoding API по HTTP
        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");// указывает, исходит ли запрос на геокодирование от устройства с датчиком
        params.put("language", "ru");// язык данных
        params.put("mode", "driving");// идем пешком, может быть driving, walking, bicycling
        // адрес или координаты отправных пунктов
//        final String[] origins = { "Россия, Москва, улица Поклонная, 12" };
        params.put("origins", taxiDestination);
        // адрес или координаты пунктов назначения
        final String[] destionations = { //
                new String(userLocation.getBytes(), "UTF-8"),
                new String(taxiDestination.getBytes(), "UTF-8")
        };
        // в запросе адреса должны разделяться символом '|'
        //String query = "name1=" + URLEncoder.encode("женя бука", "UTF-8");
        params.put("destinations", userLocation);
        final String url = baseUrl + '?' + encodeParams(params);// генерируем путь с параметрами
        System.out.println(url); // Можем проверить что вернет этот путь в браузере
        final JSONObject response = JsonReader.read(url);// делаем запрос к вебсервису и получаем от него ответ
        final JSONObject location = response.getJSONArray("rows").getJSONObject(0);
        final JSONArray arrays = location.getJSONArray("elements");// Здесь лежат все рассчитанные значения
        // Ищем путь на который мы потратим минимум времени
        getNearTaxi(arrays);
        final String distance = ((JSONObject)arrays.get(0)).getJSONObject("distance").getString("text");// расстояние в километрах
        final String duration = ((JSONObject)arrays.get(0)).getJSONObject("duration").getString("value");// время в пути
        Path path = new Path(distance, Integer.decode(duration));
//        infoDurationDistanse.put("duration", Integer.decode(duration));
        return path;
    }
//
//    public static int duration(String userLocation, String taxiDestination) throws IOException, JSONException {
//
//        return Integer.decode(duration);
//    }

    private static JSONObject getNearTaxi(JSONArray arrays ) throws JSONException {
        int min = Integer.decode(((JSONObject) arrays.get(0)).getJSONObject("duration").getString("value"));
        JSONObject temp = null;
        for (int i = 0; i < arrays.length(); i++) {
             temp = (JSONObject) arrays.get(i);
             int duration = Integer.decode(temp.getJSONObject("duration").getString("value"));
             min = Math.min(min, duration);
        }
        return temp;
    }


}