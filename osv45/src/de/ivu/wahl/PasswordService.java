/*
 * PasswordService
 * 
 * Created on 09.10.2017
 * Copyright (c) 2017 IVU Traffic Technologies AG
 */
package de.ivu.wahl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.log4j.Category;
import org.bouncycastle.crypto.generators.SCrypt;
import org.bouncycastle.util.encoders.Base64;

import de.ivu.util.debug.Log4J;

/**
 * 
 */
public class PasswordService {

  public static void main(String[] args) {
    String salt = PasswordService.INSTANCE.getSalt();
    String password = "password"; //$NON-NLS-1$
    PasswordService.INSTANCE.calcHash(password, salt);
  }

  private final static Category LOGGER = Log4J.configure(PasswordService.class);

  public static PasswordService INSTANCE = new PasswordService();

  private PasswordService() {
  }

  /**
   * References:
   * <ul>
   * <li>
   * https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt
   * -examples/</li>
   * <li>https://en.wikipedia.org/wiki/Scrypt</li>
   * <li>https://www.bouncycastle.org/docs/docs1.5on/org/bouncycastle/crypto/generators/SCrypt.html</li>
   * <li>https://tools.ietf.org/html/rfc7914</li>
   * </ul>
   * 
   * @param plainText Klartext-Eingabe
   * @return Passwort-Hash als String-Repraesentation
   */
  public String calcHash(String plainText, String salt) {

    int memoryCostParameter = 8192;
    int blockSize = 4;
    int parallelizationParameter = 8;
    int keyLength = 64;

    byte[] plainTextAsBytes = stringToBytes(plainText);
    byte[] saltAsBytes = stringToBytes(salt);

    long start = System.currentTimeMillis();
    LOGGER.info("Calculating SCrypt hashcode ..."); //$NON-NLS-1$
    byte[] result = SCrypt.generate(plainTextAsBytes,
        saltAsBytes,
        memoryCostParameter,
        blockSize,
        parallelizationParameter,
        keyLength);
    LOGGER
        .info("Calculation of SCrypt hashcode took " + (System.currentTimeMillis() - start) + " ms, hashcode: " + bytesToString(result) + ", salt = " + salt); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return bytesToString(result);
  }

  /**
   * Altes Hash-Verfahren
   */
  public String calcMD5Hash(String plainText, String salt) {
    MessageDigest md = null;
    //    md = getDigest(md, "PBKDF2WithHmacSHA1"); //$NON-NLS-1$ Unavailable
    //    md = getDigest(md, "PBKDF2"); //$NON-NLS-1$ Unavailable
    //    md = getDigest(md, "SCRYPT"); //$NON-NLS-1$ Unavailable
    //    md = getDigest(md, "BCRYPT"); //$NON-NLS-1$ Unavailable
    //    md = getDigest(md, "SHA-256"); //$NON-NLS-1$
    //    md = getDigest(md, "SHA-1"); //$NON-NLS-1$
    md = getDigest(md, "MD5"); //$NON-NLS-1$
    if (md == null) {
      // should REALLY NEVER happen
      return ""; //$NON-NLS-1$
    }

    md.reset();
    byte[] digest = md.digest((plainText + salt).getBytes());
    return bytesToString(digest);
  }

  /**
   * @param bytes
   * @return String-Repraesentation
   */
  private String bytesToString(byte[] bytes) {
    return Base64.toBase64String(bytes);
  }

  /**
   * @param string
   * @return Bytes-Repraesentation
   */
  private byte[] stringToBytes(String string) {
    return Base64.decode(string);
  }

  private static MessageDigest getDigest(MessageDigest md, String digestMethod) {
    if (md != null) {
      return md;
    }
    try {
      MessageDigest result = MessageDigest.getInstance(digestMethod);
      LOGGER.error("Digest method found: " + digestMethod); //$NON-NLS-1$
      return result;
    } catch (NoSuchAlgorithmException nsae) {
      LOGGER.error("Digest method not found: " + digestMethod); //$NON-NLS-1$
      LOGGER.error(nsae);
      return null;
    }
  }

  private byte[] getSaltAsBytes() {
    try {
      SecureRandom sr = SecureRandom.getInstance("SHA1PRNG"); //$NON-NLS-1$
      byte[] salt = new byte[16];
      sr.nextBytes(salt);
      return salt;
    } catch (NoSuchAlgorithmException e) {
      // Should never happen
      LOGGER.error("Unable to generate salt using SHA1PRNG. Using Random.nextInt() instead."); //$NON-NLS-1$
      int randomInt = new Random().nextInt(1000000000);
      return BigInteger.valueOf(randomInt).toByteArray();
    }
  }

  public String getSalt() {
    byte[] salt = getSaltAsBytes();
    return bytesToString(salt);
  }
}
