package com.zxd.blackt.blackt.Utils;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by zhuangxd on 2017/6/27.
 */

public class FromHtmlUtils {
    public static String changejs(String lyric) {
        Spanned result;
        String q;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(lyric, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(lyric);
        }
        q = String.valueOf(result);
        return q;
    }
}
