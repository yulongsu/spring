package cn.su.study.proxy;

/**
 * @author suyulong
 * @date 2018/5/22 15:59
 */
public class CountServiceImpl implements CountService {
    private int count;

    @Override
    public int count() {
        return count++;
    }
}
