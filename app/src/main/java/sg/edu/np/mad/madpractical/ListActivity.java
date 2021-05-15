package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private final static String TAG = "List Activity";
    private final ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        for (int i = 0; i < 20; ++i) {
            userList.add(new User(
                    "Name" + rng(), "Description " + rng(), i, rngFollow()
            ));
        }
        Log.v(TAG, "List Activity Created");

        RecyclerView recyclerViewCustom = findViewById(R.id.rv);
        CustomAdapter cAdapter = new CustomAdapter(userList, ListActivity.this);
        LinearLayoutManager cLayoutManager = new LinearLayoutManager(this);
        recyclerViewCustom.setLayoutManager(cLayoutManager);
        recyclerViewCustom.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCustom.setAdapter(cAdapter);

        /*
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

         */
    }

    private boolean rngFollow() {
        return new Random().nextInt(2) == 1;
    }

    private int rng() {
        return new Random().nextInt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Resume");

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        if (sharedPreferences.contains("user_id")) {
            int userId = sharedPreferences.getInt("user_id", 0);
            User user = userList.get(userId);
            userList.get(userId).setFollowed(!user.isFollowed());
        }
    }
}