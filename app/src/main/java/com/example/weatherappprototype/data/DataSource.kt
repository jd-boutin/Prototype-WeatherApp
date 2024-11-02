import com.example.weatherappprototype.model.Meteo

class Datasource() {
    fun loadMeteo(): List<Meteo> {
        return listOf<Meteo>(
            Meteo("Paris",13.6,71),
            Meteo("Lyon",16.2,3),
            Meteo("Toulon", 19.7, 0)
        )
    }
}
