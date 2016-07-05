import java.util.Base64;
import java.util.Random;
import java.math.BigInteger;
import java.util.*;
import java.io.*;


// privateKey = createNewFile(/home/lauteu34/rsa.schluessel/PrivKey.txt)
// publicKey = createNewFile(/home/lauteu34/rsa.schluessel/PubKey.txt)

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class xy {

	//    private boolean checkFile(File file) {
	//    		try{
	//    		file.createNewFile();
	//    				
	////    				"/home/lauteu34/rsa.schluessel/PubKey.txt");
	//            } catch (IOException e) {
	//                System.err.println("Error creating " + file.toString());
	//            }
	//            if (file.isFile() && file.canWrite() && file.canRead())
	//                return true;
	//        
	//        return false;
	//    }
	public static void main(String[] args) {

		switch(args[0]){
		case "decrypt":

			decrypt_verify(args, ".rsapriv", "Bitte geben Sie als zweites Argument Ihren privaten (.rsapriv) Schluessel an");

			break;

		case "encrypt":
		{
			File message = new File(args[1]);
			InputStreamReader isr;
			StringBuffer strings = new StringBuffer();
			byte[] klartextBytes = new byte[(int) message.length()];

			try {
				FileInputStream fis = new FileInputStream(message);
				int readingResult = fis.read(klartextBytes);
				if(readingResult != message.length()){
					throw new RuntimeException("Mit der Dateilänge stimmt etwas nicht.");
				}

			} catch (FileNotFoundException e) {
				System.err.println("Fehler beim Einlesen der Nachricht: Nachricht nicht gefunden");
				return;
			} catch (IOException e) {
				System.err.println("Fehler beim Einlesen der Nachricht");
			}

			if(!args[2].endsWith(".rsapub")){
				System.out.println("Bitte geben Sie als zweites Argument einen oeffentlichen (.rsapub) Schluessel an");
				return;
			}
			File file = new File(args[2]);
			rsa.Key keypub = new rsa.Key(file);

			int blockSize = keypub.getN().bitLength() / 8;
			//byte[] bytes = strings.toString().getBytes();

			int b = 0;
			ArrayList msg_encr = new ArrayList<BigInteger>();
			while(true){
				byte[] currentBlock = new byte[blockSize];

				for(int i = 0; i < blockSize; i++){
					if(b < klartextBytes.length){
						currentBlock[i] = klartextBytes[b];
						b++;
					}else{
						break;
					}

				}
				BigInteger m = new BigInteger(currentBlock);
				BigInteger en = rsa.chif(m, keypub);
				System.out.println(en);
				msg_encr.add(en);
				if(b >= klartextBytes.length){
					break;
				}
			}
			File file_enc = new File(args[1] + ".encr");
			rsa.writeBase64(file_enc, (BigInteger[]) msg_encr.toArray(new BigInteger[msg_encr.size()]));
			break;
		}
		case "verify":

			decrypt_verify(args, ".rsapub", "Bitte geben Sie als zweites Argument einen oeffentlichen (.rsapriv) Schluessel an");

			break;

		case "sign":
			break;
		case "test_write":
			File file = new File("test.txt");
			byte[] bytes = {12, 23, 1, 29};
			BigInteger[] msg = {new BigInteger("45245245"), new BigInteger("5435027548")};
			System.out.println(new BigInteger(bytes));
//			rsa.writeBase64(file, msg);
//			System.out.println("msg: " + msg);
//			
//			InputStreamReader isr;
//			try {
//				isr = new InputStreamReader(new FileInputStream(file));
//				BufferedReader br = new BufferedReader(isr);
//				while(true){
//					String line = br.readLine();
//					if(line == null){
//						break;
//					}
//					BigInteger msg_part = new BigInteger(Base64.getDecoder().decode(line));
//					System.out.println(msg_part);
//				}
//				br.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			


			break;
		case "createnewkey":

			rsa.KeyPair key = rsa.createKeyPair(false, Integer.parseInt(args[1]), 100);
			key.save(new File(args[2]));


			break;
		default:
			System.err.println(args[0] + " is not a valid command");
		}

	}

	/**
	 * @param args
	 * @param keyEnding TODO
	 * @param wrongKeyEndingMessage TODO
	 */
	private static void decrypt_verify(String[] args, String keyEnding, String wrongKeyEndingMessage) {
		File message = new File(args[1]);
		InputStreamReader isr;
		ArrayList<BigInteger> msg_encr = new ArrayList<>();

		try {
			isr = new InputStreamReader(new FileInputStream(message));
			BufferedReader br = new BufferedReader(isr);
			while(true){
				String line = br.readLine();
				if(line == null){
					break;
				}
				System.out.println(line);
				BigInteger msg_part = new BigInteger(Base64.getDecoder().decode(line));
				msg_encr.add(msg_part);
			}
			br.close();

		} catch (FileNotFoundException e) {
			System.err.println("Fehler beim Einlesen der Nachricht: Nachricht nicht gefunden");
			return;
		} catch (IOException e) {
			System.err.println("Fehler beim Einlesen der Nachricht");
		}

		if(!args[2].endsWith(keyEnding)){
			System.out.println(wrongKeyEndingMessage);
			return;
		}

		File file = new File(args[2]);
		rsa.Key keypriv = new rsa.Key(file);


		for(BigInteger msg_part : msg_encr){
			BigInteger m = msg_part;
			BigInteger decrypted = rsa.dech(m, keypriv);
			for(byte b : decrypted.toByteArray()){
				System.out.println((char) b);
		}
		System.out.println();
	}

}}