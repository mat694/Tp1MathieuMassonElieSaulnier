package com.example.massonsaulniertp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TarifHolderFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	Button btnRoute;
	EditText ligne;
	View rootView;
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static TarifHolderFragment newInstance(int sectionNumber) {
		TarifHolderFragment fragment = new TarifHolderFragment();

		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);

		return fragment;
	}

	public TarifHolderFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_tarrif, container, false);

		setWidget();
		setListener();
		return rootView;
	}

	private void setWidget() {
		btnRoute = (Button) rootView.findViewById(R.id.btnNumeroLigne);
		ligne = (EditText) rootView.findViewById(R.id.editTextNumeroLigne);

	}

	private void setListener() {

		btnRoute.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!ligne.getText().toString().equals("")) {
					Toast.makeText(v.getContext(), "PENIS MEOEW",
							Toast.LENGTH_SHORT).show();
					
					Intent intent = new Intent(rootView.getContext(),
							LigneActivity.class);
					intent.putExtra("NumeroLigne", ligne.getText().toString());
					startActivity(intent);
					

				}

			}
		});

	}
}
