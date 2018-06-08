package cn.su.exam.redPackage;

/**
 * 固定红包
 * 备注：重点在发红包和抢红包上，不同类型的红包属性就不一一枚举了
 *
 * @author suyulong
 */
public class FixPackage extends AbstractPackage{

    /**
     * 初始化
     *
     * @param type         红包类型
     * @param totalAmount  红包金额
     * @param packageCount 红包个数
     * @param remark       红包留言
     */
    FixPackage(PackageTypeEnum type, Integer totalAmount, Integer packageCount, String remark) {
        super(type, totalAmount, packageCount, remark);
    }
}
