package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * VideoPlaybackActivity embeds the player via an iframe HTML snippet.
 */
public class VideoPlaybackActivity extends AppCompatActivity {
    private WebView webViewPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_playback);

        webViewPlayer = findViewById(R.id.webViewPlayer);

        // 1) Enable JavaScript and DOM storage so the player can function properly
        WebSettings ws = webViewPlayer.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        // Allow autoplay without a user gesture
        ws.setMediaPlaybackRequiresUserGesture(false);

        // 2) Keep all navigation inside this WebView
        webViewPlayer.setWebViewClient(new WebViewClient());

        // 3) Extract the YouTube video ID from the supplied URL
        String rawUrl = getIntent().getStringExtra("videoUrl");
        if (rawUrl == null) rawUrl = "";
        String videoId = "";
        int idx = rawUrl.indexOf("v=");
        if (idx != -1) {
            videoId = rawUrl.substring(idx + 2);
            int amp = videoId.indexOf('&');
            if (amp > 0) videoId = videoId.substring(0, amp);
        }
        if (videoId.isEmpty()) {
            // Invalid URL, bail out
            finish();
            return;
        }

        // 4) Building the iframe HTML with nocookie domain and origin param
        //    passing origin=http://localhost so YouTube permits the embed
        String iframe = "<html><body style=\"margin:0;padding:0;\">" +
                "<iframe " +
                "width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube-nocookie.com/embed/" + videoId +
                "?rel=0&autoplay=1&origin=http%3A%2F%2Flocalhost\" " +
                "frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen>" +
                "</iframe></body></html>";

        // 5) Load it via loadDataWithBaseURL so the iframe is executed in the correct context
        webViewPlayer.loadDataWithBaseURL(
                "https://www.youtube-nocookie.com",
                iframe,
                "text/html",
                "utf-8",
                null
        );
    }
}
