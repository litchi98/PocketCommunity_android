package com.litchi.pocketcommunity.featrue.noticedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.data.bean.Notice;
import com.litchi.pocketcommunity.util.UrlUtils;

import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoticeDetailActivity extends AppCompatActivity {

    private CircleImageView avatar;
    private TextView name;
    private TextView title;
    private TextView publishDate;
    private TextView content;

    public static void startAction(Context context, Parcelable parcelable){
        Intent intent = new Intent(context, NoticeDetailActivity.class);
        intent.putExtra("notice", parcelable);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
         init();
         registerListener();
         fillData();
    }

    private void registerListener() {
        View view = findViewById(R.id.notice_detail_back);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fillData() {
        Notice notice = (Notice) getIntent().getParcelableExtra("notice");
        Glide.with(this).load(UrlUtils.url(UrlUtils.GET_IMAGE + "/" + notice.getAvatarId())).into(avatar);
        name.setText(notice.getName());
        title.setText(notice.getTitle());
        publishDate.setText(new SimpleDateFormat("MM-dd HH:mm").format(notice.getPublishDate()));
        content.setText(notice.getContent());
    }

    private void init() {
        avatar = (CircleImageView) findViewById(R.id.notice_detail_avatar);
        name = (TextView) findViewById(R.id.notice_detail_name);
        title = (TextView) findViewById(R.id.notice_detail_title);
        publishDate = (TextView) findViewById(R.id.notice_detail_publish_date);
        content = (TextView) findViewById(R.id.notice_detail_content);
    }
}
