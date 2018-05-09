package Mail;

import java.util.Random;

public class Otp
{
   static char[] OTP(int len)
    {
        
        System.out.print("You OTP is : ");
 
        // Using numeric values
        String numbers = "0123456789";
 
        // Using random method
        Random random = new Random();
 
        char[] otp = new char[len];
 
        for (int i = 0; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] =
             numbers.charAt(random.nextInt(numbers.length()));
           // System.out.println(otp);
        }
        
        return otp;
        
    }
     
        public static void main(String[] args) {
        int length = 6;
        System.out.println(OTP(length));   
}
}
 
