public class Subject {
    // Specified attributes
    private String name;
    private int classId;

    // Constructor
    public Subject(String name, int classId) {
        this.name = name;
        this.classId = classId;
    }

    //Getter and setter
    public String getName() {
        return name;
    }

    public int getClassId() {
        return classId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

}
