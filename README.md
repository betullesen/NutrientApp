# Nutrient Book App
----------------
NutrientBook is an Android application that displays food data retrieved from an API using RecyclerView, loads images with Glide, and provides navigation based on user interaction. Users can view the food's name, calorie content, carbohydrates, protein, fat, and other nutritional information, and can also explore the details of each food.

## Technologies Used
----------------
- **Room Database:** The Room library was used to store data locally. Room abstracts SQLite databases and provides an easy way to manage data.
  
- **Retrofit:** Retrofit was used to integrate RESTful APIs easily. Nutritional data is retrieved from an API and processed with Retrofit.
- **Glide:** Glide is used to load food images quickly and efficiently. It ensures that images are loaded properly while users browse through the food list.
- **Navigation Component:** The Navigation Component was used to manage screen transitions in the Android app. When users click on a food item, they are navigated to the food's detail page.
- **LiveData and ViewModel:** LiveData and ViewModel were used to manage the UI components and data. LiveData handles the data flow used to update the UI, while ViewModel stores data in a lifecycle-conscious manner.
- **SwipeRefreshLayout:** SwipeRefreshLayout allows users to refresh the data by swiping down on the screen.
- **SharedPreferences:** SharedPreferences was used to store small user data in a lightweight storage area.
- **Gson:** Gson was used to convert JSON data into Kotlin data classes.

## Preview


## App Features
------------

*   **Food List Display:** Users can access general information about food, including names, calories, and other nutritional values.
  
*   **Detailed Food View:** Each food item can be clicked to view more detailed information.
*   **Image Loading:** Food images are dynamically loaded with **Glide**.
*   **Data Refresh:** Users can refresh the food list using **SwipeRefreshLayout**.
*   **Data Storage:** Nutritional data is stored locally using **Room Database**.
*   **API Data Fetching:** Nutritional data is dynamically fetched from the internet using JSON data.
    
