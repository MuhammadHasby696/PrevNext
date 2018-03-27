package com.utsman.kucingapes.bismillahcontohviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    Button btnPrev, btnNext;

    /*asumsikan page default di halaman pertama*/
    int page = 0; /*nilai 0 sama dengan satu*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrev = findViewById(R.id.prev);
        btnNext = findViewById(R.id.next);

        /*seting btn prev di awal dengan visible agar btn prev menghilang/disable*/
        btnPrev.setVisibility(View.GONE);

        /*sekarang pasang adapter ke viewpager*/
        setupViewPager();

        /*buat metode page change*/
        pageChange();
    }

    private void pageChange() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
                switch (position) {
                    case 0: /*titik dua*/
                        btnPrev.setVisibility(View.GONE); /*di awal btnprev ngilang/ gak bisa dipencet*/
                        btnNext.setVisibility(View.VISIBLE);
                        break;
                    case 1: /*halaman selanjutnya*/
                        btnPrev.setVisibility(View.VISIBLE); /*halaman berikutnya btn prev nongol/ bisa dipencet*/
                        btnNext.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        btnPrev.setVisibility(View.VISIBLE);
                        btnNext.setVisibility(View.GONE); /*halaman terkahir btnNext yg ngilang*/
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupViewPager() {
        adapter = new Adapter(this);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
    }

    public void prev(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
    }

    public void next(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
    }

    private class Adapter extends PagerAdapter{
        Context context;
        LayoutInflater inflater;

        public Adapter(Context context) {
            this.context = context;
        }

        // list img
        int[] list_img = {
            R.drawable.kaget,
            R.drawable.molor,
            R.drawable.ngantuk
        };

        // list judul
        int[] list_judul = {
                R.string.judul_1,
                R.string.judul_2,
                R.string.judul_3
        };

        // list deskripsi
        int[] list_desk = {
                R.string.desk_1,
                R.string.desk_2,
                R.string.desk_3
        };

        // list color bg
        int[] list_bg = {
                getResources().getColor(R.color.merah),
                getResources().getColor(R.color.kuning),
                getResources().getColor(R.color.hujau)
        };

        @Override
        public int getCount() {
            return list_judul.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_layout,container,false);
            LinearLayout linearLayout = view.findViewById(R.id.item_layout);
            ImageView imageView = view.findViewById(R.id.img);
            TextView judul = view.findViewById(R.id.judul);
            TextView desk = view.findViewById(R.id.deskripsi);

            linearLayout.setBackgroundColor(list_bg[position]);
            imageView.setImageResource(list_img[position]);
            judul.setText(list_judul[position]);
            desk.setText(list_desk[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }
    }
}
