package ladder.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tUser")
public class User extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String login;
    @Embedded
    private Password password;
    private String email;
    @ManyToOne
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Player> players = new ArrayList<Player>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        final User other = (User) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.login == null ? 0 : this.login.hashCode();
    }

    /* ********************************************************************** */
    public boolean hasRight(Right right) {
        return role.hasRight(right);
    }
}
