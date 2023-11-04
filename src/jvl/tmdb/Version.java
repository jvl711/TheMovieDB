package jvl.tmdb;


public class Version
{
    private final static String BUILDTIME = "10/31/2023 09:18:49";
    private final static String BUILDNUMBER = "194";
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
