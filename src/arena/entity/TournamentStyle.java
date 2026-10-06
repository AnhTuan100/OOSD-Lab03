package arena.entity;

/**
 * Thực thể đại diện cho thể thức thi đấu trong hệ thống ARENA
 * (ví dụ: KnockOut - loại trực tiếp, RoundRobin - đấu vòng tròn).
 */
public class TournamentStyle {

    private String name;
    private String description;

    /**
     * Khởi tạo thể thức thi đấu.
     *
     * @param name        Tên thể thức (ví dụ: "Single Elimination", "Round Robin").
     * @param description Mô tả cách chia cặp và tính điểm của thể thức.
     */
    public TournamentStyle(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }
}
