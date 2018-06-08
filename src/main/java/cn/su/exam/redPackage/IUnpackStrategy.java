package cn.su.exam.redPackage;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 拆红包策略
 *
 * @author suyulong
 */
public interface IUnpackStrategy {
    /**
     * 红包的最小值1分
     */
    public static final Integer CENT = 1;

    /**
     * 根据当前的红包金额/红包数量实时计算对应用户抢到的红包金额并保存
     * 返回本次计算的金额
     *
     * @param remainAmount 剩余的红包金额
     * @param remainCount  剩余的红包数量
     * @param userId       对应抢红包的用户
     * @param map          保存红包记录的map
     * @return
     */
    Integer unpack(Integer remainAmount, Integer remainCount,
                  String userId, Map<String, Integer> map);
}
