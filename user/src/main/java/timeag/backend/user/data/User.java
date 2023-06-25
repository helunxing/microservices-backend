package timeag.backend.user.data;

import jakarta.persistence.*;

@Entity(name = "user_table")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    //    @ManyToOne
    @Column(name = "sub")
    private String sub;

    @Column(name = "login_key")
    private String loginKey;

    public User() {
    }

    public User(String sub, String loginKey) {
        super();
        this.setSub(sub);
        this.setLoginKey(loginKey);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String subId) {
        this.sub = subId;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    @Override
    public String toString() {
        return String.format(
                "User [id=%s, subId=%s, loginKey=%s]",
                id, sub, loginKey);
    }
}
