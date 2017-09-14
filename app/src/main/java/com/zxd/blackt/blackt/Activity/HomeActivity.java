package com.zxd.blackt.blackt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.zxd.blackt.blackt.Entity.Words;
import com.zxd.blackt.blackt.Entrance.Entrances;
import com.zxd.blackt.blackt.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.tv_eng)
    TextView tv_eng;
    @BindView(R.id.tv_cna)
    TextView tv_cna;
    @BindView(R.id.tv_date)
    TextView tvDate;
    private List<SubActionButton> subActionButtonList;
    private FloatingActionMenu actionMenu;
    private FloatingActionButton actionButton;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setFloat();
        setText();
    }

    private void setText() {

        getWords();

        showAnimation();

        getDate();

    }

    private void getDate() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvDate.setText(str);

    }

    private void showAnimation() {
        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);

        alphaAnimation.setDuration(3000);

        animationSet.addAnimation(alphaAnimation);

        tv_eng.startAnimation(animationSet);

        tv_cna.startAnimation(animationSet);

        tvDate.startAnimation(animationSet);
    }

    private void getWords() {

        Entrances entrances = Entrances.getEntrances();
        String appid = entrances.getSHOWAPPID();
        String appsign = entrances.getSHOWSIGN();
        String count = "1";
        entrances.showWords(new Subscriber<Words>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("this is trouble : ", "" + e.toString());
            }

            @Override
            public void onNext(Words words) {
                Words.ShowapiResBodyBean srbb = words.getShowapi_res_body();
                List<Words.ShowapiResBodyBean.DataBean> datas = srbb.getData();
                for (Words.ShowapiResBodyBean.DataBean dbs : datas) {
                    tv_eng.setText(dbs.getEnglish());
                    tv_cna.setText(dbs.getChinese());
                }
            }
        }, appid, appsign, count);

    }

    private void setFloat() {

        //1.主按钮
        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.fab_add);
        actionButton = new FloatingActionButton.Builder(this)
                .setTheme(R.style.MenuLabelsStyle)
                .setContentView(icon)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_CENTER)
                .build();

        //2.条目按钮
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageResource(R.mipmap.fab_settings);
        final SubActionButton button1 = itemBuilder.setContentView(itemIcon1).build();
        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageResource(R.mipmap.fab_news);
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageResource(R.mipmap.fab_note);
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        ImageView itemIcon4 = new ImageView(this);
        itemIcon4.setImageResource(R.mipmap.emo_yinyue);
        SubActionButton button4 = itemBuilder.setContentView(itemIcon4).build();
        ImageView itemIcon5 = new ImageView(this);
        itemIcon5.setImageResource(R.mipmap.fab_info);
        SubActionButton button5 = itemBuilder.setContentView(itemIcon5).build();

        //3.菜单
        actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .addSubActionView(button5)
                .setStartAngle(0)
                .setEndAngle(-180)
                .attachTo(actionButton)
                .build();

        //4.条目点击
        subActionButtonList = new ArrayList<SubActionButton>();
        subActionButtonList.add(button1);
        subActionButtonList.add(button2);
        subActionButtonList.add(button3);
        subActionButtonList.add(button4);
        subActionButtonList.add(button5);

        for (int i = 0; i < subActionButtonList.size(); i++) {
            onClicks(i);
        }

    }

    private void onClicks(final int i) {

        subActionButtonList.get(i).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);

                switch (i) {
                    case 0:
                        intent.putExtra("setting", "setting");
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("news", "news");
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("note", "note");
                        startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra("music", "music");
                        startActivity(intent);
                        break;
                    case 4:
                        intent.putExtra("info", "info");
                        startActivity(intent);
                        break;
                }

                overridePendingTransition(R.anim.tra_in, R.anim.tra_out);
                actionMenu.close(true);

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (flag) {
            Toast.makeText(this, "再次点击即可退出", Toast.LENGTH_SHORT).show();
            flag = false;
        } else {
            super.onBackPressed();
            System.exit(0);
            flag = true;
        }
    }
}
