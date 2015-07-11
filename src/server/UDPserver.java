package server;
import java.net.*;
import java.io.*;
public class UDPserver {
      private int port=2012;
      private DatagramSocket socket;
      private String filePath="C:\\Users\\Administrator\\Desktop\\test";
      
      public UDPserver(){
    	  try
    	  {
    		  socket=new DatagramSocket(port);//������Ҫ�󶨶˿ںŲſ��Խ��ܵ���Ϣ��
    	  }
    	  catch(SocketException e)
    	  {
    		  e.printStackTrace();
    	  }
    	  
      }
      public void service(){
    	  while(true)
    	  {
    		  //Constructs a DatagramPacket for receiving packets of length length
    		  //����������
    		  DatagramPacket packet=new DatagramPacket(new byte[512],512);
    		  try
    		  {
    			  //Receives a datagram packet from this socket. When this method returns, the DatagramPacket's buffer is filled with the data received. The datagram packet also contains the sender's IP address,
    			  //and the port number on the sender's machine.
    			  //This method blocks until a datagram is received
    			  //������������������
    			  socket.receive(packet);
    			  String message=new String(packet.getData(),0,packet.getLength());
    			  if(message.equals("list"))
    			  {
    				  System.out.println("list received");
    				  StringBuilder sb=new StringBuilder();
    				  File file=new File(filePath);
    				  String [] files=file.list();
    				  for(String s:files){
    					  sb.append(s);
    					  sb.append('\n');
    					  
    				  }
    				  //Set the data buffer for this packet. With the offset of this DatagramPacket set to 0,
    				  //and the length set to the length of buf.
    				 for(int i=0;i<1000;i++);
    				 packet.setData(sb.toString().getBytes());
    			  }
    		    else if(message.startsWith("echo")){
    		    	String sub=message.substring(4).trim();//��ȥ��ͷ��echo,������β���Ͽո�
    			    packet.setData(sub.getBytes());  
    			  }
    		    else{
    				packet.setData("Unknown command".getBytes());  
    				  
    			  }
    			socket.send(packet);
    			  
    		  }
    		  catch(IOException e){
    			  e.printStackTrace();
    		  }
    		  
    		  
    		  
    	  }
      }
      public static void main(String agrs[]){
    	  
    	  new UDPserver().service();
      }
}
