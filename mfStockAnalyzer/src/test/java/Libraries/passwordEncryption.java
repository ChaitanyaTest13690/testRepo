package Libraries;

import org.apache.commons.codec.binary.Base64;

public class passwordEncryption{
     
     public static String SelsetSecure(String  str) {
            byte[] encode=Base64.encodeBase64(str.getBytes());
            //System.out.println("String before encoding : "+ str);
            //System.out.println("String after encoding : "+ new String(encode));
            String strEncode= new String(encode);
            byte[] decode=Base64.decodeBase64(encode);
            System.out.println("String after decoding : "+ new String(decode));
            return strEncode;
     }
     
     public static String encodePassword(String  strActualPassword) {  // to encrypt the pass word
            byte[] encode=Base64.encodeBase64(strActualPassword.getBytes());        
            String strEncodedPassword= new String(encode);
            return strEncodedPassword;
            
     }
     public static String decodePassword(String  strEncodedPassword) {// if the password is already encrypted then use this method 
            byte[] decode=Base64.decodeBase64(strEncodedPassword);            
            String strdecode= new String(decode);
            return strdecode;          
            
     }
     
}
