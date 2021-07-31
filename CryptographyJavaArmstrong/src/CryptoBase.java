import java.io.IOException;
import java.util.ArrayList;

public abstract class CryptoBase 
{
    //This is the base class to both encryption nd decryption classes
    //The functions in this class is common to both those processes
    //The research paper attached will help you understand the things happening in this class

    

    int [] ARMSTRONG_DIGITS = {1, 5, 3, 3, 7, 0, 3, 7, 1, 4, 0, 7};
    int KEY_LENGTH= ARMSTRONG_DIGITS.length;
    ArrayList<Integer> numericKey;
    int numericKeyIndex;
    ArrayList<Integer> color;
    int colorIndex;

    public CryptoBase(String userRemark) throws IOException
    {
        //System.out.println("cb cons"); 
        numericKey= new ArrayList<Integer>();
        numericKeyIndex=0;
        this.run(userRemark);
        color=makeColor();
        colorIndex=0;
        
    }

    public void run(String userRemark) throws IOException
    {
        int sum = 0;
        for(char k:userRemark.toCharArray())
        {
            int temp= (int) k;
            if((!numericKey.contains(temp)) && numericKey.size()<KEY_LENGTH)
            {
                numericKey.add(temp);
                sum+=temp;
            }
        }

        if(numericKey.size()<KEY_LENGTH)
            throw new IOException("Weak Key");

        for (int i=0;i<KEY_LENGTH;i++)
            numericKey.set(i, (numericKey.get(i)+ARMSTRONG_DIGITS[i]) % 256);
        
        numericKey.add(sum);
         
    }

    public ArrayList<Integer> getKey()
    {
        return numericKey;
    }
        
    public ArrayList<Integer> makeColor()
    { int a=0; int b=0; int c=0;

        for(int i=0;i<numericKey.size();i++)
        {
            if(i<4) a+=numericKey.get(i);
            else if(i<8) b+=numericKey.get(i);
            else if(i<12) c+=numericKey.get(i);
        }

        int sum= numericKey.get(numericKey.size()-1);
        a=(a+sum)%256;
        b=(b+sum)%256;
        c=(c+sum)%256;
        ArrayList<Integer> ret= new ArrayList<Integer>();
        ret.add(a);
        ret.add(b);
        ret.add(c);
        return ret;
    }
    
    public abstract byte process(byte data);
}
