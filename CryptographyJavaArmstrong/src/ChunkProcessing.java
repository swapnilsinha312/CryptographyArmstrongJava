
import java.io.IOException;

public class ChunkProcessing implements Runnable
{
        //This function gets a chunk of data using the file name and start and end indexes as given 
        //This then encrypts/decrypts the data 
        //Then the data is finally writes the data into the file name as given to it
        //Note that this is a multithreaded class and several versions of this class run parallely to quicken the pricess time


    int indexFile=0;
    CryptoBase crypt;
    String operation;
    String TARGET_FILE_NAME;
    String SOURCE_FILE_NAME;
    int start;
    int end;

    public ChunkProcessing(String choice,String userRemark, String fileSrc,String fileTarget, int start , int end, int chunkNo) throws IOException
    {
        this.start= start;
        this.end=end;
        indexFile= chunkNo;
        SOURCE_FILE_NAME= fileSrc;
        TARGET_FILE_NAME= fileTarget;
        operation=choice;
        if(choice.equals("Encrypt"))
        crypt= new Encryption(userRemark);
        else 
        crypt= new Decryption(userRemark);
    } 
    
    @Override
    public void run() 
    {
        try{
        ReadFileByPosWriteByFileName abc= new ReadFileByPosWriteByFileName(start,end,SOURCE_FILE_NAME,TARGET_FILE_NAME);
        byte a[] = abc.readBinary();
        byte write[]= new byte[a.length];
        for(int i=0;i<a.length;i++)
            {
                write[i]= crypt.process(a[i]);
            }
        abc.writeBinary(write);
        }
        catch(Exception a)
        {
            System.out.println(a.getMessage()+" in class Chunk Processing ");
        }
        
        
    }


    
    
}
