package jvl.tmdb.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import jvl.tmdb.AbstractTMDBModel;
import org.json.JSONArray;
import org.json.JSONObject;

public class Movie extends AbstractTMDBModel<Movie>
{
    private boolean adult;
    private String backdrop;
    private int budget;
    private HashMap<Integer, String> genres;
    private String homepage;
    private String imdb_id;
    private int collection_id;
    private boolean in_collection;
    //"original_language":"en"
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    //"production_companies"
    //"production_countries"
    private String release_date; //YYYY-MM-DD
    private int revenue;
    private int runtime;
    //"spoken_languages"
    private String status; //Rumored, Planned, In Production, Post Production, Released, Canceled
    private String tagline;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;
    
    public boolean isAdult() 
    {
        return adult;
    }

    public int getBudget() 
    {
        return budget;
    }

    public String [] getGenres()
    {
        String [] values = new String[this.genres.size()];
        
        values = this.genres.values().toArray(values);
                
        return values;
    }
    
    public String getHomepage() 
    {
        return homepage;
    }

    public String getImdbID() 
    {
        return imdb_id;
    }

    public String getOriginalTitle() 
    {
        return original_title;
    }

    public String getOverview() 
    {
        return overview;
    }

    public double getPopularity() 
    {
        return popularity;
    }

    public String getPosterURL() 
    {
        
        return config.getSecureImageBaseURL() + "original" + poster_path;
    }
    
    public String getBackdropURL() 
    {
        return config.getSecureImageBaseURL() + "original" + backdrop;
    }

    /**
     * The date the movie was released
     * 
     * @return Date in the format YYYY-MM-DD or empty string
     */
    public String getReleaseDate() 
    {
        return release_date;
    }
    
    /**
     * Returns the year if a release date exists, or an empty string otherwise
     * @return The year in the format YYYY or empty string
     */
    public String getReleaseYear()
    {
        if(this.release_date.length() > 0)
        {
            return this.release_date.split("-")[0];
        }
        
        return "";
    }

    public int getRevenue() 
    {
        return revenue;
    }

    public int getRuntime() 
    {
        return runtime;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getTagline() 
    {
        return tagline;
    }

    public String getTitle() 
    {
        return title;
    }

    public boolean isVideo() 
    {
        return video;
    }

    public double getVoteAverage() 
    {
        return vote_average;
    }

    public int getVoteCount() 
    {
        return vote_count;
    }
    
    public int getCollectionID()
    {
        return this.collection_id;
    }
    
    public boolean isInCollection()
    {
        return this.in_collection;
    }
    
    @Override
    protected Movie constructModel(String data, Configuration config) 
    {
        this.config = config;
        JSONObject json = new JSONObject(data);
        
        this.raw_json = data;
        this.adult = json.getBoolean("adult");
        this.backdrop = json.optString("backdrop_path", "");   
        this.budget = json.getInt("budget");
    
        this.genres = new HashMap();        
        JSONArray temp_genres = json.getJSONArray("genres");
        
        for(int i = 0; i < temp_genres.length(); i++)
        {
            this.genres.put(temp_genres.getJSONObject(i).getInt("id"), temp_genres.getJSONObject(i).getString("name"));
        }
        
        this.homepage = json.optString("homepage", "");
        this.tmdb_id = json.getInt("id");
        this.imdb_id = json.optString("imdb_id", "");
        this.original_title = json.getString("original_title");
        this.overview = json.optString("overview", "");
        this.popularity = json.getDouble("popularity");
        this.poster_path = json.optString("poster_path", "");
        this.release_date = json.getString("release_date");
        this.revenue = json.getInt("revenue");
        this.runtime = json.optInt("runtime");
        this.status = json.getString("status");
        this.tagline = json.optString("tagline", "");
        this.title = json.getString("title");
        this.video = json.getBoolean("video");
        this.vote_average = json.getDouble("vote_average");
        this.vote_count = json.getInt("vote_count");
        
        if(!json.isNull("belongs_to_collection"))
        {
            this.collection_id = json.getJSONObject("belongs_to_collection").getInt("id");
            this.in_collection = true;
        }
        else
        {
            this.in_collection = false;
        }
        
        return this;
    }
    
    public static Movie parse(String data, Configuration config) 
    {
        return new Movie().constructModel(data, config);
    }

    public static Movie parse(File data, Configuration config) throws FileNotFoundException, IOException
    {
        return new Movie().constructModel(data, config);
    }

    
}
