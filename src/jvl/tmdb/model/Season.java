
package jvl.tmdb.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jvl.tmdb.AbstractTMDBModel;
import org.json.JSONArray;
import org.json.JSONObject;

public class Season extends AbstractTMDBModel<Season>
{
    private String air_date;
    private int episode_count;
    private String name;
    private String overview;
    private String poster_path;
    private int season_number;
    private ArrayList<Episode> episodes;
    
    public String getAirDate() 
    {
        return air_date;
    }

    public int getEpisodeCount() 
    {
        return episode_count;
    }


    public int getTmdb_id() 
    {
        return tmdb_id;
    }
    
    public String getName() 
    {
        return name;
    }
    
    public String getOverview() 
    {
        return overview;
    }

    public String getPosterPath()
    {
        return poster_path;
    }

    
    public int getSeasonNumber() 
    {
        return season_number;
    }
    
    /**
     * Gets episodes if this object was loaded from the seasons call.  If it 
     * was loaded from the Show object it will be null.
     * 
     * @return Episodes or null if not in source
     */
    public ArrayList<Episode> getEpisodes()
    {
        return episodes;
    }
    
    public Episode getEpisode(int episodeNumber)
    {
        for(int i = 0; i < episodes.size(); i++)
        {
            if(episodes.get(i).getEpisodeNumber() == episodeNumber)
            {
                return episodes.get(i);
            }
        }
        
        return null;
    }
    
    public boolean hasEpisode(int episodeNumber)
    {
        for(int i = 0; i < episodes.size(); i++)
        {
            if(episodes.get(i).getEpisodeNumber() == episodeNumber)
            {
                return true;
            }
        }
        
        return false;
    }

    public static Season parse(String data, Configuration config) 
    {
        return new Season().constructModel(data, config);
    }
    
    public static Season parse(File data, Configuration config) throws IOException 
    {
        return new Season().constructModel(data, config);
    }
    
    @Override
    protected Season constructModel(String data, Configuration config)
    {
        this.config = config;
        this.raw_json = data;
        JSONObject json = new JSONObject(data);
        
        this.air_date = json.optString("air_date");
        
        if(json.has("episode_count"))
        {
            this.episode_count = json.getInt("episode_count");
        }
        else
        {
            //Should have episdoe details.
            this.episodes = new ArrayList();
            
            JSONArray eps = json.getJSONArray("episodes");
            
            this.episode_count = eps.length();
            
            for(int i = 0; i < eps.length(); i++)
            {
                this.episodes.add(Episode.parseString(eps.getJSONObject(i).toString(), config));
            }
        }
        
        this.tmdb_id = json.getInt("id");
        this.name = json.getString("name");
        this.overview = json.optString("overview");
        this.poster_path = json.optString("poster_path");
        this.season_number =json.getInt("season_number");

        return this;
    }
    
}
