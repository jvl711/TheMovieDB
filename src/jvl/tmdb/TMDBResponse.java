package jvl.tmdb;

public class TMDBResponse 
{
    public static final String RATE_LIMIT_KEY = "X-RateLimit-Limit";
    public static final String RATE_LIMIT_REMAINING_KEY = "X-RateLimit-Remaining";
    public static final String RATE_LIMIT_RESET_KEY = "X-RateLimit-Reset";
    public static final String RETRY_AFTER = "Retry-After";
    
    private int rateLimit;
    private int rateRemaining;
    private long rateReset;
    private int statusCode;
    private boolean isSuccess;
    private String data;
    
    public TMDBResponse(int statusCode, boolean isSuccess)
    {
        this.statusCode = statusCode;
        this.isSuccess = isSuccess;
        this.rateLimit = 0;
        this.rateRemaining = 0;
        this.rateReset = 0;
    }
    
    public TMDBResponse(int responseCode, String data, String rateLimit, String rateRemaining, String rateReset)
    {
        this.statusCode = responseCode;
        this.data = data;
        this.isSuccess = true;
        
        if(rateLimit != null)
        {
            try
            {
                this.rateLimit = Integer.parseInt(rateLimit);
            }
            catch(Exception ex) {}
        }
        
        if(rateRemaining != null)
        {
            try
            {
                this.rateRemaining = Integer.parseInt(rateRemaining);
            }
            catch(Exception ex) {}
        }
        
        if(rateReset != null)
        {
            try
            {
                this.rateReset = Long.parseLong(rateReset);
            }
            catch(Exception ex) {}
        }
    }
    
    public int getRateLimit()
    {
        return this.rateLimit;
    }
    
    public int getRateRemaining()
    {
        return this.rateRemaining;
    }
    
    public int getStatusCode()
    {
        return this.statusCode;
    }
    
    public boolean isSuccess()
    {
        return this.isSuccess;
    }
    
    public String getData()
    {
        return data;
    }
    
}
