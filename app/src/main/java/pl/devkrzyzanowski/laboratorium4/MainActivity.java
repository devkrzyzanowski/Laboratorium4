package pl.devkrzyzanowski.laboratorium4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText note;
    EditText urlText;
    RatingBar ratingBar;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        note = findViewById(R.id.note);
        ratingBar = findViewById(R.id.ratingBar);
        button = findViewById(R.id.button);
        urlText = findViewById(R.id.url);
    }

    public void onClick(View view){
        Opinion opinion = new Opinion();
        opinion.getAuthor().setFirstName(firstName.getText().toString());
        opinion.getAuthor().setLastName(lastName.getText().toString());
        opinion.getRate().setNote(note.getText().toString());
        opinion.getRate().setRatePoints(ratingBar.getRating());
        final String opinionJson = new Gson().toJson(opinion);

        String url;

        url = (urlText.getText().toString().isEmpty()) ? "http://153.19.62.200:8080/opinion/save" : urlText.getText().toString();



        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // tu należy samodzielnie zaimplementować wyświetlanie response
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //tu należy samodzielnie zaimplementować wyświetlanie errora
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
        @Override
            public String getBodyContentType() {
            return "application/json; charset=utf-8";
        }
        @Override
            public byte[] getBody() throws AuthFailureError {
            try {
                return opinionJson == null ? null : opinionJson.getBytes("utf-8");
            } catch (UnsupportedEncodingException uee) {
                return null;
            }
        }
        };
        queue.add(postRequest);
        queue.start();
    }
    }
