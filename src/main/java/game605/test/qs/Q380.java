package game605.test.qs;

import org.junit.Test;

import java.util.*;

/**
 * O
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/17 12:04
 **/
public class Q380 {

    Set<Integer> set;
    List<Integer> list;
    Random random;

    public Q380() {
        random = new Random();
        set = new HashSet<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if(set.add(val)){
            list.add(val);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if(set.remove(val)){
            list.remove(new Integer(val));
            return true;
        }
        return false;
    }

    public int getRandom() {
        int randomNum = random.nextInt(set.size());
        return list.get(randomNum);
    }

    @Test
    public void t1(){
        Q380 q380 = new Q380();
        q380.insert(2);
        q380.insert(3);
        q380.remove(4);
        q380.remove(3);
        System.out.println(q380.getRandom());
    }

}
