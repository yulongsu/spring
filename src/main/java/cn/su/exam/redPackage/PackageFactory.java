package cn.su.exam.redPackage;

/**
 * 简单工厂类实现方法
 *
 * @author suyulong
 */
public class PackageFactory implements IPackageFactory {
    @Override
    public AbstractPackage createPackage(PackageTypeEnum type, Integer totalAmount, Integer packageCount,
                                         String remark) {
        switch (type) {
            case FIX_PACKAGE:
                return new FixPackage(type, totalAmount, packageCount, remark);
            case RANDOM_PACKAGE:
                return new RandomPackage(type, totalAmount, packageCount, remark);
            default:
                return new RandomPackage(type, totalAmount, packageCount, remark);
        }
    }
}
