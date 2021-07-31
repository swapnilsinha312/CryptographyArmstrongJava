import java.io.File;
import java.io.IOException;
import java.util.ArrayList; 

public class RunProcess 
{
    //File names ,operation is encryption/decryption, userRemark is key, others are explained in methods they are calculated in 
    String SOURCE_FILE_NAME;
    String TARGET_FILE_NAME;
    String operation;
    String userRemark;
    static int chunkNo=8;
    int[] startIndex;
    int[] endIndex;
    String []namesChunkFiles;

    RunProcess(String op,String src, String trgt, String rem)
    {
        operation= op;
        SOURCE_FILE_NAME=src;
        TARGET_FILE_NAME=trgt;
        namesChunkFiles= new String[chunkNo];
        startIndex= new int[chunkNo];
        endIndex= new int[chunkNo];
        userRemark=rem;
    }

    public String[] processFileName()
    {
        //This just gives out the file name eg if input dir is images/sample.jpg it gives images.sample and .jpg\
        // The file names for each chunk is kept like sample$.jpg where $ is from 1 to 8 (for each chunk)

        String name= SOURCE_FILE_NAME;
        int dot= name.lastIndexOf(".");
        String ret1=name.substring(0,dot);
        String ret2=name.substring(dot,name.length());
        String ret[]={ret1,ret2};
        return ret;
    }
    
    public void divideChunks()
    {
        // Here we create different chunks 
        // the chunk size has been set at 8 
        // So each file will be divided into 8 chunks and processed
        // here we decide the starting and ending index of eeach chunk
        // eg 800 byte file will have 8 chunks like 1-100 , 100-200,300-400 and so on
        // We store the indexes of each chunk in the class variables startIndex and endIndex and also the sub file name
        // As we will make each chunk a file   

        File src= new File(SOURCE_FILE_NAME);
        int srcSize = (int) src.length();
        int  chunkSize = srcSize/chunkNo;
        int end = 0;int start=0;

        String nameDivided[]=this.processFileName();
        int i=0;
        for(i=0;i<chunkNo-1;i++)
        {
            start=end;
            end=start+chunkSize;
            startIndex[i]=start;
            endIndex[i]=end;
            namesChunkFiles[i]= nameDivided[0]+i+nameDivided[1];
            //System.out.println(start+" "+end+" "+namesChunkFiles[i]);
        }

        startIndex[i]=end;
        endIndex[i]=srcSize;
        namesChunkFiles[i]= nameDivided[0]+i+nameDivided[1];
        
    }

    public void divChunks() throws IOException
    {
        // Here we call the chunk procewssing class which proceses each chunk that is encrypts/decrypts ach chunk
        // The use of multihreading is done here.
        // Since the order in whih each chunk is processed is not relevant multithreaduing helps speed up the process
        // Especially when a large file has been inputed

        this.divideChunks();
        ArrayList<Thread> threads= new ArrayList<>();
        for(int i=0;i<chunkNo;i++)
        {
            // ChunkProcessing ab= new 
            // ChunkProcessing(operation,userRemark, SOURCE_FILE_NAME,namesChunkFiles[i], startIndex[i],endIndex[i], i);
            // ab.run();
            
            Thread abcd = new Thread(new 
            ChunkProcessing(operation,userRemark, SOURCE_FILE_NAME,namesChunkFiles[i], startIndex[i],endIndex[i], i));
            threads.add(abcd);
            abcd.start();
        }

        for(int i=0;i<threads.size() && threads.size()!=0;i=(i+1)%threads.size())
        {
             
            if(!threads.get(i).isAlive())
            {
                threads.remove(i);
            }
            if(threads.size()==0) break;
        }

    }

    public void addChunksToOneFile() throws IOException
    {
        // Here all the different sub files during encryption/decryption are used to make one output file

        try
        {
            for(int i=0;i<chunkNo;i++)
            {
                FileBinaryOpObj abc= new FileBinaryOpObj(namesChunkFiles[i],TARGET_FILE_NAME);
                abc.readBinary();
                abc.writeBinary();
            }
        }
        
        catch(Exception a)
        {
            System.out.println(a.getMessage()+" chunkP ");
        }
    }

    public void deleteChunks()
    {
        //The different sub files made during encryption/decryption are deleted here

        for(int i=0;i<chunkNo;i++)
        {
            File a = new File(namesChunkFiles[i]);
            a.delete();
        }
    }

    public void Cryption() throws IOException
    {
        //This is a helper method which calls all other methods in rthe order required 
        // This cleans up the code in MainClass

        this.divChunks();
        this.addChunksToOneFile();
        this.deleteChunks();
    }

}
