package com.huangj.autotext;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int FLAG = 0x01;

    private Handler mEventHandler;
    private AutoTextView mAutoTextView;
    private int mLoopCount = 0;
    private ArrayList<String> mStringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // prepare data
        mStringArray = new java.util.ArrayList<String>();
        mStringArray.add("139****9152 获得iPhone SE一部");
        mStringArray.add("159****8139 获得iPad2一部");
        mStringArray.add("134****7602 获得周杰伦演唱会门票一张");
        mStringArray.add("170****7758 获得MacBookPro一台");

        mEventHandler = new EventHandler(this);
        mEventHandler.sendEmptyMessageDelayed(FLAG, 500);

        mAutoTextView = (AutoTextView) findViewById(R.id.id_main_switcher);
        mAutoTextView.setText("公告栏开始工作");
    }

    public static class EventHandler extends Handler {

        private WeakReference<MainActivity> wr;

        public EventHandler(MainActivity r) {
            wr = new WeakReference<MainActivity>(r);
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            MainActivity activity = wr.get();
            switch (msg.what) {
                case 1:
                    int i = activity.mLoopCount % activity.mStringArray.size();
                    activity.mAutoTextView.next();
                    activity.mAutoTextView.setText(activity.mStringArray.get(i));
                    activity.mLoopCount ++;
                    activity.mEventHandler.sendEmptyMessageDelayed(FLAG, 500);
                    break;
            }
        }
    }
}
