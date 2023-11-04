
package jvl.tmdb.model;

import org.json.JSONObject;


public class SearchResultMovie 
{

    //private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    //genre_ids array[integer]
    private int tmdb_id;
    private String original_title;
    private String original_language;
    private String title;
    //private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;

    
    public String getReleaseDate()
    {
        return release_date;
    }
    
    public String getOverview()
    {
        return overview;
    }
    
    public boolean isAdult()
    {
        return adult;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public int getTmdbID() 
    {
        return tmdb_id;
    }
    
    public static SearchResultMovie parseString(String data)
    {
        SearchResultMovie resultMovie = new SearchResultMovie();
        
        JSONObject json = new JSONObject(data);
        
        //resultMovie.poster_path = json.optString("poster_path", "");
        resultMovie.adult = json.getBoolean("adult");
        resultMovie.overview = json.getString("overview");
        resultMovie.release_date = json.optString("release_date");
        resultMovie.tmdb_id = json.getInt("id");
        resultMovie.original_title = json.getString("original_title");
        resultMovie.original_language = json.getString("original_language");
        resultMovie.title = json.getString("title");
        //resultMovie.backdrop_path = json.optString("backdrop_path", "");
        resultMovie.popularity = json.getDouble("popularity");
        resultMovie.vote_count = json.getInt("vote_count");
        resultMovie.video = json.getBoolean("video");
        resultMovie.vote_average = json.getDouble("vote_average");

        return resultMovie;
    }
    
}
