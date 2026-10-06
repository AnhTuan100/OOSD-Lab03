package arena.control;

import arena.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Control Object chịu trách nhiệm điều phối luồng nghiệp vụ cho
 * Use Case "AnnounceTournament" trong hệ thống ARENA.
 * <p>
 * Xử lý: kiểm tra tính hợp lệ dữ liệu, khởi tạo thực thể Tournament,
 * chuyển trạng thái sang ANNOUNCED và lưu vào kho lưu trữ giải đấu.
 */
public class AnnounceTournamentControl {

    private final List<Game> supportedGames;
    private final List<TournamentStyle> availableStyles;
    private final List<Tournament> announcedTournaments;

    /**
     * Khởi tạo Control Object cùng danh mục dữ liệu mẫu của hệ thống.
     */
    public AnnounceTournamentControl() {
        this.supportedGames = new ArrayList<>();
        this.availableStyles = new ArrayList<>();
        this.announcedTournaments = new ArrayList<>();
        initSampleData();
    }

    /**
     * Nạp dữ liệu cấu hình sẵn có của hệ thống ARENA.
     */
    private void initSampleData() {
        supportedGames.add(new Game("Tic-Tac-Toe", "Trò chơi cờ caro cổ điển 3x3", 2, 2));
        supportedGames.add(new Game("Chess", "Cờ vua chuẩn quốc tế FIDE", 2, 2));

        availableStyles.add(new TournamentStyle("Single Elimination", "Đấu loại trực tiếp một lần thua"));
        availableStyles.add(new TournamentStyle("Round Robin", "Thi đấu vòng tròn tính điểm"));
    }

    /**
     * Lấy danh sách các trò chơi mà hệ thống ARENA hỗ trợ.
     *
     * @return Danh sách Game chỉ đọc.
     */
    public List<Game> getSupportedGames() {
        return Collections.unmodifiableList(supportedGames);
    }

    /**
     * Lấy danh sách các thể thức thi đấu hiện có.
     *
     * @return Danh sách TournamentStyle chỉ đọc.
     */
    public List<TournamentStyle> getAvailableStyles() {
        return Collections.unmodifiableList(availableStyles);
    }

    /**
     * Xác thực các dữ liệu đầu vào và tiến hành công bố giải đấu mới.
     *
     * @param tournamentName       Tên của giải đấu.
     * @param gameIndex            Chỉ số lựa chọn trò chơi trong danh sách.
     * @param styleIndex           Chỉ số lựa chọn thể thức thi đấu trong danh sách.
     * @param organizer            Đối tượng Người tổ chức thực hiện ca sử dụng.
     * @param maxParticipants      Số lượng người chơi tối đa.
     * @param startDate            Ngày giải đấu diễn ra.
     * @param registrationDeadline Hạn đăng ký tham gia.
     * @return Đối tượng Tournament vừa được tạo và công bố.
     * @throws IllegalArgumentException Nếu dữ liệu đầu vào không hợp lệ hoặc vi phạm ràng buộc nghiệp vụ.
     */
    public Tournament announceTournament(String tournamentName,
                                         int gameIndex,
                                         int styleIndex,
                                         LeagueOwner organizer,
                                         int maxParticipants,
                                         LocalDate startDate,
                                         LocalDate registrationDeadline) {

        // 1. Kiểm tra tham số không được rỗng
        if (tournamentName == null || tournamentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên giải đấu không được để trống.");
        }
        if (organizer == null) {
            throw new IllegalArgumentException("Người tổ chức không được để null.");
        }
        if (startDate == null || registrationDeadline == null) {
            throw new IllegalArgumentException("Ngày bắt đầu và hạn đăng ký phải được cung cấp đầy đủ.");
        }

        // 2. Kiểm tra chỉ số Game và Style hợp lệ
        if (gameIndex < 0 || gameIndex >= supportedGames.size()) {
            throw new IllegalArgumentException("Trò chơi được chọn không tồn tại trong hệ thống.");
        }
        if (styleIndex < 0 || styleIndex >= availableStyles.size()) {
            throw new IllegalArgumentException("Thể thức thi đấu không hợp lệ.");
        }

        // 3. Kiểm tra số lượng người tham gia
        if (maxParticipants < 2) {
            throw new IllegalArgumentException("Giải đấu phải có tối thiểu từ 2 người chơi trở lên.");
        }

        // 4. Kiểm tra logic mốc thời gian
        LocalDate today = LocalDate.now();
        if (registrationDeadline.isBefore(today)) {
            throw new IllegalArgumentException("Hạn đăng ký không được ở trong quá khứ.");
        }
        if (startDate.isBefore(registrationDeadline)) {
            throw new IllegalArgumentException("Ngày bắt đầu giải đấu phải diễn ra sau hạn đăng ký.");
        }

        Game selectedGame = supportedGames.get(gameIndex);
        TournamentStyle selectedStyle = availableStyles.get(styleIndex);

        // 5. Khởi tạo Entity Tournament
        Tournament newTournament = new Tournament(
                tournamentName.trim(),
                selectedGame,
                selectedStyle,
                organizer,
                maxParticipants,
                startDate,
                registrationDeadline
        );

        // 6. Chuyển trạng thái sang ANNOUNCED
        newTournament.announce();

        // 7. Lưu vào cơ sở dữ liệu giải đấu của hệ thống
        announcedTournaments.add(newTournament);

        return newTournament;
    }

    /**
     * Lấy toàn bộ danh sách các giải đấu đã được công bố.
     *
     * @return Danh sách giải đấu đã công bố.
     */
    public List<Tournament> getAnnouncedTournaments() {
        return Collections.unmodifiableList(announcedTournaments);
    }
}
