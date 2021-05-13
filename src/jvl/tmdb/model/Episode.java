
package jvl.tmdb.model;

import java.io.File;
import java.io.IOException;
import jvl.tmdb.AbstractTMDBModel;
import org.json.JSONObject;


public class Episode extends AbstractTMDBModel<Episode>
{
    private String air_date;
    //Crew
    private int episode_number;
    //Guest stars
    private String name;
    private String overview;
    private String production_code;
    private int season_number;
    private String still_path;
    private double vote_average;
    private int vote_count;
    

    public String getAirDate() 
    {
        return air_date;
    }
    
    public int getEpisodeNumber() 
    {
        return episode_number;
    }
    
    public String getName() 
    {
        return name;
    }

    public String getOverview() 
    {
        return overview;
    }

    public String getProductionCode() 
    {
        return production_code;
    }

    public int getSeasonNumber()
    {
            
        return season_number;
    }

    public String getStillPath() 
    {
        return still_path;
    }

    public double getVoteAverage() 
    {
        return vote_average;
    }

    public int getVoteCount() 
    {
        return vote_count;
    }


    public static Episode parseString(String data, Configuration config) 
    {
        return new Episode().constructModel(data, config);
    }
    
    public static Episode parseString(File data, Configuration config) throws IOException 
    {
        return new Episode().constructModel(data, config);
    }

    @Override
    protected Episode constructModel(String data, Configuration config) 
    {
        //Episode episode = new Episode();
        this.config = config;
        this.raw_json = data;
        JSONObject json = new JSONObject(data);
        
        this.air_date = json.optString("air_date");
        this.episode_number = json.getInt("episode_number");
        this.name = json.getString("name");
        this.overview = json.getString("overview");
        this.tmdb_id = json.getInt("id");
        this.production_code = json.optString("production_code");
        this.season_number = json.getInt("season_number");
        this.still_path = json.optString("still_path");
        this.vote_average = json.getDouble("vote_average");
        this.vote_count = json.getInt("vote_count");

        return this;
    }

}
