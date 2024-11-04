import com.example.weatherappprototype.model.LocationWrapper
import com.example.weatherappprototype.model.Meteo

class Datasource() {
    fun loadMeteo(): List<Meteo> {
        return listOf<Meteo>(
            Meteo(LocationWrapper(2996944, "Lyon", "France", 45.74846F, 4.84671F),16.8,0, false),
            Meteo(LocationWrapper(2988507,"Paris", "France", 48.85341F, 2.3488F),13.6,71, false)
        )
    }
}
