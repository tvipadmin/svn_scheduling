/**
 * @author Juliet Mary
 *
 */
package com.televital.scheduling.passwordencrypt;


public interface IPasswordCodec {
	
		//function declaration for encryption of the license string
		public String encrypt(String content) throws EncryptionException ;
		
		//function declaration for decryption of the license string
		public String decrypt(String key) throws EncryptionException ;
		
		
	}
