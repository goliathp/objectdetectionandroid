package com.example.sherlocksfruit.Helper;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class InternetConnectionChecker extends AsyncTask<Void,Void,Boolean> {
    public interface Consumer{
        void accept(boolean internet);

    }
    Consumer consumer;

    public InternetConnectionChecker(Consumer consumer){
        this.consumer=consumer;
        execute();
    }
    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            Socket socket=new Socket();
            socket.connect(new InetSocketAddress("google.com",80),1500);
            socket.close();
            return true;
        }catch (Exception e){
            return false;
        }

    }
    @Override
    protected void onPostExecute(Boolean aBoolean){
        super.onPostExecute(aBoolean);
        consumer.accept(aBoolean);
    }
}
