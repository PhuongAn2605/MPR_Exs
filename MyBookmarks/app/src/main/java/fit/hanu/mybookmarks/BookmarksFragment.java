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
import android.widget.LinearLayout;

import java.util.function.BooleanSupplier;

/**
 * A simple {@link Fragment} subclass.
 * Use the factory method to
 * create an instance of this fragment.
 */
public class BookmarksFragment extends Fragment implements View.OnClickListener {
    private int rotation;
//    private static final String ARG_LAND = "land";
//    private static final String AGR_PORTRAIT = "portrait";
//
//    private String argLand;
//    private String argPortrait;

//    public BookmarksFragment() {
//    }
//
//    public static BookmarksFragment newInstance(String argLand, String argPortrait) {
//        BookmarksFragment fragment = new BookmarksFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_LAND, argLand);
//        args.putString(AGR_PORTRAIT, argPortrait);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            argLand = getArguments().getString(ARG_LAND);
//            argPortrait = getArguments().getString(AGR_PORTRAIT);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        View view = null;
//        view = inflater.inflate(R.layout.fragment_bookmarks_landscapse, container, false);

        if(rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180){
            view =  inflater.inflate(R.layout.fragment_bookmarks, container, false);

        }else if(rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270){
            view =  inflater.inflate(R.layout.fragment_bookmarks_landscapse, container, false);

        }
        // Inflate the layout for this fragment
//        view =  inflater.inflate(R.layout.fragment_bookmarks, container, false);
        LinearLayout zingmp3, blueone, baomoi, medium;
        zingmp3 = view.findViewById(R.id.zingmp3);
        blueone = view.findViewById(R.id.bluezone);
        baomoi = view.findViewById(R.id.baomoi);
        medium = view.findViewById(R.id.medium);

        zingmp3.setOnClickListener(this);
        blueone.setOnClickListener(this);
        baomoi.setOnClickListener(this);
        medium.setOnClickListener(this);

//        if(rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270)

        return view;
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getFragmentManager();
        Bundle data = new Bundle();
        Fragment fragment;

        if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
//            FragmentManager manager = getFragmentManager();
            fragment = new WebViewFragment();

            switch (view.getId()) {
                case R.id.zingmp3:
//                    Bundle data1 = new Bundle();
                    data.putString("URL", "https://mp3.zing.vn/");
                    fragment.setArguments(data);

                    manager.beginTransaction()
                            .replace(R.id.fragmentContainer, fragment)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.bluezone:
//                    Bundle data2 = new Bundle();
                    data.putString("URL", "https://bluezone.gov.vn/");
                    fragment.setArguments(data);

                    manager.beginTransaction()
                            .replace(R.id.fragmentContainer, fragment)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.baomoi:
//                    Bundle data3 = new Bundle();
                    data.putString("URL", "https://baomoi.com/");
                    fragment.setArguments(data);

                    manager.beginTransaction()
                            .replace(R.id.fragmentContainer, fragment)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.medium:
//                    Bundle data4 = new Bundle();
                    data.putString("URL", "https://medium.com/");
                    fragment.setArguments(data);

                    manager.beginTransaction()
                            .replace(R.id.fragmentContainer, fragment)
                            .addToBackStack(null)
                            .commit();
                    break;
            }

        } else {
//            fragment = getFragmentManager().findFragmentById(R.id.fragmentWeb);
            fragment = new WebViewFragment();
            switch (view.getId()) {
                case R.id.zingmp3:
                    Bundle dataZing = new Bundle();
                    dataZing.putString("URL", "https://mp3.zing.vn/");
                    fragment.setArguments(data);

                    manager.beginTransaction()
                            .replace(R.id.fragmentWeb, fragment)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.bluezone:
                    data.putString("URL", "https://bluezone.gov.vn/");
                    fragment.setArguments(data);

                    manager.beginTransaction()
                            .replace(R.id.fragmentWeb, fragment)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.baomoi:
                    data.putString("URL", "https://baomoi.com/");
                    fragment.setArguments(data);

                    manager.beginTransaction()
                            .replace(R.id.fragmentWeb, fragment)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.medium:
                    data.putString("URL", "https://medium.com/");
                    fragment.setArguments(data);

                    manager.beginTransaction()
                            .replace(R.id.fragmentWeb, fragment)
                            .addToBackStack(null)
                            .commit();
                    break;
            }
        }

        //Pass args
        //Bundle has key and value
//        Bundle data = new Bundle();


    }
//        if(rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
//            Fragment fragment = manager.findFragmentById(R.id.fragmentWeb);
//        }
}