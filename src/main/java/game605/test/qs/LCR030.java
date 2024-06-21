package game605.test.qs;

import java.util.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className LCR030
 * @description TODO
 * @since 2024/6/20 14:22
 */
public class LCR030 {

    // key值， val存下标
    Set<Integer> set;
    // random
    Random r;

    /** Initialize your data structure here. */
    public LCR030() {
        // 我觉得就是list+hashmap
        set = new HashSet<>();
        r = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(set.contains(val)) return false;
        set.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!set.contains(val)) return false;
        set.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Integer[] keys = set.toArray(new Integer[0]);
        return keys[r.nextInt(keys.length)];
    }

}
