package cn.su.exam.redPackage;

import cn.su.exam.BizException;

import java.util.Map;

/**
 * 固定金额均分红包
 *
 * @author suyulong
 */
public class FixUnpack implements IUnpackStrategy {

    @Override
    public Integer unpack(Integer remainAmount, Integer remainCount,
                             String userId, Map<String, Integer> map) {
        //均值
        Integer avg = remainAmount / remainCount;
        if(avg < CENT){
            throw new BizException("FAIL","均值小于最小值1分");
        }

        Integer result;
        if (remainCount == 1) {
            result = remainAmount;
        } else {
            result = avg;
        }
        map.put(userId, result);
        return result;
    }
}
