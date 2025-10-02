
// Parent class Room
class Room {
    private int roomId;
    private String roomNumber;
    private int floor;
    private int capacity;

    // Constructor
    public Room(int roomId, String roomNumber, int floor, int capacity) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.capacity = capacity;
    }

    // Getters
    public int getRoomId() {
        return roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public int getCapacity() {
        return capacity;
    }
}

// Subclass LabRoom
class LabRoom extends Room {
    private int numOfPCs;
    private String operatingSystem;

    // Constructor
    public LabRoom(int roomId, String roomNumber, int floor, int capacity, int numOfPCs, String operatingSystem) {
        super(roomId, roomNumber, floor, capacity);
        this.numOfPCs = numOfPCs;
        this.operatingSystem = operatingSystem;
    }

    // Getters
    public int getNumOfPCs() {
        return numOfPCs;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }
}

// Subclass LectureHall
class LectureHall extends Room {
    private String microphoneType;
    private boolean tieredSeating;

    // Constructor
    public LectureHall(int roomId, String roomNumber, int floor, int capacity, String microphoneType,
                       boolean tieredSeating) {
        super(roomId, roomNumber, floor, capacity);
        this.microphoneType = microphoneType;
        this.tieredSeating = tieredSeating;
    }

    // Getters
    public String getMicrophoneType() {
        return microphoneType;
    }

    public boolean hasTieredSeating() {
        return tieredSeating;
    }
}
