package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.google.zxing.WriterException;

import ttps.spring.model.QRCodeGenerator;

public class QRCodeGeneratorTest {

	@Test
	public void test() {
		try {
			String str = QRCodeGenerator.generateQRCodeImage("Test QR Code", 250, 250, QRCodeGenerator.QR_CODE_IMAGE_PATH);
			assertTrue(QRCodeGenerator.QR_CODE_IMAGE_PATH == str);
        } catch (WriterException e) {
        	assertTrue(e.getClass() == WriterException.class );
        } catch (IOException e) {
        	assertTrue(e.getClass() == IOException.class );
        }
	}

}
