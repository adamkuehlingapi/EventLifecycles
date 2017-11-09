package com.example.android.coderunner;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String LOG_TEXT_KEY = "log_text_key";
    private TextView mLog;

// @BindView(R.id.run_button) Button runButton;     //This pairs with Lambda commented out. Onclick below replaces it.
    @OnClick(R.id.run_button) public void onRunButtonClick(){logMessage("runCode");}
    @OnClick(R.id.clear_button) public void onClearButtonClick(){
        mLog.setText("");
        mLog.scrollTo(100,100);
    }  // this binds the id and clickEvent, copypasta whole function you want in here.





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     ButterKnife.bind(this);   //this instantiates everything to the Butterknife Objects; needed for any Butter Knife thing after the setContentView.

        mLog = (TextView) findViewById(R.id.log);
        mLog.setMovementMethod(new ScrollingMovementMethod());


        if (savedInstanceState != null && savedInstanceState.containsKey(LOG_TEXT_KEY)){
            mLog.setText(savedInstanceState.getString(LOG_TEXT_KEY));  //if data is being displayed after config change, restore what was passed instead of just adding.
        }

        mLog.setText("");

       // Button runButton= (Button) findViewById(R.id.run_button);  //creating Java and adding event listener.
        // More explicit setting than below, but still can be accomplished without this line, automatically linking the XML android:onClick and naming the fucntion

     //   runButton.setOnClickListener(v -> runCode(v));  //ButterKnife BindView sets the initial listeners.
        // Lamda condenses it for less code. Shorten it even more by condensing it like the clear_button

   /*     findViewById(R.id.clear_button)   //call to function using Java, without having an XML reference.
                .setOnClickListener(v -> {
                    clearCode(v);
                    Toast.makeText( getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
                });
*/
        logMessage("onCreate called!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logMessage("onPause called!");

    }

    @Override
    protected void onResume() {
        super.onResume();
        logMessage("onResume called!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logMessage("onStop called!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logMessage("onStart called!");
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
       String logText =  mLog.getText().toString();     //Put the information here that you want to preserve when an activity closes ( config changes)
       outState.putString(LOG_TEXT_KEY, logText);
        super.onSaveInstanceState(outState, outPersistentState);

    }

    private void logMessage(String message) {
//      Output message to logcat console
        Log.i(TAG, message);

//      Output message to TextView
        mLog.append(message + "\n");

//      Adjust scroll position to make last line visible
        Layout layout = mLog.getLayout();
        if (layout != null) {
            int scrollAmount = layout.getLineTop(mLog.getLineCount()) - mLog.getHeight();
            mLog.scrollTo(0, scrollAmount > 0 ? scrollAmount : 0);
        }
    }

/*    public void runCode(View view) {
        logMessage("runCode");
    }
*/

/*    public void clearCode(View view) {
      mLog.setText("");
        //mLog.scrollTo(100,100);
   }
*/


}
