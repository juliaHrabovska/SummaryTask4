package ua.nure.hrabovska.SummaryTask4.enums;

/**
 * Role enum
 *
 * @author Y. Hrabovska
 */
public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(int roleId) {
        if (roleId < 0 || roleId >= Role.values().length) {
            return null;
        }
        return Role.values()[roleId];
    }

    public String getName() {
        return this.toString().toLowerCase();
    }
}
