package com.example.sparking.GetData;

import org.json.JSONException;
import org.json.JSONObject;

public class configure {

    public static final String URL_User = "http://118.31.76.154:8080/Entity/U40a1d0e1fae02/Sparking1a/User/";
    public static final String URL_ParkingSlot = "http://118.31.76.154:8080/Entity/U40a1d0e1fae02/Sparking1a/Parkingslot/";
    public static final String URL_Slotownership = "http://118.31.76.154:8080/Entity/U40a1d0e1fae02/Sparking1a/Slotownership/";
    public static final String URL_Car="http://118.31.76.154:8080/Entity/U40a1d0e1fae02/Sparking1a/Car/";

    public static final String URL_Base="http://118.31.76.154:8080/Entity/U40a1d0e1fae02/SParking11/";
    public static final String URL_ParkFee=URL_Base+"Parkfee/";

    public static JSONObject MEJSON = new JSONObject();

    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";


    //  个人信息
    public static String USER_ID = "";
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
        try {
            USER_ID = MEJSON.getString(KEY_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
