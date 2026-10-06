package arena.boundary;

import arena.control.AnnounceTournamentControl;
import arena.entity.Game;
import arena.entity.Tournament;
import arena.entity.TournamentOrganizer;
import arena.entity.TournamentStyle;

import java.time.LocalDate;
import java.util.List;

/**
 * Boundary Object mô phỏng giao diện tiếp nhận thông tin từ Actor TournamentOrganizer.
 *
 * Chịu trách nhiệm hiển thị các tùy chọn cấu hình từ Controller và chuyển lệnh
 * gửi yêu cầu công bố giải đấu xuống Controller.
 */
public class AnnounceTournamentForm {

    private final AnnounceTournamentControl control;

    /**
     * Khởi tạo Boundary Form liên kết với Control tương ứng.
     *
     * @param control Bộ điều phối luồng nghiệp vụ công bố giải đấu.
     */
    public AnnounceTournamentForm(AnnounceTournamentControl control) {
        this.control = control;
    }

    /**
     * Hiển thị danh sách các trò chơi và thể thức hiện hành cho Actor lựa chọn.
     */
    public void displayFormOptions() {
        System.out.println("========== [ARENA] CÔNG BỐ GIẢI ĐẤU MỚI ==========");
        System.out.println("Danh sách các trò chơi có thể chọn:");
        List<Game> games = control.getSupportedGames();
        for (int i = 0; i < games.size(); i++) {
            System.out.println("  [" + i + "] " + games.get(i));
        }

        System.out.println("\nDanh sách các thể thức thi đấu:");
        List<TournamentStyle> styles = control.getAvailableStyles();
        for (int i = 0; i < styles.size(); i++) {
            System.out.println("  [" + i + "] " + styles.get(i));
        }
        System.out.println("=================================================");
    }

    /**
     * Gửi biểu mẫu thông tin giải đấu đến tầng điều khiển.
     *
     * @param name                 Tên giải đấu.
     * @param gameIndex            Chỉ số trò chơi chọn.
     * @param styleIndex           Chỉ số thể thức chọn.
     * @param organizer            Người tổ chức giải.
     * @param maxParticipants      Số lượng thí sinh tối đa.
     * @param startDate            Ngày khởi tranh.
     * @param registrationDeadline Hạn chót đăng ký.
     */
    public void submitAnnouncement(String name,
                                   int gameIndex,
                                   int styleIndex,
                                   TournamentOrganizer organizer,
                                   int maxParticipants,
                                   LocalDate startDate,
                                   LocalDate registrationDeadline) {
        System.out.println("\n>> Gửi thông tin công bố giải đấu: '" + name + "'...");
        try {
            Tournament created = control.announceTournament(
                    name,
                    gameIndex,
                    styleIndex,
                    organizer,
                    maxParticipants,
                    startDate,
                    registrationDeadline
            );

            System.out.println(">> THÀNH CÔNG: Giải đấu đã được công bố trên hệ thống ARENA!");
            System.out.println(created);
        } catch (IllegalArgumentException ex) {
            System.out.println(">> THẤT BẠI: Lỗi kiểm tra dữ liệu - " + ex.getMessage());
        }
    }
}
