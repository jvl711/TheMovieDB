
package jvl.tmdb;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jvl.tmdb.model.Credits;
import jvl.tmdb.model.Images;
import jvl.tmdb.model.Season;
import jvl.tmdb.model.TV;


public class TVAPI 
{
    private static final Logger LOG = Logger.getLogger(TVAPI.class.getName());
    
    public static TV getDetails(TMDBRequest request, int id, boolean blocking) throws IOException, RateLimitException
    {
        LOG.info("Making webservice call");
        //TODO: Determing how to handle response data.
        TMDBResponse response = request.Execute("tv", id, blocking);
        
        if(!response.isSuccess())
        {
            LOG.log(Level.WARNING, "unsuccessful status code: {0}", response.getStatusCode());
            return null;
        }
        
        return TV.parse(response.getData(), ConfigAPI.getConfig(request, blocking));
    }
    
    public static Images getImages(TMDBRequest request, int id, boolean blocking) throws IOException, RateLimitException
    {
        LOG.info("Making webservice call");
        TMDBResponse response = request.Execute("tv", id, "images", blocking);
        
        if(!response.isSuccess())
        {
            LOG.log(Level.WARNING, "unsuccessful status code: {0}", response.getStatusCode());
            return null;
        }
        
        return Images.parse(response.getData(), ConfigAPI.getConfig(request, blocking));
    }
    
    public static Credits getCredits(TMDBRequest request, int id, boolean blocking) throws IOException, RateLimitException
    {
        LOG.info("Making webservice call");
        TMDBResponse response = request.Execute("tv", id, "credits", blocking);
        
        if(!response.isSuccess())
        {
            LOG.log(Level.WARNING, "unsuccessful status code: {0}", response.getStatusCode());
            return null;
        }
        
        return Credits.parse(response.getData(), ConfigAPI.getConfig(request, blocking));
    }
    
    public static Season getSeasonDetails(TMDBRequest request, int showId, int seasonNumber, boolean blocking) throws IOException, RateLimitException
    {
        LOG.info("Making webservice call");
        TMDBResponse response = request.Execute("tv", showId, "season", seasonNumber, blocking);
        
        if(!response.isSuccess())
        {
            LOG.log(Level.WARNING, "unsuccessful status code: {0}", response.getStatusCode());
            return null;
        }

        return Season.parse(response.getData(), ConfigAPI.getConfig(request, blocking));
        
    }
    
    public static Images getSeasonImages(TMDBRequest request, int showId, int seasonNumber, boolean blocking) throws IOException, RateLimitException
    {
        LOG.info("Making webservice call");
        TMDBResponse response = request.Execute("tv", showId, "season", seasonNumber, "images", blocking);
        
        if(!response.isSuccess())
        {
            LOG.log(Level.WARNING, "unsuccessful status code: {0}", response.getStatusCode());
            return null;
        }
        
        return Images.parse(response.getData(), ConfigAPI.getConfig(request, blocking));
    }
    
    public static Images getEpisodeImages(TMDBRequest request, int showId, int seasonNumber, int episodeNumber, boolean blocking) throws IOException, RateLimitException
    {
        LOG.info("Making webservice call");
        TMDBResponse response = request.Execute("tv", showId, "season", seasonNumber, "episode", episodeNumber, "images", blocking);
        
        if(!response.isSuccess())
        {
            LOG.log(Level.WARNING, "unsuccessful status code: {0}", response.getStatusCode());
            return null;
        }
        
        return Images.parse(response.getData(), ConfigAPI.getConfig(request, blocking));
    }
    
    
}
