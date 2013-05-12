import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class FileRecieve implements Runnable{
	
	File destination;
	BlockingQueue<FileChunk> inbox;
	boolean[] recieved;
	volatile boolean cancel;
	volatile boolean lastrecieved;
	boolean done;
	
	private final int fileID;
	private final int chunkcount;
	private final File parent;
	private final long size;
	
	public void put(FileChunk chunk){
		inbox.offer(chunk);
	}
	
	public FileRecieve(File parent, FileDescriptor descriptor) {
		this.destination=parent;
		this.fileID = descriptor.fileID;
		this.chunkcount = descriptor.chunkcount;
		this.size = descriptor.size;
		
		recieved = new boolean[chunkcount];
		inbox = new LinkedBlockingQueue<FileChunk>();
		this.parent = parent;
	}

	@Override
	public void run() {
		lastrecieved = false;
		int last_chunkid =-1;
		int hüpf=0;
		try(RandomAccessFile output = new RandomAccessFile(destination, "rw")) {
			
			
			while(!(cancel || (lastrecieved&&finished()))){
				try {
					FileChunk tmp = inbox.take();
					int chunkID = tmp.getHeader().getChunkID();
					//System.out.println(chunkID);
					if(chunkID!=(last_chunkid+1)){
						output.seek(chunkID*Constants.MAX_CHUNKSIZE);
						hüpf++;
					}
					recieved[chunkID]=true;
					output.write(tmp.getData());
					last_chunkid=chunkID;
					if((tmp.getHeader().getTyp()&Constants.LAST_CHUNK)==Constants.LAST_CHUNK){
						printMissing();
						lastrecieved=true;
					}
				} catch (InterruptedException e) {
					System.out.println("Have been interrupted");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			output.close();
			System.out.println(hüpf);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
	}
	
	private void printMissing() {
		for (int i = 0; i < recieved.length; i++) {
			if(!recieved[i])System.out.print(i + " ");
		}
	}


	private boolean finished() {
		boolean finished = true;
		for (int i = 0; i < recieved.length; i++) {
			finished &=recieved[i];
		}
		System.out.println("finished called" + finished);
		return finished;
	}
	
	
	
	

}
