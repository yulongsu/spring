package cn.su.study.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author suyulong
 * @date 2018/5/10 下午3:58
 */
public class TableMap {
    /**
     * hashtable 无论是key还是value都不能是null hashmap是可以的， HashMap是支持null键和null值的，而HashTable在遇到null时，会抛出NullPointerException异常。
     * 这并不是因为HashTable有什么特殊的实现层面的原因导致不能支持null键和null值， 这仅仅是因为HashMap在实现时对null做了特殊处理，将null的hashCode值定为了0，
     * 从而将其存放在哈希表的第0个bucket中
     * <p>
     * key是无序唯一，可以有一个为null value无序不唯一，可以有多个null
     */
    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        hashtable.put(1, 1);
        //hashtable.put(null,1);
        //hashtable.put(1,null);

        HashMap<Integer, Integer> hashMap = new HashMap<>(4);
        hashMap.put(1, 1);
        hashMap.put(null, 1);
        hashMap.put(1, null);
        hashMap.put(null, null);

        hashMap.forEach((k,v) -> System.out.println(k + "," + v));



    }

}
