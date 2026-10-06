package arena.entity;

import java.time.LocalDate;

/**
 * Thực thể chính đại diện cho một Giải đấu (Tournament) trong hệ thống ARENA.
 * Lưu trữ toàn bộ thông tin cấu hình, thể thức, và trạng thái giải đấu.
 */
public class Tournament {

    private String name;
    private Game game;
    private TournamentStyle style;
    private TournamentOrganizer organizer;
    private int maxParticipants;
    private LocalDate startDate;
    private LocalDate registrationDeadline;
    private TournamentStatus status;

    /**
     * Khởi tạo một giải đấu với các thông số ban đầu.
     *
     * @param name                 Tên giải đấu.
     * @param game                 Trò chơi được tổ chức thi đấu.
     * @param style                Thể thức thi đấu áp dụng.
     * @param organizer            Người chịu trách nhiệm tổ chức.
     * @param maxParticipants      Số lượng người chơi tối đa có thể tham gia.
     * @param startDate            Ngày bắt đầu thi đấu.
     * @param registrationDeadline Hạn chót đăng ký.
     */
    public Tournament(String name,
                      Game game,
                      TournamentStyle style,
                      TournamentOrganizer organizer,
                      int maxParticipants,
                      LocalDate startDate,
                      LocalDate registrationDeadline) {
        this.name = name;
        this.game = game;
        this.style = style;
        this.organizer = organizer;
        this.maxParticipants = maxParticipants;
        this.startDate = startDate;
        this.registrationDeadline = registrationDeadline;
        this.status = TournamentStatus.PLANNED;
    }

    /**
     * Đổi trạng thái giải đấu sang ANNOUNCED (Đã công bố).
     */
    public void announce() {
        this.status = TournamentStatus.ANNOUNCED;
    }

    public String getName() {
        return name;
    }

    public Game getGame() {
        return game;
    }

    public TournamentStyle getStyle() {
        return style;
    }

    public TournamentOrganizer getOrganizer() {
        return organizer;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getRegistrationDeadline() {
        return registrationDeadline;
    }

    public TournamentStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "=== CHI TIẾT GIẢI ĐẤU ===" +
                "\nTên giải: " + name +
                "\nTrò chơi: " + game.getName() +
                "\nThể thức: " + style.getName() +
                "\nNgười tổ chức: " + organizer.getFullName() +
                "\nSố lượng đăng ký tối đa: " + maxParticipants +
                "\nHạn chót đăng ký: " + registrationDeadline +
                "\nNgày khởi tranh: " + startDate +
                "\nTrạng thái: " + status;
    }
}
