
package jvl.tmdb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import jvl.tmdb.model.Configuration;

public abstract class AbstractTMDBModel<T>
{
    protected String raw_json;
    protected Configuration config;
    protected int tmdb_id;
    
    public int getTmdbID() 
    {
        return tmdb_id;
    }
    
    public Configuration getConfiguration()
    {
        return config;
    }
    
    protected T constructModel(File file, Configuration config) throws FileNotFoundException, IOException
    {
        BufferedReader reader = null;
        String line = null;
        String data = "";

        try
        {
            reader = new BufferedReader(new FileReader(file));
            
            while((line = reader.readLine()) != null)
            {
                data += line;
            }
        }
        finally
        {
            if(reader != null)
            {
                reader.close();
            }
        }
        
        
        return constructModel(data, config);
    }
    
    public void save(File file) throws FileNotFoundException
    {
        //Delete the file if it exists.
        if(file.exists())
        {
            file.delete();
        }
        
        //Create the parent directories if they do not exist
        file.getParentFile().mkdirs();
        
        PrintStream out = new PrintStream(new FileOutputStream(file, false));
        out.print(this.raw_json);
        out.flush();
        out.close();
    }
    
    protected abstract T constructModel(String data, Configuration config);
    
    
}
