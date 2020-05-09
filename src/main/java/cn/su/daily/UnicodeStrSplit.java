package cn.su.daily;

/**
 * @author suyulong
 * @date 2020/1/19 7:08 PM
 */
public class UnicodeStrSplit {
    public static void main(String[] args) {
        String str
            = "中国人民银行有关部门负责人16日表示，2019年12"
            + "月，金融数据整体向好，结构优化。央行将继续采取货币政策操作，保障春节前流动性平稳。此外，未来进一步下调存款准备金率存在一定空间，但空间有限。小微贷款增速加快数据显示，2019年12月末，广义货币（M2"
            + "）余额198.65万亿元，同比增长8.7%，增速比上月末和上年同期分别高0.5个和0.6个百分点。2019年社会融资规模增量累计为25.58万亿元，比上年多3"
            + ".08万亿元。2019年12月社会融资规模增量为2.1万亿元，比上年同期多1719亿元。2019年末社会融资规模存量为251.31万亿元，同比增长10"
            + ".7%。央行调查统计司司长阮健弘表示，2019年12月，金融数据整体向好，结构在优化，企业中长期贷款企稳回升。金融机构对小微企业的贷款增速持续加快，金融体系对实体经济的支持力度不断增强。";
        System.out.println(str.length());
        System.out.println(str.substring(0, str.length()));
        System.out.println(str.substring(0, Math.min(str.length(), 255)));
    }
}
