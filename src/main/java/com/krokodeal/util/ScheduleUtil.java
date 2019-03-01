package com.krokodeal.util;

import java.util.ArrayList;

public interface ScheduleUtil {

    static String setCommandToAddSchtasks(String daily, String time, ArrayList<String> stringOfallParsedFields) {
        return "schtasks /Create /TN \"KrokodealTask" +
                " /TR \"C:\\Windows\\System32\\calc.exe "
                + String.join(" ", stringOfallParsedFields)
                + " \" " + daily + " " + time;
    }

    static String setCommandToAddSchtasks(String time, ArrayList<String> days, ArrayList<String> stringOfallParsedFields) {
        return "schtasks /Create /TN \"KrokodealTask" +
                " /TR \"C:\\Windows\\System32\\calc.exe "
                + String.join(" ", stringOfallParsedFields)
                + " \" " + " /d " + String.join(",", days) + " " + time;
    }
}
