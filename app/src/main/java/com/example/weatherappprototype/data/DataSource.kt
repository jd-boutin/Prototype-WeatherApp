import com.example.weatherappprototype.model.Location
import com.example.weatherappprototype.model.Meteo

class Datasource() {
    fun loadMeteo(): List<Meteo> {
        return listOf<Meteo>(
            Meteo(Location("Lyon", "France", 45.74846F, 4.84671F),16.8,0),
            Meteo(Location("Paris", "France", 48.85341F, 2.3488F),13.6,71)
        )
    }
}
