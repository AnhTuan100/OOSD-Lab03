package arena;

import arena.boundary.AnnounceTournamentForm;
import arena.control.AnnounceTournamentControl;
import arena.entity.TournamentOrganizer;

import java.time.LocalDate;

/**
 * Lớp điều khiển chương trình kiểm thử mô phỏng (Simulation Driver).
 *
 * Tái hiện lại chu trình kích hoạt Use Case "AnnounceTournament":
 * 1. Actor (TournamentOrganizer) mở form công bố giải đấu.
 * 2. Form (Boundary) lấy các danh mục cấu hình từ Control để hiển thị.
 * 3. Actor nhập dữ liệu và submit.
 * 4. Control xác thực và tạo Tournament (Entity) với trạng thái ANNOUNCED.
 */
public class ArenaSimulationMain {

    public static void main(String[] args) {
        // Khởi tạo tầng Control và Boundary
        AnnounceTournamentControl control = new AnnounceTournamentControl();
        AnnounceTournamentForm boundaryForm = new AnnounceTournamentForm(control);

        // Khởi tạo Actor: TournamentOrganizer đã đăng nhập vào hệ thống
        TournamentOrganizer organizer = new TournamentOrganizer(
                "alice_organizer",
                "Alice Smith",
                "alice@arena-tournaments.org"
        );

        // Bước 1: Hiển thị form cho Actor xem các tùy chọn
        boundaryForm.displayFormOptions();

        // Bước 2: Kịch bản 1 - Công bố giải đấu hợp lệ (Normal Flow)
        System.out.println("\n--- [KỊCH BẢN 1] Người tổ chức nhập thông tin hợp lệ ---");
        LocalDate deadline = LocalDate.now().plusDays(10);
        LocalDate startDay = LocalDate.now().plusDays(15);

        boundaryForm.submitAnnouncement(
                "ARENA Winter Chess Cup 2026",
                1,              // Chọn cờ vua (Chess)
                0,              // Chọn Single Elimination
                organizer,
                16,
                startDay,
                deadline
        );

        // Bước 3: Kịch bản 2 - Kiểm tra lỗi xác thực nghiệp vụ (Exceptional Flow)
        System.out.println("\n--- [KỊCH BẢN 2] Người tổ chức chọn ngày bất hợp lý (ngày bắt đầu trước hạn chót) ---");
        boundaryForm.submitAnnouncement(
                "Lỗi Ngày Cup",
                0,
                0,
                organizer,
                8,
                LocalDate.now().plusDays(2),  // Ngày bắt đầu trước ngày hạn chót
                LocalDate.now().plusDays(5)   // Hạn chót
        );
    }
}
