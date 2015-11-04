package com.example.epicodus.congressapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mUserInput;
    private Button mSubmitButton;
    private String mZipCode;
    public static final String Tag = MainActivity.class.getSimpleName();
    private ArrayList<CongressDetails> mCongressDetails;
    private TextView mThatCongress;
    private String mSingleCongressPerson;
    List<String> congressPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserInput = (EditText) findViewById(R.id.userInput);
        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mThatCongress = (TextView) findViewById(R.id.theCongress);
        congressPerson = new ArrayList<String>();

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mZipCode = mUserInput.getText().toString();
                getCongressInfo(mZipCode);
            }
        });
//        CongressDetails singleCongressPerson = mCongressDetails.get(0);
//        String firstName = singleCongressPerson.getFirstName();
//        mThatCongress.setText(firstName);

    }
    public void getCongressInfo(String zipcode) {
    String apikey = "5f303ea967a84244a4a293d50d690d21";
    String congressUrl = "https://congress.api.sunlightfoundation.com/legislators/locate?apikey=" + apikey + "&zip=" + zipcode;
    if(isNetworkAlailable())
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(congressUrl)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }
            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(Tag, jsonData);

                    if (response.isSuccessful()) {
                        mCongressDetails = getCurrentDetails(jsonData);

                    } else {
                        alertUserAboutError();
                    }
                } catch (IOException e) {
                    Log.e(Tag, "Exception caught: ", e);
                }_app.git
                catch ( JSONException e) {
                    Log.e(Tag, "Exception caught: ", e);

                }


            }
        });
    }
    else
    {
        Toast.makeText(this, "Network is not available", Toast.LENGTH_LONG).show();
    }
    Log.d(Tag, "MAin UI code is running!");
}
    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }

    private ArrayList<CongressDetails> getCurrentDetails(String jsonData) throws JSONException {
     JSONObject details = new JSONObject(jsonData);
        //JSONArray details = new JSONArray(jsonData);
        ArrayList<CongressDetails> congressPeople = new ArrayList<CongressDetails>();

        JSONArray currentDetails = details.getJSONArray("results");
        for (int i = 0; i < currentDetails.length(); i++) {
            JSONObject explrObject = currentDetails.getJSONObject(i);
            String firstName = explrObject.getString("first_name");
            String lastName = explrObject.getString("last_name");
            String party = explrObject.getString("party");
            String gender = explrObject.getString("gender");
            String birthday = explrObject.getString("birthday");
            CongressDetails congressPerson = new CongressDetails(firstName, lastName, party, gender, birthday);
            congressPeople.add(congressPerson);
        }






        return congressPeople;
    }

    private boolean isNetworkAlailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }


}
