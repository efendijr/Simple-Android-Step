package com.jevi.timeline;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CostumViewPager viewPager;
    int NUMB = 5;
    ImageButton step_1, step_2, step_3, step_4, step_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        step_1 = (ImageButton) findViewById(R.id.step_1);
        step_2 = (ImageButton) findViewById(R.id.step_2);
        step_3 = (ImageButton) findViewById(R.id.step_3);
        step_4 = (ImageButton) findViewById(R.id.step_4);
        step_5 = (ImageButton) findViewById(R.id.step_5);

        viewPager = (CostumViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new Adapter(MainActivity.this, NUMB));
        viewPager.setCurrentItem(0);
        viewPager.setPagingEnabled(false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        step_1.setImageResource(R.drawable.status_selected);
                        break;
                    case 1:
                        step_2.setImageResource(R.drawable.status_selected);
                        break;
                    case 2:
                        step_3.setImageResource(R.drawable.status_selected);
                        break;
                    case 3:
                        step_4.setImageResource(R.drawable.status_selected);
                        break;
                    case 4:
                        step_5.setImageResource(R.drawable.status_selected);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class Adapter extends PagerAdapter {
        int numb;
        Context mContext;

        public Adapter(Context context, int numb) {
            this.numb = numb;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return numb;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);

            TextView text = (TextView)itemView.findViewById(R.id.text);
            text.setText("Page "+(position+1));

            Button next = (Button)itemView.findViewById(R.id.next);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(position + 1);
                }
            });

            Button previous = (Button)itemView.findViewById(R.id.previous);
            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (position) {
                        case 0:
                            step_1.setImageResource(R.drawable.status_unselected);
                            break;
                        case 1:
                            step_2.setImageResource(R.drawable.status_unselected);
                            break;
                        case 2:
                            step_3.setImageResource(R.drawable.status_unselected);
                            break;
                        case 3:
                            step_4.setImageResource(R.drawable.status_unselected);
                            break;
                        case 4:
                            step_5.setImageResource(R.drawable.status_unselected);
                            break;
                        default:
                            break;
                    }

                    viewPager.setCurrentItem(position - 1);
                }
            });

            if (position == 0){
                previous.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
            }else if(position == 4){
                previous.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
            }

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
