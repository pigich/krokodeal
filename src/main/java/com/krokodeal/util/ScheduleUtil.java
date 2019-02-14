package com.krokodeal.util;

import java.util.ArrayList;

public interface ScheduleUtil {

    static String setCommandToAddSchtasks(String daily, String time) {

        return "schtasks /Create /TN \"KrokodealTask" +
                " /TR \"C:\\Windows\\System32\\calc.exe\" " + daily + " " + time;
    }

    static String setCommandToAddSchtasks(String time, ArrayList<String> days) {

        return "schtasks /Create /TN \"KrokodealTask" +
                " /TR \"C:\\Windows\\System32\\calc.exe\" " + " /d " + String.join(",", days) + " " + time;
    }

}
