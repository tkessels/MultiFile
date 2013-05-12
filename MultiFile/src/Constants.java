import java.net.InetAddress;
import java.net.UnknownHostException;


public class Constants {
	static final int MAX_PAYLOAD = 65507;
	static final int HEADERSIZE  = 4+4+4+4+1;
	static final int MAX_CHUNKSIZE = MAX_PAYLOAD-HEADERSIZE;
	static final int MULTICAST_PORT = 6789;
	static final String MULTICAST_IP = "230.229.228.227";
	static final int MULTICAST_TTL = 10;
	
/*	 1 0 0 0 0 0 0 0  Datachunk		1
	 0 1 0 0 0 0 0 0  FirstChunk	2	
	 0 0 1 0 0 0 0 0  LastChunk		4
	 0 0 0 1 0 0 0 0  StatusReport	8	
	 0 0 0 0 1 0 0 0  				16
	 0 0 0 0 0 1 0 0  				32
	 0 0 0 0 0 0 1 0  File			64
	 0 0 0 0 0 0 0 1  Catalogue		128
*/
	public static final int DATACHUNK 	= 1;
	public static final int FIRST_CHUNK = 2;
	public static final int LAST_CHUNK 	= 4;
	public static final int STATUS 		= 8;
	public static final int UNUSED_1	= 16;
	public static final int UNUSED_2	= 32;
	public static final int File		= 64;
	public static final int Catalogue	= 128;
	
	
	
	
	public static InetAddress getMCGroup(){
		try {
			return InetAddress.getByName(MULTICAST_IP);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
