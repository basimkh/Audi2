/**
 * Created by Hp1 on 14/02/2018.
 */

public class Car {
     private long id;
    private String name;
    private String filepathl;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilepathl() {
        return filepathl;
    }

    public void setFilepathl(String filepathl) {
        this.filepathl = filepathl;
    }

    public Car(long id, String name, String filepathl) {

        this.id = id;
        this.name = name;
        this.filepathl = filepathl;
    }

    public void setId(long id) {
        this.id = id;

    }
}
