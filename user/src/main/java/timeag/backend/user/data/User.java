package timeag.backend.user.data;

import jakarta.persistence.*;

@Entity(name = "user_table")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    //    @ManyToOne
    @Column(name = "sub_id")
    private String subId;

    @Column(name = "login_key")
    private String loginKey;

    public User() {
    }

    public User(String subId, String loginKey) {
        super();
        this.setSubId(subId);
        this.setLoginKey(loginKey);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
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
                id, subId, loginKey);
    }
}
