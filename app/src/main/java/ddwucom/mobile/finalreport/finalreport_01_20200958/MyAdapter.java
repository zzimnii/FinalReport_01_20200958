package ddwucom.mobile.finalreport.finalreport_01_20200958;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Movie> myDataList;
    private LayoutInflater layoutInflater;
    int img_pos = 0;

    public MyAdapter(Context context, int layout, ArrayList<Movie> myDataList) {
        this.context = context;
        this.layout = layout;
        this.myDataList = myDataList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return myDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return myDataList.get(i).get_id();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {

        final int position = pos;
        ViewHolder holder;

        if (myDataList.get(pos).get_id()==1){
            img_pos = R.mipmap.broker;
        } else if (myDataList.get(pos).get_id()==2){
            img_pos = R.mipmap.city2;
        } else if (myDataList.get(pos).get_id()==3){
            img_pos = R.mipmap.witch;
        } else if (myDataList.get(pos).get_id()==4){
            img_pos = R.mipmap.ds;
        } else if (myDataList.get(pos).get_id()==5){
            img_pos = R.mipmap.sp;
        } else{
            img_pos = R.mipmap.ic_launcher;
        }

        if (convertView == null) {
            convertView = layoutInflater.inflate(layout, viewGroup, false);

            holder = new ViewHolder();
            holder.mvPoster = convertView.findViewById(R.id.mvPoster);
            holder.mvTitle = (TextView) convertView.findViewById(R.id.mvTitle);
            holder.mvActor = (TextView) convertView.findViewById(R.id.mvActor);
            holder.mvDirector = (TextView) convertView.findViewById(R.id.mvDirector);
            holder.mvReview = (TextView) convertView.findViewById(R.id.mvReview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mvPoster.setImageResource(img_pos);
        holder.mvTitle.setText(myDataList.get(position).getTitle());
        holder.mvActor.setText(myDataList.get(position).getActor());
        holder.mvDirector.setText(myDataList.get(position).getDirector());
        holder.mvReview.setText(myDataList.get(position).getReview());

        return convertView;
    }

    static class ViewHolder {
        ImageView mvPoster;
        TextView mvTitle;
        TextView mvActor;
        TextView mvDirector;
        TextView mvReview;
    }
}