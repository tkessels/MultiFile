import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class FileTransfer implements Runnable{

	
	
	private final File dataSource;
	BufferedInputStream fin;
	int chunkCount;
	boolean[] requested; 
	
	
	
	public FileTransfer(File data) throws FileNotFoundException, UnknownHostException {
		dataSource=data;
		if(!dataSource.canRead()) throw new IllegalArgumentException("Kann die Datei " + data + " nicht lesen");
		fin = new BufferedInputStream(new FileInputStream(dataSource));
		chunkCount = (int) Math.ceil(dataSource.length()/(float)Constants.MAX_CHUNKSIZE);
		//RandomAccessFile test = new RandomAccessFile(data, "r");
	}
	
	public int getChunkCount(){
		return chunkCount;
	}
	
	public void getAnnouncmentData(){
		
	}

	public void run() {
		try(MulticastSocket mc = new MulticastSocket(Constants.MULTICAST_PORT)) {
			mc.setLoopbackMode(true);
			mc.joinGroup(Constants.getMCGroup());
			mc.setTimeToLive(Constants.MULTICAST_TTL);
			mc.setSendBufferSize(26214400);




		byte[] cup = new byte[Constants.MAX_PAYLOAD];
		int chunkID = 0;
		int len = -1;
			while((len=fin.read(cup,Constants.HEADERSIZE,Constants.MAX_CHUNKSIZE))!=-1){
				byte flag = Constants.DATACHUNK; 
				if(chunkID==0) flag|=Constants.FIRST_CHUNK;
				if(chunkID==(chunkCount-1))flag|=Constants.LAST_CHUNK;
				new FileChunkHeader(flag,0,0,chunkID,len).writeBytes(cup, 0);
				mc.send(new DatagramPacket(cup, cup.length, Constants.getMCGroup(),Constants.MULTICAST_PORT));
				System.out.println(len+"sending "+chunkID);
				chunkID++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

	public static void main(String[] args) throws Exception {
		FileTransfer test = new FileTransfer(new File("d:\\Daten\\Downloads\\kubuntu-12.10-desktop-i386.iso"));
		System.out.println(test.getChunkCount());
		test.run();
		
	}
	

}
