import java.io.IOException;
import java.util.ArrayList;

public class Encryption extends CryptoBase
{
    public Encryption(String userRemark) throws IOException
    {
        super(userRemark);
        //System.out.println("enc cons");
        
    }

    public byte process(byte data)
    {
        Integer a = super.numericKey.get(super.numericKeyIndex);
        data=(byte) (data ^ a.byteValue());
        super.numericKeyIndex++;
        super.numericKeyIndex%=super.KEY_LENGTH;
        ArrayList<Byte> rc= ByteKing.byteToNibbles(data);
        byte row= rc.get(0);
        byte col= rc.get(1);
        Integer encoded = (super.color.get(super.colorIndex) + row * 16 + col) % 256;
        super.colorIndex = (super.colorIndex + 1) % super.color.size();

        return encoded.byteValue();
    } 

    //This class encryptes one byte of data using color and inout key
    //Read the research paper to understand the concept 
}
