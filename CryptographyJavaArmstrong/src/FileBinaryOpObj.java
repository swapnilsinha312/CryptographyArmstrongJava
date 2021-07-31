import java.io.*;
import java.util.ArrayList;

public class FileBinaryOpObj {
    public static int BUFFER= 2048;
    String SOURCE_IMAGE_PATH;
    String TARGET_IMAGE_PATH;
    private InputStream is;
    private OutputStream os;
    private ArrayList<byte[]> listByteArrays;
    private ArrayList<Integer> noByteInArray;
    private int flag;

    //This file is to take binary fie input and output
    //The file is stored in an arraylist of elements of 2048 bytes each


    public FileBinaryOpObj(String Source , String Target)
    {
        SOURCE_IMAGE_PATH=Source;
        TARGET_IMAGE_PATH=Target;
        listByteArrays= new ArrayList<>();
        noByteInArray= new ArrayList<>();
        flag=0;
    }

    public boolean isError()
    {
        return flag==1;
    }
    public ArrayList<byte[]> getByteArrayList()
    {
        return listByteArrays;
    }

    public ArrayList<Integer> getNoByteArray()
    {
        return noByteInArray;
    }
    
    public void setByteArrayList(ArrayList<byte[]> a)
    {
        listByteArrays= a;
    }

    public void setNoByteArray(ArrayList<Integer> a)
    {
        noByteInArray=a;
    }

    public byte[] readBinary()
    {
        
        try{
            is= new FileInputStream(SOURCE_IMAGE_PATH);
            byte[] imageBuffer= new byte[BUFFER];
            
            int bytesRead = is.read(imageBuffer);
            while(bytesRead!=-1)
            {
                listByteArrays.add(imageBuffer);
                noByteInArray.add(bytesRead);
                imageBuffer= new byte[BUFFER];
                bytesRead = is.read(imageBuffer);
            }
            is.close();
            return imageBuffer;
        }

        catch(IOException io){
            System.out.println("Error in file");
            flag=1; 
            return new byte[0];
        }
    }

    public boolean writeBinary()
    {
        
        if(flag==1)
        {
            System.out.println("Error in file");
            return false;
        }

        try
        {
            os = new FileOutputStream(TARGET_IMAGE_PATH,true);
            for(int i=0;i<listByteArrays.size();i++)
            {
                os.write(listByteArrays.get(i),0,noByteInArray.get(i));
            }
            os.close();
            return true;
        }

        catch(IOException io)
        {
            System.out.println("Error in file");
            flag=1;
            return false;
        }
        
    }

    public static void main(String args[])
    {
        String SOURCE_IMAGE_PATH= "FileOperations\\Sample1In.jpg";
        String TARGET_IMAGE_PATH= "FileOperations\\Sample1Out.jpg";
        FileBinaryOpObj abc = new FileBinaryOpObj(SOURCE_IMAGE_PATH,TARGET_IMAGE_PATH);
        abc.readBinary();
        abc.writeBinary();
        ArrayList <byte[]> ret = abc.getByteArrayList();
        ArrayList <Integer> retNo= abc.getNoByteArray();
        if(ret.size()!=0 && retNo.size()!=0)
        System.out.println("YES");

    }

}
