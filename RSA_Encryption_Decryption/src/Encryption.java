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
        String teststring = "hanuman is always there" ;
        
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

