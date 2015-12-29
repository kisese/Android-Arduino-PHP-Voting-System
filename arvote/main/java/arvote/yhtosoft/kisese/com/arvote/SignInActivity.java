package arvote.yhtosoft.kisese.com.arvote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Brayo on 2/21/2015.
 */
public class SignInActivity  extends ActionBarActivity {

    BootstrapButton sign_in, cont;
    EditText first_name, phone_number;
    BootstrapCircleThumbnail circle;

    private TextView text;
    private View layout;
    private SharedPreferences users;
    private String fname, phone;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        users = getSharedPreferences("user", Context.MODE_PRIVATE);
        user = users.getString("valid_user", "");

        if(!user.isEmpty()){
            Intent intent = new Intent(SignInActivity.this, MainFragmentActivity.class);
            SignInActivity.this.startActivity(intent);
        }

        LayoutInflater inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        text = (TextView) layout.findViewById(R.id.text);
        registerViews();

        cont = (BootstrapButton)findViewById(R.id.cont);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, MainFragmentActivity.class);
                SignInActivity.this.startActivity(intent);
            }
        });

        //add the custom view to the actionBar
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setIcon(R.drawable.vipi_logo);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7B68EE")));
        actionBar.getThemedContext();
        actionBar.addOnMenuVisibilityListener(null);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();

    }

    public void registerViews() {

        first_name = (EditText)findViewById(R.id.login_first_name);
        phone_number = (EditText)findViewById(R.id.login_phone_number);
        sign_in = (BootstrapButton)findViewById(R.id.login);
        circle = (BootstrapCircleThumbnail)findViewById(R.id.login_circle);


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( checkValidation () ) {
                    loginPost();
                    rotate();
                }
                else
                    Toast.makeText(SignInActivity.this, "Form contains error, or there is a blank field", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void rotate(){
        Animation animation = AnimationUtils.loadAnimation(SignInActivity.this, R.anim.rotate);
        circle.startAnimation(animation);
    }
    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.isName(first_name, true)) ret = false;
        if (!Validation.isPhoneNumber(phone_number, false)) ret = false;

     return ret;
    }


    public void loginPost(){


        fname = first_name.getText().toString();
        phone = phone_number.getText().toString();


        new SignInProcess(fname, phone).execute();

    }


    class SignInProcess  extends AsyncTask<String,Void,String> {

        String f_name, phone_n;

        Toast toast;

        public SignInProcess(String fname, String phone) {
            f_name = fname;
            phone_n = phone;

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          for(int i = 0; i<2; i++) {
              text.setText("Logging in..." + f_name + " " + phone_n);
              toast = new Toast(getApplicationContext());
              toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
              toast.setDuration(Toast.LENGTH_LONG);
              toast.setView(layout);
              toast.show();
          }

        }


        @Override
        protected String doInBackground(String... params) {
            try {


                String link = "http://kisese.byethost7.com/kura/sign_in.php";


                String data  = URLEncoder.encode("name", "UTF-8")
                        + "=" + URLEncoder.encode(f_name, "UTF-8");

                data += "&" + URLEncoder.encode("phone", "UTF-8")
                        + "=" + URLEncoder.encode(phone_n, "UTF-8");

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
            super.onPostExecute(s);
            toast.cancel();
            if(s.contains("Exception")){
                try {
                    Toast.makeText(getApplicationContext(),s + "Your Username/Password combination is wrong, please try again", Toast.LENGTH_LONG).show();
                }catch(WindowManager.BadTokenException e){
                    e.printStackTrace();
                }
            }else {

                users = getSharedPreferences("user", Context.MODE_PRIVATE);

                SharedPreferences.Editor preferencesEditor = users.edit();
                preferencesEditor.putString("valid_user", s);
                preferencesEditor.commit();

                Intent intent = new Intent(SignInActivity.this, MainFragmentActivity.class);
                SignInActivity.this.startActivity(intent);
            }
        }


    }

}
