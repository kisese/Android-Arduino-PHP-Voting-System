package arvote.yhtosoft.kisese.com.arvote;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Brayo on 12/28/2014.
 */
public class VoteProcessActivity  extends AsyncTask<String,Void,String> {

    private Context context;
    private ProgressDialog pDialog;
    private SharedPreferences user;


    public VoteProcessActivity(Context context) {
        this.context  = context;

    }

    protected void onPreExecute(){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Processing your vote...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }


    @Override
    protected String doInBackground(String... params) {

        try {
            String user = params[0];//user
            String candidate = params[1]; //candidate
            String no_vote = params[2]; //0
            String vote = params[3]; //1

            //user = user.replace(" ", "");


            String link="http://kisese.byethost7.com/kura/kura2.php";


            String data  = URLEncoder.encode("user", "UTF-8")
                    + "=" + URLEncoder.encode(user, "UTF-8");

            data += "&" + URLEncoder.encode("candidate", "UTF-8")
                    + "=" + URLEncoder.encode(candidate, "UTF-8");

            data += "&" + URLEncoder.encode("no_vote", "UTF-8")
                    + "=" + URLEncoder.encode(no_vote, "UTF-8");

            data += "&" + URLEncoder.encode("vote", "UTF-8")
                    + "=" + URLEncoder.encode(vote, "UTF-8");


            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();
        }catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String s) {

        if(s.contains("Exception")){
            Toast.makeText(context, "Oops it seems an error occurred, please try again!", Toast.LENGTH_LONG).show();
        }else {
            //set shared preferences variable


                Toast.makeText(context, "You voted for "+s+" , thank you for voting", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(context, MainFragmentActivity.class);
            // intent.putExtra("voted_for", s.toUpperCase());
            context.startActivity(intent);

        }

        pDialog.dismiss();
        super.onPostExecute(s);
    }


    @SuppressWarnings("deprecation")
    private void alertYangu(String text){
        final AlertDialog alertView = new AlertDialog.Builder(context).create();
        alertView.setTitle("");
        alertView.setMessage("You voted for "+text.toUpperCase()+" , thank you for voting");
        alertView.setButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                alertView.dismiss();
            }
        });
        alertView.show();
    }



}
