package com.team.bonapp.data.db

val FilterValues: LinkedHashMap<String, List<String>> = linkedMapOf(
    "Diet" to listOf(
        "Balanced", "High-Fiber", "High-Protein", "Low-Carb", "Low-Fat", "Low-Sodium"
    ),
    "Health" to listOf(
        "Alcohol-Cocktail", "Alcohol-Free", "Celery-Free", "Dairy-Free",
        "Egg-Free", "Fish-Free", "Gluten-Free", "Immuno-Supportive",
        "Keto-Friendly", "Low-Sugar", "Mediterranean",
        "Mollusk-Free", "Paleo", "Peanut-Free", "Tree-Nut-Free", "Vegan",
        "Vegetarian", "Wheat-Free"
    ),
    "Cuisine Type" to listOf(
        "American", "Asian", "British", "Central Europe", "Chinese",
        "Eastern Europe", "French", "Indian", "Italian", "Japanese",
        "Kosher", "Mediterranean", "Mexican", "Middle Eastern",
        "Nordic", "South American", "South East Asian"
    ),
    "Meal Type" to listOf(
        "Breakfast", "Dinner", "Lunch", "Snack", "Teatime"
    ),
    "Dish Type" to listOf(
        "Biscuit and cookies", "Bread", "Cereals", "Condiments and sauces",
        "Desserts", "Drinks", "Main course", "Pancake", "Preps",
        "Preserve", "Salad", "Sandwiches", "Side dish", "Soup",
        "Starter", "Sweets"
    ),
)

val MealValues: LinkedHashMap<String, List<String>> = linkedMapOf(
        "Dish type category" to listOf(
            "Bread", "Soup", "Desserts",
            "Drinks", "Salad", "Main course"
        )
    )
