import java.io.IOException;

public class MainClass 
{
    public static void main(String args[]) throws IOException
    { 
        String SourceFile="C:\\Users\\Swap\\OneDrive\\Desktop\\college\\Git\\CryptographyJavaArmstrong\\data\\abcd.jpeg";
        String EncryFile="C:\\Users\\Swap\\OneDrive\\Desktop\\college\\Git\\CryptographyJavaArmstrong\\data\\abcd-en.jpeg";
        String TargetFile="C:\\Users\\Swap\\OneDrive\\Desktop\\college\\Git\\CryptographyJavaArmstrong\\data\\abcd-out.jpeg";
        String key="IJKABCDGHIEFMNBOP";//"ABCDEFGHIJKLMNOPQ";

        long startTime = System.nanoTime();

        RunProcess abc = new RunProcess("Encrypt", SourceFile, EncryFile, key); 
        abc.Cryption();
        System.out.println("Encryption complete");

        RunProcess abcd = new RunProcess("Decrypt", EncryFile, TargetFile, key); 
        abcd.Cryption();
        System.out.println("Decryption complete");        
    
        Long endTime = System.nanoTime();
        System.out.println(("Time taken in seconds = "+(endTime - startTime)/1000000000.0));
        
        //This class runs the entire program, with the file names and key defined here
        //I tried a 30.4 mb  file and it took 3.3261438 s
        //This can be further brought down if we change the number of chunks with respect to file size
    }
    
}
