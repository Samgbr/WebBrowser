package example.com.webbrowser;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText d = (EditText)toolbar.findViewById(R.id.search_query);

        final Button backButton = (Button)findViewById(R.id.back_button);
        final Button forwardButton = (Button) findViewById(R.id.forward_button);

        final WebView myWebView = (WebView) findViewById(R.id.webview);

        WebViewClient webViewClient = new WebViewClient();
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        myWebView.setWebViewClient(webViewClient);
        Toast toast = Toast.makeText(MainActivity.this, "Please insert Url to search box like (google.com)...",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.LEFT,10,80);
        toast.show();
        ImageButton aGo = (ImageButton)toolbar.findViewById(R.id.action_go);
        aGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard(MainActivity.this);
                String s = d.getText().toString();
                myWebView.loadUrl("http://www." + s);
                if(!forwardButton.isEnabled()){
                    forwardButton.setEnabled(true);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myWebView.canGoBack()) {
                    myWebView.goBack();
                    forwardButton.setEnabled(true);
                    return;
                } else {
                    backButton.setEnabled(false);
                }
            }
        });
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myWebView.canGoForward()){
                    myWebView.goForward();
                    backButton.setEnabled(true);
                    return;
                } else {
                    forwardButton.setEnabled(false);
                }
            }
        });

        /*
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });  */
    }

    //Hides Soft keyboard
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
