package arvote.yhtosoft.kisese.com.arvote;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;

/**
 * Created by Brayo on 1/1/2015.
 */
public class BarGraphActivity extends ActionBarActivity{

    private LinearLayout bar_layout;
    private SharedPreferences user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);

        user = getSharedPreferences("user", Context.MODE_PRIVATE);

        bar_layout = (LinearLayout)findViewById(R.id.bar_layout);

        String a = user.getString("results_a", "0");
        String b = user.getString("results_b", "0");
        String c = user.getString("results_tally", "0");
        String d = user.getString("results_male", "0");
        String e = user.getString("results_female", "0");
        String f = user.getString("voters_total", "0");
        String g = user.getString("results_c", "0");
        String h = user.getString("results_d", "0");
        String i = user.getString("results_e", "0");
        String j = user.getString("results_f", "0");
        // graph_view.setVisibility(View.VISIBLE);

        fade();
        float fa = Float.parseFloat(a);
        float fb = Float.parseFloat(b);
        float fc = Float.parseFloat(g);
        float fd = Float.parseFloat(h);
        float fe = Float.parseFloat(i);
        float ff = Float.parseFloat(j);


// init example series data
        GraphViewSeries exampleSeries = new GraphViewSeries("Candidate A", new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(60, 179, 113), 3), new GraphView.GraphViewData[]{
                new GraphView.GraphViewData(1, 0.0d)
                , new GraphView.GraphViewData(2, fa) //a
                , new GraphView.GraphViewData(3, 0.0d)
                , new GraphView.GraphViewData(4, 0.0d) //b
                , new GraphView.GraphViewData(5, 0.0d)
                , new GraphView.GraphViewData(6, 0.0d) //c
                , new GraphView.GraphViewData(7, 0.0d)
                , new GraphView.GraphViewData(8, 0.0d) //d
                , new GraphView.GraphViewData(9, 0.0d)
                , new GraphView.GraphViewData(10, 0.0d) //e
                , new GraphView.GraphViewData(11, 0.0d)
                , new GraphView.GraphViewData(12, 0.0d) //f
                , new GraphView.GraphViewData(13, 0.0d)
        });


        GraphViewSeries exampleSeries2 = new GraphViewSeries("Candidate B", new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(90, 250, 00), 3), new GraphView.GraphViewData[]{
                new GraphView.GraphViewData(1, 0.0d)
                , new GraphView.GraphViewData(2, 0.0d) //a
                , new GraphView.GraphViewData(3, 0.0d)
                , new GraphView.GraphViewData(4, fb) //b
                , new GraphView.GraphViewData(5, 0.0d)
                , new GraphView.GraphViewData(6, 0.0d) //c
                , new GraphView.GraphViewData(7, 0.0d)
                , new GraphView.GraphViewData(8, 0.0d) //d
                , new GraphView.GraphViewData(9, 0.0d)
                , new GraphView.GraphViewData(10, 0.0d) //e
                , new GraphView.GraphViewData(11, 0.0d)
                , new GraphView.GraphViewData(12, 0.0d) //f
                , new GraphView.GraphViewData(13, 0.0d)
        });


        GraphViewSeries exampleSeries3 = new GraphViewSeries("Candidate C", new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(0,206,209), 3), new GraphView.GraphViewData[]{
                new GraphView.GraphViewData(1, 0.0d)
                , new GraphView.GraphViewData(2, 0.0d) //a
                , new GraphView.GraphViewData(3, 0.0d)
                , new GraphView.GraphViewData(4, 0.0d) //b
                , new GraphView.GraphViewData(5, 0.0d)
                , new GraphView.GraphViewData(6, fc) //c
                , new GraphView.GraphViewData(7, 0.0d)
                , new GraphView.GraphViewData(8, 0.0d) //d
                , new GraphView.GraphViewData(9, 0.0d)
                , new GraphView.GraphViewData(10, 0.0d) //e
                , new GraphView.GraphViewData(11, 0.0d)
                , new GraphView.GraphViewData(12, 0.0d) //f
                , new GraphView.GraphViewData(13, 0.0d)
        });


        GraphViewSeries exampleSeries4 = new GraphViewSeries("Candidate D", new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(0,128,128), 3), new GraphView.GraphViewData[]{
                new GraphView.GraphViewData(1, 0.0d)
                , new GraphView.GraphViewData(2, 0.0d) //a
                , new GraphView.GraphViewData(3, 0.0d)
                , new GraphView.GraphViewData(4, 0.0d) //b
                , new GraphView.GraphViewData(5, 0.0d)
                , new GraphView.GraphViewData(6, 0.0d) //c
                , new GraphView.GraphViewData(7, 0.0d)
                , new GraphView.GraphViewData(8, fd) //d
                , new GraphView.GraphViewData(9, 0.0d)
                , new GraphView.GraphViewData(10, 0.0d) //e
                , new GraphView.GraphViewData(11, 0.0d)
                , new GraphView.GraphViewData(12, 0.0d) //f
                , new GraphView.GraphViewData(13, 0.0d)
        });


        GraphViewSeries exampleSeries5 = new GraphViewSeries("Candidate E", new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(0,128,128), 3), new GraphView.GraphViewData[]{
                new GraphView.GraphViewData(1, 0.0d)
                , new GraphView.GraphViewData(2, 0.0d) //a
                , new GraphView.GraphViewData(3, 0.0d)
                , new GraphView.GraphViewData(4, 0.0d) //b
                , new GraphView.GraphViewData(5, 0.0d)
                , new GraphView.GraphViewData(6, 0.0d) //c
                , new GraphView.GraphViewData(7, 0.0d)
                , new GraphView.GraphViewData(8, 0.0d) //d
                , new GraphView.GraphViewData(9, 0.0d)
                , new GraphView.GraphViewData(10, fe) //e
                , new GraphView.GraphViewData(11, 0.0d)
                , new GraphView.GraphViewData(12, 0.0d) //f
                , new GraphView.GraphViewData(13, 0.0d)
        });


        GraphViewSeries exampleSeries6 = new GraphViewSeries("Candidate F", new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(0,128,128), 3), new GraphView.GraphViewData[]{
                new GraphView.GraphViewData(1, 0.0d)
                , new GraphView.GraphViewData(2, 0.0d) //a
                , new GraphView.GraphViewData(3, 0.0d)
                , new GraphView.GraphViewData(4, 0.0d) //b
                , new GraphView.GraphViewData(5, 0.0d)
                , new GraphView.GraphViewData(6, 0.0d) //c
                , new GraphView.GraphViewData(7, 0.0d)
                , new GraphView.GraphViewData(8, 0.0d) //d
                , new GraphView.GraphViewData(9, 0.0d)
                , new GraphView.GraphViewData(10, 0.0d) //e
                , new GraphView.GraphViewData(11, 0.0d)
                , new GraphView.GraphViewData(12, ff) //f
                , new GraphView.GraphViewData(13, 0.0d)
        });

// init example series data

        GraphView graphView = new BarGraphView(
                this // context
                , "Voting Results" // heading
        );


        graphView.addSeries(exampleSeries); // data
        graphView.addSeries(exampleSeries2); // data
        graphView.addSeries(exampleSeries3); // data
        graphView.addSeries(exampleSeries4); // data
        graphView.addSeries(exampleSeries5); // data
        graphView.addSeries(exampleSeries6); // data
        graphView.getGraphViewStyle().setGridColor(Color.GREEN);
        graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.YELLOW);
        graphView.getGraphViewStyle().setVerticalLabelsColor(Color.RED);
        graphView.getGraphViewStyle().setTextSize(getResources().getDimension(R.dimen.small_text));
        graphView.setScalable(true);
        graphView.setShowLegend(true);
        graphView.setLegendAlign(GraphView.LegendAlign.BOTTOM);
        graphView.setViewPort(2, 40);
        graphView.setScrollable(true);
// optional - activate scaling / zooming
        graphView.setScalable(true);
        graphView.setHorizontalLabels(new String[]{"Candidates"});


        RelativeLayout graph_view = (RelativeLayout) findViewById(R.id.graph_view);
        graph_view.addView(graphView);

        //add the custom view to the actionBar
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setIcon(R.drawable.vipi_logo);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7B68EE")));
        actionBar.getThemedContext();
        actionBar.addOnMenuVisibilityListener(null);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
    }



    public void fade(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        bar_layout.startAnimation(animation);
    }

}
