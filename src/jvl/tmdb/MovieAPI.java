
package jvl.tmdb;

import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.Level;
import jvl.tmdb.model.Credits;
import jvl.tmdb.model.Images;
import jvl.tmdb.model.Movie;
import jvl.tmdb.model.MovieReleases;


public class MovieAPI 
{
    private static final Logger LOG = Logger.getLogger(MovieAPI.class.getName());
    
    
    public static Movie getDetails(TMDBRequest request, int id, boolean blocking) throws IOException, RateLimitException
    {
        LOG.info("Making webservice call");

        //TODO: Determing how to handle response data.
        TMDBResponse response = request.Execute("movie", id, blocking);

        if(!response.isSuccess())
        {
            LOG.log(Level.WARNING, "unsuccessful status code: {0}", response.getStatusCode());
            return null;
        }
        
        return Movie.parse(response.getData(), ConfigAPI.getConfig(request, blocking));
    }
    
    public static Images getImages(TMDBRequest request, int id, boolean blocking) throws IOException, RateLimitException
    {
        LOG.info("Making webservice call");
        
        TMDBResponse response = request.Execute("movie", id, "images", blocking);
        
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
        TMDBResponse response = request.Execute("movie", id, "credits", blocking);
        
        if(!response.isSuccess())
        {
            LOG.log(Level.WARNING, "unsuccessful status code: {0}", response.getStatusCode());
            return null;
        }
        
        return Credits.parse(response.getData(), ConfigAPI.getConfig(request, blocking));
    }
    
    public static MovieReleases getReleases(TMDBRequest request, int id, boolean blocking) throws IOException, RateLimitException
    {
        LOG.info("Making webservice call");
        TMDBResponse response = request.Execute("movie", id, "release_dates", blocking);
        
        if(!response.isSuccess())
        {
            LOG.log(Level.WARNING, "unsuccessful status code: {0}", response.getStatusCode());
            return null;
        }
        
        return MovieReleases.parse(response.getData(), ConfigAPI.getConfig(request, blocking));
    }
}
