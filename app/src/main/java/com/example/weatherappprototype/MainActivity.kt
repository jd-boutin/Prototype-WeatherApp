package com.example.weatherappprototype

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults.inputFieldColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
            Box (Modifier.background(Brush.linearGradient(listOf(Color(0xFF4B9AD2), Color(0xFF03549F))))) {
                MeteoApp(overviewViewModel)
            }
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

    Column (modifier = Modifier
        .fillMaxSize()){
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
            Box (modifier=Modifier.fillMaxSize().background(Brush.linearGradient(listOf(Color(0xFF4B9AD2), Color(0xFF03549F))))) {
                MeteoList(
                    meteoList = searchResults.value ?: listOf(),
                    onFavoriteClick = {location: LocationWrapper -> overviewVM.onFavoriteClick(location)},
                )
            }
        }
        Text (text="Favoris", color = Color.LightGray, fontSize = 14.sp, modifier=Modifier.padding(8.dp))
        MeteoList(
            meteoList = meteoData.value ?: listOf(),
            onFavoriteClick = {location: LocationWrapper -> overviewVM.onFavoriteClick(location)}
        )
        Text (text="Populaires", color = Color.LightGray, fontSize = 14.sp, modifier=Modifier.padding(8.dp))
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
            }
        }
    }
}


@Composable
fun MeteoCard(meteo: Meteo, onFavoriteClick: () -> Unit, modifier: Modifier = Modifier) {
    val imageResource = when (meteo.ww_code) {
        0 -> R.drawable.sunny
        1 -> R.drawable.cloudy
        2 -> R.drawable.cloudy
        3 -> R.drawable.full_cloudy
        45, 48, 51, 53, 55, 56, 57 -> R.drawable.fog
        61, 63, 65 -> R.drawable.rainy
        66, 67 -> R.drawable.snow
        71, 73, 75 -> R.drawable.snow
        77 -> R.drawable.snow
        80, 81, 82 -> R.drawable.showers
        85, 86 -> R.drawable.snow
        95 -> R.drawable.showers
        96 -> R.drawable.rainy_storm
        99 -> R.drawable.storm
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
    ListItem(
        colors= ListItemColors(
            containerColor = Color(0xFF0D6CAF),
            headlineColor = Color.White,
            leadingIconColor = Color.White,
            disabledHeadlineColor = Color.LightGray,
            disabledLeadingIconColor = Color.LightGray,
            disabledTrailingIconColor = Color.LightGray,
            overlineColor = Color.White,
            supportingTextColor = Color.White,
            trailingIconColor = Color.White
        ),
        leadingContent = {
            Image(
                painter = painterResource(imageResource),
                contentDescription = null,
                modifier = Modifier
            )

        },
        headlineContent = {
            Column(
                modifier = Modifier
                    .padding(0.dp, 10.dp)

            ) {
                Text(
                    text = location,
                    fontSize = 10.sp,
                    lineHeight = 10.sp,
                    modifier = Modifier.padding(0.dp, 0.dp)
                )
                    Text(
                        text = meteoDesc,
                        modifier = Modifier
                    )
                    Text(
                        text = "${temperature}°",
                        modifier = Modifier
                    )

            }
        },
        trailingContent = {
            Row {
                Icon(
                    saveIcon,
                    contentDescription = "Ajouter aux favoris",
                    modifier = Modifier.clickable(onClickLabel = "Ajouter cet élément aux favoris") {
                        onFavoriteClick() }

                )
                Icon(
                    Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = "Expand"
                )
            }


        },


        modifier = modifier.clickable {  }
            .padding(8.dp)
            .clip(RoundedCornerShape(15.dp))





    )

}




@Preview
@Composable
fun MeteoCardPreview(){
    val imageResource = R.drawable.cloudy
    val location = "Paris"
    val temperature = 18.6
    val meteoDesc = "Globalement dégagé"
    WeatherAppPrototypeTheme{
        MeteoItem(location, temperature, imageResource, meteoDesc, false, onFavoriteClick = {})
    }

}