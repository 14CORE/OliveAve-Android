package cs246.oliveave;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class ViewNewProducts extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_new_products);
        progressBar = (ProgressBar) findViewById(R.id.progressBarNewProducts);

        progressBar.getProgress();
        webView = (WebView) findViewById(R.id.website);
        WebSettings webSettings = webView.getSettings();
        webView.setWebViewClient(new ViewNewProductsClient());
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://oliveave.com/new/");

    }

    public class ViewNewProductsClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }
    }
}