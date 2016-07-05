import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.*;
import java.io.*;



/*
 * TODO: Wie werden Texte vor dem Chiffrieren kodiert?
 * TODO: Wie werden Schlaessel und andere Binaerdaten gespeichert
 * (BASE64, hex?)
 * 
 * TODO: Woher kommt der Zufall? 
 */
public class rsa {
	public static class Key {
		/**
		 * 
		 */
		public Key(File file) {
			try{
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
				BufferedReader br = new BufferedReader(isr);
				
				String stringN = br.readLine();
				String stringExp = br.readLine();
				
				this.N = new BigInteger(Base64.getDecoder().decode(stringN));
				this.exponent = new BigInteger(Base64.getDecoder().decode(stringExp));
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		};
		
		/**
		 * 
		 */
		
		private BigInteger exponent;
		private BigInteger N;
		
		public Key(BigInteger exponent, BigInteger N) {
			this.N = N;
			this.exponent = exponent;
		};


		
		public BigInteger getExponent(){
			return exponent;
		}
		public BigInteger getN(){
			return N;
		}
		
		/*TODO: erstellen eines File-Objekts und Aufrufen der save-Methode nach dem Genererieren der Schlüssel
		 *        bzw. auf Wunsch
		 */
		public void save(File file){  //experimentell: funktioniert evtl. noch nicht wie vorhergesehen
			try {
				FileOutputStream stream = new FileOutputStream(file);
			
				String base64exp = Base64.getEncoder().encodeToString(this.exponent.toByteArray());
				String base64N = Base64.getEncoder().encodeToString(N.toByteArray());
				stream.write(base64exp.getBytes());
				stream.write("\n".getBytes());
				stream.write(base64N.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	
	public static class KeyPair
	{
		private Key priv ;
		private Key pub;
		
		public KeyPair(File file) {
			
		}
		public KeyPair(Key priv, Key pub) {
			this.priv = priv;
			this.pub = pub;
		}
		public void save(File file) {
			file.mkdir();
			File publicKeyFile = new File(file, "publickey.rsapub");
			File privateKeyFile = new File(file, "privatekey.rsapriv");
			
			priv.save(privateKeyFile);
			pub.save(publicKeyFile);
		}
		public Key getPriv() {
			return priv;
		}
		public Key getPub() {
			return pub;
		}
		
	};

	public final int KeySize = 100;
	

	
	public Key  extractpubKey() {
		return null;
	}
	
	
	public static BigInteger chif(BigInteger message, Key oeff){
		return message.modPow(oeff.getExponent(), oeff.getN());
	}
	public static BigInteger dech(BigInteger message, Key priv){
		return message.modPow(priv.getExponent(), priv.getN());
	}
	public static BigInteger sign(BigInteger message, Key priv){
		return message.modPow(priv.getExponent(), priv.getN());
	}
	public static BigInteger veri(BigInteger message, Key oeff){
		return message.modPow(oeff.getExponent(), oeff.getN());
	}
	
		
	public static KeyPair createKeyPair(boolean manualRandom, int bitLength, int certainly){
//		System.out.println("Wollen Sie eine manuelle Zufallserzeugung durchfuehren?");
//		Scanner scanner = new Scanner(System.in);
//		char answer = scanner.nextLine().toLowerCase().toCharArray()[0];
//		
//		boolean manualRandom = (answer == 'y' || answer == 'j' || answer == '1' || answer == 't');
			
		BigInteger p = new BigInteger(bitLength, certainly, newRandom(manualRandom));
		BigInteger q = new BigInteger(bitLength, certainly, newRandom(manualRandom));
		
		BigInteger n = p.multiply(q);
		
		BigInteger phiFromN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		BigInteger e = new BigInteger(bitLength, newRandom(manualRandom));
		while (e.gcd(phiFromN).equals(BigInteger.ONE) == false){
			e = e.add(BigInteger.ONE);
		}
		
		BigInteger d = e.modInverse(phiFromN);
		
		
		return new KeyPair(new Key(d, n), new Key(e, n));
	}

	private static SecureRandom newRandom(boolean manualRandom) {

//		if(manualRandom){
//			
//		} else {
//			
//			byte[] seed = SecureRandom.getSeed(20);
//
//			RandomThread[] threads = new RandomThread[seed.length*8];
//			for(int i = 0; i < threads.length; i++){
//				threads[i] = new RandomThread();
//				threads[i].start();
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			
//			int t = 0;
//			for (int i = 0; i < seed.length; i++) {
//				byte seedpart = Byte.MIN_VALUE;
//				long factor = 1;
//				for (int j = 0; j < 8; j++) {
//					RandomThread thread = threads[t];
//					while (!thread.finish) {
//						try {
//							Thread.sleep(10);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//					if (thread.bit)
//						seedpart += factor;
//					factor *= 2;
//					t++;
//				}
//				seed[i] = seedpart;
//			}
//			return new SecureRandom(seed);
//		}
		
		return new SecureRandom();
	}

	public static class RandomThread extends Thread{
		
		boolean bit;
		boolean finish;
		
		@Override
		public void run() {
			
			long millisBegin = System.currentTimeMillis();
			double random;
			
			do {
				random = Math.random();
			} while(System.currentTimeMillis() < millisBegin + 50);
			
			millisBegin = System.currentTimeMillis();
			int time = (int) Math.floor(random * 100);
			
			do {
				random = Math.random();
			} while(System.currentTimeMillis() < millisBegin + time);
			
			this.bit = random < 0.5;
			
			finish = true;
		}
		
	}
}
