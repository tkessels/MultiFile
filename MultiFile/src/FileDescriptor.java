import java.io.File;


public class FileDescriptor {
	final File datei;
	final long size;
	final int fileID;
	final int chunkcount;
	
	
	public FileDescriptor(File datei, long size, int chunkcount) {
		this.datei = datei;
		this.size = size;
		fileID = datei.hashCode();
		this.chunkcount = chunkcount;
	}

}
