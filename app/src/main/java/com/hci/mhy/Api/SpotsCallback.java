package com.hci.mhy.Api;

import android.content.Context;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import okhttp3.Request;
import okhttp3.Response;

public abstract class SpotsCallback<T> extends  BaseCallback<T>{

    SpotsDialog dialog;

    public SpotsCallback(Context context) {
        dialog = new SpotsDialog(context);
    }

    public void showDialog(){
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss();
    }

    public void setMessage(String message){
        dialog.setMessage(message);
    }

    @Override
    public void onRequestBefore(Request request) {
        showDialog();
    }

    @Override
    public void onFailure(IOException e) {
        dismissDialog();
    }

    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }
}
