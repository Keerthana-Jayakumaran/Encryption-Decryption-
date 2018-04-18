	import javax.crypto.Cipher;
	import javax.crypto.KeyGenerator;
	import javax.crypto.SecretKey;
	//import javax.xml.bind.DatatypeConverter;
public class AES_Encryption {

	/**
	 * This example program shows how AES encryption and decryption can be done in Java.
	 * Please note that secret key and encrypted text is unreadable binary and hence 
	 * in the following program we display it in hexadecimal format of the underlying bytes.
	 * @author Jayson
	 */
	
	 
	    /**
	     * 1. Generate a plain text for encryption
	     * 2. Get a secret key (printed in hexadecimal form). In actual use this must 
	     * by encrypted and kept safe. The same key is required for decryption.
	     * 3. 
	     */
	    public static void main(String[] args) throws Exception {
	        String plainText = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><TA>  <Response>    <User_Id>WIPRO</User_Id>    <Aadhar_Id>490960443367</Aadhar_Id>    <Auth info=\"03{dd689b63926143ac4cb1f9eedbd7d7646e8cab4117e82921e8f95d245c42315f,e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855,0100002000000210,2.0,20180410195849,1,0,0,0,2.0,f0aadd26d628ff196d9217fed1314c3e6606d8f03f2e4afe22cd69504ed57a8e,f68a26888cb0aef055a45d1c851f4c85f3a0c61784d3186f199d6328dbbf216c,f68a26888cb0aef055a45d1c851f4c85f3a0c61784d3186f199d6328dbbf216c,NA,NA,NA,NA,NA,NA,NA,NA,NA,NA,registered,MANTRA.AND.001,1.0.0,MANTRA.MSIPL,MFS100,L0,NA}\" status=\"y\" Description=\"Authenticated Successfully\" Code=\"6a12b54663934912b3dd11f8ed379042\" RRN=\"810019165730\" />  </Response></TA>";
	        SecretKey secKey = getSecretEncryptionKey();
	        byte[] cipherText = encryptText(plainText, secKey);
	        String decryptedText = decryptText(cipherText, secKey);
	        
	        System.out.println("Original Text:" + plainText);
	        System.out.println("AES Key (Hex Form):"+new String(secKey.getEncoded()));
	       // System.out.println("Encrypted Text (Hex Form):"+bytesToHex(cipherText))+new String(cipherText);
	        System.out.println("Encrypted Text (Hex Form):"+ new String(cipherText));
	        System.out.println("Descrypted Text:"+decryptedText);
	        
	    }
	    
	    /**
	     * gets the AES encryption key. In your actual programs, this should be safely
	     * stored.
	     * @return
	     * @throws Exception 
	     */
	    public static SecretKey getSecretEncryptionKey() throws Exception{
	        KeyGenerator generator = KeyGenerator.getInstance("AES");
	        generator.init(128); // The AES key size in number of bits
	        SecretKey secKey = generator.generateKey();
	        return secKey;
	    }
	    
	    /**
	     * Encrypts plainText in AES using the secret key
	     * @param plainText
	     * @param secKey
	     * @return
	     * @throws Exception 
	     */
	    public static byte[] encryptText(String plainText,SecretKey secKey) throws Exception{
			// AES defaults to AES/ECB/PKCS5Padding in Java 7
	        Cipher aesCipher = Cipher.getInstance("AES");
	        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
	        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
	        return byteCipherText;
	    }
	    
	    /**
	     * Decrypts encrypted byte array using the key used for encryption.
	     * @param byteCipherText
	     * @param secKey
	     * @return
	     * @throws Exception 
	     */
	    public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
			// AES defaults to AES/ECB/PKCS5Padding in Java 7
	        Cipher aesCipher = Cipher.getInstance("AES");
	        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
	        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
	        return new String(bytePlainText);
	    }
	    
	    /**
	     * Convert a binary byte array into readable hex form
	     * @param hash
	     * @return 
	     */
//	    private static String  bytesToHex(byte[] hash) {
//	        return DatatypeConverter.printHexBinary(hash);
//	    }
	

}
