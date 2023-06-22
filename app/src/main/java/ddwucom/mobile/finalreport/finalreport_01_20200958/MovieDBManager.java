package ddwucom.mobile.finalreport.finalreport_01_20200958;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MovieDBManager {

    MovieDBHelper movieDBHelper = null;
    Cursor cursor = null;

    public MovieDBManager(Context context) {
        movieDBHelper = new MovieDBHelper(context);
    }

    //DB의 모든 movie를 반환
    public ArrayList<Movie> getAllMovie() {
        ArrayList movieList = new ArrayList();
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MovieDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(MovieDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE));
            String actor = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_ACTOR));
            String director = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR));
            String review = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_REVIEW));
            movieList.add(new Movie(id, title, actor, director, review));
        }

        cursor.close();
        movieDBHelper.close();
        return movieList;
    }
    //새로운 movie 추가
    public boolean addNewMovie(Movie newMovie) {
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(MovieDBHelper.COL_TITLE, newMovie.getTitle());
        value.put(MovieDBHelper.COL_ACTOR, newMovie.getActor());
        value.put(MovieDBHelper.COL_DIRECTOR, newMovie.getDirector());
        value.put(MovieDBHelper.COL_REVIEW, newMovie.getReview());
        long count = db.insert(MovieDBHelper.TABLE_NAME, null, value);

        if(count > 0) return true;
        return false;
    }

    public boolean modifyMovie(Movie movie) {
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(MovieDBHelper.COL_TITLE, movie.getTitle());
        row.put(MovieDBHelper.COL_REVIEW, movie.getReview());
        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(movie.get_id()) };
        int result = sqLiteDatabase.update(MovieDBHelper.TABLE_NAME, row, whereClause, whereArgs);
        movieDBHelper.close();
        if (result > 0) return true;
        return false;
    }

    //id 기준으로 삭제
    public boolean removeMovie(long id) {
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id)};
        int result = db.delete(MovieDBHelper.TABLE_NAME, whereClause, whereArgs);
        movieDBHelper.close();
        if(result > 0) return true;
        return true;
    }

    public String searchMovie(String search){

        SQLiteDatabase db = movieDBHelper.getReadableDatabase();

        String selection = "title=?";
        String[] selectArgs = new String[]{search};
        Cursor cursor =
                db.query(movieDBHelper.TABLE_NAME, null, selection, selectArgs, null, null, null, null);

        String result = "";
        while (cursor.moveToNext()){
            result += "영화 제목: " + cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE)) + "\n 출연 배우: ";
            result += cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_ACTOR)) + "\n 감독: ";
            result += cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR)) + "\n 리뷰: ";
            result += cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_REVIEW));
        }

        cursor.close();
        movieDBHelper.close();
        return result;
    }
    public void close() {
        if (movieDBHelper != null) movieDBHelper.close();
        if (cursor != null) cursor.close();
    };
}