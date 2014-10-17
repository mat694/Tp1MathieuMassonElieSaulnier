package multipleListView;

import java.util.ArrayList;
import java.util.HashMap;

import static multipleListView.Constant.NUMEROLIGNE_COLUMN;
import static multipleListView.Constant.PREMIEREDIRECTION_COLUMN;
import static multipleListView.Constant.DEUXIEMEDIRECTION_COLUMN;

import com.example.massonsaulniertp1.R;
import com.example.massonsaulniertp1.RoutesFragment;

import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class listviewAdapter extends BaseAdapter
{
    public ArrayList<HashMap> list;
    RoutesFragment activity;
    LayoutInflater inflater;
    public listviewAdapter(RoutesFragment routesFragment, ArrayList<HashMap> list, LayoutInflater inflater) {
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
       
 
                if (convertView == null)
                {
                    convertView = inflater.inflate(R.layout.listview_row, null);
                    holder = new ViewHolder();
                    holder.NumeroLigne = (TextView) convertView.findViewById(R.id.NumeroLigne);
                    holder.PremierDirection = (TextView) convertView.findViewById(R.id.PremierDirection);
                    holder.DeuxiemeDirection = (TextView) convertView.findViewById(R.id.DeuxiemeDirection);
           
                    convertView.setTag(holder);
                }
                else
                {
                    holder = (ViewHolder) convertView.getTag();
                }
 
                HashMap map = list.get(position);
                holder.NumeroLigne.setText((CharSequence) map.get(NUMEROLIGNE_COLUMN));
                holder.PremierDirection.setText((CharSequence) map.get(PREMIEREDIRECTION_COLUMN));
                holder.DeuxiemeDirection.setText((CharSequence) map.get(DEUXIEMEDIRECTION_COLUMN));
 
            return convertView;
    }
 
}

/*
 * CLASSE ET METHODE TROUVER 
 * Lien : http://www.technotalkative.com/android-multi-column-listview/
 * Date:16/10/2014 
 *
 * */
