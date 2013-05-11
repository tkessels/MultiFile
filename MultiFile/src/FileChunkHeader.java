
public class FileChunkHeader {
//	static final int MAX_PAYLOAD = 65507;
//	static final int HEADERSIZE  = 4+4+4+4+1;
//	static final int MAX_CHUNKSIZE = MAX_PAYLOAD-HEADERSIZE;
	
	private final byte typ;
	private final int sessionID;
	private final int fileID;
	private final int chunkID;
	private final int datalength;
	
	
	
	
	
	
	public FileChunkHeader(byte typ, int sessionID, int fileID, int chunkID,int datalength) {
		this.typ = typ;
		this.sessionID = sessionID;
		this.fileID = fileID;
		this.chunkID = chunkID;
		this.datalength = datalength;
	}
	
	public FileChunkHeader(final byte[] source){
		this.typ = source[0];
		this.sessionID = readInt(source, 1);
		this.fileID =  readInt(source, 5);
		this.chunkID =  readInt(source, 9);
		this.datalength =  readInt(source, 13);
	}
	
	
	private static void writeInt(final int x , byte[] target,final int offset){
		target[offset] = (byte) (x >> 24);
		target[offset+1] = (byte) (x >> 16);
		target[offset+2] = (byte) (x >> 8);
		target[offset+3] = (byte) (x);
	}
	
	private static int readInt(final byte[] source, int offset){
		return (int) (((source[offset] & 0xff) << 24) |((source[offset+1] & 0xff) << 16) |((source[offset+2] & 0xff) << 8)| (source[offset+3] & 0xff) );
	}
	
	
	public byte[] getBytes(){
		byte[] tmp = new byte[1+4+4+4+4];
		tmp[0] = (byte) typ;
		writeInt(sessionID, tmp, 1);
		writeInt(fileID, tmp, 5);
		writeInt(chunkID, tmp, 9);
		writeInt(datalength, tmp, 13);
		return tmp;
	}
	
	public void writeBytes(byte[] target, int offset){
		target[offset] = (byte) typ;
		writeInt(sessionID, target, offset+1);
		writeInt(fileID, target, offset+5);
		writeInt(chunkID, target, offset+9);
		writeInt(datalength, target, offset+13);
	}
	
	

	@Override
	public String toString() {
		return "FileChunkHeader [typ=" + typ + ", sessionID=" + sessionID
				+ ", fileID=" + fileID + ", chunkID=" + chunkID
				+ ", datalength=" + datalength + "]";
	}
	
	
	
//	public static int getMaxPayload() {
//		return MAX_PAYLOAD;
//	}
//
//	public static int getHeadersize() {
//		return HEADERSIZE;
//	}

	public byte getTyp() {
		return typ;
	}

	public int getSessionID() {
		return sessionID;
	}

	public int getFileID() {
		return fileID;
	}

	public int getChunkID() {
		return chunkID;
	}

	public int getDatalength() {
		return datalength;
	}

	
	
	
	


}
