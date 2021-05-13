
package jvl.tmdb;

import java.io.IOException;
import java.util.HashMap;
import jvl.tmdb.model.SearchResultType;
import jvl.tmdb.model.SearchResults;


public class SearchAPI 
{

    public static SearchResults searchMovies(TMDBRequest request, String query, boolean blocking) throws IOException, RateLimitException
    {
        HashMap<String, String> params = new HashMap();
        
        params.put("query", query);
        params.put("page", "1");
        
        TMDBResponse response = request.Execute("search", "movie", params, blocking);
        
        if(!response.isSuccess())
        {
            System.out.println("getSeasonDetails was unsuccessful status code: " + response.getStatusCode());
            return null;
        }
        
        return SearchResults.parseString(response.getData(), SearchResultType.MOVIE, query);
    }
    
    public static SearchResults searchMovies(TMDBRequest request, String query, int year, boolean blocking) throws IOException, RateLimitException
    {
        HashMap<String, String> params = new HashMap();
        
        params.put("query", query);
        params.put("page", "1");
        params.put("year", year + "");
        
        TMDBResponse response = request.Execute("search", "movie", params, blocking);
        
        if(!response.isSuccess())
        {
            System.out.println("getSeasonDetails was unsuccessful status code: " + response.getStatusCode());
            return null;
        }
        
        return SearchResults.parseString(response.getData(), SearchResultType.MOVIE, query);
    }
    
    public static SearchResults searchTV(TMDBRequest request, String query, boolean blocking) throws IOException, RateLimitException
    {
        HashMap<String, String> params = new HashMap();
        
        params.put("query", query);
        params.put("page", "1");
        
        TMDBResponse response = request.Execute("search", "tv", params, blocking);
        
        if(!response.isSuccess())
        {
            System.out.println("getSeasonDetails was unsuccessful status code: " + response.getStatusCode());
            return null;
        }
        
        return SearchResults.parseString(response.getData(), SearchResultType.SHOWS, query);
    }
    
}
