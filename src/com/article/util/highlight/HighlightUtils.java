package com.article.util.highlight;

import java.util.Arrays;

/**
 * Created by dell on 14-1-1.
 */
public class HighlightUtils {
    /**
     * 对标题和内容的检索关键字进行高亮
     *
     * @param content 内容
     * @param key 关键字
     * @param substr 是否截取content
     * @return
     */
    public static String highlight(String content, String key, Boolean substr, int length) {
        KeywordFilterBuilder builder = new KeywordFilterBuilder();
        builder.setKeywords(Arrays.asList(key));
        builder.setSkipChars(Arrays.asList('*', ' '));
        KeywordFilter filter = builder.build();

        // 对content进行处理
        if(substr) {
            int len = content.length();

            if(len > length) {
                if(content.indexOf(key) != -1) {
                    if(len > content.indexOf(key) + length) {
                        content = "..." + content.substring(content.indexOf(key), content.indexOf(key) + length) + "...";
                    } else {
                        content = "..." + content.substring(content.indexOf(key), len);
                    }
                } else {
                    content = content.substring(0, length) + "...";
                }
            }
        }

        key = filter.replace(content, new ReplaceStrategy(){
            public String replaceWith(String keywords) {
                return "<font color='red'>" + keywords + "</font>";
            }
        });

        return key;
    }

    /**
     *
     * @param content
     * @param key
     * @return
     */
    public static String highlight(String content, String key) {
        return highlight(content, key, false, 0);
    }

    /**
     * 删除input字符串中的html格式
     *
     * @param input
     * @return
     */
    public static String trimHtml(String input) {
        if (input == null || input.trim().equals("")) {
            return "";
        }

        // 去掉所有html元素,
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                "<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");

        return str;
    }
}
