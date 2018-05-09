package Mail;


import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import threefactor.EmailVerification;
import threefactor.OTPVerification;


 class SendMail {
     
     
     
     public Connection con;
   public Statement st;
    
    public SendMail() {
      //  initComponents();
        
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
       con =DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
       st=con.createStatement();
       }
       catch(Exception ex)
               {
               System.out.println(ex);
               }

    
            
    
        Random rand = new Random();
    
int otp = rand.nextInt(1000000);

String rmail = null;
String smail="Your One Time Password :" ;
try{
    String mailid= "Select * from mydb where online='1'";
    String Otp="Update mydb set OTP='"+otp+"' where online='1'";
    st.addBatch(Otp);
    st.executeBatch();
    ResultSet rs=st.executeQuery(mailid);
    if(rs.next())
    {
        rmail=rs.getString("emailid");
        JOptionPane.showMessageDialog(null, "OTP has been sent  !");
        //new OTPVerification().setVisible(true);
            //    this.setVisible(false);
    }
    
}
catch(Exception e)
{
    JOptionPane.showMessageDialog(null, "Error sending OTP   !");
}
final String username = "thrfctr@gmail.com";
        final String password = "Deepak123";
        
        
              
              
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
          
             
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("thrfctr@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("ds2401996@gmail.com"));
            message.setSubject("One Time password(3FA)");
            message.setText(String.valueOf(otp));
            Transport.send(message);

            System.out.print("Msg Sent Successfully to: ");
            System.out.println(rmail);
            System.out.print(smail);
            System.out.println(otp);
           
           

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
      }        
    
     public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmailVerification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmailVerification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmailVerification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmailVerification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new EmailVerification().setVisible(true);
            }
        });
    }

    }
        
           
   
  
  