
package jvl.tmdb.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;
import javax.imageio.ImageIO;

public class Image 
{
    private double aspect_ratio;
    private String file_path;
    private int height;
    private String iso_639_1;
    private double vote_average;
    private int vote_count;
    private int width;
    private ImageType imageType;
    
    private Configuration config;
    
    
    public String getISO_639_1()
    {
        return this.iso_639_1;
    }
    
    public int getVoteCount()
    {
        return this.vote_count;
    }
    
    public double getVoteAverage()
    {
        return this.vote_average;
    }
    
    public int getHeight()
    {
        return this.height;
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    public String getFileName()
    {
        return this.file_path;
    }
    
    public String getFileExt()
    {
        String ext = "jpg";
        
        try
        {
            ext = this.getFileName().split(".")[1];
        }
        catch(Exception ex){ }
        
        return ext;
    }
    
    /**
     * Returns a URL to the image at largest possible size
     * @return Secure URL to original image
     */
    public String getURL()
    {
        return this.getURL(999999);
    }
    
    /**
     * Returns a URL to the closest valid size of the image width given
     * @param size Requested width of the image
     * @return Secure URL to image
     */
    public String getURL(int size)
    {
        return this.config.getSecureImageBaseURL() + this.getValidSize(size) + this.file_path;
    }
    
    /**
     * Gets a size string closest to the given size
     * @param size Requested width of image
     * @return Closest request size
     */
    public String getValidSize(int size)
    {
        if(imageType == ImageType.BACKDROP)
        {
            return this.getBackdropSize(size);
        }
        else if(imageType == ImageType.POSTER)
        {
            return this.getPosterSize(size);
        }
        else if(imageType == ImageType.STILLS)
        {
            return this.getStillSize(size);
        }
        else
        {
            throw new RuntimeException("Unsupported Image Type");
        }
    }
    
    
    private String getPosterSize(int requestedSize)
    {
        String [] sizes = config.getPosterSizes();
        int difference = 99999;
        String retSize = "original";
        
        for(int i = 0; i < sizes.length; i++)
        {
            if(sizes[i].startsWith("w"))
            {
                int size = Integer.parseInt(sizes[i].substring(1));
                
                if(Math.abs(size - requestedSize) < difference)
                {
                    retSize = sizes[i];
                    difference = Math.abs(size - requestedSize);
                }
            }
            else if(sizes[i].equalsIgnoreCase("original"))
            {
                if(Math.abs(this.width - requestedSize) < difference)
                {
                    retSize = sizes[i];
                    difference = Math.abs(this.width - requestedSize);
                }
            }
        }
        
        return retSize;
    }
    
    private String getStillSize(int requestedSize)
    {
        String [] sizes = config.getStillSizes();
        int difference = 99999;
        String retSize = "original";
        
        for(int i = 0; i < sizes.length; i++)
        {
            if(sizes[i].startsWith("w"))
            {
                int size = Integer.parseInt(sizes[i].substring(1));
                
                if(Math.abs(size - requestedSize) < difference)
                {
                    retSize = sizes[i];
                    difference = Math.abs(size - requestedSize);
                }
            }
            else if(sizes[i].equalsIgnoreCase("original"))
            {
                if(Math.abs(this.width - requestedSize) < difference)
                {
                    retSize = sizes[i];
                    difference = Math.abs(this.width - requestedSize);
                }
            }
        }
        
        return retSize;
    }
    
    private String getBackdropSize(int requestedSize)
    {
        String [] sizes = config.getBackdropSizes();
        int difference = 99999;
        String retSize = "original";
        
        for(int i = 0; i < sizes.length; i++)
        {
            if(sizes[i].startsWith("w"))
            {
                int size = Integer.parseInt(sizes[i].substring(1));
                
                if(Math.abs(size - requestedSize) < difference)
                {
                    retSize = sizes[i];
                    difference = Math.abs(size - requestedSize);
                }
            }
            else if(sizes[i].equalsIgnoreCase("original"))
            {
                if(Math.abs(this.width - requestedSize) < difference)
                {
                    retSize = sizes[i];
                    difference = Math.abs(this.width - requestedSize);
                }
            }
        }
        
        return retSize;
    }
    
    public static Image parseString(String data, ImageType imageType, Configuration config) 
    {
        Image image = new Image();
        image.config = config;
        JSONObject json = new JSONObject(data);
        
        image.imageType = imageType;
        
        image.aspect_ratio = json.getDouble("aspect_ratio");
        image.file_path = json.getString("file_path");
        image.height = json.getInt("height");
        image.width = json.getInt("width");
        image.vote_count = json.getInt("vote_count");
        image.vote_average = json.getDouble("vote_average");
        
        if(!json.isNull("iso_639_1"))
        {
            image.iso_639_1 = json.getString("iso_639_1");
        }
        else
        {
            image.iso_639_1 = "";
        }
        
        return image;
    }
    
    public void saveImage(File file, int sizeWidth) throws MalformedURLException, IOException
    {
        URL url = new URL(this.getURL(sizeWidth));
        InputStream is = null;
        OutputStream os = null;
        
        if(file.exists())
        {
            //Assuming image already cached.  Just return
            //TODO:  I changed this so image can be overwritten
            file.delete();
            //return;
        }
        
        file.getParentFile().mkdirs();

        try
        {
            is = url.openStream();
            BufferedImage tmpImage = ImageIO.read(is);
                           
            ImageIO.write(tmpImage, this.getFileExt(), file);
            
            /*
            os = new FileOutputStream(file);

            byte[] buffer = new byte[2048];
            int length;

            while ((length = is.read(buffer)) != -1)
            {
                os.write(buffer, 0, length);
            }
            */
        }
        finally
        {
            if(is != null)
            {
                is.close();
            }
            
            if(os != null)
            {
                os.flush();
                os.close();
            }
        }
        
    }
    
}
