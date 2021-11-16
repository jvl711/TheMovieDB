package jvl.tmdb;


public class Version
{
    private final static String BUILDTIME = "11/15/2021 19:18:08";
    private final static String BUILDNUMBER = "32";
    private final static String VERSION = "1.0.5";
    
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
