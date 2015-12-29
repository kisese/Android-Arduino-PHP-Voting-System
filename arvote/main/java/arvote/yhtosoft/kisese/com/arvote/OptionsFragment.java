package arvote.yhtosoft.kisese.com.arvote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

/**
 * Created by Brayo on 2/22/2015.
 */
public class OptionsFragment  extends Fragment {


   BootstrapButton admin, settings, logout;
    private SharedPreferences user;
    private String us;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.options_activity, container, false);


        admin = (BootstrapButton)rootView.findViewById(R.id.admin_login);
        settings = (BootstrapButton)rootView.findViewById(R.id.settings);
        logout = (BootstrapButton)rootView.findViewById(R.id.logout);


        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        us = user.getString("valid_user", "");


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInBrowser();
                //Intent intent = new Intent(OptionsActivity.this, AdminWebViewActivity.class);
                //OptionsActivity.this.startActivity(intent);
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.edit().remove("valid_user").commit();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                getActivity().startActivity(intent);

                Toast.makeText(getActivity(), "Logged Out", Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }


    public void openInBrowser(){
        String url_string = "http://kisese.byethost7.com/kura/admin_login.php";

        Intent i  = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url_string));
        startActivity(i);
    }

}
