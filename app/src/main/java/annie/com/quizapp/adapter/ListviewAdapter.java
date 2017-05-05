package annie.com.quizapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import annie.com.quizapp.R;
import annie.com.quizapp.model.User;

/**
 * Created by Annie on 28/04/2017.
 */

public class ListviewAdapter extends ArrayAdapter {

    private Context context;
    private List<User> userList;

    public ListviewAdapter(Context ctx, List<User> userList) {
        super(ctx, R.layout.listview_layout, userList);
        this.context = ctx;
        this.userList = userList;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_layout, null);

        }

        TextView username = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView userScore = (TextView) convertView.findViewById(R.id.tvScore);
        ImageView img = (ImageView) convertView.findViewById(R.id.imageViewLanguage);

        User user = userList.get(position);
        username.setText(user.getUsername());
        userScore.setText(user.getScore());

        if (user.getLanguage() == "Romanian") {
            img.setImageResource(R.drawable.ro_flag);
        } else {
            img.setImageResource(R.drawable.uk_flag);
        }


        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_layout, null);

        }

        TextView username = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView userScore = (TextView) convertView.findViewById(R.id.tvScore);
        ImageView img = (ImageView) convertView.findViewById(R.id.imageViewLanguage);

        User user = userList.get(position);
        username.setText(user.getUsername());
        userScore.setText(user.getScore());

        if (user.getLanguage().trim().equals("Romanian")) {
            img.setImageResource(R.drawable.ro_flag);
        } else {
            img.setImageResource(R.drawable.uk_flag);
        }

        return convertView;
    }


}