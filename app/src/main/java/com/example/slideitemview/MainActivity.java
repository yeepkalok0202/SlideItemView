package com.example.slideitemview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<SlideItem> slideItemArrayList=new ArrayList<>();
    ViewPager2 viewPager2;

    //auto slide
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2= findViewById(R.id.viewPager);
        for(int i=0;i<5;i++){
            int resourceId = getResources().getIdentifier("pic" + (i + 1), "drawable", getPackageName());
            slideItemArrayList.add(new SlideItem(resourceId));
        }

        viewPager2.setAdapter(new Slide_adapter(slideItemArrayList,viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        // tablayout
        TabLayout tabLayout=findViewById(R.id.tabDots);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        }).attach();
        //end
        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1- Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);

            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //int tabPosition= position% slideItemArrayList.size();
                tabLayout.selectTab(tabLayout.getTabAt(position));
                handler.removeCallbacks(slideRunnable);
                handler.postDelayed(slideRunnable,3000);
            }
        });


    }

    private Runnable slideRunnable= new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(slideRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(slideRunnable,3000);
    }
}