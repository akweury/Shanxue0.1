package testlearn.shanxue.edu.shanxue01.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    private static final String TAG = "TimeUtil";

    private String currentTime;

    public TimeUtil() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        this.currentTime = dateFormat.format(date);
    }

    public String getCurrentTime() {
        return currentTime;
    }





}
