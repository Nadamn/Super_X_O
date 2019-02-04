package super_x_o;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;



class Player
{
    int id;
    String name;
    String pwd;
    int score;
    
    public Player(int id,String name,String passWord,int score){
        this.id=id;
        this.name=name;
        this.pwd=passWord;
        this.score=score;
    }
    
    public int getID(){return id;}
    public String getName(){return name;}
    public String getPwd(){return pwd;}
    public int getScore(){return score;}
}

public class DataBaseManager 
{
    Connection con;
    DriverManager DM;
    Statement st;
    Player p;
    static ResultSet rs;
    
    
    public static void  main (String[] args) throws SQLException
    {
        DataBaseManager obj = new DataBaseManager();
        //rs = obj.selectFunc("select * from Students");
       // rs = obj.insertFunc("dummyUser", "d12345", 400);
        
        
        
        
    }
    
    public DataBaseManager() throws SQLException
    {
        try
        {
            DM.registerDriver(new org.gjt.mm.mysql.Driver());
            con = DM.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3277229","sql3277229","nr7ins6aHR");
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public ResultSet selectFunc(String expression) throws SQLException
    {
        st = con.createStatement();
        rs = st.executeQuery(expression);
        return rs;
    }
 /*
    public ResultSet updateFunc(String st1, String st2,String st3,String st4,int sid) throws SQLException
    {
        PreparedStatement sst = con.prepareStatement("update Students set FirstName = ? , LastName = ? , email = ? , telephone = ? where SID=?;");
        sst.setString(1, st1);
        sst.setString(2, st2);
        sst.setString(3, st3);
        sst.setString(4, st4);
        sst.setInt(5,sid);
        sst.executeUpdate();
        rs = this.selectFunc("select * from Students");
        return rs;
    }
*/
    
    
        public ResultSet insertFunc(String st1, String st2,int sc) throws SQLException
    {
        PreparedStatement sst = con.prepareStatement("insert into players (name,pwd,score) values (?,?,?);");
        sst.setString(1, st1);
        sst.setString(2, st2);
        sst.setInt(3, sc);
       
        sst.executeUpdate();
        rs = this.selectFunc("select * from players");
        return rs;
    }

   /*
   public void deleteFunc(int sid) throws SQLException
    {
        PreparedStatement sst = con.prepareStatement("delete from Students where SID = ?;");
        sst.setInt(1,sid);
        sst.executeUpdate();
        rs = this.selectFunc("select * from Students");
    }
*/
    
    public Vector convert(ResultSet rr) throws SQLException
    {
        Vector myVector = new Vector();
        while(rr.next())
        {
            Player newPlayer=new Player(rr.getInt(1),rr.getString(2),rr.getString(3),rr.getInt(4));
            myVector.add(newPlayer);
        }
        return myVector;
    }
    
    public Vector getPlayerNames() throws SQLException{
        Vector myVector = new Vector();
         
        ResultSet rr=this.selectFunc("select * from players");
         while(rr.next())
        {
            String newName=new String(rr.getString(2));
            myVector.add(newName);
        }
        return myVector;
    }
    
    
    
}
