package arvote.yhtosoft.kisese.com.arvote;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

/**
 * Created by Brayo on 2/22/2015.
 */
public class StandingsFragment extends Fragment {

    TextView results_a, results_b, results_c, results_d, results_e, results_f, results_tally, results_male, results_female;
    private SharedPreferences user;
    private LinearLayout results_layout;
    private BootstrapButton change_vote, bar_graph;

    ImageButton refresh;

    private TextView text;
    private View layout;
    private String us;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.results_activity, container, false);

        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        us = user.getString("valid_user", "");

        bar_graph = (BootstrapButton)rootView.findViewById(R.id.bar_graph);
        change_vote = (BootstrapButton)rootView.findViewById(R.id.change_vote);
        refresh = (ImageButton)rootView.findViewById(R.id.refresh);
        if(us.isEmpty()){
            change_vote.setVisibility(View.GONE);
        }else{
            change_vote.setVisibility(View.VISIBLE);
        }

        change_vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VotedForActivity.class);
                getActivity().startActivity(intent);
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotate();
                getResults();
            }
        });

        bar_graph.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Fetching graph", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), BarGraphActivity.class);
                getActivity().startActivity(intent);
            }
        });

        results_layout = (LinearLayout)rootView.findViewById(R.id.results_layout);
        results_a = (TextView)rootView.findViewById(R.id.result_a);
        results_b = (TextView)rootView.findViewById(R.id.result_b);
        results_c = (TextView)rootView.findViewById(R.id.result_c);
        results_d = (TextView)rootView.findViewById(R.id.result_d);
        results_e = (TextView)rootView.findViewById(R.id.result_e);
        results_f = (TextView)rootView.findViewById(R.id.result_f);
        results_male = (TextView)rootView.findViewById(R.id.result_male);
        results_female = (TextView)rootView.findViewById(R.id.result_female);
        results_tally = (TextView)rootView.findViewById(R.id.total_tally);


        getResults();
        fade();
        rotate();


        return rootView;
    }

    public void fade(){
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade);
        results_layout.startAnimation(animation);
    }

    public void rotate(){
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        refresh.startAnimation(animation);
    }

    public void getResults(){
        new ResultsProcessActivity(this, results_a, results_b, results_c, results_d, results_e, results_f, results_tally).execute();
    }

}
