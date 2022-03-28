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

The app module is mostly empty and used for the initialization of the Hilt Container.
Actually , we need to have a module which has complete visibility of the system (i.e. knows about all the modules of the system)

Other than this module , no other module has complete visibility of the system rather they only work with what they require

**Visibility**
All modules in the application



###### lobby
The lobby module is so named because this is the is the place where the application user arrives akin to a hotel lobby.
Here he is able to interact with the hotel, in application terms he is able to interact with the application.

This module can also be thought of as "UI" module. Contains the UI of the application

**Visibility**
Can only see the business module


###### business

Contains all the POJO kind of code that handles non-android related business logic for the application.

**Visibility**
Can only see the repository module


###### repository

Contains the repositories for various entities in the system . IRL this layer would also contain the databases

**Visibility**
Can only see the network  module

###### networks
Contains retrofit. IRL would contain other network related code.

###### common
Contains all the helpers, extensions ,themes , styles , fonts , colors and other things that would be perhaps needed by the entire application. 













