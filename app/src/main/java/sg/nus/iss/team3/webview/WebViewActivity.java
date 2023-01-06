package sg.nus.iss.team3.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {

    private String mUrl;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    public WebViewActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //receive the url from main
        Intent intent=getIntent();
        //EXTERNAL_URL get from main beacuse public static
        mUrl = intent.getStringExtra(MainActivity.EXTERNAL_URL);

        //get the web view with id
        mWebView = findViewById(R.id.web_view);
        //set web to web view
        //call mywebviewclient class instead of webveiwclient
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.loadUrl(mUrl);

//        //enable javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //find progressbar with id
        mProgressBar = findViewById(R.id.progress_bar);
        //set progress max
        mProgressBar.setMax(100);

        //progress bar status
        mWebView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView webView, int newProgress){
                if(newProgress == 100){
                    mProgressBar.setVisibility(View.GONE);
                }
                else{
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });




    }

    // When users press the Android Back button, load the previous web page if there is.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    // When users click on any link. If the link is a NUS link, it should continue loading in the app’ WebView. Otherwise, let the default browser take care.
    private class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, WebResourceRequest resourceRequest
        ){
            //this is the website my webview will load the page
            if("www.iss.nus.edu.sg".equals(resourceRequest.getUrl().getHost())){
                return false;
            }
            else{
                //otherwise, launch another activity that handles urls
                Intent intent = new Intent(Intent.ACTION_VIEW, resourceRequest.getUrl());
                startActivity(intent);
                return true;
            }

        }
    }
}