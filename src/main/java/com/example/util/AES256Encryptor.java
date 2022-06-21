package com.example.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES is a symmetric encryption algorithm.
 * 
 * AES is block cipher capable of handling 128 bit blocks, using keys sized at
 * 128, 192, and 256 bits. Each cipher encrypts and decrypts data in blocks of
 * 128 bits using cryptographic keys of 128-, 192- and 256-bits, respectively.
 * It uses the same key for encrypting and decrypting,
 * 
 * @author yogan
 *
 */
public final class AES256Encryptor {

	/**
	 * Add a salt to encryption mechanism
	 */
	private final static String SALT = "Yoga";

	/**
	 * Add some pepper to encryption mechanism
	 */
	private final static String PEPPER = ".0786@Nanda";

	/**
	 * Secret key to encrypt and decrypt the given data.
	 */
	private final static String SECRET_KEY = "HA_HA_HA";

	/**
	 * Instance key :: To get the SecretKeyFactory instance.
	 */
	private final static String KEY_FACTORY_INSTANCE = "PBKDF2WithHmacSHA256";

	/**
	 * To get the Cipher Instance
	 */
	private static final String CIPHER_INSTANCE = "AES/CBC/PKCS5Padding";

	/**
	 * The iteration count.
	 */
	private static final int ITERATION_COUNT = 10000;// 65536

	/**
	 * to-be-derived key length i.e. encryption bit length i.e 128, 192, 256
	 */
	private static final int KEY_LENGTH = 256;

	/**
	 * String SALT
	 */
	private static final String STR_SALT = "SALT";

	/**
	 * String PEPPER
	 */
	private static final String STR_PEPPER = "PEPPER";

	/**
	 * String ENCRYPT
	 */
	private static final String STR_ENCRYPT = "ENCRYPT";

	/**
	 * String DECRYPT
	 */
	private static final String STR_DECRYPT = "DECRYPT";


	/*****************************************************************/
	private AES256Encryptor() {
		throw new AssertionError("AES256Encryptor is not initilizable");
	}
	/*****************************************************************/

	private static IvParameterSpec ivspec;
	static {
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		ivspec = new IvParameterSpec(iv);
	}

	public static String encryptString(String strToEncrypt, final String secret_key) {
		return Encryptor.encryptString(strToEncrypt, secret_key);
	}

	public static String decryptString(String strToDecrypt, final String secret_key) {
		return Encryptor.decryptString(strToDecrypt, secret_key);
	}

	public static String encrypt(String strToEncrypt) {
		return Encryptor.encrypt(strToEncrypt);
	}

	public static String decrypt(String strToDncrypt) {
		return Encryptor.decrypt(strToDncrypt);
	}

	/**
	 * 
	 * @author kisho
	 *
	 */
	static class Encryptor {
		Encryptor() {
		};

		static Encryptor encryptor = new Encryptor();

		public static String encryptString(String strToEncrypt, final String secret_key) {
			try {
				Objects.requireNonNull(strToEncrypt, "To encrypt value must be non-null, found null");
				String saltedVal = encryptor.addSaltWithSecretKey(strToEncrypt, secret_key);
				return encryptor.addPepperWithSecretKey(saltedVal, secret_key);

			} catch (Exception encryptEX) {
				encryptEX.printStackTrace();
				return null;
			}
		}

		public static String decryptString(String strToDecrypt, final String secret_key) {
			try {
				Objects.requireNonNull(strToDecrypt, "To decrypt value must be non-null, found null");
				String saltedVal = encryptor.removePepperWithSecretKey(strToDecrypt, secret_key);
				return encryptor.removeSaltWithSecretKey(saltedVal, secret_key);

			} catch (Exception decryptEX) {
				decryptEX.printStackTrace();
				return null;
			}
		}

		public static String encrypt(String strToEncrypt) {
			try {
				Objects.requireNonNull(strToEncrypt, "To encrypt value must be non-null, found null");
				String saltedVal = encryptor.addSalt(strToEncrypt.trim());
				return encryptor.addPepper(saltedVal);

			} catch (Exception e) {
				e.printStackTrace();

			}
			return null;
		}

		public static String decrypt(String strToDncrypt) {
			try {
				Objects.requireNonNull(strToDncrypt, "To decrypt value must be non-null, found null");
				String saltedVal = encryptor.removePepper(strToDncrypt.trim());
				return encryptor.removeSalt(saltedVal);

			} catch (Exception e) {
				e.printStackTrace();

			}
			return null;
		}

		/***************************************************************************************************
		 * encryptString # decryptString methods
		 ***************************************************************************************************/
		private final String encryptValueWithSaltORPepperAndSecretKey(String strToEncrypt, String saltORPepper,
				String secret_key) throws Exception {
			return encryptDecryptString(strToEncrypt, saltORPepper, STR_ENCRYPT, secret_key);
		}

		private final String addSaltWithSecretKey(String strToEncrypt, String secret_key) throws Exception {
			return encryptValueWithSaltORPepperAndSecretKey(strToEncrypt, STR_SALT, secret_key);
		}

		private String addPepperWithSecretKey(String strToEncrypt, String secret_key) throws Exception {
			return encryptValueWithSaltORPepperAndSecretKey(strToEncrypt, STR_PEPPER, secret_key);
		}

		private final String decryptValueWithSaltORPepperAndSecretKey(String strToDecrypt, String saltORPepper,
				String secret_key) throws Exception {
			return encryptDecryptString(strToDecrypt, saltORPepper, STR_DECRYPT, secret_key);
		}

		private final String removeSaltWithSecretKey(String strToDecrypt, String secret_key) throws Exception {
			return decryptValueWithSaltORPepperAndSecretKey(strToDecrypt, STR_SALT, secret_key);
		}

		private String removePepperWithSecretKey(String strToDecrypt, String secret_key) throws Exception {
			return decryptValueWithSaltORPepperAndSecretKey(strToDecrypt, STR_PEPPER, secret_key);
		}

		/***************************************************************************************************
		 * encryptString # decryptString methods
		 ***************************************************************************************************/

		/****************************************************************************************
		 * encrypt # decrypt methods
		 ****************************************************************************************/
		private final String encryptValueWithSaltORPepper(String strToEncrypt, String saltORPepper) throws Exception {
			return encryptDecryptString(strToEncrypt, saltORPepper, STR_ENCRYPT, null);
		}

		private final String decryptValueWithSaltORPepper(String strToDncrypt, String saltORPepper) throws Exception {
			return encryptDecryptString(strToDncrypt, saltORPepper, STR_DECRYPT, null);
		}

		private String removeSalt(String strToEncrypt) throws Exception {
			return decryptValueWithSaltORPepper(strToEncrypt, STR_SALT);
		}

		private String removePepper(String strToEncrypt) throws Exception {
			return decryptValueWithSaltORPepper(strToEncrypt, null);
		}

		private String addSalt(String strToEncrypt) throws Exception {
			return encryptValueWithSaltORPepper(strToEncrypt, STR_SALT);
		}

		private String addPepper(String strToEncrypt) throws Exception {
			return encryptValueWithSaltORPepper(strToEncrypt, null);
		}

		/****************************************************************************************
		 * encrypt # decrypt methods
		 ****************************************************************************************/

		private final String encryptDecryptString(String stringValue, String saltORPepper, String mode,
				String secret_key) throws Exception {
			final String salt_pepper = STR_SALT.equalsIgnoreCase(saltORPepper) ? SALT : PEPPER;
			final int encrypt_decrypt = STR_ENCRYPT.equalsIgnoreCase(mode) ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE;
			secret_key = Objects.isNull(secret_key) ? SECRET_KEY : secret_key;

			SecretKeyFactory factory = getSecretKeyFactoryInstance();
			KeySpec spec = new PBEKeySpec(secret_key.toCharArray(), salt_pepper.getBytes(), ITERATION_COUNT,
					KEY_LENGTH);

			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = getCipherInstance();
			cipher.init(encrypt_decrypt, secretKey, ivspec);

			if (STR_ENCRYPT.equalsIgnoreCase(mode)) {
				return Base64.getEncoder().encodeToString(cipher.doFinal(stringValue.getBytes("UTF-8")));
			}
			return new String(cipher.doFinal(Base64.getDecoder().decode(stringValue)));
		}

		private final Cipher getCipherInstance() throws NoSuchAlgorithmException, NoSuchPaddingException {
			return Cipher.getInstance(CIPHER_INSTANCE);
		}

		private final SecretKeyFactory getSecretKeyFactoryInstance() throws NoSuchAlgorithmException {
			return SecretKeyFactory.getInstance(KEY_FACTORY_INSTANCE);
		}
	}
}
