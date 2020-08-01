package com.example.trivial.controller;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private static AppController instance;  //AppController instance
    private RequestQueue requestQueue;      //requestQueue instance
    private ImageLoader imageLoader;        //imageLoader instance
    private static Context ctx;             //ctx instance



//    private AppController(Context context) {   //AppController constructor
//        ctx = context;                         // assign ctx to context
//        requestQueue = getRequestQueue();      // getRequestQueue method called on requestQueue


//        imageLoader = new ImageLoader(requestQueue,
//                new ImageLoader.ImageCache() {
//                    private final LruCache<String, Bitmap>
//                            cache = new LruCache<String, Bitmap>(20);
//
//                    @Override
//                    public Bitmap getBitmap(String url) {
//                        return cache.get(url);
//                    }
//
//                    @Override
//                    public void putBitmap(String url, Bitmap bitmap) {
//                        cache.put(url, bitmap);
//                    }
//                });
//    }

    public static synchronized AppController getInstance() {
//        if (instance == null) {
//            instance = new AppController(context);
//    }
        return instance;
    }

//   public AppController(Context context) {
//        if (instance == null) instance = new AppController(this.context);
//        final AppController instance = AppController.instance;
//        AppController instance1 = instance;
//        return instance1;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);
//        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
