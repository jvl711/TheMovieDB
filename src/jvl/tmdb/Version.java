package jvl.tmdb;


public class Version
{
    private final static String BUILDTIME = "05/06/2022 14:29:19";
    private final static String BUILDNUMBER = "45";
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
