package mx.gob.imss.cit.bp.services.util;

import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Encriptador {
	
	public static final String ALGORITMO_AES = "AES";
	
	private static final String sKey = "So long and thanks for all the f";
	
	/**
	 * Logger
	 */
	private static final  Logger LOG = LoggerFactory.getLogger(Encriptador.class);
	
	public Encriptador() {
	}
		
	public byte[] encrypt(byte[] input, byte[] keyBytes) throws IOException {
		Cipher out = null;
		
		Key key = new SecretKeySpec(keyBytes, ALGORITMO_AES);
		byte[] result = null;
		try {
			out =Cipher.getInstance(ALGORITMO_AES);
			out.init(Cipher.ENCRYPT_MODE, key);
			result = out.doFinal(input);
		} catch (Exception e){
			LOG.error("ERROR - encrypt: " + e.getMessage());
		}

		return result;
	}
	
	public byte[] encrypt(byte[] input) throws IOException {
		return encrypt(input, sKey.getBytes());
	}
	
	public byte[] decrypt(byte[] input, byte[] keyBytes) throws IOException{
		Cipher in = null;

		Key key = new SecretKeySpec(keyBytes, ALGORITMO_AES);		
		byte[] result = null;
		try {
			in = Cipher.getInstance(ALGORITMO_AES);
			in.init(Cipher.DECRYPT_MODE, key);
			result = in.doFinal(input);
			
	    } catch (Exception e){
			LOG.error("ERROR - decrypt: " + e.getMessage());
		}
		return result;
	}
	
	public byte[] decrypt(byte[] input) throws IOException {
		return decrypt(input, sKey.getBytes() );
	}
}
