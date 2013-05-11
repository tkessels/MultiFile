
public class FileChunk {
	static final int MAX_PAYLOAD = 65507;
	static final int HEADERSIZE  = 4+4+4+4+1;
	static final int MAX_CHUNKSIZE = MAX_PAYLOAD-HEADERSIZE;
	
	private byte[] data;
	private FileChunkHeader header;
	
	public FileChunk(byte[] data) {
		header = new FileChunkHeader(data);
		this.data = new byte[header.getDatalength()];
		System.arraycopy(data, HEADERSIZE, this.data, 0, header.getDatalength());
	}
	
	public byte[] getData(){
		return data;
	}
	
	
	public FileChunkHeader getHeader(){
		return header;
	}
	

}
