     /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usama
 */
public class Db_Connection1 {
    Connection con;
    Statement st;
    ResultSet rs;
    
     Db_Connection1(){
         
         try{ 
         Class.forName("com.mysql.cj.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db","root","");
         st=con.createStatement();
         System.out.println("Db is connected");
         
         
         }catch(Exception e){
         
         System.out.println(e);
         }
     }
     
     public int RegisterUser(int ACNO,String ACName,String FName,String DOB,String Phone,String Edu,String Adrs,String Ocu,int _Pin){
         int k=0;
        try{
            String sql="INSERT INTO `atm_details`(`AccNum`, `AccName`, `FaName`, `DOB`, `Phone`, `Education`, `Address`, `Occupation`, `Pin`) VALUES ("+ACNO+",'"+ACName+"','"+FName+"','"+DOB+"','"+Phone+"','"+Edu+"','"+Adrs+"','"+Ocu+"',"+_Pin+")";
            k=st.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
        }
        return k;
     }
     
     public int searchAccountNumber(String OldAcNum){
        int Ano=0;
        try{
            String sql="SELECT * FROM `atm_details` WHERE `AccNum`='"+OldAcNum+"'";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()){
                Ano=rs.getInt("AccNum");
            }
        }catch(Exception e){
            System.out.print(e);
        }
        return Ano;
    }
    
    public ResultSet userLogin(String Accountitle,int pin){
         try{
            String sql="SELECT * FROM atm_details where AccName='"+Accountitle+"' and Pin="+pin+"";
            rs=st.executeQuery(sql);
        }catch(Exception e){
            System.out.println(e);
        }
         return rs;
     }
}
