# Weather App - Prototype
## Présentation
Ce projet est un prototype d'une application météo.
Développé pour Android en Kotlin avec Jetpack Compose.

A ce stade, les données météorologiques sont chargées depuis l'API pour chacune des villes dont les coordonnées sont renseignées dans *model/LocationRepository*.
A terme, l'utilisateur aura la possibilité de rechercher une ville et de l'ajouter aux favoris (qui seront enregistrés).

## Captures d'écran
<div>
<img src="./docs/overview.png" alt="Page principale">
<img src="./docs/search.png" alt="Page de recherche">
</div>

## Fonctionnalités
### 🖼️ UI
* Compose UI
* Material Design

### 🏠 Architecture
* Jetpack Compose UI
* Architecture MVVM
* Connexion à l'API OpenMeteo (Retrofit + Serialization)
* Sauvegarde locale utilisant les SharedPreferences

## API
Pour ce prototype, nous avons choisi d'utiliser l'[API OpenMeteo](https://open-meteo.com/en/docs#hourly=temperature_2m,weather_code).
Cette API présente en effet l'avantage de ne pas nécessiter d'authentification, et de présenter les résultats simplement sous format JSON.
Pour gérer les lieux, nous avons choisi d'utiliser l'[API Geocoding](https://open-meteo.com/en/docs/geocoding-api).


## TODO
- [x] Connecter SearchBar à l'API Geocoding
- [x] Enregistrer les localisations favorites sur le téléphon
- [x] Changer icône (retirer fond bleu)
- [x] Créer une meilleure UI
- [ ] Ajout du composant principal à l'accueil qui montre la météo locale -> localisation par IP
- [ ] Affichage des lieux populaires
- [ ] Afficher une vue vide quand on clique sur "plus de précisions" chaque localisation
- [ ] Indicateur de progression dans le chargement des données / d'erreur le cas échéant
- [ ] Possibilité de recharger les données
- [ ] Créer la vue détaillée pour chaque localisation