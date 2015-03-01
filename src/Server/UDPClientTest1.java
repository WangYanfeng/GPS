package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * WangYanfeng 作者 E-mail:wangyanfeng_2012@163.com
 * 创建时间：2015年2月6日 下午2:27:11
 * 类说明
 */
public class UDPClientTest1 {
	public final static int DEFAULT_PORT = 2323;
	public final static int MAX_PACKET_SIZE = 65507;
	
	private static DatagramPacket outgoing;
	
	public static void main(String args[]){
		String host="localhost";
		byte[] byte1=new byte[1];
		byte1[0]=3;
		String gpgga="$GPGGA,070128.00,3802.4652,N,11425.9609,E,1,10,2.24,58.8,M,-2.4,M,,*77";
		int k=0;
		while(true){
			try {
				if(k==400){
					break;
				}
				k++;
				Thread.sleep(1000);
				
				try
				{
					//维度每分 1.85km
					//行军速度大概每秒钟50m,测试每秒行进0.027分
					//前端界面 3km 对应 600px。3km 即  每分 对应370个像素点
					//经度对应的应该比维度对应的小
					String[] temp=gpgga.split(",");
					Double d1=Double.parseDouble(temp[1])+1;
					temp[1]="0"+d1.toString();
					Double d2=Double.parseDouble(temp[2])-0.0008;
					temp[2]=d2.toString();
					Double d4=Double.parseDouble(temp[4])-0.0002;
					temp[4]=d4.toString();
					gpgga="";
					for(int i=0;i<temp.length;i++){
						gpgga+=temp[i];
						gpgga+=",";
					}
					gpgga=gpgga.substring(0, gpgga.length()-1);
					byte[] byte2=gpgga.getBytes();
					byte[] byte3=new byte[byte2.length+byte1.length];
					System.arraycopy(byte1, 0, byte3, 0, byte1.length);  
			        System.arraycopy(byte2, 0, byte3, byte1.length, byte2.length);  
					
			        outgoing=new DatagramPacket(byte3, byte3.length, InetAddress.getByName(host), DEFAULT_PORT);
					DatagramSocket socket=new DatagramSocket();
					socket.send(outgoing);
				} catch (IOException e)
				{
					// TODO: handle exception
					System.err.println(e);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
