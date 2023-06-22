package ddwucom.mobile.finalreport.finalreport_01_20200958;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etActor;
    EditText etDirector;
    EditText etReview;

    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = findViewById(R.id.etTitle);
        etActor = findViewById(R.id.etActor);
        etDirector = findViewById(R.id.etDirector);
        etReview = findViewById(R.id.etReview);

        movieDBManager = new MovieDBManager(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                if(etTitle.getText().toString().equals("")) {
                    Toast.makeText(this, "필수입력 항목이 비어 있음", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean result = movieDBManager.addNewMovie(
                        new Movie(etTitle.getText().toString(), etActor.getText().toString(),
                                etDirector.getText().toString(), etReview.getText().toString()));

                if(result) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("movie", etTitle.getText().toString());
                    resultIntent.putExtra("movie", etActor.getText().toString());
                    resultIntent.putExtra("movie", etDirector.getText().toString());
                    resultIntent.putExtra("movie", etReview.getText().toString());

                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(this,"새로운 영화 추가 실패!", Toast.LENGTH_SHORT).show();;
                }
                break;
            case R.id.btnCancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}

