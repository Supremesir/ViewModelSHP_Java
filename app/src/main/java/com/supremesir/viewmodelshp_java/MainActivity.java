package com.supremesir.viewmodelshp_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.supremesir.viewmodelshp_java.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MyViewModel myViewModel;
    ActivityMainBinding binding;

    @Override
    protected void onPause() {
        super.onPause();
        // onStop和onDestroy有可能会被跳过
        // 若手机系统崩溃，断电等，数据仍然会丢失
        myViewModel.save();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        myViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(getApplication(), this))
                        .get(MyViewModel.class);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(this);
    }
}
