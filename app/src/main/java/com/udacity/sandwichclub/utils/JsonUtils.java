package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

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
            JSONObject nameJSON = sandwichJSON.getJSONObject("name");

            // create main name object from json object
            sandwich.setMainName(nameJSON.getString("mainName"));

            // create array object for also known as
            JSONArray alsoJsonArray = nameJSON.getJSONArray("alsoKnownAs");

            // iterate through also known as data and compile list
            List<String> alsoKnownJson = new ArrayList<>();
            for (int i = 0; i < alsoJsonArray.length(); i++) {
                alsoKnownJson.add(alsoJsonArray.getString(i));
            }
            sandwich.setAlsoKnownAs(alsoKnownJson);


            // define the remaining objects from sandwich constructor - placeOfOrigin, description, image, and ingredients
            sandwich.setPlaceOfOrigin(sandwichJSON.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichJSON.getString("description"));
            sandwich.setImage(sandwichJSON.getString("image"));

            JSONArray ingredientsJSON = sandwichJSON.getJSONArray("ingredients");

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
