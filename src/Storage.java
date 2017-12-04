import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Storage {
    private static Map<Integer, MyObject> objects = new HashMap<>();
    private static Integer summaryDuration = 0;
    static {
        MyObject object;
        object = new MyObject();
        object.setAuthor("Nirvana");
        object.setName("In Bloom");
        object.setDuration(new DurationTime(03, 15));

        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        try {
            object.setPublication(format1.parse("12.10.2017"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        create(object);
    }

    public static Collection<MyObject> readAll() {
        return objects.values();
    }

    public static MyObject readById(Integer id) {
        return objects.get(id);
    }

    public static void create(MyObject object) {
        Integer id = 1;
        Set<Integer> ids = objects.keySet();
        if(!ids.isEmpty()) {
            id += Collections.max(ids);
        }
        object.setId(id);
        if(id == 2) {
            int a = 5;
        }
        summaryDuration  += object.getDurationSec();

        objects.put(id, object);

    }

    public static String getSummaryDuration() {
        double days = Math.floor(summaryDuration/86400);
        double seconds =  summaryDuration%86400;
        double hours =  Math.floor(seconds/3600);
        seconds = seconds%3600;
        double minutes = Math.floor(seconds/60);
        seconds = seconds%60;
        return (int)days + " дней," + (int)hours + ":" + (int)minutes + ":" + (int)seconds;
    }

    public static void update(MyObject object) {
        objects.put(object.getId(), object);
    }

    public static void delete(Integer id) {
        summaryDuration  -= Storage.readById(id).getDurationSec();
        objects.remove(id);
    }
}