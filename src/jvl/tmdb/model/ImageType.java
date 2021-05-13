
package jvl.tmdb.model;


public enum ImageType 
{
    
    POSTER(1, "Poster") ,
    BACKDROP(2, "Backdrop"),
    STILLS(3, "Stills");
    
    private int value;
    private String name;

    private ImageType(int value, String name)
    {
        this.name = name;
        this.value = value;
    }
    
    public String getName()
    {
        return this.name;
    }
}
