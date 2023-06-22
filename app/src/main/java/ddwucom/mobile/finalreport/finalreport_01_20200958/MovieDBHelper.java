package ddwucom.mobile.finalreport.finalreport_01_20200958;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MovieDBHelper extends SQLiteOpenHelper {

    final static String TAG = "MovieDBHelper";
    final static String DB_NAME = "movies.db";

    public final static String TABLE_NAME = "movie_table";
    public final static String COL_ID = "_id";
    public final static String COL_TITLE = "title";
    public final static String COL_ACTOR = "actor";
    public final static String COL_DIRECTOR = "director";
    public final static String COL_REVIEW = "review";

    public MovieDBHelper(Context context) { super(context, DB_NAME, null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + " ( " + COL_ID + " integer primary key autoincrement, "
                + COL_TITLE + " text, " + COL_ACTOR + " text, " +
                COL_DIRECTOR + " text, " + COL_REVIEW + " text )";
        Log.d(TAG, sql);
        db.execSQL(sql);
        insertSample(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void insertSample(SQLiteDatabase db){
        db.execSQL("insert into " + TABLE_NAME + " values (null, '브로커', '송강호, 강동원, 배두나', '고레에다 히로카즈', '재밌어요');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '범죄도시2', '마동석, 손석구, 최귀화, 박지환', '이상용', '1보다 2가 더 재밌어요');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '마녀2', '신시아, 박은빈, 서은수, 진구', '박훈정', '시간 가는줄 모르고 봤어요');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '닥터스트레인지', '베네딕트 컴버배치, 레이첼 맥아담스', '스콧데릭슨', '추천추천!!');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '스파이더맨', '톰홀랜드, 젠데이아', '존 왓츠', '스파이더맨 짱!');");
    }
}
