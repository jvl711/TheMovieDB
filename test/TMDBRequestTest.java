/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import jvl.tmdb.ConfigAPI;
import jvl.tmdb.MovieAPI;
import jvl.tmdb.RateLimitException;
import jvl.tmdb.SearchAPI;
import jvl.tmdb.TVAPI;
import jvl.tmdb.TMDBRequest;
import jvl.tmdb.model.Cast;
import jvl.tmdb.model.Configuration;
import jvl.tmdb.model.Credits;
import jvl.tmdb.model.Crew;
import jvl.tmdb.model.Episode;
import jvl.tmdb.model.Images;
import jvl.tmdb.model.Movie;
import jvl.tmdb.model.MovieRelease;
import jvl.tmdb.model.MovieReleases;
import jvl.tmdb.model.SearchResultMovie;
import jvl.tmdb.model.SearchResultShow;
import jvl.tmdb.model.SearchResults;
import jvl.tmdb.model.Season;
import jvl.tmdb.model.TV;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TMDBRequestTest 
{    
    private static String movieResponse = "{\"adult\":false,\"backdrop_path\":\"/lmZFxXgJE3vgrciwuDib0N8CfQo.jpg\",\"belongs_to_collection\":{\"id\":86311,\"name\":\"The Avengers Collection\",\"poster_path\":\"/qJawKUQcIBha507UahUlX0keOT7.jpg\",\"backdrop_path\":\"/zuW6fOiusv4X9nnW3paHGfXcSll.jpg\"},\"budget\":300000000,\"genres\":[{\"id\":12,\"name\":\"Adventure\"},{\"id\":878,\"name\":\"Science Fiction\"},{\"id\":28,\"name\":\"Action\"}],\"homepage\":\"http://marvel.com/movies/movie/223/avengers_infinity_war_part_1\",\"id\":299536,\"imdb_id\":\"tt4154756\",\"original_language\":\"en\",\"original_title\":\"Avengers: Infinity War\",\"overview\":\"As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.\",\"popularity\":210.234,\"poster_path\":\"/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg\",\"production_companies\":[{\"id\":420,\"logo_path\":\"/hUzeosd33nzE5MCNsZxCGEKTXaQ.png\",\"name\":\"Marvel Studios\",\"origin_country\":\"US\"}],\"production_countries\":[{\"iso_3166_1\":\"US\",\"name\":\"United States of America\"}],\"release_date\":\"2018-04-25\",\"revenue\":2046239637,\"runtime\":149,\"spoken_languages\":[{\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Released\",\"tagline\":\"An entire universe. Once and for all.\",\"title\":\"Avengers: Infinity War\",\"video\":false,\"vote_average\":8.3,\"vote_count\":7979}";
    public static final Logger LOG = Logger.getLogger(TMDBRequestTest.class.getName());;
    public static final String apikey = "9f2734102601193cadb25780ea358edc";
    
    @BeforeClass
    public static void setUpClass() 
    {

    }
    
    @AfterClass
    public static void tearDownClass() 
    {

    }
    
    @Before
    public void setUp() 
    {
        //StreamHandler handler = new StreamHandler(System.out, new SimpleFormatter());
        
        
        
        System.out.println("Log handlers: " + Logger.getLogger("jvl").getHandlers().length);
                
        
        for(int i = Logger.getLogger("jvl").getHandlers().length - 1; i >= 0; i--)
        {
            System.out.println("Removing log handler: " + Logger.getLogger("jvl").getHandlers()[i]);
            Logger.getLogger("").removeHandler(Logger.getLogger("jvl").getHandlers()[i]);
        }
        
        
        Logger.getLogger("jvl").setLevel(Level.FINE);
        //Logger.getLogger("").setLevel(Level.FINE);
        //Logger.getLogger("").setUseParentHandlers(false);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void TestLogging()
    {
        //LOG.setLevel(Level.FINE);
        LOG.log(Level.FINE, "Fine Level");
        LOG.log(Level.INFO, "INFO Level");
        
    }
    

    
    @Test 
    public void GetMovieImages() throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        
        LOG.log(Level.INFO, "TEST");
        
        
        Logger.getLogger("jvl.tmdb.TMDBRequest").setLevel(Level.INFO);
        Logger.getLogger("sun").setLevel(Level.WARNING);
        
        Images images = MovieAPI.getImages(request, 445571, false);
        Configuration config = ConfigAPI.getConfig(request, false);
        
        
        LOG.info("Backdrops: " + images.getBackdrops().size());
        LOG.info("Posters: " + images.getPosters().size());
        LOG.info("Vlaid size at 400: " + images.getBackdrops().get(0).getValidSize(400));
        
        for(int i = 0; i < config.getBackdropSizes().length; i++)
        {
            LOG.info(config.getBackdropSizes()[i]);
        }
        
        /*
        for(int i = 0; i < images.getBackdrops().size(); i++)
        {
            System.out.println(images.getBackdrops().get(i).getFileName());
            System.out.println("\tvote count: " + images.getBackdrops().get(i).getVoteCount());
            System.out.println("\tvote average: " + images.getBackdrops().get(i).getVoteAverage());
        }
        */
        
        //ArrayList<Image> posters = images.getPosters();
     
        //Image poster = posters.get(0);
        
        //System.out.println("Get highest rated poster @400 URL: " + images.getBackdrop().getURL(1920));
        //System.out.println("Get valid size : " + images.getBackdrop().getValidSize(99999));
        
    }
    

    
    @Test
    public void SearchMovies()throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        
        SearchResults results = SearchAPI.searchMovies(request, "luca", false);
        
        ArrayList<SearchResultMovie> movies = results.getMovies();
        
        System.out.println("Page: " + results.getPage());
        System.out.println("Total Results: " + results.getTotalResults());
        System.out.println("Total Pages: " + results.getTotalPages());
        System.out.println("Results: " + movies.size());

        for(int i = 0; i < movies.size(); i++)
        {
            System.out.println("\tTitle: " + movies.get(i).getTitle());
            System.out.println("\t\tTitle: " + movies.get(i).getReleaseDate());
            System.out.println("\t\tID: " + movies.get(i).getTmdbID());
        }
        
    }
    
    @Test
    public void SearchShows() throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        SearchResults results = SearchAPI.searchTV(request, "Dark", false);

        ArrayList<SearchResultShow> shows = results.getShows();
        
        System.out.println("Page: " + results.getPage());
        System.out.println("Total Results: " + results.getTotalResults());
        System.out.println("Total Pages: " + results.getTotalPages());
        System.out.println("Results: " + shows.size());        
        
        
        for(int i = 0; i < shows.size(); i++)
        {
            System.out.println("\tTitle: " + shows.get(i).getName());
            System.out.println("\t\tID: " + shows.get(i).getTmdbID());
            
        }
        
    }
    
    @Test
    public void GetShow() throws IOException, RateLimitException
    {
        //5715
        TMDBRequest request = new TMDBRequest(apikey);
        
        TV show = TVAPI.getDetails(request, 133541, false);
        
        System.out.println("Name: " + show.getName());
        System.out.println("Original Name: " + show.getOriginalName());
        System.out.println("Overview: " + show.getOverview());
        System.out.println("Status: " + show.getStatus());
        System.out.println("Type: " + show.getType());
        System.out.println("ID: " + show.getTmdb_id());
        System.out.println("First Air Date: " + show.getFirstAirDate());
        System.out.println("Genres: " + Arrays.toString(show.getGenres()));
        
        System.out.println("Season records: " + show.getSeasons().size());

        for(int i = 0; i < show.getSeasons().size(); i++)
        {
            System.out.println("\tSeason number: " + show.getSeasons().get(i).getSeasonNumber());
            System.out.println("\tEpisode count: " + show.getSeasons().get(i).getEpisodeCount());
            System.out.println("\tName: " + show.getSeasons().get(i).getName());
            System.out.println("\tOverview: " + show.getSeasons().get(i).getOverview());
        }
    }
    
    @Test
    public void GetShowCredits() throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        
        Credits credits = TVAPI.getCredits(request, 1399, true);
        
        ArrayList<Cast> ca = credits.getCast();
        
        for(int i = 0; i < ca.size(); i++)
        {
            Cast cast = ca.get(i);
            System.out.println(cast.getCharacter());
            System.out.println("\tOrder: " + cast.getOrder());
            System.out.println("\tName: " + cast.getName());
            System.out.println("\tGender: " + cast.getGenderString());
        }
        
        ArrayList<Crew> cr = credits.getCrew();
        
        for(int i = 0; i < cr.size(); i++)
        {
            Crew crew = cr.get(i);
            System.out.println(crew.getJob());
            System.out.println("\tName: " + crew.getName());
            
        }
    }
    
    @Test
    public void GetShowImages() throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        
        Images images = TVAPI.getImages(request, 69629, false);
        
        System.out.println("Backdrops count: " + images.getBackdrops().size());
        System.out.println("Posterss count: " + images.getPosters().size());
        
        images.getPoster().saveImage(new File("d:\\" + images.getPoster().getFileName()), 600);
        images.getBackdrop().saveImage(new File("d:\\" + images.getBackdrop().getFileName()), 1920);
    }
    
    @Test
    public void GetSeason() throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        
        Season season = TVAPI.getSeasonDetails(request, 69629, 3, false);
        
        System.out.println("Season name: " + season.getName());
        
        ArrayList<Episode> episodes = season.getEpisodes();
        
        for(int i = 0; i < episodes.size(); i++)
        {
            System.out.println("Episode: " + episodes.get(i).getName());
            System.out.println("\tSeason Number: " + episodes.get(i).getSeasonNumber());
            System.out.println("\tEpisode Number: " + episodes.get(i).getEpisodeNumber());
            System.out.println("\tOverview: " + episodes.get(i).getOverview());
        }
        
    }
    
    @Test
    public void GetSeasonImages() throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        
        Images images = TVAPI.getSeasonImages(request, 69629, 2, false);
        
        System.out.println("Posters size: " + images.getPosters().size());
        System.out.println("Backdrops size: " + images.getBackdrops().size());
        
        images.getPoster().saveImage(new File("d:\\" + images.getPoster().getFileName()), 600);
    }
    
    @Test
    public void GetEpisodeImages() throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        
        Images images = TVAPI.getEpisodeImages(request, 69629, 2, 2, false);
        
        Configuration config = ConfigAPI.getConfig(request, false);

        //System.out.println("Still size: " + images.getStills().size());
        //System.out.println("Get still url: " + images.getStill().getURL(600));
        
        images.getStill().saveImage(new File("d:\\" + images.getStill().getFileName()), 1000);
    }
    
    @Test
    public void GetMovie() throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        
        Movie movie = MovieAPI.getDetails(request, 299536, false);
        
        System.out.println("Adult: " + movie.isAdult());
        //System.out.println("Backdrop: " + movie.getBackdrop());
        System.out.println("Backdrop URL: " + movie.getBackdropURL());
        System.out.println("Homepage: " + movie.getHomepage());
        System.out.println("Budget: " + movie.getBudget());
        System.out.println("Genres: " + Arrays.toString(movie.getGenres()));
        System.out.println("IMDB: " + movie.getImdbID());
        System.out.println("Original Title: " + movie.getOriginalTitle());
        System.out.println("Overview: " + movie.getOverview());
        //System.out.println("Poster Path: " + movie.getPosterPath());
        System.out.println("Poster URL: " + movie.getPosterURL());
        System.out.println("Release Date: " + movie.getReleaseDate());
        System.out.println("Status: " + movie.getStatus());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Budget: " + movie.getBudget());
        System.out.println("Populartiy: " + movie.getPopularity());
        System.out.println("Revenue: " + movie.getRevenue());
        System.out.println("Runtime: " + movie.getRuntime());
        System.out.println("ID: " + movie.getTmdbID());
        System.out.println("Vote Average: " + movie.getVoteAverage());
        System.out.println("Vote Count: " + movie.getVoteCount());
        System.out.println("Collection ID: " + movie.getCollectionID());

    }
    
    @Test
    public void GetMovieCredits() throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        
        Credits credits = MovieAPI.getCredits(request, 299536, true);
        
        ArrayList<Cast> ca = credits.getCast();
        
        for(int i = 0; i < ca.size(); i++)
        {
            Cast cast = ca.get(i);
            System.out.println(cast.getCharacter());
            System.out.println("\tOrder: " + cast.getOrder());
            System.out.println("\tName: " + cast.getName());
            System.out.println("\tGender: " + cast.getGenderString());
        }
        
        ArrayList<Crew> cr = credits.getCrew();
        
        for(int i = 0; i < cr.size(); i++)
        {
            Crew crew = cr.get(i);
            System.out.println(crew.getJob());
            System.out.println("\tName: " + crew.getName());
            
        }
    }
    
    @Test
    public void GetMovieDirector() throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        
        Credits credits = MovieAPI.getCredits(request, 420809, true);
        
        
        
        ArrayList<Crew> cr = credits.getCrew();
        
        for(int i = 0; i < cr.size(); i++)
        {
            Crew crew = cr.get(i);
        
            if(cr.get(i).getJob().equalsIgnoreCase("director"))
            {
                //String name = cr.get(i).getName();
                System.out.println("Director: " + cr.get(i).getName());
                System.out.println("Director: " + cr.get(i).getDepartment());
            }
            
            
        }
    }    
    
    @Test
    public void getMovieParentalRating() throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        
        MovieReleases releases = MovieAPI.getReleases(request, 136799, true);
        
        System.out.println("Parental Rating: " + releases.getParentalRating());
    }
    
    @Test
    public void getMovieReleases() throws IOException, RateLimitException
    {
        TMDBRequest request = new TMDBRequest(apikey);
        
        ArrayList<MovieRelease> releases = MovieAPI.getReleases(request, 348350, true).getReleases();
        
        for(int i = 0; i < releases.size(); i++)
        {
            System.out.println("Release: " + i);
            System.out.println("\tCountry Code: " + releases.get(i).getCountryCode());
            System.out.println("\tCertification: " + releases.get(i).getCertification());
            System.out.println("\tLanguage Code: " + releases.get(i).getLanguageCode());
            System.out.println("\tNote: " + releases.get(i).getNote());
            System.out.println("\tRelease Date: " + releases.get(i).getReleaseDate());
            System.out.println("\tRelease Type: " + releases.get(i).getReleaseTypeCode());
        }
        
    }
    
    /*
    @Test
    public void GetMovieImages() throws IOException
    {
        TMDBRequest request = new TMDBRequest("9f2734102601193cadb25780ea358edc");
        
        TMDBResponse response = MovieAPI.GetImages(request, "299536");
        
        System.out.println("Status Code: " + response.statusCode);
        System.out.println("Rate Limit: " + response.rateLimit);
        System.out.println("Rate Limit Remaining: " + response.rateRemaining);
        System.out.println("Data: " + response.data);
    }
    */
}
