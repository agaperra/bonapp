package com.team.bonapp.data.db

val FilterValues: LinkedHashMap<String, List<String>> = linkedMapOf(
    "Diet" to listOf(
        "balanced", "high-fiber", "high-protein", "low-carb", "low-fat", "low-sodium"
    ),
    "Health" to listOf(
        "alcohol-cocktail", "alcohol-free", "celery-free", "crustacean-free", "dairy-free",
        "DASH", "egg-free", "fish-free", "fodmap-free", "gluten-free", "immuno-supportive",
        "keto-friendly", "kidney-friendly", "kosher", "low-potassium", "low-sugar", "lupine-free",
        "Mediterranean", "mollusk-free", "mustard-free", "No-oil-added", "paleo", "peanut-free",
        "pecatarian", "pork-free", "red-meat-free", "sesame-free", "shellfish-free", "soy-free",
        "sugar-conscious", "sulfite-free", "tree-nut-free", "vegan",
        "vegetarian", "wheat-free"
    ),
    "Cuisine Type" to listOf(
        "American",
        "Asian",
        "British",
        "Central Europe",
        "Chinese",
        "Eastern Europe",
        "French",
        "Indian",
        "Italian",
        "Japanese",
        "Kosher",
        "Mediterranean",
        "Mexican",
        "Middle Eastern",
        "Nordic",
        "South American",
        "South East Asian"
    ),
    "Meal type" to listOf(
        "Breakfast", "Dinner", "Lunch", "Snack", "Teaime"
    ),
    "Dish type" to listOf(
        "Biscuit and cookies",
        "Bread",
        "Cereals",
        "Condiments and sauces",
        "Desserts",
        "Drinks",
        "Main course",
        "Pancake",
        "Preps",
        "Preserve",
        "Salad",
        "Sandwiches",
        "Side dish",
        "Soup",
        "Starter",
        "Sweets"
    ),
    "Dish type category" to listOf(
        "Bread",
        "Soup",
        "Desserts",
        "Drinks",
        "Salad",
    )
//    "Category" to listOf(
//        "generic-foods", "generic-meals", "packaged-foods", "fast-foods"
//    )
)