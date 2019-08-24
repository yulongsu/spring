package cn.su.daily;

import java.util.Arrays;
import java.util.List;

/**
 * @author suyulong
 * @date 2019/7/11 9:33 PM
 */
public class T {
    private Long l;
    private String s;

    /**
     * Gets the value of l.
     *
     * @return the value of l
     */
    public Long getL() {
        return l;
    }

    /**
     * Sets the l.
     *
     * <p>You can use getL() to get the value of l</p>
     *
     * @param l l
     */
    public void setL(Long l) {
        this.l = l;
    }

    /**
     * Gets the value of s.
     *
     * @return the value of s
     */
    public String getS() {
        return s;
    }

    /**
     * Sets the s.
     *
     * <p>You can use getS() to get the value of s</p>
     *
     * @param s s
     */
    public void setS(String s) {
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof T)) {
            return false;
        }
        T t = (T)o;
        return t.l==l
            && t.s==s
            ;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (l==null?0:l.hashCode());
        result = 31 * result + (s==null?0:s.hashCode());
        return result;
    }

    public static void main(String[] args) {
        T t1 = new T();
        t1.setS("银监");
        T t2 = new T();
        t2.setS("银监");
        if(t1.equals(t2)){
            System.out.println("==");
        }
        System.out.println(t1.hashCode());

        List<String> list = Arrays.asList("1","2");
        System.out.println(list.toString());

    }
}
