package cn.su.daily;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;

/**
 * @author suyulong
 * @date 2019/9/2 3:50 PM
 */
public class HTMLDecode {

    public static void main(String[] args) {
        String html = "《中国银监会关于印发&lt;贷款风险分类指引&gt;的通知》";
        String html2 = "《审计准则第1231号&mdash;针对评估的重大错报风险采取的应对措施》";

        //System.out.println(StringEscapeUtils.unescapeHtml4(html));
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(String.join(",",list));
    }
}
