package jvl.tmdb;


public class Version
{
    private final static String BUILDTIME = "05/14/2023 07:33:54";
    private final static String BUILDNUMBER = "170";
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
