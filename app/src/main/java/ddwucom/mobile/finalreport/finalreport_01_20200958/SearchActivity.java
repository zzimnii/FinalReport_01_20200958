package ddwucom.mobile.finalreport.finalreport_01_20200958;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    MovieDBManager movieDBManager;
    EditText shTitle;
    TextView searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        shTitle = findViewById(R.id.shTitle);
        searchResult = findViewById(R.id.searchResult);

        movieDBManager = new MovieDBManager(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_search:
                if (shTitle.getText().toString().equals("")){
                    Toast.makeText(this, "제목을 입력하지 않았습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                String result = movieDBManager.searchMovie(shTitle.getText().toString());
                searchResult.setText(result);
                break;
            case R.id.btn_cancel:
                Toast.makeText(this, "검색 취소", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }
}