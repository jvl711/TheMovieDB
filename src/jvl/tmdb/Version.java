package jvl.tmdb;


public class Version
{
    private final static String BUILDTIME = "04/21/2022 19:48:24";
    private final static String BUILDNUMBER = "38";
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
