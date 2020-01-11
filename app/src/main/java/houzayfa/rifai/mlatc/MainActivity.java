package houzayfa.rifai.mlatc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendToAPI(View view) {
        input = findViewById(R.id.input);
        result = findViewById(R.id.result);
        String text = input.getText().toString().trim();
        text = text.replaceAll("\n", " ");

        Call<ResponseBody> call = Client.getInstance().getApi().createArticle(text);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    s = s.substring(1, s.length() - 1);
                    s = s.replaceAll("\"", " ");
                    result.setText("التصّنيف الدلالي: " + s);
                    result.setTypeface(null, Typeface.BOLD_ITALIC);
                    result.setTextSize(18);
                    result.setVisibility(View.VISIBLE);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
