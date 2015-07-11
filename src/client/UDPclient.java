package client;
import java.net.*;
import java.io.*;
public class UDPclient {
	private DatagramSocket socket;//一个socket标识 对应一个进程,
	private int port=2012;
	
	public UDPclient(){
		try
		{
			socket=new DatagramSocket();
			//socket.setSoTimeout(10); //超时设置
		}
		catch (SocketException e)
		{
			e.printStackTrace();
			
		}
		
	}
	public void Chat(){
		try
		{
			InetAddress inet=InetAddress.getLocalHost();//为本地主机创建一个InetAddress对象，获取本地主机的IP地址
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
			//String message="list";
			String message="";
			message=in.readLine();
			while(!message.equals("close"))
			{
				//获取用户数据并转化为字节流
				byte[] bytes=message.getBytes();
				//UDP数据报：用户的发送数据，用户数据长度，对端ip地址和端口号
				DatagramPacket output_packet=new DatagramPacket(bytes,bytes.length,inet,port);
				//socket发送队列
				socket.send(output_packet);
				
				//定义接受缓冲区大小，和一次性读取的数据的长度，协议规定
				DatagramPacket input_packet=new DatagramPacket(new byte[512],512);
				//socket接收队列
		        socket.receive(input_packet);
		        //???这是要打印出UDP报文和报头的长度字段吗？？
		        System.out.println(new String(input_packet.getData(),0,input_packet.getLength()));
				//继续读取
		        message=in.readLine();
		        //message="close";
				
			}
			System.out.println("closed");
		}
		//捕获找不到远端主机异常
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		//捕获多个异常
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String agrs[]){
		new UDPclient().Chat();
		
	}

}
