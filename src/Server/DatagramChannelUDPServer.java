package Server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * WangYanfeng 作者 E-mail:wangyanfeng_2012@163.com
 * 创建时间：2015年2月3日 下午3:12:44
 * 
 * 非阻塞UDP服务器，测试发出GPS通信包
 */
public class DatagramChannelUDPServer {
	public final static int DEFAULT_PORT = 2323;
	public final static int MAX_PACKET_SIZE = 65507;

	public static void main(String[] args)
	{
		int port = DEFAULT_PORT;
		try
		{
			DatagramChannel channel = DatagramChannel.open();
			DatagramSocket socket = channel.socket();
			SocketAddress address = new InetSocketAddress(port);
			socket.bind(address);
			ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
			System.out.println("Server wait...");
			while (true)
			{
				SocketAddress client = channel.receive(buffer);
				buffer.flip();
				
				System.out.println(client+"says:");
				while(buffer.hasRemaining()) System.out.println(buffer.get());

				buffer.clear();
				//$GPGGA,215026.00,2233.94798,N,11402.42687,E,1,10,2.24,58.8,M,-2.4,M,,*77
				String gpgga="$GPGGA,215026.00,2233.94798,N,11402.42687,E,1,10,2.24,58.8,M,-2.4,M,,*77";
				//ByteBuffer gpsData = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
				byte[] rebuf=gpgga.getBytes();
				
				//gpsData.put((byte)1);
				//gpsData.put(gpgga.getBytes());
				byte i=1;
				buffer.put(i);
				buffer.put(rebuf, 0, rebuf.length);
				buffer.rewind();
				channel.send(buffer, client);
				buffer.clear();
			}

		} catch (IOException e)
		{
			// TODO: handle exception
			System.err.println(e);
		}

	}
}
