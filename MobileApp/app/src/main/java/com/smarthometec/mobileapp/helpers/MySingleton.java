package com.smarthometec.mobileapp.helpers;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context context;
    private MySingleton(Context contexto){
        context = contexto;
        requestQueue = getRequestQueue();
    }
    private RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized MySingleton getInstance(Context context){
        if(mInstance==null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }
    public<T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

}

