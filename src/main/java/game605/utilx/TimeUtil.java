package game605.utilx;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public class TimeUtil {

    public static Timestamp strToTimestamp(String strTime) throws ParseException {
        System.out.println("获得到的时间");
        System.out.println(strTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Long beginUseTime = sdf.parse(strTime).getTime();
        return new Timestamp(beginUseTime);
    }

}
