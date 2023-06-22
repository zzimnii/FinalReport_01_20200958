package ddwucom.mobile.finalreport.finalreport_01_20200958;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    Movie movie;

    EditText etTitle;
    EditText etActor;
    EditText etDirector;
    EditText etReview;
    ImageView etPoster;
    int img_pos = 0;

    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        movie = (Movie) getIntent().getSerializableExtra("movie");

        if (movie.get_id()==1){
            img_pos = R.mipmap.broker;
        } else if (movie.get_id()==2){
            img_pos = R.mipmap.city2;
        } else if (movie.get_id()==3){
            img_pos = R.mipmap.witch;
        } else if (movie.get_id()==4){
            img_pos = R.mipmap.ds;
        } else if (movie.get_id()==5){
            img_pos = R.mipmap.sp;
        } else{
            img_pos = R.mipmap.ic_launcher;
        }

        etTitle = findViewById(R.id.etTitle);
        etActor = findViewById(R.id.etActor);
        etDirector = findViewById(R.id.etDirector);
        etReview = findViewById(R.id.etReview);
        etPoster = findViewById(R.id.imageView);

        etTitle.setText(movie.getTitle());
        etActor.setText(movie.getActor());
        etDirector.setText(movie.getDirector());
        etReview.setText(movie.getReview());
        etPoster.setImageResource(img_pos);

        movieDBManager = new MovieDBManager(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                if(etTitle.getText().toString().equals(movie.getTitle())) {
                    Toast.makeText(this, "필수입력 항목이 수정되지 않음", Toast.LENGTH_SHORT).show();
                    return;
                }

                movie.setTitle(etTitle.getText().toString());
                movie.setActor(etActor.getText().toString());
                movie.setDirector(etDirector.getText().toString());
                movie.setReview(etReview.getText().toString());

                if (movieDBManager.modifyMovie(movie)) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("movie", movie);
                    setResult(RESULT_OK, resultIntent);
                } else {
                    setResult(RESULT_CANCELED);
                }
                break;

            case R.id.btn_cacel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}

