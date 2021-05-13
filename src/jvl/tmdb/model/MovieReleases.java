/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvl.tmdb.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jvl.tmdb.AbstractTMDBModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author jvl711
 */
public class MovieReleases extends AbstractTMDBModel<MovieReleases>
{
    private ArrayList<MovieRelease> releases;
    
    public ArrayList<MovieRelease> getReleases()
    {
        return this.releases;
    }
    
    /**
     * Looks for the best match Parental Rating in the US.  It attempts to get 
     * Theatrical Release rating, otherwise the last one it finds will be returned
     * 
     * @return Parental Rating 
     */
    public String getParentalRating()
    {
        String cert = "";
        
        for(int i = 0; i < this.releases.size(); i++)
        {
            if(releases.get(i).getCountryCode().equalsIgnoreCase("US") && !releases.get(i).getCertification().isEmpty())
            {
                cert = releases.get(i).getCertification();
                
                if(releases.get(i).getReleaseTypeCode() == 3)
                {
                    break;
                }
            }
        }
        
        return cert;
    }
    
    public static MovieReleases parse(String data, Configuration config) 
    {
        return new MovieReleases().constructModel(data, config);
    }
    
    public static MovieReleases parse(File data, Configuration config) throws IOException 
    {
        return new MovieReleases().constructModel(data, config);
    }

    @Override
    protected MovieReleases constructModel(String data, Configuration config)
    {
        this.config = config;
        this.releases = new ArrayList<MovieRelease>();
        
        this.raw_json = data;
        
        JSONObject json = new JSONObject(data);
        
        JSONArray results = json.getJSONArray("results");
        
        for(int i = 0; i < results.length(); i++)
        {            
            String countryCode = results.getJSONObject(i).optString("iso_3166_1");
            
            if(!countryCode.equalsIgnoreCase(""))
            {
                JSONArray release_dates = results.getJSONObject(i).getJSONArray("release_dates");

                for(int j = 0; j < release_dates.length(); j++)
                {
                    MovieRelease release = MovieRelease.parseString(release_dates.getJSONObject(j).toString(), countryCode, config);
                    this.releases.add(release);
                }
            }
        }
        
        return this;
    }
}
