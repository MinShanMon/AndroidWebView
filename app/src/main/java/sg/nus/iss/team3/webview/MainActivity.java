package sg.nus.iss.team3.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String EXTERNAL_URL = "externalUrl";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finde btn by id
        Button launchBtn = findViewById(R.id.launchBtn);
        //set onclick listener
        launchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String externalUrl = "https://www.iss.nus.edu.sg/graduate-programmes/programme/detail/graduate-diploma-in-systems-analysis";
                //pass url to web viewactivity
                launchExternalPage(externalUrl);
            }
        });
    }

    //pass url to webviewactivity with intent
    void launchExternalPage(String exUrl){
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra(EXTERNAL_URL, exUrl);
        startActivity(intent);
    }
}