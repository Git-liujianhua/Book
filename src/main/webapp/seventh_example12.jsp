<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=gb2312" %>
<HTML><body bgcolor=AAEF9E><font size=2>
<%  Connection con=null;
    Statement stat; 
    ResultSet rs;
	try{
		  Class.forName("com.mysql.jc.jdbc.Driver");
    
		  int n=100;
          String uri="jdbc:mysql://localhost:3306/bank?"+"user=root&password=root&characterEncoding=gb2312";
          con=DriverManager.getConnection(uri);
          con.setAutoCommit(false);  //关闭自动提交模式
          stat=con.createStatement();
          rs=stat.executeQuery("SELECT userMoney FROM user WHERE name='A'");
          rs.next();
          double aMoney=rs.getDouble("userMoney");
          rs=stat.executeQuery("SELECT userMoney FROM user WHERE name='B'");
          rs.next();
          double bMoney=rs.getDouble("userMoney");
          out.print("转账前A的userMoney的值是"+aMoney+"<br>");
          out.print("转账前B的userMoney的值是"+bMoney+"<br>");
          aMoney=aMoney-n;
          if(aMoney>=0) {
            bMoney=bMoney+n;
            stat.executeUpdate("UPDATE user SET userMoney ="+aMoney+" WHERE name='A'");
            stat.executeUpdate("UPDATE user SET userMoney="+bMoney+" WHERE name='B'");
            int a=1/0;
            con.commit();                 //开始事务处理
            
          }
          rs=stat.executeQuery("SELECT * FROM user WHERE name='A'||name='B'"); 
          out.println("<b>转账后的情况如下:<br>"); 
          while(rs.next()) {
              out.print(rs.getString(1)+"	");
              out.print(rs.getString(2)); 
              out.print("<br>");
          }
          
     }
     catch(Exception e){
         con.rollback();           //撤消事务所做的操作
         out.println(e);
     }finally{
		 con.close();
	 }
%>
</font></body></HTML>
