package cn.su.exam.redPackage;

import java.util.Map;
import java.util.Random;


/**
 * 随机金额拆分红包，二倍均值法 金额 = random（0， 2 * remainAmount/remainCount）
 *
 * @author suyulong
 */
public class RandomUnpack implements IUnpackStrategy {

    @Override
    public Integer unpack(Integer remainAmount, Integer remainCount, String userId,
                          Map<String, Integer> map) {
        Integer result;
        Random random = new Random();
        if (remainCount == 1) {
            result = remainAmount;
        } else {
            result = random.nextInt(2 * remainAmount / remainCount);
            result = (result < CENT) ? CENT : result;
        }
        map.put(userId, result);
        return result;
    }
}
