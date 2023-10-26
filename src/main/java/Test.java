import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        LocalDateTime currentDateTime = LocalDateTime.now();
        int year = currentDateTime.getYear();
        int month = currentDateTime.getMonthValue();
        int day = currentDateTime.getDayOfMonth();
        int hour = currentDateTime.getHour();
        int minute = currentDateTime.getMinute();
        int second = currentDateTime.getSecond();

        System.out.println("Năm: " + year);
        System.out.println("Tháng: " + month);
        System.out.println("Ngày: " + day);
        System.out.println("Giờ: " + hour);
        System.out.println("Phút: " + minute);
        System.out.println("Giây: " + second);
    }
}
