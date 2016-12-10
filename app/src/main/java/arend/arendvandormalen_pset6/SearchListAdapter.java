package arend.arendvandormalen_pset6;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Arend on 2016-12-10.
 * Adapter to fill list with search results
 */

public class SearchListAdapter extends ArrayAdapter<ArtObject> {

    public SearchListAdapter(Context context, ArrayList<ArtObject> searchResults){
        super(context, R.layout.single_list_item, searchResults);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View artView = inflater.inflate(R.layout.single_list_item, parent, false);

        String artTitle = getItem(position).getTitle();
        TextView artTitleView = (TextView) artView.findViewById(R.id.title_single_item);
        artTitleView.setText(artTitle);

        String artMaker = getItem(position).getArtist();
        TextView artMakerView = (TextView) artView.findViewById(R.id.maker_single_item);
        artMakerView.setText(artMaker);

        return artView;

    }
}
