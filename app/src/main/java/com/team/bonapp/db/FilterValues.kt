package com.team.bonapp.db

val FilterValues: LinkedHashMap<String, List<String>> = linkedMapOf(
    "test" to listOf("c1", "c2"),
    "Diet" to listOf("balanced", "high-fiber", "high-protein", "low-carb", "low-fat", "low-sodium"),
    "Health" to listOf(
        "alcohol-cocktail", "alcohol-free", "celery-free", "crustacean-free", "dairy-free",
        "DASH", "egg-free", "fish-free", "fodmap-free", "gluten-free", "immuno-supportive",
        "keto-friendly", "kidney-friendly", "kosher", "low-potassium", "low-sugar", "lupine-free",
        "Mediterranean", "mollusk-free", "mustard-free", "No-oil-added", "paleo", "peanut-free",
        "pecatarian", "pork-free", "red-meat-free", "sesame-free", "shellfish-free", "soy-free",
        "sugar-conscious", "sulfite-free", "tree-nut-free", "vegan",
        "vegetarian", "wheat-free" ),
)