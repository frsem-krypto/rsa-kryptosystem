import java.util.Random;
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
	
		
	public static KeyPair createKeyPair(int bitLength, int certainly){
		BigInteger p = new BigInteger(bitLength, certainly, newRandom());
		BigInteger q = new BigInteger(bitLength, certainly, newRandom());
		
		BigInteger n = p.multiply(q);
		
		BigInteger phiVonN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		BigInteger e = new BigInteger(bitLength, newRandom());
		while (e.gcd(phiVonN).equals(BigInteger.ONE) == false){
			e = e.add(BigInteger.ONE);
		}
		
		BigInteger d = e.modInverse(phiVonN);
		
		
		return new KeyPair(new Key(d, n), new Key(e, n));
	}

	private static Random newRandom() {
		
		
		System.out.println("Wollen Sie eine manuelle Zufallserzeugung durchfuehren?");
		Scanner scanner = new Scanner(System.in);
		char answer = scanner.nextLine().toLowerCase().toCharArray()[0];
		scanner.close();
		
		if(answer == 'y' || answer == 'j' || answer == '1' || answer == 't'){
			
		} else {
			for(int i = 0; i < 100; i++){
				new RandomThread().start();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		return new Random();
	}

	public static class RandomThread extends Thread{
		
		public double random;
		
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
			
			this.random = random;
		}
		
	}
}
