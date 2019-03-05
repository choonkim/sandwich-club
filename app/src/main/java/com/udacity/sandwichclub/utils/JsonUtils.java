package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOW_AS = "alsoKnownAs";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION= "description";
    public static final String KEY_INGREDIENTS = "ingredients";
    public static final String KEY_IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) {

        // check to see if we have any data first
        if (json == null && !json.equals("")) {
            return null;
        }

        // try to build our sandwich object using Sandwich class
        try {

            // grab json array objects from strings.xml
            Sandwich sandwich = new Sandwich();

            //create json variable for sandwich
            JSONObject sandwichJSON = new JSONObject(json);

            // collect the necessary json objects referenced from activity_detail.xml
            JSONObject nameJSON = sandwichJSON.getJSONObject(KEY_NAME);

            // create main name object from json object
            sandwich.setMainName(nameJSON.getString(KEY_MAIN_NAME));

            // create array object for also known as
            JSONArray alsoJsonArray = nameJSON.getJSONArray(KEY_ALSO_KNOW_AS);

            // iterate through also known as data and compile list
            List<String> alsoKnownJson = new ArrayList<>();
            for (int i = 0; i < alsoJsonArray.length(); i++) {
                alsoKnownJson.add(alsoJsonArray.getString(i));
            }
            sandwich.setAlsoKnownAs(alsoKnownJson);


            // define the remaining objects from sandwich constructor - placeOfOrigin, description, image, and ingredients
            sandwich.setPlaceOfOrigin(sandwichJSON.getString(KEY_PLACE_OF_ORIGIN));
            sandwich.setDescription(sandwichJSON.getString(KEY_DESCRIPTION));
            sandwich.setImage(sandwichJSON.getString(KEY_IMAGE));

            JSONArray ingredientsJSON = sandwichJSON.getJSONArray(KEY_INGREDIENTS);

            // iterate through ingredients data and compile list
            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsJSON.length(); i++) {
                ingredientsList.add(ingredientsJSON.getString(i));
            }
            sandwich.setIngredients(ingredientsList);

            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
