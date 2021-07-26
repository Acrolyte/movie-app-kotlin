<p align="left">
	<img width="240" src="https://raw.githubusercontent.com/dsckiet/resources/master/dsckiet-logo.png" />
	<h2 align="left"> Usher </h2>
	<h4 align="left"> Sourced by TMDB API, it updates users all about movies and TV shows <h4>
</p>

---
[![DOCS](https://img.shields.io/badge/Documentation-see%20docs-green?style=for-the-badge&logo=appveyor)](INSERT_LINK_FOR_DOCS_HERE) 
  [![UI ](https://img.shields.io/badge/User%20Interface-Link%20to%20UI-orange?style=for-the-badge&logo=appveyor)](INSERT_UI_LINK_HERE)


## Functionalities
- [ ] Updates its charts based on latest and trending movies or TV shows.
- [ ] Gives detailed information about movies and casts.


<br>


## Instructions to run

* Pre-requisites:
	-  Android Studio v4.0
	-  A working Android physical device or emulator with USB debugging enabled

* Directions to setup/install
- Clone this repository to your local storage using Git bash:
```bash
git clone https://github.com/dsckiet/movie-app-kotlin.git
```
- Open this project from Android Studio
- Connect to an Android physical device or emulator
- To install the app into your device, run the following using command line tools
```bash
gradlew installDebug
```

* Directions to execute
-  To launch hands free, run the following using command line tools
	```bash
	adb shell monkey -p com.dsckiet.movie_app_kotlin -c android.intent.category.LAUNCHER 1
	```

<br>

## Contributors

* [Aditya](https://github.com/ydasc815)
* [Yuvraj](https://github.com/reyyuvraj)
* [Arpit](https://github.com/Acrolyte)



<br>
<br>

<p align="center">
	Made during 🌙 by DSC KIET
</p>
