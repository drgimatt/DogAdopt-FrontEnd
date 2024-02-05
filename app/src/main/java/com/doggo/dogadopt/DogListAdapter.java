package com.doggo.dogadopt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.doggo.dogadopt.activity.updateActivity;
import com.doggo.dogadopt.activity.viewActivity;
import com.doggo.dogadopt.model.Dog;
import com.escandor.dogadopt.R;

import java.util.List;

public class DogListAdapter extends BaseAdapter {

    private final Context context;
    private final List<Dog> dogs;
    private final String userType;
    private final Long userID;

    public DogListAdapter(Context context, List<Dog> dogs, String userType, Long userID) {
        this.context = context;
        this.dogs = dogs;
        this.userType = userType;
        this.userID = userID;
    }

    @Override
    public int getCount() {
        return dogs.size();
    }

    @Override
    public Object getItem(int position) {
        return dogs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_request_layout, parent, false);
            viewHolder.dNameTxt = convertView.findViewById(R.id.dNameTxt);
            viewHolder.dStatusTxt = convertView.findViewById(R.id.dStatusTxt);
            viewHolder.function = convertView.findViewById(R.id.item_button);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Dog currentDog = dogs.get(position);

        viewHolder.dNameTxt.setText("Name: " + currentDog.getName().replace("\"", ""));
        viewHolder.dStatusTxt.setText("Status: " + currentDog.getStatus().replace("\"", ""));
        viewHolder.picture.setImageBitmap(Bitmap.createScaledBitmap(
                BitmapFactory.decodeByteArray(currentDog.getPhoto(), 0, currentDog.getPhoto().length),
                130,
                130,
                false
        ));

        viewHolder.function.setText("ADMIN".equals(userType) ? "EDIT" : "USER".equals(userType) ? "VIEW" : "");

        viewHolder.function.setOnClickListener(v -> {
            Intent intent = new Intent(context, "ADMIN".equals(userType) ? updateActivity.class : viewActivity.class);
            intent.putExtra("dogID", currentDog.getId());
            intent.putExtra("userID", userID);
            context.startActivity(intent);
        });

        return convertView;
    }

    private static class ViewHolder {
        ImageView picture;
        TextView dNameTxt;
        TextView dStatusTxt;
        Button function;
    }
}
