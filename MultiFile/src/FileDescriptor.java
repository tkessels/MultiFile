public class FileDescriptor {
	final String datei;
	final long size;
	final int fileID;
	final int chunkcount;
	boolean isComplete;
	
	
	
	public FileDescriptor(String datei, long size, int chunkcount) {
		this.datei = datei;
		this.size = size;
		this.fileID = hashCode();
		this.chunkcount = chunkcount;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datei == null) ? 0 : datei.hashCode());
		result = prime * result + (int) (size ^ (size >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDescriptor other = (FileDescriptor) obj;
		if (datei == null) {
			if (other.datei != null)
				return false;
		} else if (!datei.equals(other.datei))
			return false;
		if (size != other.size)
			return false;
		return true;
	}
	


}
