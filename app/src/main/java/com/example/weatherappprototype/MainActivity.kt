package com.example.weatherappprototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappprototype.model.Meteo
import com.example.weatherappprototype.ui.theme.WeatherAppPrototypeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppPrototypeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MeteoItem(
                        Meteo("Paris", 18.5F, 1),
                        modifier = Modifier.padding(innerPadding)
                            .fillMaxWidth()

                    )
                }
            }
        }
    }
}



@Composable
fun MeteoItem(meteo: Meteo, modifier: Modifier = Modifier) {
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

    Column(
        modifier = modifier
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
            .background(Color.LightGray)
            .padding(10.dp)
    ) {
        Text(
            text = meteo.location,
            color = Color.Black,
            fontSize = 10.sp,
            lineHeight = 2.sp,
            modifier = Modifier.padding(0.dp, 0.dp)
        )

        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${meteo.temperature}°",
                color = Color.Black,
                modifier = Modifier
            )
            Image(
                painter = painterResource(imageResource),
                contentDescription = null,
                modifier = Modifier.padding(5.dp)
            )


            Text(
                text = meteoDesc,
                color = Color.Black,
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeteoItemPreview() {
    val location = "Paris"
    val temperature = 18.6
    WeatherAppPrototypeTheme {
        val imageResource = R.drawable.wi_day_sunny_overcast
        val meteoDesc : String = "Globalement dégagé"


        Column(
            modifier = Modifier
                .padding(5.dp)
                .clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                .background(Color.LightGray)
                .padding(10.dp)
        ) {
            Text(
                text = "Paris",
                fontSize = 10.sp,
                lineHeight = 2.sp,
                modifier = Modifier.padding(0.dp, 0.dp)
            )

            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${temperature}°",
                    modifier = Modifier
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
    }
}