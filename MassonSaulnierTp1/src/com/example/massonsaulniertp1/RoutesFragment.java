package com.example.massonsaulniertp1;

import java.util.ArrayList;
import java.util.HashMap;

import multipleListView.listviewAdapter;
import static multipleListView.Constant.NUMEROLIGNE_COLUMN;
import static multipleListView.Constant.PREMIEREDIRECTION_COLUMN;
import static multipleListView.Constant.DEUXIEMEDIRECTION_COLUMN;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class RoutesFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	
	private ArrayList<HashMap> list;
	
	private static final String ARG_SECTION_NUMBER = "section_number";


	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static RoutesFragment newInstance(int sectionNumber) {
		RoutesFragment fragment = new RoutesFragment();

		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View	rootView =   inflater.inflate(
				R.layout.fragment_route, container, false);
	
	    ListView lview = (ListView) rootView.findViewById(R.id.listview);
        populateList();
        listviewAdapter adapter = new multipleListView.listviewAdapter(this, list , inflater);
        lview.setAdapter(adapter);

		return rootView;
	}


    @SuppressWarnings("rawtypes")
	private void populateList() {
 
        list = new ArrayList<HashMap>();
 
        HashMap<String, String> temp = new HashMap<String, String>();
            temp.put(NUMEROLIGNE_COLUMN,"11");
            temp.put(PREMIEREDIRECTION_COLUMN, "N");
            temp.put(DEUXIEMEDIRECTION_COLUMN, "W");
       
        list.add(temp);
 
        HashMap<String, String> temp1 = new HashMap<String, String>();
            temp1.put(NUMEROLIGNE_COLUMN,"97");
            temp1.put(PREMIEREDIRECTION_COLUMN, "S");
            temp1.put(DEUXIEMEDIRECTION_COLUMN, "O");

        list.add(temp1);
 
        HashMap<String, String> temp2 = new HashMap<String, String>();
            temp2.put(NUMEROLIGNE_COLUMN,"9Meow");
            temp2.put(PREMIEREDIRECTION_COLUMN, "N");
            temp2.put(DEUXIEMEDIRECTION_COLUMN, "N");

        list.add(temp2);
 
        HashMap<String, String> temp3 = new HashMap<String, String>();
            temp3.put(NUMEROLIGNE_COLUMN,"ELIE ROCK");
            temp3.put(PREMIEREDIRECTION_COLUMN, "Nord");
            temp3.put(DEUXIEMEDIRECTION_COLUMN, "SUD");
     
        list.add(temp3);
 

    }
	
}