# Weather App - Prototype
## Présentation
> Ce projet est en développement

Ce projet est un prototype d'une application météo simple.
Développé pour Android en Kotlin avec Jetpack Compose.

A ce stade, les données météorologiques sont chargées depuis l'API pour chacune des villes dont les coordonnées sont renseignées dans *model/LocationRepository*.
A terme, l'utilisateur aura la possibilité de rechercher une ville et de l'ajouter aux favoris (qui seront enregistrés).

## Captures d'écran
![Page principale](docs/overview.png)

## API
Pour ce prototype, nous avons choisi d'utiliser l'[API OpenMeteo](https://open-meteo.com/en/docs#hourly=temperature_2m,weather_code).
Cette API présente en effet l'avantage de ne pas nécessiter d'authentification, et de présenter les résultats simplement sous format JSON.

## Crédits
Les icônes météo proviennent du [travail de Lukas Bischoff](https://github.com/erikflowers/weather-icons), sous licence [SIL OFL 1.1](http://scripts.sil.org/OFL).
