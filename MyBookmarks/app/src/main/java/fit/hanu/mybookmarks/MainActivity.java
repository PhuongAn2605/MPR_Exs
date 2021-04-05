package fit.hanu.mybookmarks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends SingleFragmentActivity {
//    private AdView mAdView;
    @Override
    protected Fragment createFragment() {

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
        return new BookmarksFragment();
    }


    //handle event

}