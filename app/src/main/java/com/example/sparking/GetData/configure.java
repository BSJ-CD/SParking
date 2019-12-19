package com.example.sparking.GetData;

import org.json.JSONException;
import org.json.JSONObject;

public class configure {

    public static final String URL_User = "http://118.31.76.154:8080/Entity/U40a1d0e1fae02/SParking11/User/";
    public static final String URL_ParkingSlot = "http://118.31.76.154:8080/Entity/U40a1d0e1fae02/SParking11/Parkingslot/";

    public static JSONObject MEJSON = new JSONObject();

    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";


    //  个人信息
    public static String ID = "";
    public static String USERNAME = "";
    public static String PASSWORD = "";


    public static void changeData() {
        try {
            USERNAME = MEJSON.getString(KEY_USERNAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            PASSWORD = MEJSON.getString(KEY_PASSWORD);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
