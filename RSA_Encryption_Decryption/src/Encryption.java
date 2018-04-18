import java.math.BigInteger; 
import java.util.Random;
import java.io.*;
public class Encryption {

    private BigInteger p; 
    private BigInteger q; 
    private BigInteger N; 
    private BigInteger phi; 
    private BigInteger e; 
    private BigInteger d; 
    private int bitlength = 2048; 
    private int blocksize = 1024; //blocksize in byte 
     
    private Random r; 
     public Encryption() { 
        r = new Random(); 
        p = BigInteger.probablePrime(bitlength, r); 
        q = BigInteger.probablePrime(bitlength, r); 
        N = p.multiply(q); 
           
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); 
        e = BigInteger.probablePrime(bitlength/2, r); 
         
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) { 
            e.add(BigInteger.ONE); 
        } 
 d = e.modInverse(phi);  
    } 
     
    public Encryption(BigInteger e, BigInteger d, BigInteger N) { 
        this.e = e; 
        this.d = d; 
        this.N = N; 
    } 
    private static String bytesToString(byte[] encrypted) { 
        String test = ""; 
        for (byte b : encrypted) { 
            test += Byte.toString(b); 
        } 
        return test; 
    } 
     
 //Encrypt message
     public byte[] encrypt(byte[] message) {      
        return (new BigInteger(message)).modPow(e, N).toByteArray(); 
    } 
       
// Decrypt message
    public byte[] decrypt(byte[] message) { 
        return (new BigInteger(message)).modPow(d, N).toByteArray(); 
    }  
  
	public static void main (String[] args) throws IOException
{ 
		Encryption rsa = new Encryption(); 
        DataInputStream in=new DataInputStream(System.in);  
        String teststring ;

//        teststring ="<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><TA>  <Response>    <User_Id>WIPRO</User_Id>    <Aadhar_Id>490960443367</Aadhar_Id>    <Auth info=\"03{dd689b63926143ac4cb1f9eedbd7d7646e8cab4117e82921e8f95d245c42315f,e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855,0100002000000210,2.0,20180410195849,1,0,0,0,2.0,f0aadd26d628ff196d9217fed1314c3e6606d8f03f2e4afe22cd69504ed57a8e,f68a26888cb0aef055a45d1c851f4c85f3a0c61784d3186f199d6328dbbf216c,f68a26888cb0aef055a45d1c851f4c85f3a0c61784d3186f199d6328dbbf216c,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,registered,MANTRA.AND.001,1.0.0,MANTRA.MSIPL,MFS100,L0,NA}\" status=\"y\" Description=\"Authenticated Successfully\" Code=\"6a12b54663934912b3dd11f8ed379042\" RRN=\"810019165730\" />  </Response></TA>"; 
        
        
        teststring = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><TA> <Response>    <User_Id>WIPRO</User_Id>    <Aadhar_Id>490960443367</Aadhar_Id>    <Auth info=\"03{dd689b63926143ac4cb1f9eedbd7d7646e8cab4117e82921e8f95d245c42315f,e3b0c44298fc1c149afbf4c8996fb9247ae41e4649b934ca495991b7852b855,0100002000000210,2.0,20180410195849,1,0,0,0,2.0,f0aadd26d628ff196d9217fed1314c3e6606d8f03f2e4afe22cd69504ed57a8e,f68a26888cb0aef055a45d1c851f4c85f3a0c61784d3186f199d6328dbbf216c,f68a26888cb0aef055a45d1c851f4c85f3a0c61784d3186f199d6328dbbf216c,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,registered,MANTRA.AND.001,1.0.0,MANTRA.MSIPL,MFS100,L0,NA}\" status=\"y\" Description=\"Authenticated Successfully\" Code=\"6a12b54663934912b3dd11f8ed379042\" RRN=\"810019165730\" />  </Response></TA>";
        
        System.out.println("Encrypting String: " + teststring); 
        System.out.println("String length: " + teststring.getBytes("UTF-8").length);
        System.out.println("String in Bytes: " + teststring.getBytes().length); 

        // encrypt 
        byte[] encrypted = rsa.encrypt(teststring.getBytes());                   
        
        System.out.println("Encrypted String in Bytes: " +bytesToString(encrypted)); 
         
        // decrypt 
        byte[] decrypted = rsa.decrypt(encrypted);       
        System.out.println("Decrypted String in Bytes: " +  bytesToString(decrypted)); 
         
        System.out.println("Decrypted String: " + new String(decrypted)); 
         
    } 

 
}

