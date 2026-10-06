package arena.entity;

/**
 * Định nghĩa các trạng thái của một giải đấu trong hệ thống ARENA.
 */
public enum TournamentStatus {
    /** Giải đấu mới được lên kế hoạch, chưa công bố */
    PLANNED,

    /** Giải đấu đã được công bố chính thức và mở cho người chơi đăng ký */
    ANNOUNCED,

    /** Giải đấu đang diễn ra các trận đấu */
    IN_PROGRESS,

    /** Giải đấu đã kết thúc */
    FINISHED,

    /** Giải đấu đã bị hủy bỏ */
    CANCELED
}
