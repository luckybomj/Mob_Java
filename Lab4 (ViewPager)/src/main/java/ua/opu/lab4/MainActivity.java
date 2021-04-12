package ua.opu.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import ua.opu.lab4.fragments.Fragment1;
import ua.opu.lab4.fragments.Fragment2;
import ua.opu.lab4.fragments.Fragment3;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        viewPager2 = findViewById(R.id.container);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        Adapter adapter = new Adapter(this);
        adapter.addFragment(new Fragment1());
        adapter.addFragment(new Fragment2());
        adapter.addFragment(new Fragment3());

        viewPager2.setAdapter(adapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_item_1:
                    viewPager2.setCurrentItem(0, true);
                    break;
                case R.id.menu_item_2:
                    viewPager2.setCurrentItem(1, true);
                    break;
                case R.id.menu_item_3:
                    viewPager2.setCurrentItem(2, true);
                    break;
            }
            return true;
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.menu_item_1);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.menu_item_2);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.menu_item_3);
                        break;
                }
            }
        });
    }

    private class Adapter extends FragmentStateAdapter {

        private List<Fragment> list =  new ArrayList<>();

        public Adapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        public void addFragment(Fragment fragment) {
            list.add(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return list.get(position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}