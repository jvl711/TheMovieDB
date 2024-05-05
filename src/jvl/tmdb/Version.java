package jvl.tmdb;


public class Version
{
    private final static String BUILDTIME = "03/24/2024 09:09:10";
    private final static String BUILDNUMBER = "230";
    private final static String VERSION = "1.0.6";
    
    public static String getVersion()
    {
        return VERSION;
    }
    
    public static String getBuildNumber()
    {
        return BUILDNUMBER;
    }

    public static String getBuildTime()
    {
        return BUILDTIME;
    }
}
