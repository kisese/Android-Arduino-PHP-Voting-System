package brayo.kisese.yhtomit.colorswitch;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;


public class ColorPickerActivity extends ActionBarActivity {


        LinearLayout colorView;
        TextView coloText, colorRgb;
         String hexColor;

    @Override
		public void onCreate(Bundle savedInstanceState) {  
			super.onCreate(savedInstanceState);  
			setContentView(R.layout.activity_main);  

            colorView = (LinearLayout)findViewById(R.id.colorView);
            coloText = (TextView)findViewById(R.id.colorText);
            colorRgb = (TextView)findViewById(R.id.colorRgb);
            final ColorPicker picker = (ColorPicker)findViewById(R.id.picker);
            SVBar svBar = (SVBar)findViewById(R.id.svbar);
            SaturationBar saturationBar = (SaturationBar)findViewById(R.id.saturationBar);
            ValueBar valueBar = (ValueBar)findViewById(R.id.valueBar);

            fade();

            picker.addSVBar(svBar);
            picker.addSaturationBar(saturationBar);
            picker.addValueBar(valueBar);

            //to get the color
            picker.getColor();

            picker.setOldCenterColor(picker.getColor());


            picker.setShowOldCenterColor(false);
            picker.setOnColorChangedListener(new ColorPicker.OnColorChangedListener() {
                @Override
                public void onColorChanged(int color) {
                    ColorPickerActivity.this.findViewById(android.R.id.content).setBackgroundColor(picker.getColor());
                    hexColor = String.format("#%06X", (0xFFFFFF & color));

                    int red = Color.red(color);
                    int green = Color.green(color);
                    int blue = Color.blue(color);
                    coloText.setText("Hex Values: "  + hexColor);
                    colorRgb.setText("RGB Values: " + red + ", " + green + ", " + blue);
                }
            });


            picker.setOnColorSelectedListener(new ColorPicker.OnColorSelectedListener() {
                @Override
                public void onColorSelected(int color) {
                    ColorPickerActivity.this.findViewById(android.R.id.content).setBackgroundColor(picker.getColor());
                    hexColor = String.format("#%06X", (0xFFFFFF & color));

                    int red = Color.red(color);
                    int green = Color.green(color);
                    int blue = Color.blue(color);
                    coloText.setText("Hex Values: "  + hexColor);
                    colorRgb.setText("RGB Values: " + red + ", " + green + ", " + blue);
                }
            });

            getSupportActionBar().hide();
		
		}

    public void fade(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        colorView.startAnimation(animation);
        colorView.startAnimation(animation);
    }

    /*
    public void rotate1(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        button1.startAnimation(animation);
    }

    public void rotate2(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        button2.startAnimation(animation);
    }



		public void colorChanged(int color) {    
			ColorPickerActivity.this.findViewById(android.R.id.content).setBackgroundColor(color); 
			String hexColor = String.format("#%06X", (0xFFFFFF & color));
			hex.setText("Preview: "  + hexColor);
            sample.setBackgroundColor(color);
		}  
		*/

		
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu); // call super's method

        return true; // options menu creation was handled
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // switch based on the MenuItem id
        switch (item.getItemId())
        {
          
        } // end switch

        return super.onOptionsItemSelected(item); // call super's method
    }

}
