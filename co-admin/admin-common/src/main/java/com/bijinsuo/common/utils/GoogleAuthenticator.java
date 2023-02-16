package com.bijinsuo.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import de.taimos.totp.TOTP;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author Winter
 */
public class GoogleAuthenticator {
  private static final String company = "BJS";

  private GoogleAuthenticator() {
  }

  public static String createSecretKey() {
    SecureRandom random = new SecureRandom();
    byte[] bytes = new byte[20];
    random.nextBytes(bytes);
    Base32 base32 = new Base32();
    return base32.encodeToString(bytes);
  }

  public static String getGoogleAuthenticatorBarCode(String secretKey, String account) {
    try {
      return "otpauth://totp/"
          + URLEncoder.encode(company + ":" + account, "UTF-8").replace("+", "%20")
          + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
          + "&issuer=" + URLEncoder.encode(company, "UTF-8").replace("+", "%20");
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException(e);
    }
  }

  public static void createQRCode(String barCodeData, OutputStream os) {
    try {
      BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE, 10, 10);
      MatrixToImageWriter.writeToStream(matrix, "png", os);
    } catch (WriterException | IOException e) {
      e.printStackTrace();
    }
  }

  public static String createQRCodeBase64String(String barCodeData) {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    createQRCode(barCodeData, os);
    return "data:image/png;base64," + Base64.getEncoder().encodeToString(os.toByteArray());
  }

  private static String getTOTPCode(String secretKey) {
    Base32 base32 = new Base32();
    byte[] bytes = base32.decode(secretKey);
    String hexKey = Hex.encodeHexString(bytes);
    return TOTP.getOTP(hexKey);
  }

  public static void verify(String secretKey, String code) throws IllegalStateException {
    if (!getTOTPCode(secretKey).equals(code)) {
      throw new IllegalStateException();
    }
  }
}
