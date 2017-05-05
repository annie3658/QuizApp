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

import annie.com.quizapp.R;

/**
 * Created by Annie on 28/04/2017.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context c;
    private String[] flags;
    private int[] images;

    public SpinnerAdapter(Context ctx, String[] flags, int[] images) {
        super(ctx, R.layout.spinner_layout, flags);
        this.c = ctx;
        this.flags = flags;
        this.images = images;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.spinner_layout, null);

        }
        TextView countryTextView = (TextView) convertView.findViewById(R.id.tvCountry);
        ImageView img = (ImageView) convertView.findViewById(R.id.imageViewSpinner);

        countryTextView.setText(flags[position]);
        img.setImageResource(images[position]);

        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.spinner_layout, null);

        }
        TextView countryTextView = (TextView) convertView.findViewById(R.id.tvCountry);
        ImageView img = (ImageView) convertView.findViewById(R.id.imageViewSpinner);

        countryTextView.setText(flags[position]);
        img.setImageResource(images[position]);

        return convertView;
    }

}

