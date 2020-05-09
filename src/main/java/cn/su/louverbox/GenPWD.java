package cn.su.louverbox;

/**
 * @author suyulong
 * @date 2019/12/10 10:48 AM
 */
public class GenPWD {

    //用户与密码分割符
    public static final String USER_PASSWD_SPLIT = "++__++";

    static String getEncodePasswd(String userName, String passwd) {
        return EnDeCodeUtil.md5(userName + USER_PASSWD_SPLIT + passwd);
    }

    public static void main(String[] args) {
        System.out.println(getEncodePasswd("yuchunjiang","mV9IZAxj"));
        System.out.println(getEncodePasswd("suyulong","admin"));
    }
}