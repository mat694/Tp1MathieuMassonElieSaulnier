package multipleListView;

import java.util.ArrayList;
import java.util.HashMap;

import static multipleListView.Constant.NUMEROLIGNE_COLUMN;
import static multipleListView.Constant.PREMIEREDIRECTION_COLUMN;
import static multipleListView.Constant.DEUXIEMEDIRECTION_COLUMN;
import static multipleListView.Constant.PREMIERIDCACHER;
import static multipleListView.Constant.DEUXIEMEIDCACHER;

import com.example.massonsaulniertp1.LigneActivity;
import com.example.massonsaulniertp1.R;
import com.example.massonsaulniertp1.RouteActivity;
import com.example.massonsaulniertp1.RoutesFragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class listviewAdapter extends BaseAdapter {
	public ArrayList<HashMap> list;
	RoutesFragment activity;
	LayoutInflater inflater;

	public listviewAdapter(RoutesFragment routesFragment,
			ArrayList<HashMap> list, LayoutInflater inflater) {
		super();
		this.activity = routesFragment;
		this.list = list;
		this.inflater = inflater;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	private class ViewHolder {
		TextView NumeroLigne;
		TextView PremierDirection;
		TextView DeuxiemeDirection;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listview_row, null);
			holder = new ViewHolder();
			holder.NumeroLigne = (TextView) convertView
					.findViewById(R.id.NumeroLigne);
			holder.PremierDirection = (Button) convertView
					.findViewById(R.id.PremierDirection);

			holder.DeuxiemeDirection = (Button) convertView
					.findViewById(R.id.DeuxiemeDirection);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		HashMap map = list.get(position);
		holder.NumeroLigne.setText((CharSequence) map.get(NUMEROLIGNE_COLUMN));
		holder.PremierDirection.setText((CharSequence) map
				.get(PREMIEREDIRECTION_COLUMN));
		holder.DeuxiemeDirection.setText((CharSequence) map
				.get(DEUXIEMEDIRECTION_COLUMN));

		final String ID1 = (String) map.get(PREMIERIDCACHER);
		holder.PremierDirection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RouteActivity.class);
				intent.putExtra("IDTrip", ID1.toString());
				v.getContext().startActivity(intent);

			}
		});

		final String ID2 = (String) map.get(DEUXIEMEIDCACHER);
		holder.DeuxiemeDirection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RouteActivity.class);
				intent.putExtra("IDTrip", ID2.toString());
				v.getContext().startActivity(intent);

			}
		});

		return convertView;
	}

}

/*
 * CLASSE ET METHODE TROUVER Lien :
 * http://www.technotalkative.com/android-multi-column-listview/ Date:16/10/2014
 */
