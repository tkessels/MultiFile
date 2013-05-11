import java.util.Random;


public class Transfer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random x = new Random(System.currentTimeMillis());
		
		FileChunkHeader tmp1 = new FileChunkHeader((byte) x.nextInt(), x.nextInt(), x.nextInt(), x.nextInt(), x.nextInt(65535));
		System.out.println(tmp1);
		byte[] tmp2 = tmp1.getBytes();
		
		FileChunkHeader tmp3 = new FileChunkHeader(tmp2);
		System.out.println(tmp3);



	}

}
