package nsit.vivek.com.billing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScrollingActivityEstimated extends AppCompatActivity {

    String list;
    Calculate calcObj = null;
    private ArrayAdapter<String> mForecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling_activity_estimated);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            list = calcObj.getList();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_item, container, false);


        List<String> weekforecast = new ArrayList<String>(Arrays.asList(list));
        mForecastAdapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.list_item,
                R.id.list_item_forecast_textview,
                weekforecast);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * @param adapterView ->AdapterView: The AdapterView where the click happened.
             * @param view        -> view: The view within the AdapterView that was clicked (this will be a view provided by the adapter)
             * @param position    -> int: The position of the view in the adapter.
             * @param id          -> long: The row id of the item that was clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String forecast = mForecastAdapter.getItem(position);
                Toast.makeText(getApplicationContext(), forecast, Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }
}