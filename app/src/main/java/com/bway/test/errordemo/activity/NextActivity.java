package com.bway.test.errordemo.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bway.test.errordemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NextActivity extends AppCompatActivity {

    @BindView(R.id.next_button)
    Button nextButton;
    @BindView(R.id.next_image)
    ImageView nextImage;
    @BindView(R.id.next_button02)
    Button nextButton02;
    private String imagepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        Intent intent = getIntent();
        imagepath = intent.getStringExtra("imagpath");
        Glide.with(this).load(imagepath).into(nextImage);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(nextImage, "rotation", 0f, 360f);
                animator.setDuration(3000);
                animator.start();
                animator = ObjectAnimator.ofFloat(nextButton02, "alpha", 1f, 0f, 1f);
                animator.setDuration(3000);
                animator.start();
            }
        });


    }
}
