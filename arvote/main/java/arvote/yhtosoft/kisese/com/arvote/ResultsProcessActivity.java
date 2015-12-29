package arvote.yhtosoft.kisese.com.arvote;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Brayo on 2/22/2015.
 */
public class ResultsProcessActivity  extends AsyncTask<String,Void,String> {

    private TextView results_a,results_b, results_c, results_d, results_e, results_f, results_tally;
    private Context context;
    private SharedPreferences user;
    private ProgressDialog pDialog;

    public ResultsProcessActivity(StandingsFragment standingsFragment, TextView results_a, TextView results_b, TextView results_c, TextView results_d, TextView results_e, TextView results_f, TextView results_tally) {
        this.context  = standingsFragment.getActivity();
        this.results_a  = results_a;
        this.results_b = results_b;
        this.results_c = results_c;
        this.results_d = results_d;
        this.results_e = results_e;
        this.results_f = results_f;
        this.results_tally = results_tally;
    }

    protected void onPreExecute(){
        Toast.makeText(context, "Fetching results...", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String re = "";//user

            String link="http://kisese.byethost7.com/kura/kura3.php";


            String data  = URLEncoder.encode("results", "UTF-8")
                    + "=" + URLEncoder.encode(re, "UTF-8");


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
            //set shared preferences variable  total-a-b-male-female

            String reg[] = s.split("-");

            user = context.getSharedPreferences("user", Context.MODE_PRIVATE);

            SharedPreferences.Editor preferencesEditor = user.edit();
            preferencesEditor.putString("results_a", reg[1]);
            preferencesEditor.putString("results_b", reg[2]);
            preferencesEditor.putString("results_tally", reg[0]);
            preferencesEditor.putString("results_male", reg[3]);
            preferencesEditor.putString("results_female", reg[4]);
            preferencesEditor.putString("voters_total", reg[5]);
            preferencesEditor.putString("results_c", reg[6]);
            preferencesEditor.putString("results_d", reg[7]);
            preferencesEditor.putString("results_e", reg[8]);
            preferencesEditor.putString("results_f", reg[9]);
            preferencesEditor.commit();



            this.results_a.setText("A:      " + reg[1]);
            this.results_b.setText("B:      " + reg[2]);
            this.results_c.setText("C:      " + reg[6]);
            this.results_d.setText("D:      " + reg[7]);
            this.results_e.setText("E:      " + reg[8]);
            this.results_f.setText("F:      " + reg[9]);
            this.results_tally.setText("Total votes: " + reg[0]);


                Toast.makeText(context, reg[0] + " votes were cast", Toast.LENGTH_LONG).show();


        }


        super.onPostExecute(s);
    }


    @SuppressWarnings("deprecation")
    private void alertYangu(String text){
        final AlertDialog alertView = new AlertDialog.Builder(context).create();
        alertView.setTitle("");
        alertView.setMessage(text);
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
