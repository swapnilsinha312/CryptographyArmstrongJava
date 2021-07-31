import java.util.ArrayList;

public class ByteKing 
{
    // this class is used to process individual bytes and converting them to nibbles or vice vera
    // one byte has 2 nibbles 
    // lower 4 bits make lower nibble upper 4 bits make higher nibble
    // 1 byte = 8 bits = 2 nibbles

    public static ArrayList<Byte> byteToNibbles(byte data)
    {
        Integer a=  4;
        Integer b= 15;
        byte lower_nibble = (byte) (data & b.byteValue());
        byte higher_nibble = (byte) (data >> a.byteValue());
        ArrayList<Byte> ret= new ArrayList<>();
        ret.add(higher_nibble);
        ret.add(lower_nibble);
        return ret;
    }

    public static byte nibblesToByte(ArrayList<Byte> nibbles)
    {
        Integer a= 4;
        Integer b=nibbles.get(0) << a;
        Integer c= nibbles.get(1)|b;
        return (byte) (c.byteValue());
    }
 
}
