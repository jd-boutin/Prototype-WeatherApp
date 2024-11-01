import com.example.weatherappprototype.model.Meteo

class Datasource() {
    fun loadMeteo(): List<Meteo> {
        return listOf<Meteo>(
            Meteo("Paris",13.6F,71),
            Meteo("Lyon",16.2F,3),
            Meteo("Toulon", 19.7F, 0)
        )
    }
}
