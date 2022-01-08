package de.eahjena.app.wi.recyclerview;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private RequestQueue mRequestQueue;
    private static VolleySingleton mInstance;




    private VolleySingleton(Context context) {

        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());

    }




    public static synchronized VolleySingleton getmInstance(Context context) {

        if (mInstance == null) {

            mInstance = new VolleySingleton(context);
        }
            return mInstance;
        }

        public RequestQueue getRequestQueue(){return mRequestQueue;}


        }




