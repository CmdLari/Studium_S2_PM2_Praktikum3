package ComicCode.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MapUtils {
    private MapUtils(){};

    public static <K extends Comparable<? super K>> void ppMap(Map<K, Map<utils.YearInterval, List<String>>> aMap) {
        List<K> al = new ArrayList<K>(aMap.keySet());
        Collections.sort(al);
        for (K key : al) {
            System.out.printf("%s->", key);
            for (Map.Entry<utils.YearInterval,List<String>> entry : aMap.get(key).entrySet()) {
                System.out.printf("%s %s ", entry.getKey(),entry.getValue().toString());
            }
            System.out.println();
        }
    }
}
