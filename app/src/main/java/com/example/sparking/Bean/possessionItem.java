package com.example.sparking.Bean;
import java.io.Serializable;

public class possessionItem implements Serializable{
    String PID;
    String slot_name;
    public possessionItem(String PID,String slot_name)
    {
        this.PID = PID;
        this.slot_name = slot_name;
    }
    public String getPID()
    {
        return this.PID;
    }
    public String getslot_name()
    {
        return this.slot_name;
    }
}
