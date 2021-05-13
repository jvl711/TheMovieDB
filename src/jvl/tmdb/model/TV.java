
package jvl.tmdb.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import jvl.tmdb.AbstractTMDBModel;
import org.json.JSONArray;
import org.json.JSONObject;


public class TV extends AbstractTMDBModel<TV>
{

    private String backdrop_path;
    //created_by
    private int [] episode_runtime; 
    private String first_air_date;
    private HashMap<Integer, String> genres;
    private String homepage;
    private boolean in_production;
    //languages
    private String last_air_date;
    //last_episode_to_air
    private String name;
    //next_episode_to_air
    //networks
    private int number_of_episodes;
    private int number_of_seasons;
    //origin_country
    private String original_language;
    private String original_name;
    private String overview;
    private double popularity;
    private String poster_path;
    //production_companies
    //seasons
    private String status;
    private String type;
    private double vote_average;
    private int vote_count;
    
    private ArrayList<Season> seasons = new ArrayList();
    
    public String [] getGenres()
    {
        String [] values = new String[this.genres.size()];
        
        values = this.genres.values().toArray(values);
                
        return values;
    }

    public ArrayList<Season> getSeasons()
    {
        return seasons;
    }
    
    public boolean hasSeason(int seasonNumber)
    {
        if(this.getNumberOfSeasons() < seasonNumber)
        {
            return false;
        }
        
        return true;
    }
    
    public String getBackdropPath() 
    {
        return backdrop_path;
    }

    public int getEpisodeRuntime() 
    {
        //TODO: Enhance to return the shortest runtime....
        
        return episode_runtime[0];
    }

    public String getFirstAirDate() 
    {
        return first_air_date;
    }

    public String getHomepage() 
    {
        return homepage;
    }

    public int getTmdb_id() 
    {
        return tmdb_id;
    }

    public boolean isInProduction() 
    {
        return in_production;
    }

    public String getLastAirDate() 
    {
        return last_air_date;
    }

    public String getName() 
    {
        return name;
    }

    public int getNumberOfEpisodes() 
    {
        return number_of_episodes;
    }

    public int getNumberOfSeasons() 
    {
        return number_of_seasons;
    }

    public String getOriginalLanguage() 
    {
        return original_language;
    }

    public String getOriginalName() 
    {
        return original_name;
    }

    public String getOverview() 
    {
        return overview;
    }

    public double getPopularity() 
    {
        return popularity;
    }

    public String getPosterPath() 
    {
        return poster_path;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getType() 
    {
        return type;
    }
    
    public double getVoteAverage() 
    {
        return vote_average;
    }

    public int getVoteCount() 
    {
        return vote_count;
    }

    public static TV parse(String data, Configuration config) 
    {
        return new TV().constructModel(data, config);
    }
    
    public static TV parse(File data, Configuration config) throws IOException 
    {
        return new TV().constructModel(data, config);
    }

    @Override
    protected TV constructModel(String data, Configuration config)
    {
        //TV show = new TV();
        this.config = config;
        this.raw_json = data;
        JSONObject json = new JSONObject(data);
        
        this.backdrop_path = json.optString("backdrop_path");
        
        JSONArray runtimes = json.getJSONArray("episode_run_time");
        this.episode_runtime = new int[runtimes.length()];
        
        for (int i = 0; i < runtimes.length(); i++)
        {
            this.episode_runtime[i] = runtimes.getInt(i);
        }
        
        this.first_air_date = json.getString("first_air_date");
        
        
        this.genres = new HashMap();        
        JSONArray temp_genres = json.getJSONArray("genres");
        
        for(int i = 0; i < temp_genres.length(); i++)
        {
            this.genres.put(temp_genres.getJSONObject(i).getInt("id"), temp_genres.getJSONObject(i).getString("name"));
        }

        this.homepage = json.getString("homepage");
        this.tmdb_id = json.getInt("id");
        this.in_production = json.getBoolean("in_production");
        this.last_air_date = json.getString("last_air_date");
        this.name = json.getString("name");
        this.number_of_episodes = json.getInt("number_of_episodes");
        this.number_of_seasons = json.getInt("number_of_seasons");
        this.original_language = json.getString("original_language");
        this.original_name = json.getString("original_name");
        this.overview = json.getString("overview");
        this.popularity = json.getDouble("popularity");
        this.poster_path = json.optString("poster_path", "");
        
        JSONArray temp_seasons = json.getJSONArray("seasons");
        
        for(int i = 0; i < temp_seasons.length(); i++)
        {
            this.seasons.add(Season.parse(temp_seasons.getJSONObject(i).toString(), config));
        }
        
        this.status = json.getString("status");
        this.type = json.getString("type");
        this.vote_average = json.getDouble("vote_average");
        this.vote_count = json.getInt("vote_count");
        
        return this;
    }

}
