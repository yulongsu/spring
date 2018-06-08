package cn.su.exam.redPackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 红包抽象类
 *
 * @author suyulong
 */
public abstract class AbstractPackage implements Serializable {
    /**
     * 红包的ID，唯一标示
     */
    private String packageId;

    /**
     * 群的唯一标示
     */
    private String groupId;

    /**
     * 红包的总金额,单位是分
     */
    private final Integer totalAmount;

    /**
     * 红包个数
     */
    private final Integer packageCount;

    /**
     * 红包留言
     */
    private final String remark;

    /**
     * 红包剩余金额,单位是分
     */
    private Integer remainAmount;

    /**
     * 红包剩余个数
     */
    private Integer remainCount;

    /**
     * 顺序记录抢红包用户名和对应金额 <key: userId, value : money>
     */
    private LinkedHashMap<String, Integer> redRecordMap;

    /**
     * 拆红包策略
     */
    private IUnpackStrategy unpackStrategy;

    /**
     * 初始化
     *
     * @param type         红包类型
     * @param totalAmount  红包金额
     * @param packageCount 红包个数
     * @param remark       红包留言
     */
    AbstractPackage(PackageTypeEnum type, Integer totalAmount, Integer packageCount, String remark) {
        this.totalAmount = totalAmount;
        this.remainAmount = totalAmount;
        this.packageCount = packageCount;
        this.remainCount = packageCount;
        this.remark = remark;
        redRecordMap = new LinkedHashMap<>(packageCount);

        if (type.equals(PackageTypeEnum.FIX_PACKAGE)) {
            unpackStrategy = new FixUnpack();
        } else if (type.equals(PackageTypeEnum.RANDOM_PACKAGE)) {
            unpackStrategy = new RandomUnpack();
        }
    }

    /**
     * 发红包
     */
    public void give() {
        //向后台发送红包请求（群标示,红包id,红包金额,红包个数)。具体细节详见时序图
        send(groupId, packageId, totalAmount, packageCount);
    }

    //mock
    private void send(String groupId,String packageId,Integer amount, Integer count){}

    /**
     * 抢红包 固定金额/随机金额
     */
    public void grab(String userId) {
        if (redRecordMap.containsKey(userId)) {
            //每个用户只能抢一次
            return;
        }
        synchronized (remainCount) {
            Integer money = unpackStrategy.unpack(remainAmount, remainCount, userId, redRecordMap);
            remainCount--;
            remainAmount -= money;
        }
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Integer getPackageCount() {
        return packageCount;
    }

    public String getRemark() {
        return remark;
    }

    public Integer getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(Integer remainAmount) {
        this.remainAmount = remainAmount;
    }

    public Integer getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(Integer remainCount) {
        this.remainCount = remainCount;
    }

    public LinkedHashMap<String, Integer> getRedRecordMap() {
        return redRecordMap;
    }

    public void setRedRecordMap(LinkedHashMap<String, Integer> redRecordMap) {
        this.redRecordMap = redRecordMap;
    }
}
