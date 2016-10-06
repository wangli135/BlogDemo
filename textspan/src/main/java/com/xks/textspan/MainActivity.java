package com.xks.textspan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivty";

    private TextView urlTextView;
    private TextView phoneTextView;

    private String text = "百度"
            + "13007147721"
            + "前景"
            + "背景"
            + "删除线"
            + "下划线"
            + "图片"
            + "H1H2H3H4H5H6"
            + "H1H2H3H4H5H6"
            + "X3X2X1"
            + "字体"
            + "正常字体"
            + "图标";

    private String text2 = "http://www.baidu.com";

    private String expression = "微笑";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlTextView = (TextView) findViewById(R.id.url_tv);

        SpannableString spannableString = new SpannableString(text2);
        spannableString.setSpan(new URLSpan("http://www.taobao.com"), 0, text2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        urlTextView.setText(spannableString);
//        urlTextView.setMovementMethod(LinkMovementMethod.getInstance());

        //SpannableStringBuilder基本用法
        /*SpannableString spannableString = new SpannableString(text);
        URLSpan baiduSpan = new URLSpan("http://www.baidu.com");
        //设置URL链接
        spannableString.setSpan(baiduSpan, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //设置电话号码链接
        URLSpan telSpan=new URLSpan("tel:13007147721");
        spannableString.setSpan(telSpan, 2, 13, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //设置前景色
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#0000FF")), 13, 15, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //设置背景色
        spannableString.setSpan(new BackgroundColorSpan(Color.RED), 15, 17, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //设置删除线
        spannableString.setSpan(new StrikethroughSpan(), 17, 20, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //下划线
        spannableString.setSpan(new UnderlineSpan(), 20, 23, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //设置图片
        spannableString.setSpan(new ImageSpan(this, BitmapFactory.decodeResource(getResources(), R.drawable.smail)), 23, 25, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //设置文本大小

        //绝对尺寸
        spannableString.setSpan(new AbsoluteSizeSpan(100), 25, 27, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(90), 27, 29, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(80), 29, 31, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(70), 31, 33, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(60), 33, 35, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(50), 35, 37, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //相对尺寸
        spannableString.setSpan(new RelativeSizeSpan(6), 37, 39, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(5), 39, 41, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(4), 41, 43, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(3), 43, 45, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(2), 45, 47, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(1), 47, 49, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //设置X方向的改变
        spannableString.setSpan(new ScaleXSpan(3), 49, 51, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ScaleXSpan(2), 51, 53, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ScaleXSpan(1), 53, 55, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //设置字体
        spannableString.setSpan(new TypefaceSpan("monospace"), 55, 57, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        urlTextView.setText(spannableString);
        urlTextView.setMovementMethod(LinkMovementMethod.getInstance());*/

        //改变文字
       /* spannableString.replace(0, 2, "淘宝");
        spannableString.insert(2, "电话号码：", 0, 5);

        //更改效果
        spannableString.removeSpan(baiduSpan);
        spannableString.removeSpan(telSpan);
        spannableString.setSpan(new URLSpan("http://www.taobao.com"), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(telSpan, 7, 18, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        urlTextView.setText(spannableString);
*/

        /*//SpannableString设置多个Span
        SpannableString spannableString = new SpannableString(text);

        //设置URLSpan
        URLSpan baiduSpan = new URLSpan("http://www.baidu.com");
        URLSpan taobaoSpan = new URLSpan("http://www.baidu.com");
        spannableString.setSpan(baiduSpan, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(taobaoSpan, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置前景
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置背景
        spannableString.setSpan(new BackgroundColorSpan(Color.RED), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new BackgroundColorSpan(Color.BLUE), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //删除百度URLSpan
        spannableString.removeSpan(baiduSpan);*/

    }
}
