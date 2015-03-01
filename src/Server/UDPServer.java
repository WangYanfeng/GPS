package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import Dao.GPSDataDao;
import Util.GPSDataEntity;
import Util.GPSDataParse;

/**
 * WangYanfeng 作者 E-mail:wangyanfeng_2012@163.com
 * 创建时间：2015年2月3日 下午6:41:55
 * 类说明
 */
public class UDPServer {
	public static void main(String args[]){
		int DEFAULT_PORT = 2323;
		//
		int MAX_PACKET_SIZE=65507;
		byte[] buffer=new byte[MAX_PACKET_SIZE];
		try {
			System.out.println("Wait...");
			DatagramSocket socket = new DatagramSocket(DEFAULT_PORT);
			DatagramPacket packet=new DatagramPacket(buffer,buffer.length);
			
			while(true){
				try {
					socket.receive(packet);
					byte[] result=packet.getData();
					System.out.println(new String(result));
					
					//解析数据
					GPSDataEntity entity=GPSDataParse.parse(result);
					System.out.println("维度:"+entity.getLatitude()+"经度:"+entity.getLongitude()+"海拔:"+entity.getAltitude());
					//存数据库
					GPSDataDao dao=new GPSDataDao();
					dao.saveData(entity);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
