package ClientManagerSystem;

/**
 * Created by User on 17.11.2016.
 */
public class Client {


    private int ID;
    private String name;
    private String sName;

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getsName() {
        return sName;
    }

    public Client(int ID, String name, String sName) {
        this.ID = ID;
        this.name = name;
        this.sName = sName;


    }




    public void setID(int ID) {
        this.ID = ID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (ID != client.ID) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        return sName != null ? sName.equals(client.sName) : client.sName == null;

    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sName != null ? sName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", sName='" + sName + '\'' +
                '}';
    }
}
