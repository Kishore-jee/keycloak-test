package com.example.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.SplittableRandom;

import org.apache.commons.lang3.RandomStringUtils;

final public class OTPGenerator {
	private static OTPGenerator INSTANCE = null;

	private OTPGenerator() {
	}

	public static OTPGenerator INSTANCE() {
		synchronized (OTPGenerator.class) {
			if (Objects.isNull(INSTANCE)) {
				System.out.println("Obj creating...");
				INSTANCE = new OTPGenerator();
			}
		}
		System.out.println("Returning existing Obj...");
		return INSTANCE;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * For linux - use algorithm "NativePRNG" FOr WIN - SHA1PRNG
	 * 
	 * @param length
	 * @return
	 */
	public String otpWithSecureRandom(final int length) {
		StringBuilder otpBuilder = null;
		SecureRandom secureRandom = null;
		try {
//			System.out.println(new SecureRandom().getAlgorithm());
			otpBuilder = new StringBuilder();
			secureRandom = SecureRandom.getInstance(new SecureRandom().getAlgorithm());// NativePRNG

			for (int i = 1; i <= length; i++) {
				otpBuilder.append(secureRandom.nextInt(9));
			}
//			IntStream.rangeClosed(1, length).forEach(c -> otpBuilder.append(secureRandom.nextInt(9)));
//			String.valueOf(length).chars().forEach(c -> otpBuilder.append(secureRandom.nextInt(9)));
//			otpBuilder.append(secureRandom.nextInt(9));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return otpBuilder.toString();
	}

	public String otpWithSplitableRandom(final int length) {
		StringBuilder otpBuilder = null;
		SplittableRandom splittableRandom = null;
		try {
			otpBuilder = new StringBuilder();
			splittableRandom = new SplittableRandom();

			for (int i = 1; i <= length; i++) {
				otpBuilder.append(splittableRandom.nextInt(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otpBuilder.toString();
	}

	public String otpWithRandom(final int length) {
		StringBuilder otpBuilder = null;
		Random random = null;
		try {
			otpBuilder = new StringBuilder();
			random = new Random();

			for (int i = 1; i <= length; i++) {
				otpBuilder.append(random.nextInt(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otpBuilder.toString();
	}

	/**
	 * Used commons-lang3 :: commons.lang3 package
	 * @param length
	 * @return
	 */
	public String getOtpInDigits(final int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	/**
	 * Used commons-lang3 :: commons.lang3 package
	 * @param length
	 * @return
	 */
	public String getOtpInAlphabetic(final int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	/**
	 * Used commons-lang3 :: commons.lang3 package
	 * @param length
	 * @return
	 */
	public String getOtpInAlphanumeric(final int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}
}
