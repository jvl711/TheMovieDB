
package jvl.tmdb;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TMDBRequest 
{

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final int CONNECT_TIMEOUT = 6000;
    private String apiKey;
    private static final Logger LOG = Logger.getLogger(TMDBRequest.class.getName());
      
    /*
    public TMDBRequest()
    {
        LOG.log(Level.FINE, "Setting https.protocols: {0}", "TLSv1,TLSv1.1,TLSv1.2");
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        this.apiKey = TMDBRequest.DEFAULT_API_KEY;
    }
*/
    
    
    public TMDBRequest(String apiKey)
    {
        LOG.log(Level.FINE, "Setting https.protocols: {0}", "TLSv1,TLSv1.1,TLSv1.2");
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        this.apiKey = apiKey;
    }

    public String GetAPIKey()
    {
        return this.apiKey;
    }
    
    public void SetAPIKey(String apiKey)
    {
        this.apiKey = apiKey;
    }
    
    public TMDBResponse Execute(String section, boolean blocking) throws MalformedURLException, IOException, RateLimitException
    {
        
        return Execute(section, -1, null, null, blocking);
    }
    
    public TMDBResponse Execute(String section, String command, boolean blocking) throws MalformedURLException, IOException, RateLimitException
    {
        
        
        return Execute(section, -1, command , null, blocking);
    }
    
    public TMDBResponse Execute(String section, String command, HashMap<String, String> QueryParams, boolean blocking) throws MalformedURLException, IOException, RateLimitException
    {
        
        
        return Execute(section, -1, command , QueryParams, blocking);
    }
    
    public TMDBResponse Execute(String section, int id, boolean blocking) throws MalformedURLException, IOException, RateLimitException
    {
        
        
        return Execute(section, id, null , null, blocking);
    }
    public TMDBResponse Execute(String section, int id, String command, boolean blocking) throws MalformedURLException, IOException, RateLimitException
    {
        
        
        return Execute(section, id, command , null, blocking);
    }
    
    public TMDBResponse Execute(String section, int id, String command, HashMap<String, String> QueryParams, boolean blocking) throws MalformedURLException, IOException, RateLimitException    
    {
        
        
        return Execute(section, id, null, -1, null, -1, command , QueryParams, blocking);
    }
    
    public TMDBResponse Execute(String section, int id, String secondarySection, int secondaryId, boolean blocking) throws MalformedURLException, IOException, RateLimitException    
    {
        
        
        return Execute(section, id, secondarySection, secondaryId, null, -1, null , null, blocking);
    }
    
    public TMDBResponse Execute(String section, int id, String secondarySection, int secondaryId, String command, boolean blocking) throws MalformedURLException, IOException, RateLimitException    
    {
        
        
        return Execute(section, id, secondarySection, secondaryId, null, -1, command , null, blocking);
    }
    
    public TMDBResponse Execute(String section, int id, String secondarySection, int secondaryId, String tertiarySection, int tertiaryId, String command, boolean blocking) throws MalformedURLException, IOException, RateLimitException    
    {
        
        
        return Execute(section, id, secondarySection, secondaryId, tertiarySection, tertiaryId, command , null, blocking);
    }
    
    public TMDBResponse Execute(String section, int id, String secondarySection, int secondaryId, String tertiarySection, int tertiaryId, String command, HashMap<String, String> QueryParams, boolean blocking) throws MalformedURLException, IOException, RateLimitException
    {
        String url = BASE_URL + section;
        
        LOG.log(Level.INFO, "Parameters [Section: {0}, ID: {1}, SecondarySection: {2}, SecondaryID: {3}, TertiaryIDSection: {4}, TertiaryID: {5}, Command: {6}, QueryParams: {7}, Blocking: {8}]", new Object [] {section, id, secondarySection, secondaryId, tertiarySection, tertiaryId, command, QueryParams, blocking});
        
        if(id != -1)
        {
            url += "/" + id;
        }
        
        if(secondarySection != null && !secondarySection.isEmpty() && secondaryId != -1)
        {
            url += "/" + secondarySection + "/" + secondaryId;
        }
        
        if(tertiarySection != null && !tertiarySection.isEmpty() && tertiaryId != -1)
        {
            url += "/" + tertiarySection + "/" + tertiaryId;
        }
        
        if(command != null && !command.isEmpty())
        {
            url += "/" + command;
        }
        
        url += "?api_key=" + this.GetAPIKey();    
        
        if(QueryParams != null)
        {
            String [] keys = new String[QueryParams.keySet().size()];
            
            keys = QueryParams.keySet().toArray(keys);

            for(int i = 0; i < keys.length; i++)
            {
                url += "&" + keys[i] + "=" + URLEncoder.encode(QueryParams.get(keys[i]), "UTF-8");
                //URLEncoder.encode(QueryParams.get(keys[i]), "UTF-8");
            }
            
        }
        
        return ExecuteBase(url, blocking);
    }
    
    private TMDBResponse ExecuteBase(String url, boolean blocking) throws MalformedURLException, IOException, RateLimitException
    {
        TMDBResponse response = null;
        BufferedInputStream input;
        BufferedReader reader;
        String temp;
        String output = "";
        
        
        
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        
        con.setConnectTimeout(CONNECT_TIMEOUT);
        con.setUseCaches(false);
        
        
        if(con.getResponseCode() < 400)
        {
            input = new BufferedInputStream(con.getInputStream());
            reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            
            String rateLimitString = con.getHeaderField(TMDBResponse.RATE_LIMIT_KEY);
            String rateLimitRemainingString = con.getHeaderField(TMDBResponse.RATE_LIMIT_REMAINING_KEY);
            String rateLimitResetString = con.getHeaderField(TMDBResponse.RATE_LIMIT_RESET_KEY);
            
            while((temp = reader.readLine()) != null)
            {
                output += temp;
            }
            
            response = new TMDBResponse(con.getResponseCode(), output, rateLimitString, rateLimitRemainingString, rateLimitResetString);
        }
        else
        {

            if(con.getResponseCode() == 429)
            {
                int retryAfter = Integer.parseInt(con.getHeaderField(TMDBResponse.RETRY_AFTER));
                
                System.out.println("Rate Limit Reached...");
                System.out.println("Retry After: " + retryAfter);
                
                if(blocking)
                {
                    System.out.println("Blocking call is true.  Sleeping for " + retryAfter + " seconds");
                    try 
                    {
                        Thread.sleep(1000 * (retryAfter));
                    } 
                    catch (InterruptedException ex) 
                    {
                        System.out.println("Threw an exception attempting to sleep");
                    }
                    
                    return this.ExecuteBase(url, blocking);
                }
                else
                {
                    throw new RateLimitException("TMDB Rate limit was exceeded and blocking call was not true");
                }
            }
            else if(con.getResponseCode() == 401)
            {
                throw new APIKeyException("The API key that was provided was rejected by the server");
            }
            else if (con.getResponseCode() == 404)
            {
                response = new TMDBResponse(con.getResponseCode(), false);
            }
            else
            {
                response = new TMDBResponse(con.getResponseCode(), false);
            }

        }
        
        return response;
    }
}
