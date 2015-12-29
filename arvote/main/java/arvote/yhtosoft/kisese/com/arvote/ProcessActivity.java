package arvote.yhtosoft.kisese.com.arvote;

/**
 * Created by Brayo on 2/22/2015.
 */
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by Brayo on 12/27/2014.
 */
public class ProcessActivity  extends AsyncTask<String,Void,String> {

    private Context context;
    private ProgressDialog pDialog;
    private SharedPreferences user;


    private SharedPreferences users;

    // User Session Manager Class
    //UserSessionManager  session = new UserSessionManager(this.context);


    public ProcessActivity(Context context) {
        this.context  = context;
    }

    protected void onPreExecute(){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Completing your Registration ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            String first_name = params[0]; //fname
            String last_name = params[1]; //lname
            String phone = params[2]; //phone
            String email = params[3]; //email
            String gender = params[4];//gender

            String link="http://kisese.byethost7.com/kura/kura1.php";


            String data  = URLEncoder.encode("first_name", "UTF-8")
                    + "=" + URLEncoder.encode(first_name, "UTF-8");

            data += "&" + URLEncoder.encode("last_name", "UTF-8")
                    + "=" + URLEncoder.encode(last_name, "UTF-8");

            data += "&" + URLEncoder.encode("phone_number", "UTF-8")
                    + "=" + URLEncoder.encode(phone, "UTF-8");

            data += "&" + URLEncoder.encode("email", "UTF-8")
                    + "=" + URLEncoder.encode(email, "UTF-8");

            data += "&" + URLEncoder.encode("gender", "UTF-8")
                    + "=" + URLEncoder.encode(gender, "UTF-8");

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
        pDialog.dismiss();
        super.onPostExecute(s);

        if(s.contains("Exception")||s.equals("That user is already registered")){
            try {
                Toast.makeText(context, "You are already registered", Toast.LENGTH_LONG).show();
            }catch(WindowManager.BadTokenException e){
                e.printStackTrace();
            }
        }else {
            //set shared preferences variable
            users = context.getSharedPreferences("user", Context.MODE_PRIVATE);

            SharedPreferences.Editor preferencesEditor = users.edit();
            preferencesEditor.putString("valid_user", s);
            preferencesEditor.commit();

            try{
                Toast.makeText(context, "User " + s + " Has been registered to vote", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, MainFragmentActivity.class);
                intent.putExtra("valid_user", s);
                context.startActivity(intent);
            }catch(WindowManager.BadTokenException e){
                e.printStackTrace();
            }
        }

    }
}
