package com.example.carpoolpllanner;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnector {
    Connection con;
    String usname,passw, ip,port, database;
    public Connection connector() {
        ip = "localhost";
        database = "gwvp";
        usname = "jonathan";
        passw = "123456";
        port = "4306";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;

        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnURL = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename"+database+";user="+usname+";password="+passw+";";
            conn = DriverManager.getConnection(ConnURL);
        }catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }
        return conn;
    }

}
