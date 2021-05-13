
package jvl.tmdb;


public class APIKeyException extends RuntimeException
{
    public APIKeyException(String errorMessage) 
    {
        super(errorMessage);
    }
}
