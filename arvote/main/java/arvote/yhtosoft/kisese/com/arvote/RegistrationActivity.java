package arvote.yhtosoft.kisese.com.arvote;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;


/*
 * Created by Brayo on 12/27/2014.
 */
public class RegistrationActivity extends ActionBarActivity {

    private static final int RC_SIGN_IN = 0;
    // Logcat tag
    private static final String TAG = "MainActivity";

    // Profile pic image size in pixels
    private static final int PROFILE_PIC_SIZE = 400;




    private RadioGroup gender_group;
    private RadioButton btn_male, btn_female;


    private EditText fname, lname, phone_number, email;
    private BootstrapButton submit;
    private LinearLayout reg_layout;

    private TextView text;
    private View layout;
    // User Session Manager Class


    private SharedPreferences user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        user = getSharedPreferences("user", Context.MODE_PRIVATE);
        user.getString("valid_user", null);

        try {
            if (!user.getString("valid_user", null).equals(null)) {
                Intent intent = new Intent(this, VotedForActivity.class);
                this.startActivity(intent);

            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }

        reg_layout = (LinearLayout)findViewById(R.id.reg_layout);


        registerViews();





        LayoutInflater inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        text = (TextView) layout.findViewById(R.id.text);





        fade();



        //add the custom view to the actionBar
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setIcon(R.drawable.vipi_logo);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7B68EE")));
        actionBar.getThemedContext();
        actionBar.addOnMenuVisibilityListener(null);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
    }

    public void registerViews(){

        fname = (EditText)findViewById(R.id.fname);
        lname = (EditText)findViewById(R.id.lname);
        phone_number = (EditText)findViewById(R.id.phone_number);
        email = (EditText)findViewById(R.id.email);
        submit = (BootstrapButton)findViewById(R.id.register);
        gender_group = (RadioGroup)findViewById(R.id.gender_group);
        btn_male = (RadioButton)findViewById(R.id.radio_male);
        btn_female = (RadioButton)findViewById(R.id.radio_female);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( checkValidation () )
                loginPost();
                else
                    Toast.makeText(RegistrationActivity.this, "Form contains error, or there is a blank field", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.isName(fname, true)) ret = false;
        if (!Validation.isName(lname, true)) ret = false;
        if (!Validation.isPhoneNumber(phone_number, false)) ret = false;
        if (!Validation.isEmailAddress(email, true)) ret = false;


        return ret;
    }
    
    public void loginPost(){
        String first_name = fname.getText().toString();
        String last_name = lname.getText().toString();
        String phone_string = phone_number.getText().toString();
        String email_string = email.getText().toString();
        String gender = "gender";


        if (gender_group.getCheckedRadioButtonId() == R.id.radio_male){
            gender = btn_male.getText().toString();
        }else if(gender_group.getCheckedRadioButtonId() == R.id.radio_female){
            gender = btn_female.getText().toString();
        }

        text.setText("Sending Details...");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

        new ProcessActivity(this).execute(first_name, last_name, phone_string, email_string, gender);

    }



    public void fade(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        reg_layout.startAnimation(animation);
    }


    }


