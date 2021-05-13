
package jvl.tmdb.model;

import org.json.JSONObject;


public class SearchResultShow 
{
    private String poster_path;
    private double popularity;
    private int tmdb_id;
    private String backdrop_path;
    private double vote_average;
    private String overview;
    private String first_air_date;
    private String original_language;
    private int vote_count;
    private String name;
    private String original_name;
    
    public String getName()
    {
        return this.name;
    }
    
    public String getOverview()
    {
        return this.overview;
    }
            
    public int getTmdbID() 
    {
        return tmdb_id;
    }
    
    public static SearchResultShow parseString(String data)
    {
        SearchResultShow resultShow = new SearchResultShow();
        
        JSONObject json = new JSONObject(data);
        
        resultShow.poster_path = json.optString("poster_path", "");
        resultShow.popularity = json.getDouble("popularity");
        resultShow.tmdb_id = json.getInt("id");
        resultShow.backdrop_path = json.optString("backdrop_path", "");
        resultShow.vote_average = json.getDouble("vote_average");
        resultShow.overview = json.getString("overview");
        resultShow.first_air_date = json.getString("first_air_date");
        //origin_country
        //genre_ids
        resultShow.original_language = json.getString("original_language");
        resultShow.vote_count = json.getInt("vote_count");
        resultShow.name = json.getString("name");
        resultShow.original_name = json.getString("original_name");

        return resultShow;
    }
    
}
