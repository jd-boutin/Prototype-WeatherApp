package com.example.weatherappprototype

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappprototype.model.LocationWrapper
import com.example.weatherappprototype.model.Meteo
import com.example.weatherappprototype.ui.theme.WeatherAppPrototypeTheme
import com.example.weatherappprototype.viewmodel.OverviewViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val overviewViewModel = ViewModelProvider(this)[OverviewViewModel::class]
        getApplicationContext()
        overviewViewModel.getMeteoListOverview()
        enableEdgeToEdge()
        setContent {
            MeteoApp(overviewViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeteoApp(overviewVM: OverviewViewModel){
    val meteoData = overviewVM.overview.observeAsState()

    val searchText = overviewVM.searchText.collectAsState()
    val searchBarActive = overviewVM.searchBarActive.collectAsState()
    val searchResults = overviewVM.searchResults.observeAsState()

    Column {
        SearchBar(
            query = searchText.value,
            onSearch = overviewVM::onSearchTextChange,
            onQueryChange = overviewVM::onSearchTextChange,
            active=searchBarActive.value,
            onActiveChange = overviewVM::onSearchActivity,
            leadingIcon = {Icon(Icons.Rounded.Search, contentDescription = null)},
            placeholder = {Text("Rechercher un lieu")},
            modifier = Modifier
                .statusBarsPadding()
                .padding(5.dp)
        ) {

            MeteoList(
                meteoList = searchResults.value ?: listOf(),
                onFavoriteClick = {location: LocationWrapper -> overviewVM.onFavoriteClick(location)}
            )
        }
        MeteoList(
            meteoList = meteoData.value ?: listOf(),
            onFavoriteClick = {location: LocationWrapper -> overviewVM.onFavoriteClick(location)}
        )
    }
}




@Composable
fun MeteoList(meteoList: List<Meteo>, onFavoriteClick: (LocationWrapper) -> Unit, modifier: Modifier=Modifier) {
    LazyColumn(modifier = modifier) {
        items(meteoList) { meteo ->
            Column (modifier=modifier) {
                MeteoCard(
                    meteo = meteo,
                    onFavoriteClick = {
                        onFavoriteClick(meteo.location)},
                    modifier = Modifier.padding(0.dp)
                )
                HorizontalDivider()
            }
        }
    }
}


@Composable
fun MeteoCard(meteo: Meteo, onFavoriteClick: () -> Unit, modifier: Modifier = Modifier) {
    val imageResource = when (meteo.ww_code) {
        0 -> R.drawable.wi_day_sunny
        1 -> R.drawable.wi_day_sunny_overcast
        2 -> R.drawable.wi_day_cloudy
        3 -> R.drawable.wi_cloudy
        45, 48, 51, 53, 55, 56, 57 -> R.drawable.wi_day_fog
        61, 63, 65 -> R.drawable.wi_rain
        66, 67 -> R.drawable.wi_day_sleet
        71, 73, 75 -> R.drawable.wi_snow
        77 -> R.drawable.wi_day_snow
        80, 81, 82 -> R.drawable.wi_showers
        85, 86 -> R.drawable.wi_sleet
        95 -> R.drawable.wi_storm_showers
        96 -> R.drawable.wi_day_sleet_storm
        99 -> R.drawable.wi_thunderstorm
        else -> R.drawable.wi_na
    }
    val meteoDesc : String = when (meteo.ww_code) {
        0 -> stringResource(R.string.desc_ww_0)
        1 -> stringResource(R.string.desc_ww_1)
        2 -> stringResource(R.string.desc_ww_2)
        3 -> stringResource(R.string.desc_ww_3)
        45 -> stringResource(R.string.desc_ww_45)
        48 -> stringResource(R.string.desc_ww_48)
        51 -> stringResource(R.string.desc_ww_51)
        53 -> stringResource(R.string.desc_ww_53)
        55 -> stringResource(R.string.desc_ww_55)
        56 -> stringResource(R.string.desc_ww_56)
        57 -> stringResource(R.string.desc_ww_57)
        61 -> stringResource(R.string.desc_ww_61)
        63 -> stringResource(R.string.desc_ww_63)
        65 -> stringResource(R.string.desc_ww_65)
        71 -> stringResource(R.string.desc_ww_71)
        73 -> stringResource(R.string.desc_ww_73)
        75 -> stringResource(R.string.desc_ww_75)
        77 -> stringResource(R.string.desc_ww_77)
        80 -> stringResource(R.string.desc_ww_80)
        81 -> stringResource(R.string.desc_ww_81)
        82 -> stringResource(R.string.desc_ww_82)
        85 -> stringResource(R.string.desc_ww_85)
        86 -> stringResource(R.string.desc_ww_86)
        95 -> stringResource(R.string.desc_ww_95)
        96 -> stringResource(R.string.desc_ww_96)
        99 -> stringResource(R.string.desc_ww_99)
        else -> stringResource(R.string.desc_ww_unknown)
    }

    MeteoItem("${meteo.location.name} - ${meteo.location.country}", meteo.temperature, imageResource, meteoDesc, meteo.pinned, onFavoriteClick = {onFavoriteClick()}, modifier=modifier)
}


@Composable
fun MeteoItem(location: String, temperature: Double, imageResource: Int, meteoDesc: String, saved: Boolean, onFavoriteClick: ()->Unit, modifier: Modifier=Modifier) {
    val saveIcon = when (saved) {
        true -> Icons.Rounded.Favorite
        else -> Icons.Rounded.FavoriteBorder
    }
    WeatherAppPrototypeTheme{
        ListItem(
            leadingContent = {
                Icon(
                    saveIcon,
                    contentDescription = "Ajouter aux favoris",
                    modifier = Modifier.clickable(onClickLabel = "Ajouter cet élément aux favoris") {
                        onFavoriteClick() }
                )
            },
            headlineContent = {
                Column(
                    modifier = Modifier
                        .padding(10.dp)

                ) {
                    Text(
                        text = location,
                        fontSize = 10.sp,
                        lineHeight = 10.sp,
                        modifier = Modifier.padding(0.dp, 0.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${temperature}°",
                            modifier = Modifier
                        )
                        VerticalDivider(

                            modifier = Modifier
                                .padding(10.dp)
                                .height(10.dp)
                        )
                        Image(
                            painter = painterResource(imageResource),
                            contentDescription = null,
                            modifier = Modifier.padding(5.dp)
                        )


                        Text(
                            text = meteoDesc,
                            modifier = Modifier
                        )
                    }
                }
            },
            trailingContent = {

                Icon(
                    Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = "Expand"
                )
            },

            modifier = modifier.clickable {  }


        )
    }
}




@Preview
@Composable
fun MeteoCardPreview(){
    val imageResource = R.drawable.wi_day_sunny_overcast
    val location = "Paris"
    val temperature = 18.6
    val meteoDesc = "Globalement dégagé"
    WeatherAppPrototypeTheme{
        MeteoItem(location, temperature, imageResource, meteoDesc, false, onFavoriteClick = {})
    }

}