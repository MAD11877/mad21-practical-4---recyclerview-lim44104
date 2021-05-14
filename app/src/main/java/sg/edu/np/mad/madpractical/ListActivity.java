package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private final static String TAG = "List Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Log.v(TAG, "List Activity Created");

        ImageView alertImg = findViewById(R.id.round_android);
        alertImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);

                builder.setTitle("Profile")
                        .setMessage("MADness")
                        .setCancelable(false)
                        .setNegativeButton("Close", null)
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                                intent.putExtra("randomNum", new Random().nextInt());
                                startActivity(intent);
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}