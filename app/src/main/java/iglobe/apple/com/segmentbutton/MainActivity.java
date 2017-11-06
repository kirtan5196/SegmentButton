package iglobe.apple.com.segmentbutton;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {

    ListView list;
    String[] web = {
            "Wordpress",
            "Drupal"
    } ;
    Integer[] imageId = {
            R.drawable.ic_message_red,
            R.drawable.ic_message_red

    };

    FragmentTransaction ft;
    Fragment1           frg1;
    Fragment2           frg2;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frg1 = new Fragment1();
        frg2 = new Fragment2();

        RadioButton btn1 = findViewById(R.id.radio1);
        btn1.setChecked(true);
        getSupportFragmentManager().beginTransaction().add(R.id.frame, frg1).commit();

        // set listener
        ((RadioGroup) findViewById(R.id.radio_group)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ft = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.radio1:
                        ft.replace(R.id.list, frg1);
                        CustomList adapter = new
                                CustomList(MainActivity.this, web, imageId);
                        list=(ListView)findViewById(R.id.list);
                        list.setAdapter(adapter);
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

                            }
                        });
                        break;
                    case R.id.radio2:
                        ft.replace(R.id.frame, frg2);
                        break;
                }
                ft.commit();
            }
        });
    }
}

