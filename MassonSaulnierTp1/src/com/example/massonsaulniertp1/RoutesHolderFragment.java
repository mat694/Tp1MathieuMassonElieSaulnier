package com.example.massonsaulniertp1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class RoutesHolderFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static RoutesHolderFragment newInstance(int sectionNumber) {
		RoutesHolderFragment fragment = new RoutesHolderFragment();

		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public RoutesHolderFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView =   inflater.inflate(
				R.layout.fragment_route, container, false);
		Toast t = Toast.makeText(getActivity(), "Tarrif", Toast.LENGTH_SHORT);
		t.show();
		return rootView;
	}
}