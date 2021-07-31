import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


class ReadFileByPosWriteByFileName
{
    //This class reads the chunks in accordance with the start and end position given
    // This also writes the bytes of the chunk into the  file once they are processed
    

    int start;
    int end;
    int BUFFER;
    String SOURCE_FILE_NAME;
    String INT_FILE_NAME;
    private OutputStream os;
    private InputStream is;
 
    public ReadFileByPosWriteByFileName(int s, int e, String nameFile,String intermediate)
    {
        INT_FILE_NAME=intermediate;
        SOURCE_FILE_NAME= nameFile;
        BUFFER=end-start;
        start=s;
        end=e;
    }

    public byte[] readBinary() throws IOException
    {
        
        try{
            is= new FileInputStream(SOURCE_FILE_NAME);
            if(end>((FileInputStream) is).getChannel().size()) 
                end=(int) ((FileInputStream) is).getChannel().size();
            
            byte[] imageBuffer= new byte[end-start];
            is.skip(start);
            is.read(imageBuffer);
            
            
            
            is.close();
            return imageBuffer;

            // System.out.println("MAIN "+imageBuffer.length);
            // is= new FileInputStream(SOURCE_FILE_NAME);
            // byte[] imageBuffer= new byte[BUFFER];
            // is.skip(start);
            // int bytesRead = is.read(imageBuffer);
            // if(bytesRead==-1)
            //     throw new IOException("Empty");
            // System.out.println("R "+bytesRead);
            // is.close();
            // return imageBuffer;
        }

        catch(IOException io)
        {
            System.out.println("Error in file write r "+io.getMessage());
            throw new IOException("Reading main file");
        }
    }

    public boolean writeBinary(byte [] data) throws IOException
    {
        try
        {
            if(data.length==0)
                throw new IOException("size 0");
            
            os = new FileOutputStream(INT_FILE_NAME,true);
            if(data.length!=0)
            {
                os.write(data,0,data.length);
            }
            os.close();
            return true;
        }

        catch(IOException io)
        {
            System.out.println("Error in file write w "+io.getMessage());
            throw new IOException("Writing sub File");
        }
        
    }

    // public static void main(String args[]) throws IOException
    // {
    //     String nameFile="C:\\Users\\Swap\\OneDrive\\Desktop\\college\\Java\\JavaSetup\\FileOperations\\Sample1In.jpg";
    //     String intermediate="C:\\Users\\Swap\\OneDrive\\Desktop\\college\\Java\\JavaSetup\\FileOperations\\Sample1Out1.jpg";
        

        


    //     ReadFileByPosWriteByFileName abc= new ReadFileByPosWriteByFileName(0,2048,nameFile,intermediate);
    //     ReadFileByPosWriteByFileName abcd= new ReadFileByPosWriteByFileName(2048,1000000,nameFile,intermediate);
    //     byte[]a=abc.readBinary();
    //     System.out.println(a.length+" len");
    //     abc.writeBinary(a);
    //     byte b[]=  abcd.readBinary();
    //     abcd.writeBinary(b);
    //     System.out.println(b.length+" len");
    // }

}