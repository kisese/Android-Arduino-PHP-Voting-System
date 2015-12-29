package arvote.yhtosoft.kisese.com.arvote;

import android.content.Context;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


/**
 * Created by Brayo on 2/4/2015.
 */
public class ChangeVote extends AsyncTask<String,Void,String>{

    String name;
    private ProgressDialog pDialog;
    Context context;
    private SharedPreferences user;
    private String id;

    public ChangeVote(String name, Context applicationContext) {
        this.name = name;
        this.context = applicationContext;
    }


    @Override
    protected void onPreExecute() {
        //super.onPreExecute();

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Processing...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String re = name;//user



            String link = "http://kisese.byethost7.com/kura/kura6.php";


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
            Toast.makeText(context, "Oops it seems an error occurred, you have already placed a vote!", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, "Your vote has been reset, Place a new vote", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, MainFragmentActivity.class);
            intent.putExtra("valid_user", name);
            context.startActivity(intent);
            //alertYangu();
        }

        super.onPostExecute(s);
        pDialog.dismiss();
    }

    @SuppressWarnings("deprecation")
    private void alertYangu(){
        final AlertDialog alertView = new AlertDialog.Builder(context).create();
        alertView.setTitle("");
        alertView.setMessage("Your vote has been reset, proceed to vote again");
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
