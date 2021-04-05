package fit.hanu.mybookmarks;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class WebViewFragment extends Fragment implements View.OnClickListener{
    private int rotation;
    private Button btnBack;
    private WebView webView;
//    private static final String ARG_LAND = "LAND";
//    private static final String ARG_PORTRAIT = "PORTRAIT";
//
//    private String argLand;
//    private String argPortrait;

//    public WebViewFragment() {
//    }
//
//    public static WebViewFragment newInstance(String argLand, String argPortrait){
//        WebViewFragment fragment = new WebViewFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_LAND, argLand);
//        args.putString(ARG_PORTRAIT, argPortrait);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if(getArguments() != null){
//            argLand = getArguments().getString(ARG_PORTRAIT);
//            argPortrait = get
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_web_view, container, false);
        rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();

        //References
        webView = view.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        //Enable JS
        WebSettings settings = webView.getSettings();
//        Log.i("PHUONG", "setting" + settings);
        settings.setJavaScriptEnabled(true);

        if(rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180){
            String url = getArguments().getString("URL");

            webView.loadUrl(url);

        }

        if(rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            btnBack.setVisibility(View.GONE);
            if (getArguments() != null) {
                String url = getArguments().getString("URL");
                Log.i("URL", "url: " + url);
                webView.loadUrl(url);
            }

//        if(rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180){
//            String url = getArguments().getString("URL");
//
//            webView.loadUrl(url);
//
//        }

            //Get passed data
//        String url = getArguments().getString("URL");
//        if(url != null) {
//            webView.loadUrl(url);
//        }else{
//            webView.loadUrl("http://fit.hanu.vn/");
//        }

            //Back event
        }

            return view;

    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if(rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270){
//            btnBack.setVisibility(View.GONE);
//            if(savedInstanceState != null){
//                String url = getArguments().getString("URL");
//                Log.i("URL", "url: " + url);
//                webView.loadUrl(url);
//            }
////            String url2 = getArguments().getString("URL");
////            if(url2 != null) {
////                webView.loadUrl(url2);
////            }else{
////                webView.loadUrl("http://fit.hanu.vn/");
////            }
//        }
//    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getFragmentManager();
        manager.popBackStack();
    }
}