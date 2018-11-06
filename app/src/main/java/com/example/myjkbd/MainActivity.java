package com.example.myjkbd;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myjkbd.adapter.TopicAdapter;
import com.example.myjkbd.customView.ReaderViewPager;
import com.example.myjkbd.customView.SlidingUpPanelLayout;
import com.example.myjkbd.fragment.ReadFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ReaderViewPager mReaderViewPager;
    private ImageView mShadowView;
    private Button mBtnPro;
    private Button mBtnNext;
    private TextView mBtnLoadAnwer;
    private RecyclerView mList;
    private LinearLayout mDragView;
    private SlidingUpPanelLayout mSlidingLayout;
    private ArrayList<String> mList1;
    private TopicAdapter mTopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mList1 = initData();

        initSlidingUoPanel();

        initList();

        initReadViewPager();
    }

    private int prePosition;
    private int curPosition;

    private void initList() {
        GridLayoutManager manager = new GridLayoutManager(this, 6);
        mTopAdapter = new TopicAdapter(this);
        mList.setLayoutManager(manager);
        mList.setAdapter(mTopAdapter);

        mTopAdapter.setOnTopicClickListener(new TopicAdapter.OnTopicClickListener() {
            @Override
            public void onClick(TopicAdapter.TopicVieHolder holder, int position) {
                curPosition = position;
                if (mSlidingLayout != null && (mSlidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
                    mSlidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                }
                mReaderViewPager.setCurrentItem(position);
                mTopAdapter.noitfyCurPosition(curPosition);
                mTopAdapter.noifyPrePostion(prePosition);
                prePosition = curPosition;

            }
        });
    }

    private int prePosition2;
    private int curPosition2;

    private void initReadViewPager() {
        mReaderViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return ReadFragment.newInstance(mList1.get(position));
            }

            @Override
            public int getCount() {
                return mList1.size();
            }
        });
        mReaderViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mShadowView.setTranslationX(mReaderViewPager.getWidth() - positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                curPosition2 = position;
                mTopAdapter.noitfyCurPosition(curPosition2);
                mTopAdapter.noifyPrePostion(prePosition2);
                prePosition2 = curPosition2;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initSlidingUoPanel() {
        int height = getWindowManager().getDefaultDisplay().getHeight();
        SlidingUpPanelLayout.LayoutParams layoutParams = new SlidingUpPanelLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (height * 0.8f));
        mDragView.setLayoutParams(layoutParams);

    }

    private ArrayList<String> initData() {
        ArrayList<String> list = new ArrayList<>();
        int a = 200;
        for (int i = 0; i < a; i++) {
            list.add("i" + i);
        }
        return list;
    }

    private void initView() {
        mReaderViewPager = (ReaderViewPager) findViewById(R.id.readerViewPager);
        mShadowView = (ImageView) findViewById(R.id.shadowView);
        mBtnPro = (Button) findViewById(R.id.btn_pro);
        mBtnNext = (Button) findViewById(R.id.btn_next);
        mBtnLoadAnwer = (TextView) findViewById(R.id.btn_load_anwer);
        mList = (RecyclerView) findViewById(R.id.list);
        mDragView = (LinearLayout) findViewById(R.id.dragView);
        mSlidingLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);

        mBtnPro.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        mDragView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pro:

                break;
            case R.id.btn_next:

                break;
            case R.id.dragView:

                break;
        }
    }
}
