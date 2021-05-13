
package jvl.tmdb.model;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @see <a href="https://developers.themoviedb.org/3/configuration/get-api-configuration">https://developers.themoviedb.org/3/configuration/get-api-configuration</a>
 * @author jvl711
 */
public class Configuration 
{
    private String base_url;
    private String secure_base_url;
    private String [] backdrop_sizes;
    private String [] logo_sizes;
    private String [] poster_sizes;
    private String [] profile_sizes;
    private String [] still_sizes;
    private String [] change_keys;
    
    
    
    /**
     * Returns the base url to return an image with a slash on the end.
     * Example: http://image.tmdb.org/t/p/
     * @return A url in string form
     */
    public String getImageBaseURL()
    {
        return this.base_url;
    }
    
    /**
     * Returns the secure base url to return an image with a slash on the end.
     * Example: https://image.tmdb.org/t/p/
     * @return A url in string form
     */
    public String getSecureImageBaseURL()
    {
        return this.secure_base_url;
    }
    
    /**
     * Returns an array of valid poster sizes based on width.  The sizes are prefixed with 'w' 
     * There is also 'original' in the list, which is used to get the original size.
     * Example w300, w780, w1280, original
     * @return An array of allowed sizes. 
     */
    public String [] getPosterSizes()
    {
        return this.poster_sizes;
    }
    
    /**
     * Returns an array of valid backdrop sizes based on width.  The sizes are prefixed with 'w' 
     * There is also 'original' in the list, which is used to get the original size.
     * Example w300, w780, w1280, original
     * @return An array of allowed sizes. 
     */
    public String [] getBackdropSizes()
    {
        return this.backdrop_sizes;
    }
    
    /**
     * Returns an array of valid logo sizes based on width.  The sizes are prefixed with 'w' 
     * There is also 'original' in the list, which is used to get the original size.
     * Example w300, w780, w1280, original
     * @return An array of allowed sizes. 
     */
    public String [] getLogoSizes()
    {
        return this.logo_sizes;
    }
    
    /**
     * Returns an array of valid profile sizes based on width.  The sizes are prefixed with 'w' 
     * There is also 'original' in the list, which is used to get the original size.
     * Example w300, w780, w1280, original
     * @return An array of allowed sizes. 
     */
    public String [] getProfileSizes()
    {
        return this.profile_sizes;
    }
    
    /**
     * Returns an array of valid still sizes based on width.  The sizes are prefixed with 'w' 
     * There is also 'original' in the list, which is used to get the original size.
     * Example w300, w780, w1280, original
     * @return An array of allowed sizes. 
     */
    public String [] getStillSizes()
    {
        return this.still_sizes;
    }
    
    public static Configuration parseString(String data)
    {
        Configuration config = new Configuration();
     
        JSONObject json = new JSONObject(data);
        

        
        JSONObject images = json.getJSONObject("images");
        
        config.base_url = images.getString("base_url");
        config.secure_base_url = images.getString("secure_base_url");
        
        JSONArray logo = images.getJSONArray("logo_sizes");
        config.logo_sizes = new String[logo.length()];
        
        for(int i = 0; i < logo.length(); i++)
        {
            config.logo_sizes[i] = logo.getString(i);
        }
        
        /*
        JSONArray backdrops = images.getJSONArray("backdrop_sizes");
        config.backdrop_sizes = new String[backdrops.length()];
        
        for(int i = 0; i < backdrops.length(); i++)
        {
            config.backdrop_sizes[i] = backdrops.getString(i);
        }
        */
        
        /* This is temporary.  It seems there are more sizes supported than they report */
        config.backdrop_sizes = new String[10];
        config.backdrop_sizes[0] = "w45";
        config.backdrop_sizes[1] = "w92";
        config.backdrop_sizes[2] = "w154";
        config.backdrop_sizes[3] = "w185";
        config.backdrop_sizes[4] = "w300";
        config.backdrop_sizes[5] = "w342";
        config.backdrop_sizes[6] = "w500";
        config.backdrop_sizes[7] = "w780";
        config.backdrop_sizes[8] = "w1280";
        config.backdrop_sizes[9] = "original";
        
        /*
        JSONArray posters = images.getJSONArray("poster_sizes");
        config.poster_sizes = new String[posters.length()];
        
        for(int i = 0; i < posters.length(); i++)
        {
            config.poster_sizes[i] = posters.getString(i);
        }
        */
        
        /* This is temporary.  It seems there are more sizes supported than they report */
        config.poster_sizes = new String[10];
        config.poster_sizes[0] = "w45";
        config.poster_sizes[1] = "w92";
        config.poster_sizes[2] = "w154";
        config.poster_sizes[3] = "w185";
        config.poster_sizes[4] = "w300";
        config.poster_sizes[5] = "w342";
        config.poster_sizes[6] = "w500";
        config.poster_sizes[7] = "w780";
        config.poster_sizes[8] = "w1280";
        config.poster_sizes[9] = "original";
        
        
        JSONArray profiles = images.getJSONArray("profile_sizes");
        config.profile_sizes = new String[profiles.length()];
        
        for(int i = 0; i < profiles.length(); i++)
        {
            config.profile_sizes[i] = profiles.getString(i);
        }
        
        /*
        JSONArray stills = images.getJSONArray("still_sizes");
        config.still_sizes = new String[stills.length()];
        
        for(int i = 0; i < stills.length(); i++)
        {
            config.still_sizes[i] = stills.getString(i);
        }
        */
        
        /* This is temporary.  It seems there are more sizes supported than they report */
        config.still_sizes = new String[10];
        config.still_sizes[0] = "w45";
        config.still_sizes[1] = "w92";
        config.still_sizes[2] = "w154";
        config.still_sizes[3] = "w185";
        config.still_sizes[4] = "w300";
        config.still_sizes[5] = "w342";
        config.still_sizes[6] = "w500";
        config.still_sizes[7] = "w780";
        config.still_sizes[8] = "w1280";
        config.still_sizes[9] = "original";
        
        
        JSONArray keys = json.getJSONArray("change_keys");
        config.change_keys = new String[keys.length()];
        
        for(int i = 0; i < keys.length(); i++)
        {
            config.change_keys[i] = keys.getString(i);
        }
        
        
        return config;
    }
    
}

