package cn.su.exam.redPackage;

/**
 * 红包工厂
 *
 * @author suyulong
 */
public interface IPackageFactory {
    /**
     * 创建红包
     *
     * @param type         红包类型
     * @param totalAmount  红包总金额
     * @param packageCount 红包个数
     * @param remark       红包留言
     * @return
     */
    AbstractPackage createPackage(PackageTypeEnum type, Integer totalAmount, Integer packageCount,
                                  String remark);
}
