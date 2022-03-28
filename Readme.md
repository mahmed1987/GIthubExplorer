# DawnHealth app challenge

###### A demo showcasing the industry standard approach (Jetpack compose , Multi modules , MVVM , CLEAN) for developing in android

## Tech Stack Used

1. Jetpack compose
2. Hilt
3. Retrofit
4. Coil
5. ViewModels
6. Kotlin Flows
7. Jetpack Navigation for compose
8. Dark/Light Mode Support

## Current Limitations

1. The new SplashScreen API wasn't integrated , as a result of which the SplashScreen is shown on
   cold and warm starts. If I would integrate the new SplashScreen API the SplashScreen would only
   appear on cold starts.

2. The RepositoryDetails screen doesn't work well in Landscape orientation.

3. There was something wrong with the android icon provided in figma , that was preventing it to be
   imported via Image Asset Studio, hence I used a different svg to make the icons

This application takes the approach of extreme code isolation by distributing various components of
the application in modules. The following modules together make the application

## Modules Description


###### app

The app module is mostly empty. 

###### common



