package com.example.massonsaulniertp1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.Toast;

public class NousFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */

	private static final String ARG_SECTION_NUMBER = "section_number";
	private Button button;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static NousFragment newInstance(int sectionNumber) {
		NousFragment fragment = new NousFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	
	
	public NousFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_nous, container,
				false);
		
		setWidget(rootView);
		setListner();
		return rootView;
	}

	private void setWidget(View view) {
		button = (Button) view.findViewById(R.id.btnGoStm);
	}

	private void setListner() {
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				btnGoStm();
			}
		});

	}

	private void btnGoStm() {
		String url = "http://www.stm.info/";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

}
