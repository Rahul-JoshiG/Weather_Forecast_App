<div align="center"> <img src="https://github.com/Rahul-JoshiG/Weather-Forecast-App/blob/master/app/src/main/res/drawable/logo.png" alt="App Logo" style="width: 150px; height: 150px;" /> 

</div> <h1 align="center">Weather Forecast App</h1> 

<p align="center"> A powerful and user-friendly app to get real-time weather updates, a 7-day forecast, and customize your weather experience. </p> 

<div align="center"> 
  <a href="#features">🚀 Features</a> 
  <a href="#tech-stack">🛠️ Tech Stack</a>  
  <a href="#screens">📸 Screenshots</a> 
  <a href="#installation">📥 Installation</a>  
  <a href="#contact">📧 Contact</a> </div>

## Overview
The **Weather Forecast App** provides accurate and up-to-date weather information for your current location or a selected city. With a user-friendly interface and robust architecture, the app ensures a seamless experience for weather updates.

## Features
- **Today Fragment**:
  - Displays the current location's temperature, humidity, and weather conditions.
- **Week Fragment**:
  - Provides a 7-day weather forecast, including key details for each day.
- **Settings Fragment**:
  - Allows users to:
      - Switch between Metric and Imperial units.
      - Customize the app's theme (Light/Dark).
      - Search for and update the weather for different cities.
- **Bottom Navigation Bar**:
  - Quick navigation between Today, Week, and Settings fragments.

## Tech Stack
  - **Java**: Core language for development.
  - **XML**: Designed the responsive and intuitive UI.
  - **MVVM Architecture**: Ensures scalability, separation of concerns, and maintainability.
  - **Retrofit**: Used for fetching weather data via RESTful APIs.
  - **Glide**: Efficiently loads and displays weather-related images.

## Screens
### 1. Today Fragment
- Displays real-time weather data:
  - Temperature
  - Humidity
  - Weather condition

### 2. Week Fragment
- Provides a detailed 7-day forecast with essential weather information.

### 3. Settings Fragment
- Theme customization (e.g., light or dark mode).
- City selection using a search bar.

## Screenshorts 
| **Today Weather** | **Week Weather** | **Setting** |
|--------------------------|--------------------------|---------------------------|
| ![image](https://github.com/user-attachments/assets/aa811ee5-b54b-42b9-b3f6-9dfd53d3a0f6)| ![image](https://github.com/user-attachments/assets/fc3bccc9-ea35-478b-962f-5d6a98bcf47e)| ![image](https://github.com/user-attachments/assets/f23caada-146a-4037-972c-c5c9d3f0634a)|


## Installation
1. Clone this repository.
   ```bash
   git clone <repository-url>
   ```
2. Open the project in Android Studio.
3. Build and run the project on an emulator or physical device.

## API Integration
The app fetches weather data using a weather API. Ensure you add your API key in the `build.gradle` or `local.properties` file:
```gradle
API_KEY="your_api_key_here"
```

## Dependencies
Here are the major libraries used in this project:
- **Dagger Hilt**: Dependency Injection
- **Retrofit**: REST API integration
- **Glide**: Image loading and processing

Add the following dependencies to your `build.gradle` file:
```gradle
implementation "com.google.dagger:hilt-android:<version>"
kapt "com.google.dagger:hilt-compiler:<version>"
implementation "com.squareup.retrofit2:retrofit:<version>"
implementation "com.github.bumptech.glide:glide:<version>"
kapt "com.github.bumptech.glide:compiler:<version>"
```

## Architecture
The app follows the **MVVM (Model-View-ViewModel)** architecture:
- **Model**: Manages data, including API calls using Retrofit.
- **View**: UI components, including fragments for Today, Week, and Settings.
- **ViewModel**: Acts as a bridge between Model and View, ensuring clean separation of concerns.

## How to Use
1. Launch the app.
2. Use the bottom navigation bar to switch between Today, Week, and Settings fragments.
3. In the Today fragment, view real-time weather data.
4. In the Week fragment, explore the 7-day forecast.
5. In the Settings fragment:
   - Change the theme.
   - Search for and select a city to update the weather data.
  
## License
This project is licensed under the MIT License. See the LICENSE file for more details.

## Contact
  **Developed by Rahul Joshi**
      - GitHub: Rahul-JoshiG
      - LinkedIn: Rahul Joshi
      - Email: rahul.joshi.dev@gmail.com
