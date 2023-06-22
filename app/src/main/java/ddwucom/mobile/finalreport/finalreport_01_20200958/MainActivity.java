//과제명: 영화 리뷰앱
//분반: 01분반
//학번: 20200958 성명: 김지민
//제출일: 2022년 6월 23일
package ddwucom.mobile.finalreport.finalreport_01_20200958;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";
    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;

    ArrayList<Movie> myDataList = null;
    MyAdapter myAdapter;
    ListView listView;
    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        movieDBManager = new MovieDBManager(this);

        myDataList = new ArrayList<>();
        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, myDataList);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Movie movie = myDataList.get(position);
                Intent updateIntent = new Intent(MainActivity.this, UpdateActivity.class);
                updateIntent.putExtra("movie", movie);
                startActivityForResult(updateIntent, UPDATE_CODE);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("영화 삭제")
                        .setMessage(myDataList.get(position).getTitle() + " 영화를 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //deleteRecord(pos);
                                boolean result = movieDBManager.removeMovie(myDataList.get(pos).get_id());
                                if(result) {
                                    Toast.makeText(MainActivity.this,"영화 삭제완료!", Toast.LENGTH_SHORT).show();
                                    myDataList.clear();
                                    myDataList.addAll(movieDBManager.getAllMovie());
                                    myAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MainActivity.this,"영화 삭제실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("삭제 취소", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        myDataList.clear();
        myDataList.addAll(movieDBManager.getAllMovie());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch(item.getItemId()) {
            case R.id.addMovie:
                Intent addIntent = new Intent(this, AddActivity.class);
                startActivityForResult(addIntent, REQ_CODE);
                break;
            case R.id.searchMovie:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                break;
            case R.id.info:
                builder.setTitle("개발자 소개")
                        .setMessage("모바일 소프트웨어 01분반\n" + "20200958 김지민\n")
                        .setIcon(R.mipmap.me)
                        .setCancelable(false)
                        .setPositiveButton("닫기",null);
                builder.show();
                break;
            case R.id.finish:
                builder.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("취소",null);
                builder.show();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    String movie = data.getStringExtra("movie");
                    Toast.makeText(this, movie + "추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if( requestCode == UPDATE_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
