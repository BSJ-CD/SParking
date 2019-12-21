package com.example.sparking.Bean;
import java.io.Serializable;

public class ItemData implements Serializable{
    String PID;
    String slot_owner_id;
    String slot_name;
    public ItemData(String PID,String slot_owner_id,String slot_name)
    {
        this.PID = PID;
        this.slot_owner_id =slot_owner_id;
        this.slot_name = slot_name;
    }
    public String getPID()
    {
        return this.PID;
    }
    public String getslot_owner_id()
    {
        return this.slot_owner_id;
    }
    public String getslot_name()
    {
        return this.slot_name;
    }
}
