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

    /**
     * Creates a hashed password for a given plain text password and salt.
     * @param password the plain text password. If <tt>null</tt>, a random one is generated
     * @param salt the salt. If <tt>null</tt>, the password will be stored without hashing it.
     * @return a new password instance with the hashed password
     */
    public static Password createPassword(String password, String salt) {
        LOG.debug("Entered password: " + password);
        if (password == null) {
            password = generateRandomBase64String(8);
            LOG.debug("Generated random password: " + password);
        }
        String hash;
        if (salt == null) {
            // store plain text password (needed for e.g. maintenance)
            hash = password;
        } else {
            // use salt to create a hashed password
            hash = DigestUtils.md5Hex(password + salt);
        }
        LOG.debug("Calculated hash: " + hash);
        return new Password(hash, salt);
    }

    /**
     * Creates a new password together with a new random salt.
     * @param password the plain text password. If <tt>null</tt>, a random one is generated.
     * @return the hashed password, based on a randomly generated salt
     */
    public static Password createNewPassword(String password) {
        // create salt with nine characters
        String salt = generateRandomBase64String(9);
        LOG.debug("Generated salt: " + salt);
        return createPassword(password, salt);
    }

    private static String generateRandomBase64String(int size) {
        byte[] bytes = new byte[size];
        RND.nextBytes(bytes);
        bytes = Base64.encodeBase64(bytes);
        return new String(bytes, Charset.forName("UTF-8")).substring(0, size);
    }

    protected Password() {
        // used by hibernate
    }

    private Password(String hashedPassword, String salt) {
        this.password = hashedPassword;
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
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
        if ((this.salt == null) ? (other.salt != null) : !this.salt.equals(other.salt)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 59 * hash + (this.salt != null ? this.salt.hashCode() : 0);
        return hash;
    }

}
