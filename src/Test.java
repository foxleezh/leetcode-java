import solution.*;
import sun.misc.BASE64Encoder;
import sun.security.pkcs.PKCS7;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

public class Test {
    public static void main(String[] args) {
        BaseQustion q = new Q25();
        q.solution();
    }
}
