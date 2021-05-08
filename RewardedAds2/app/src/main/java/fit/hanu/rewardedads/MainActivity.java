package fit.hanu.rewardedads;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;


public class MainActivity extends AppCompatActivity {

    Button button;

    RewardedAd rewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this);

        button = findViewById(R.id.button);

        rewardedAd = new RewardedAd(this, "ca-app-pub-3940256099942544/5224354917");

        rewardedAd.loadAd(new AdRequest.Builder().build(), new RewardedAdLoadCallback(){

            @Override
            public void onRewardedAdLoaded() {
                super.onRewardedAdLoaded();
                button.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError loadAdError) {
                super.onRewardedAdFailedToLoad(loadAdError);
                Toast.makeText(MainActivity.this, "Error: "+loadAdError.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rewardedAd.isLoaded()){

                    RewardedAdCallback callback = new RewardedAdCallback() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            ((TextView) findViewById(R.id.TV))
                                    .setText(String.valueOf(Integer
                                            .parseInt(((TextView) findViewById(R.id.TV)).getText().toString()) + 5));
                        }

                        @Override
                        public void onRewardedAdClosed() {
                            super.onRewardedAdClosed();
                            Toast.makeText(MainActivity.this, "Ad Closed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onRewardedAdFailedToShow(AdError adError) {
                            super.onRewardedAdFailedToShow(adError);
                            Toast.makeText(MainActivity.this, "Error: "+adError.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onRewardedAdOpened() {
                            super.onRewardedAdOpened();
                            Toast.makeText(MainActivity.this, "Ad Opened", Toast.LENGTH_SHORT).show();
                        }

                    };

                    rewardedAd.show(MainActivity.this, callback);

                }

            }
        });


    }

}