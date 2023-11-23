package ComicCode.scanner;

import utils.YearInterval;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

import comicanalysis.ComicFilmAnalyzer;

import static comicanalysis.ComicFilmAnalyzer.entryToNumOfFilms;

public class WikipediaComicFilmScannerMain {
	private static final String DIR_PREFIX = "";

	public static void main(String[] args) throws MalformedURLException, IOException {
		/*
		 * Wir arbeiten mit einer lokalen Datei / Kopie einer Wikipediaseite.
		 * Referenz auf die Datei wird als URI String übergeben: file:///<absoluter Pfad zur Datei>
		 * Path wiki3DFilmLocal = Paths.get("Listevon3D-Filmen–Wikipedia.html") erzeugt einen relativen
		 * Pfad zur der Datei (Bezug das aktuelle Projekt)
		 * wiki3DFilmLocal.toAbsolutePath() erzeugt den absoluten Pfad, der als
		 * Argument übergeben wird.
		 */
		Path wikiComicLocal = Paths.get("Liste von Comicverfilmungen.html");
		ComicFilmScanner wp1 = new ComicFilmScanner("file:///" + wikiComicLocal.toAbsolutePath());
		//wp1.echoPage();

		/*
		 * Parsen der Seite und Einsammeln der Liste von 3D-Filmen eines Jahres / eines Zeitraums
		 * in einem Verzeichnis (einer Java-Map).
		 */
		long start = System.currentTimeMillis();
		Map<String, Map<YearInterval, List<String>>> comicFilmMap = wp1.contentToComicMap();
//		System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
//		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//		ppMap(comicFilmMap);

//		List<String> allComics = ComicFilmAnalyzer.allComics(comicFilmMap);
//		for (String comic:allComics) {
//			System.out.printf("\n%s", comic);
//		}

//		Set<String> allFilms = ComicFilmAnalyzer.allFilms(comicFilmMap);
//		for (String film:allFilms) {
//			System.out.printf("\n%s", film);
//		}

		YearInterval testInterval = new YearInterval(Year.of(2000), Year.of(2001));

//		Set<String> allComicsInYear = ComicFilmAnalyzer.collectComicsIn(comicFilmMap, testInterval);

//		Set<String> allFilmsIn = ComicFilmAnalyzer.collectFilmsIn(comicFilmMap, testInterval);
//		for (String film:allFilmsIn) {
//			System.out.printf("\n%s", film);
//		}

//		Set<String> allComicsIn = ComicFilmAnalyzer.collectComicsIn(comicFilmMap, testInterval);
//		for (String film:allComicsIn) {
//			System.out.printf("\n%s", film);
//		}

//		List<String> comicsStartingWith = ComicFilmAnalyzer.comicsStartingWith(comicFilmMap, "The");
//		for (String comic:comicsStartingWith) {
//			System.out.printf("\n%s", comic);
//		}

//		for (Map<YearInterval, List<String>> filmMap:comicFilmMap.values()) {
//			System.out.printf("\n%s : %d",filmMap.keySet().iterator().next().toString(),ComicFilmAnalyzer.mapToNumOfFilms(filmMap));
//		}

//		comicFilmMap.entrySet().stream().forEach(entry -> System.out.printf("\n%s", entryToNumOfFilms().applyAsInt(entry)));

//		System.out.printf("\n%s", ComicFilmAnalyzer.comicWithMostFilms(comicFilmMap));

//		System.out.printf("\n%s", ComicFilmAnalyzer.comicsWithMostFilms(comicFilmMap));

//		System.out.printf("\n%s", ComicFilmAnalyzer.anyFilmIn(comicFilmMap, testInterval));

//		for (YearInterval interval : ComicFilmAnalyzer.groupComicsByYearInterval(comicFilmMap).keySet()) {
//			System.out.printf("\n%s", ComicFilmAnalyzer.groupComicsByYearInterval(comicFilmMap));
//		}
	}
	
//	private static <K extends Comparable<? super K>> void ppMap(Map<K, Map<YearInterval,List<String>>> aMap) {
//		List<K> al = new ArrayList<K>(aMap.keySet());
//		Collections.sort(al);
//		for (K key : al) {
//			System.out.printf("%s->", key);
//			for (Map.Entry<YearInterval,List<String>> entry : aMap.get(key).entrySet()) {
//				System.out.printf("%s %s ", entry.getKey(),entry.getValue().toString());
//			}
//			System.out.println();
//		}
//	}


}
