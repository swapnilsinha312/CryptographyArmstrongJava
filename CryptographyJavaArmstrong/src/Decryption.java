import java.io.IOException;
import java.util.ArrayList;

public class Decryption extends CryptoBase
{static int i=0;
    Decryption(String userRemark) throws IOException
    {
        super(userRemark);
    }
        
    //This class decryptes one byte of data using color and input key
    //Read the research paper to understand the concept 

    public byte process( byte encoded)
        {
        Integer temp = (encoded - super.color.get(super.colorIndex) + 256) % 256;
        Integer row = temp/16;
        Integer col = temp % 16;
        super.colorIndex = (super.colorIndex + 1) % super.color.size();
        ArrayList<Byte> send = new ArrayList<Byte>();
        send.add(row.byteValue());
        send.add(col.byteValue());
        byte data = ByteKing.nibblesToByte(send);
  
        Integer dataTemp = data ^ super.numericKey.get(super.numericKeyIndex);
        super.numericKeyIndex = (super.numericKeyIndex + 1) % super.KEY_LENGTH;
        
        return dataTemp.byteValue();    
    }

    
}