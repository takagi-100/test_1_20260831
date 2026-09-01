package jp.ac.meijou.android.s251205100;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205100.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.button.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            binding.text.setText(R.string.name);

        });
        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                binding.text.setText(editable.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }
        });
        prefDataStore = PrefDataStore.getInstance(this);
        prefDataStore.getString("name")
                .ifPresent(text -> {
                    if("a".equals(text)){
                        binding.text.setText("Aの画像");
                        binding.imageView.setImageResource(R.drawable.ic_add_home);
                    }else if("b".equals(text)){
                        binding.text.setText("Bの画像");
                        binding.imageView.setImageResource(R.drawable.ic_add_location);
                    }else{
                        binding.text.setText("知らない画像");
                    }
        });



        binding.saveButton.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            if("a".equals(text)){
                binding.imageView.setImageResource(R.drawable.ic_android);
            }else if("b".equals(text)){
                binding.imageView.setImageResource(R.drawable.ic_add_location);
            }else{
                text = "unknown";
            }
            prefDataStore.setString("name", text);
        });


    }
}