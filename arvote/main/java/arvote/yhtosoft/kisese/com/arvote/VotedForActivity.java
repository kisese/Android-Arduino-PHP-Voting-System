package arvote.yhtosoft.kisese.com.arvote;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Brayo on 2/4/2015.
 */
public class VotedForActivity extends ActionBarActivity {

    private SharedPreferences users;
    String name;
    TextView user_name, voted_for;
    Context context = this;

    BootstrapButton change_results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voted_for_activity);

        user_name = (TextView)findViewById(R.id.user_id);
        voted_for = (TextView)findViewById(R.id.voted_for);
        change_results = (BootstrapButton)findViewById(R.id.change_vote);



        users = getSharedPreferences("user", Context.MODE_PRIVATE);
        name =  users.getString("valid_user", null);

        user_name.setText("Welcome " + name);

        getVote();

        change_results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ChangeVote(name, context).execute();
                //Intent intent = new Intent(VotedForActivity.this, VoteActivity.class);
                //VotedForActivity.this.startActivity(intent);
            }
        });

        //add the custom view to the actionBar
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setIcon(R.drawable.vipi_logo);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7B68EE")));
        actionBar.show();
    }

    public void getVote(){
        new GetVote(name, voted_for).execute();
    }

    private class GetVote extends AsyncTask<String, Void, String> {

        String name;
        TextView voted_for;
        private ProgressDialog pDialog;

        public GetVote(String name, TextView voted_for) {
            this.name = name;
            this.voted_for = voted_for;
        }

        @Override
        protected void onPreExecute() {
            // super.onPreExecute();

            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }


        @Override
        protected String doInBackground(String... params) {
            try {
                String re = name;//user

                String link = "http://kisese.byethost7.com/kura/kura5.php";


                String data = URLEncoder.encode("vote", "UTF-8")
                        + "=" + URLEncoder.encode(re, "UTF-8");


                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                return sb.toString();

            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }



        @Override
        protected void onPostExecute(String s) {



            if (s.contains("Exception")) {
                Toast.makeText(context, "Oops it seems an error occurred, please try again!", Toast.LENGTH_LONG).show();
            }else{
                if(s.equals(null)||s.isEmpty()){
                    voted_for.setText("You have not voted yet, please go back and place your vote");
                }
                voted_for.setText("You recently voted for " + s.toUpperCase());
                //set shared preferences variable
                users = context.getSharedPreferences("user", Context.MODE_PRIVATE);

                SharedPreferences.Editor preferencesEditor = users.edit();
                preferencesEditor.putString("id", s);
                preferencesEditor.commit();
            }



            super.onPostExecute(s);
            pDialog.dismiss();
        }
    }


}
