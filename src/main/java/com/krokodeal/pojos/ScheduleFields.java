package com.krokodeal.pojos;

import java.util.*;

public class ScheduleFields {

    public static Map<String, Boolean> chosenDaysMap = new HashMap<>();
    public static ArrayList<String> timeList = new ArrayList<>(
            Arrays.asList("08:00", "08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00",
                    "12:30","13:00","13:30","14:00","14:30","15:30","16:00","16:30","17:00","17:30", "18:00")
    );
    private static String selectedTime="";


    public static String getChosenDays() {
                if (chosenDaysMap.size() > 0) {
            Set<String> keySet = chosenDaysMap.keySet();
                    return "/d "+String.join(",", keySet);
        } else return "";
    }

    public static String getDaily() {
        return "/SC DAILY";
    }
    public static String getSelectedTime() {
        return selectedTime;
    }

    public static void setSelectedTime(String selectedTime) {
        ScheduleFields.selectedTime = "/ST "+selectedTime;
    }
}
