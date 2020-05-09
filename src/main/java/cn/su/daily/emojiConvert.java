package cn.su.daily;

import com.github.binarywang.java.emoji.EmojiConverter;

/**
 * @author suyulong
 * @date 2020/3/3 2:47 PM
 */
public class emojiConvert {
    private EmojiConverter emojiConverter = EmojiConverter.getInstance();

    public static void main(String[] args) {
        String str = "  An 😀😃awesome 😃😃string with a few 😉😃emojis!\uD83D\uDC84\uD83C\uDF77\uD83E\uDD71\uD83D"
            + "\uDE18\uD83D\uDE07\uD83D\uDE05\uD83D\uDE00\uD83D\uDE31";
        System.out.println(str);
        System.out.println("EmojiConverterTest.testToHtml()=====>");
        System.out.println(escape(str));
    }

    public static String escape(String src){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < src.length(); i++){
            sb.append("&#");
            sb.append(Integer.toString(src.codePointAt(i)));
            sb.append(";");
        }
        return sb.toString();
    }
}
