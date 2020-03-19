package com.hfad.bobaway.data;

import java.io.Serializable;
import java.util.ArrayList;

public class BobaWayItem implements Serializable {
    public String name;
    public BobaWayItem_Location location;
    public BobaWayItem_OpenHours hours;
    public String id;


    public class BobaWayItem_OpenHours implements Serializable {
        public BobaWayItem_Hours[] open;
    }

    public class BobaWayItem_Hours implements Serializable {
        public String start;
        public String end;
        public int day;

    }

    public class BobaWayItem_Location implements Serializable {
        public String city;
        public String country;
        public String address1;
        public String state;
        public String zip_code;
    }
}
