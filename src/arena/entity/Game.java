package arena.entity;

/**
 * Thực thể đại diện cho một trò chơi được hỗ trợ bởi hệ thống ARENA
 * (ví dụ: TicTacToe, Chess, Asteroids).
 */
public class Game {

    private String name;
    private String description;
    private int minPlayers;
    private int maxPlayers;

    /**
     * Khởi tạo một đối tượng trò chơi mới.
     *
     * @param name        Tên của trò chơi.
     * @param description Mô tả ngắn về trò chơi.
     * @param minPlayers  Số lượng người chơi tối thiểu trong 1 trận.
     * @param maxPlayers  Số lượng người chơi tối đa trong 1 trận.
     */
    public Game(String name, String description, int minPlayers, int maxPlayers) {
        this.name = name;
        this.description = description;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public String toString() {
        return name + " (" + minPlayers + "-" + maxPlayers + " người chơi)";
    }
}
