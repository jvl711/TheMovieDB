
package jvl.tmdb.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;


public class SearchResults 
{
    private int page;
    private int total_pages;
    private int total_results;
    private String searchQuery;
    private ArrayList<SearchResultMovie> movies;
    private ArrayList<SearchResultShow> shows;
    
    
    public SearchResults(String searchQuery)
    {
        movies = new ArrayList<SearchResultMovie>();
        shows = new ArrayList<SearchResultShow>();
        this.searchQuery = searchQuery;
    }
    
    public int getPage()
    {
        return page;
    }
    
    public int getTotalPages()
    {
        return total_pages;
    }
    
    public int getTotalResults()
    {
        return total_results;
    }
    
    public ArrayList<SearchResultMovie> getMovies()
    {
        return movies;
    }
    
    public ArrayList<SearchResultShow> getShows()
    {
        return shows;
    }
    
    public static SearchResults parseString(String data, SearchResultType type, String searchQuery)
    {
        SearchResults results = new SearchResults(searchQuery);
        
        JSONObject json = new JSONObject(data);
        
        results.page = json.getInt("page");
        results.total_pages = json.getInt("total_pages");
        results.total_results = json.getInt("total_results");
        
        JSONArray resultArray = json.getJSONArray("results");
        
        if(type == SearchResultType.MOVIE)
        {
            for(int i = 0; i < resultArray.length(); i++)
            {
                results.movies.add(SearchResultMovie.parseString(resultArray.get(i).toString()));
            }
        }
        else if(type == SearchResultType.SHOWS)
        {
            for(int i = 0; i < resultArray.length(); i++)
            {
                results.shows.add(SearchResultShow.parseString(resultArray.get(i).toString()));
            }
            
            //look to see if there is an eact match for the query string.  If so move it to the top of the list
            for(int i = 0; i < results.shows.size(); i++)
            {
                if(results.shows.get(i).getName().equalsIgnoreCase(searchQuery))
                {
                    SearchResultShow show = results.shows.remove(i);
                    results.shows.add(0, show);
                    break;
                }
            }
        }
        
        return results;
    }
    
}
