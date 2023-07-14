
package jvl.tmdb.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import jvl.tmdb.AbstractTMDBModel;
import org.json.JSONArray;
import org.json.JSONObject;


public class Images extends AbstractTMDBModel<Images>
{
    
    private ArrayList<Image> posters;
    private ArrayList<Image> backdrops;
    private ArrayList<Image> stills;
    
    private Configuration config;
    
    public Images()
    {
        posters = new ArrayList();
        backdrops = new ArrayList();
        stills = new ArrayList();
    }
    
    public ArrayList<Image> getPosters()
    {
        return posters;
    }
    
    /**
     * Looks for highest rated poster
     * @return 
     */
    public Image getPoster()
    {
        int poster = 0;
        double vote = 0;
        
        for(int i = 0; i < this.posters.size(); i++)
        {
            Image image = this.posters.get(i);
            if((image.getISO_639_1().equalsIgnoreCase("en") || image.getISO_639_1().equalsIgnoreCase("")) && image.getVoteAverage() > vote)
            {
                poster = i;
                vote = image.getVoteAverage();
            }
        }
        
        return posters.get(poster);
    }
    
    public Image getPoster(String name)
    {
        for(int i = 0; i < this.posters.size(); i++)
        {
            Image image = this.posters.get(i);
            
            if(image.getFileName().equalsIgnoreCase(name))
            {
                return image;
            }
        }
        
        return null;
    }
    
    
    public ArrayList<Image> getBackdrops()
    {
        return backdrops;
    }
    
    public Image getBackdrop()
    {
        int backdrop = 0;
        double vote = 0;
        
        for(int i = 0; i < this.backdrops.size(); i++)
        {
            Image image = this.backdrops.get(i);
            
            if((image.getISO_639_1().equalsIgnoreCase("en") || image.getISO_639_1().equalsIgnoreCase("")) && image.getVoteAverage() > vote)
            {
                backdrop = i;
                vote = this.backdrops.get(i).getVoteAverage();
            }
        }
        
        return backdrops.get(backdrop);
    }
    
    public Image getBackdrop(String name)
    {
        for(int i = 0; i < this.backdrops.size(); i++)
        {
            Image image = this.backdrops.get(i);
            
            if(image.getFileName().equalsIgnoreCase(name))
            {
                return image;
            }
        }
        
        return null;
    }
    
    public ArrayList<Image> getStills()
    {
        return stills;
    }
    
    public Image getStill(String name)
    {
        for(int i = 0; i < this.stills.size(); i++)
        {
            Image image = this.stills.get(i);
            
            if(image.getFileName().equalsIgnoreCase(name))
            {
                return image;
            }
        }
        
        return null;
    }
    
    public Image getStill()
    {
        int still = 0;
        double vote = 0;
        
        for(int i = 0; i < this.stills.size(); i++)
        {
            Image image = this.stills.get(i);
            
            if((image.getISO_639_1().equalsIgnoreCase("en") || image.getISO_639_1().equalsIgnoreCase("")) && image.getVoteAverage() > vote)
            {
                still = i;
                vote = image.getVoteAverage();
            }
        }
        
        return stills.get(still);
    }
    
    public static Images parse(String data, Configuration config)
    {
        return new Images().constructModel(data, config);
    }

    public static Images parse(File data, Configuration config) throws IOException
    {
        return new Images().constructModel(data, config);
    }
    
    @Override
    protected Images constructModel(String data, Configuration config) 
    {
        //Images images = new Images();
        this.raw_json = data;
        this.config = config;
        
        
        JSONObject json = new JSONObject(data);
        
        this.tmdb_id = json.getInt("id");
        
        if(json.has("backdrops"))
        {
            JSONArray bd = json.getJSONArray("backdrops");

            for(int i = 0; i < bd.length(); i++)
            {
                this.backdrops.add(Image.parseString(bd.getJSONObject(i).toString(), ImageType.BACKDROP, config));
            }
        }
        
        if(json.has("posters"))
        {
            JSONArray post = json.getJSONArray("posters");

            for(int i = 0; i < post.length(); i++)
            {
                this.posters.add(Image.parseString(post.getJSONObject(i).toString(), ImageType.POSTER, config));
            }
        }
        
        if(json.has("stills"))
        {
            JSONArray post = json.getJSONArray("stills");

            for(int i = 0; i < post.length(); i++)
            {
                this.stills.add(Image.parseString(post.getJSONObject(i).toString(), ImageType.STILLS, config));
            }
        }
        
        
        return this;
    }
    
}
