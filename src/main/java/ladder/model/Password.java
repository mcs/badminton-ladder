package ladder.model;

import java.io.Serializable;
import java.security.SecureRandom;
import javax.persistence.Embeddable;
import org.apache.commons.codec.digest.DigestUtils;

@Embeddable
public class Password implements Serializable {

    private static final SecureRandom RND_GEN = new SecureRandom();

    private String passhash;
    private int salt;

    protected Password() {
    }

    public Password(String passhash, int salt) {
        this.passhash = passhash;
        this.salt = salt;
    }

    public static String generatePasshash(String password, int salt) {
        password = password + salt;
        return DigestUtils.md5Hex(password);
    }

    public String getPasshash() {
        return passhash;
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
        if ((this.passhash == null) ? (other.passhash != null) : !this.passhash.equals(other.passhash)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return passhash.hashCode();
    }
}
