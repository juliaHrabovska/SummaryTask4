package ua.nure.hrabovska.SummaryTask4.database.entity;

import ua.nure.hrabovska.SummaryTask4.enums.Role;


/**
 * Account entity
 *
 * @author Y. Hrabovska
 */

public class Account extends Entity {

    private String email;

    private String password;

    private Role role_id;

    private boolean is_banned;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole_id() {
        return role_id;
    }

    public void setRole_id(Role role_id) {
        this.role_id = role_id;
    }

    public boolean is_banned() {
        return is_banned;
    }

    public void setIs_banned(boolean is_banned) {
        this.is_banned = is_banned;
    }

    @Override
    public String toString() {
        return "Account [email=" + email
                + ", role_id=" + role_id
                + ", is_banned=" + is_banned
                + "]";
    }
}
