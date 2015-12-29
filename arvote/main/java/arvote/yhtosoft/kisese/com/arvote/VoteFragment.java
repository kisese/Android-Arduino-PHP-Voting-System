package arvote.yhtosoft.kisese.com.arvote;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class VoteFragment extends Fragment {



    private BootstrapButton vote_a, vote_b, vote_c, vote_d, vote_e, vote_f, signup;
    private LinearLayout a, b, c, d ,e, f;
    private String user;

    private ImageButton page_reload;

    private TextView text;
    private View layout;
    private ProgressDialog pDialog;
    private SharedPreferences users;
    private String us;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        users = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        user = users.getString("valid_user", "");

        View rootView;
        if(user.isEmpty()){
            rootView  = inflater.inflate(R.layout.sign_up_activity, container, false);
            signup = (BootstrapButton) rootView.findViewById(R.id.sign_up);

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                    getActivity().startActivity(intent);
                }
            });
        }else {
            rootView = inflater.inflate(R.layout.vote_activity, container, false);


            a = (LinearLayout) rootView.findViewById(R.id.a);
            b = (LinearLayout) rootView.findViewById(R.id.b);
            c = (LinearLayout) rootView.findViewById(R.id.c);
            d = (LinearLayout) rootView.findViewById(R.id.d);
            e = (LinearLayout) rootView.findViewById(R.id.e);
            f = (LinearLayout) rootView.findViewById(R.id.f);
            page_reload = (ImageButton)rootView.findViewById(R.id.page_reload);

            vote_a = (BootstrapButton) rootView.findViewById(R.id.vote_a);
            vote_b = (BootstrapButton) rootView.findViewById(R.id.vote_b);
            vote_c = (BootstrapButton) rootView.findViewById(R.id.vote_c);
            vote_d = (BootstrapButton) rootView.findViewById(R.id.vote_d);
            vote_e = (BootstrapButton) rootView.findViewById(R.id.vote_e);
            vote_f = (BootstrapButton) rootView.findViewById(R.id.vote_f);

            a.setVisibility(View.GONE);
            b.setVisibility(View.GONE);
            c.setVisibility(View.GONE);
            d.setVisibility(View.GONE);
            e.setVisibility(View.GONE);
            f.setVisibility(View.GONE);

            //checking for flags
            new GetFlags().execute();

            page_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a.setVisibility(View.GONE);
                    b.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                    e.setVisibility(View.GONE);
                    f.setVisibility(View.GONE);
                    rotate();
                    new GetFlags().execute();
                }
            });

            vote_a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteA();
                }
            });

            vote_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteB();
                }
            });

            vote_c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteC();
                }
            });


            vote_d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteD();
                }
            });


            vote_e.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteE();
                }
            });

            vote_f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteF();
                }
            });
        }


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        //checking for flags

        if(user.isEmpty()){}else {
            a.setVisibility(View.GONE);
            b.setVisibility(View.GONE);
            c.setVisibility(View.GONE);
            d.setVisibility(View.GONE);
            e.setVisibility(View.GONE);
            f.setVisibility(View.GONE);
            new GetFlags().execute();
        }
    }

public void getFlags(){
    new GetFlags().execute();
}

    public void voteA(){
        new VoteProcessActivity(getActivity()).execute(user,"a", "0", "1");
    }

    public void voteB(){
        new VoteProcessActivity(getActivity()).execute(user,"b", "0", "1");
    }

    public void voteC(){
        new VoteProcessActivity(getActivity()).execute(user,"c", "0", "1");
    }

    public void voteD(){
        new VoteProcessActivity(getActivity()).execute(user,"d", "0", "1");
    }


    public void voteE(){
        new VoteProcessActivity(getActivity()).execute(user,"e", "0", "1");
    }

    public void voteF(){
        new VoteProcessActivity(getActivity()).execute(user,"f", "0", "1");
    }


    private class GetFlags extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){
            rotate();
            if(user.isEmpty()){}else {
                Toast.makeText(getActivity(), "Loading...", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String re = "";//user

                String link = "http://kisese.byethost7.com/kura/kura4.php";


                String data = URLEncoder.encode("results", "UTF-8")
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
            if(user.isEmpty()){}else {
                if (s.equals("pair 1")) {
                    a.setVisibility(View.VISIBLE);
                    b.setVisibility(View.VISIBLE);
                } else if (s.equals("pair 2")) {
                    c.setVisibility(View.VISIBLE);
                    d.setVisibility(View.VISIBLE);
                } else if (s.equals("pair 3")) {
                    e.setVisibility(View.VISIBLE);
                    f.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getActivity(), "Option is unavailable at the moment", Toast.LENGTH_LONG).show();
                }

            }
        }

    }

    public void rotate(){
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        page_reload.startAnimation(animation);
    }
}
