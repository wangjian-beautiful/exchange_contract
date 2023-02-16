package utils;

import com.google.zxing.WriterException;
import com.bijinsuo.common.utils.GoogleAuthenticator;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Winter
 */
public class GoogleAuthenticatorTest {
  private final String secretKey = "ASSDWFIQ7I2EQPCRL2UQQWAMXLCEDKEK";
  private final String uid = "1";

  @Test
  void testCreateQrCode() throws IOException, WriterException {
    String barCode = GoogleAuthenticator.getGoogleAuthenticatorBarCode(secretKey, uid);
//    Path file = Files.createFile(Paths.get("temp0.png"));
//    OutputStream os = Files.newOutputStream(file);
//    GoogleAuthenticator.createQRCode(barCode, os);
////    GoogleAuthenticator.verify(secretKey,"624227");
//    BitMatrix matrix = new MultiFormatWriter().encode(barCode, BarcodeFormat.QR_CODE, 10, 10);
//    ByteArrayOutputStream os = new ByteArrayOutputStream();
//    MatrixToImageWriter.writeToStream(matrix, "png", os);
//    String s =Base64.getEncoder().encodeToString(os.toByteArray());
//    String s2 = "iVBORw0KGgoAAAANSUhEUgAAACkAAAApAQAAAACAGz1bAAAA4ElEQVR42mP4DwINDFipD4Jhb9gbGL5fvHP1ewPDl5DTe8WBVNDBVBAVP+UskPp+zeAuUO6DaGgIUOX/357yQH2f5ezM2hsYfhSGdcwHCrLpL38PVKl7aSmQ+nr23lX1Boa/6RMz+4EqoxpZ9IGG6b7eU9/A8NMtMyYdZPu1M0BTfsc9dwPKfV16yNAeqP1o2gnzBoZPD7c7AeX+eGk6A7X/LfBcvRxou8O7C0Dt/7+YPQdq+CC12tQfqOH6wXaQ0aH/A/KBVMy2g0Brv8R5vwTJXYnQqwf5r8xtOw6/QygAFHKePqOK/wIAAAAASUVORK5CYII=";
//    String s3 = "data:image/gif;base64,"+s2;
    String s = GoogleAuthenticator.createQRCodeBase64String(barCode);
    System.out.println(s);
    GoogleAuthenticator.verify("slfsjlfsnbfkol3lbnfodsinlfsf",null);
  }
}
