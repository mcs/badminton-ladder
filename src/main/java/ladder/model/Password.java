package ladder.model;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import javax.persistence.Embeddable;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Embeddable
public class Password implements Serializable {

    private static final SecureRandom RND = new SecureRandom();
    private static final Log LOG = LogFactory.getLog(Password.class);

    private String password;
    private String salt;
    
    public static Password createPassword(String password, String salt) {
        String toHash = password + salt;
        String hash = DigestUtils.md5Hex(toHash);
        LOG.debug("Calculated hash: " + hash);
        return new Password(hash, salt);
    }

    public static Password createNewPassword(String password) {
        byte[] bytes = new byte[8];
        RND.nextBytes(bytes);
        bytes = Base64.encodeBase64(bytes);
        String salt = new String(bytes, Charset.forName("UTF-8")).substring(0, 9);
        LOG.debug("Generated salt: " + salt);
        return createPassword(password, salt);
    }

    public Password() {
    }

    public Password(String password, String salt) {
        this.password = password;
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Password other = (Password) obj;
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }
}
