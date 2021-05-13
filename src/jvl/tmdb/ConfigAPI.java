
package jvl.tmdb;

import java.io.IOException;
import jvl.tmdb.model.Configuration;

public class ConfigAPI 
{
    /*
     * Cache the config.  This should very rarely change.
    */
    private static Configuration config;
    
    public static Configuration getConfig(TMDBRequest request, boolean blocking) throws IOException, RateLimitException
    {
        //TODO: Determing how to handle response data.
        
        if(ConfigAPI.config == null)
        {
            TMDBResponse response = request.Execute("configuration", blocking);
            
            if(!response.isSuccess())
            {
                System.out.println("getSeasonDetails was unsuccessful status code: " + response.getStatusCode());
                return null;
            }

            config = Configuration.parseString(response.getData());
            
            return ConfigAPI.config;
        }
        else
        {
            return ConfigAPI.config;
        }
    }
}
