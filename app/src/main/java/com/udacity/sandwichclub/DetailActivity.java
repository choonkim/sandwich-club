package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    // create variables for image and text views
    private TextView originTextView;
    private TextView alsoKnownAsTextView;
    private TextView ingredientsTextView;
    private TextView descriptionTextView;
    private ImageView ingredientsImageView;

    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // reference our image and text view variables
        ingredientsImageView = findViewById(R.id.image_iv);
        originTextView = findViewById(R.id.origin_tv);
        alsoKnownAsTextView = findViewById(R.id.also_known_tv);
        ingredientsTextView = findViewById(R.id.ingredients_tv);
        descriptionTextView = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsImageView);

        populateUI();
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        // get sandwich name
        setTitle(sandwich.getMainName());

        // get sandwich origin
        originTextView.setText(sandwich.getPlaceOfOrigin());

        // get also known as sandwich name
        alsoKnownAsTextView.setText(TextUtils.join(", ", sandwich.getAlsoKnownAs()));

        // get sandwich ingredients
        ingredientsTextView.setText(TextUtils.join(",", sandwich.getIngredients()));

        // get sandwich description
        descriptionTextView.setText(sandwich.getDescription());
    }
}
