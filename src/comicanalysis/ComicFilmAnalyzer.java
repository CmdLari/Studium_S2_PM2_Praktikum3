package comicanalysis;

import utils.YearInterval;

import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComicFilmAnalyzer {

    /**
     * Die Methode berechnet aus der Map, die Comics auf Verfilmungen abbildet,
     * die Liste aller Comics.
     *
     * @param comicFilmMap Map, die Comics auf Verfilmungen abbildet
     * @return Liste der Comics
     */
    public static List<String> allComics(Map<String, Map<YearInterval, List<String>>> comicFilmMap) {

        return comicFilmMap.keySet().stream().collect(Collectors.toList());
    }

    /**
     * Die Methode berechnet aus der Map, die Comics auf Verfilmungen abbildet,
     * die Menge aller Verfilmungen.
     *
     * @param comicFilmMap Map, die Comics auf Verfilmungen abbildet
     * @return Menge der Verfilmungen
     */

    public static Set<String> allFilms(Map<String, Map<YearInterval, List<String>>> comicFilmMap) {

        return comicFilmMap.values().stream().flatMap(filmMap -> filmMap.values().stream())
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Die Methode berechnet aus der Map, die Comics auf Verfilmungen abbildet,
     * die Menge aller Verfilmungen, die in einem gegebenen Jahresintervall liegen.
     *
     * @param comicFilmMap Map, die Comics auf Verfilmungen abbildet
     * @param yearInterval eine Jahresangabe
     * @return Menge der Verfilmungen in dem geforderten Jahr
     */


    public static Set<String> collectFilmsIn(Map<String, Map<YearInterval, List<String>>> comicFilmMap,
                                         YearInterval yearInterval) {
        return comicFilmMap.values().stream().map(filmMap->filmMap.entrySet().stream().
                filter(yearFilter -> yearInterval.contains(yearFilter.getKey())).
                flatMap(filteredMap -> filteredMap.getValue().stream()).collect(Collectors.toSet())
                ).flatMap(Set::stream).collect(Collectors.toSet());
    }

    /**
     * Die Methode berechnet aus der Map, die Comics auf Verfilmungen abbildet,
     * die Liste aller Comics, die in einem gegebenen Jahresintervall verfilmt wurden.
     *
     * @param comicFilmMap Map, die Comics auf Verfilmungen abbildet
     * @param yearInterval eine Jahresangabe
     * @return Liste der Comics mit Verfilmungen in dem geforderten Jahr
     */
    public static Set<String> collectComicsIn(Map<String, Map<YearInterval, List<String>>> comicFilmMap,
                                         YearInterval yearInterval) {
        return comicFilmMap.entrySet().stream().filter(filtered -> filtered.getValue().containsKey(yearInterval)).
                map(cleanedMap -> cleanedMap.getKey()).collect(Collectors.toSet());
    }

    /**
     * Die Methode berechnet aus der Map, die Comics auf Verfilmungen abbildet,
     * die Liste aller Comics, die mit einem gegebenen Präfix beginnen.
     *
     * @param comicFilmMap Map, die Comics auf Verfilmungen abbildet
     * @param prefix der Präfix, mit dem die Comicnamen beginnen.
     * @return Liste der Comics mit dem geforderten Präfix
     */
    public static List<String> comicsStartingWith(Map<String, Map<YearInterval, List<String>>> comicFilmMap,
                                          String prefix) {
        return comicFilmMap.keySet().stream().filter(filtered -> filtered.startsWith(prefix)).collect(Collectors.toList());
    }

    /**
     * Die Methode bildet eine Map, die Jahresangaben auf Verfilmungen abbildet,
     * auf die Anzahl der Verfilmungen ab.
     *
     * @param filmMap Map, die Comics auf Verfilmungen abbildet
     * @return Summe aller Verfilmungen in der Map.
     */
    public static int mapToNumOfFilms(Map<YearInterval, List<String>> filmMap) {
        return filmMap.values().stream().mapToInt(List::size).sum();
    }



    /**
     * Die Methode liefert als Ergebnis eine ToIntFunction die Map.Entry<String,Map<YearInterval,List<String>>> Paare auf
     * die Gesamtzahl
     * der Verfilmungen abbildet. Der Typ der Lambda-Variable wird aus dem Typargument der ToIntFuntcion inferiert.
     *
     * @return die ToIntFunction für das Mapping
     */
    public static ToIntFunction<Map.Entry<String,Map<YearInterval,List<String>>>> entryToNumOfFilms() {

//        ToIntFunction<Map.Entry<String,Map<YearInterval,List<String>>>> filmsPerComic =
//                entry -> mapToNumOfFilms(entry.getValue());
//
//        return filmsPerComic;
        return entry -> mapToNumOfFilms(entry.getValue());

    }

    /**
     * Die Methode berechnet aus der Map, die Comics auf Verfilmungen abbildet,
     * einen Comic mit den meisten Verfilmungen.
     *
     * @param comicFilmMap Map, die Comics auf Verfilmungen abbildet
     * @return ein Comic mit den meisten Verfilmungen
     */
    public static String comicWithMostFilms(Map<String, Map<YearInterval, List<String>>> comicFilmMap) {

        return comicFilmMap.entrySet().stream().
                max(Comparator.comparingInt(entry -> entryToNumOfFilms().applyAsInt(entry))).
                map(maxMap -> maxMap.getKey()).orElse(null);
    }


    /**
     * Die Methode berechnet aus der Map, die Comics auf Verfilmungen abbildet,
     * alle Comics mit den meisten Verfilmungen.
     *
     * @param comicFilmMap Map, die Comics auf Verfilmungen abbildet
     * @return ein Comic mit den meisten Verfilmungen
     */
    public static List<String> comicsWithMostFilms(Map<String, Map<YearInterval, List<String>>> comicFilmMap) {

        int counter = comicFilmMap.entrySet().stream().mapToInt(entryToNumOfFilms()).max().orElse(0);

        return comicFilmMap.entrySet().stream().filter(entry -> entryToNumOfFilms().applyAsInt(entry)==counter).
                map(Map.Entry::getKey).collect(Collectors.toList());
    }


    /**
     * Die Methode prüft für eine Map, die Comics auf Verfilmungen abbildet,
     * ob es eine Verfilmung gibt, die in einem gegebenen Jahresintervall liegt.
     *
     * @param comicFilmMap Map, die Comics auf Verfilmungen abbildet
     * @param yearInterval eine Jahresangabe
     * @return true, wenn es eine Verfilmung gibt, die in einem gegebenen Jahresintervall liegt.
     */
    public static boolean anyFilmIn(Map<String, Map<YearInterval, List<String>>> comicFilmMap,
                                             YearInterval yearInterval) {
        return comicFilmMap.values().stream().anyMatch(entry -> entry.containsKey(yearInterval));
    }

    /**
     * Die Methode gruppiert für eine Map, die Comics auf Verfilmungen abbildet,
     * die Comics nach Jahresintervall. Arbeiten Sie mit einem geschachtelten forEach auf Maps
     * und der Methode computeIfAbsent.
     *
     * @param comicFilmMap Map, die Comics auf Verfilmungen abbildet
     * @return ein Map, die Jahres Intervalle auf Mengen von Comics zuordnet.
     */
    public static Map<YearInterval,Set<String>> groupComicsByYearInterval(Map<String, Map<YearInterval, List<String>>> comicFilmMap) {
        Map<YearInterval, Set<String>> comicsPerYear = new HashMap<>();

        comicFilmMap.forEach((comicKey, innerMapValue) -> {
            innerMapValue.forEach((yearIntervalKey, filmsValue) ->
                    comicsPerYear.computeIfAbsent(yearIntervalKey, hashSetComics -> new HashSet<>()).add(comicKey)
            );
        });

        return comicsPerYear;
    }

}
