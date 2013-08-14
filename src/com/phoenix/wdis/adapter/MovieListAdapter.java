package com.phoenix.wdis.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.phoenix.wdis.R;
import com.phoenix.wdis.model.ShowDetails;

public class MovieListAdapter extends ArrayAdapter<ShowDetails> {
	private ArrayList<ShowDetails> list;
	private Activity activity;
	private int textViewResourceId;

	public MovieListAdapter(Activity a, int textViewResourceId,
			ArrayList<ShowDetails> list) {
		super(a, textViewResourceId, list);

		this.textViewResourceId = textViewResourceId;
		this.list = list;
		this.activity = a;
	}

	public static class ViewHolder {
		public TextView showName;
		public TextView showRating;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		ViewHolder holder;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(textViewResourceId, null);
			holder = new ViewHolder();
			holder.showName = (TextView) v.findViewById(R.id.mov_name);
			holder.showRating = (TextView) v.findViewById(R.id.mov_rating);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}

		final ShowDetails custom = list.get(position);
		if (custom != null) {
			holder.showName.setText(custom.getShowName());
			holder.showRating.setText(custom.getRating());
		}
		return v;
	}
}
