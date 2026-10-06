package arena.entity;

/**
 * Thực thể đại diện cho người dùng có vai trò Người tổ chức giải (LeagueOwner).
 */
public class LeagueOwner {

    private String username;
    private String fullName;
    private String email;

    /**
     * Khởi tạo đối tượng ban tổ chức giải.
     *
     * @param username Tên đăng nhập.
     * @param fullName Họ và tên đầy đủ.
     * @param email    Địa chỉ email liên hệ.
     */
    public LeagueOwner(String username, String fullName, String email) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return fullName + " (" + username + ")";
    }
}
