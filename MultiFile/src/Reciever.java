import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.Set;


public class Reciever {
	Set<FileRecieve> transfers;
	File directory;
	

	
	public static void main(String[] args) throws IOException {
		//ask for session name
		//join session
		//pull catalogue
		//start recievers (maybe only the first one)
		//start sorterBot
		//report ready
		
	// PERIODICALLY:
		//check finished files
		//1-request missing parts	(maybe only 3)
		//2-request new files		(maybe only 3)
		//3-report status/progress	(reporting status may be sufficient)
		
		
		
		
		
		MulticastSocket mc = new MulticastSocket(Constants.MULTICAST_PORT);
		//mc.setLoopbackMode(true);
		mc.joinGroup(Constants.getMCGroup());
		mc.setTimeToLive(Constants.MULTICAST_TTL);
		mc.setReceiveBufferSize(26214400);
		
		while(true){
			byte[] buf =new byte[65535];
			DatagramPacket p = new DatagramPacket(buf, buf.length);
			mc.receive(p);
		}
//		FileRecieve tmp = 
	}
}
