package client;
import java.net.*;
import java.io.*;
public class UDPclient {
	private DatagramSocket socket;//һ��socket��ʶ ��Ӧһ������,
	private int port=2012;
	
	public UDPclient(){
		try
		{
			socket=new DatagramSocket();
			//socket.setSoTimeout(10); //��ʱ����
		}
		catch (SocketException e)
		{
			e.printStackTrace();
			
		}
		
	}
	public void Chat(){
		try
		{
			InetAddress inet=InetAddress.getLocalHost();//Ϊ������������һ��InetAddress���󣬻�ȡ����������IP��ַ
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
			//String message="list";
			String message="";
			message=in.readLine();
			while(!message.equals("close"))
			{
				//��ȡ�û����ݲ�ת��Ϊ�ֽ���
				byte[] bytes=message.getBytes();
				//UDP���ݱ����û��ķ������ݣ��û����ݳ��ȣ��Զ�ip��ַ�Ͷ˿ں�
				DatagramPacket output_packet=new DatagramPacket(bytes,bytes.length,inet,port);
				//socket���Ͷ���
				socket.send(output_packet);
				
				//������ܻ�������С����һ���Զ�ȡ�����ݵĳ��ȣ�Э��涨
				DatagramPacket input_packet=new DatagramPacket(new byte[512],512);
				//socket���ն���
		        socket.receive(input_packet);
		        //???����Ҫ��ӡ��UDP���ĺͱ�ͷ�ĳ����ֶ��𣿣�
		        System.out.println(new String(input_packet.getData(),0,input_packet.getLength()));
				//������ȡ
		        message=in.readLine();
		        //message="close";
				
			}
			System.out.println("closed");
		}
		//�����Ҳ���Զ�������쳣
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		//�������쳣
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String agrs[]){
		new UDPclient().Chat();
		
	}

}
