package com.example.carpoolpllanner;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SearchableActivity extends ListActivity {
    private Connection conn;
    private String connResult="";
    private ListAdapter ListAp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
         // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    private void doMySearch(String query) {
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setTitle("Loading your search");
        pDialog.setMessage("Loading...");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        //bring connection to sql database to get result
        SearchView sv = (SearchView) findViewById(R.id.searchbar);
        ListView ls = (ListView) findViewById(R.id.searchList);
        try {
            SqlConnector sqlConn = new SqlConnector();
            conn = sqlConn.connector();
            if(conn!=null){
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){

                    ls.setAdapter(ListAp);
                }
            }
        } catch (Exception ex) {

        }


    }
}
