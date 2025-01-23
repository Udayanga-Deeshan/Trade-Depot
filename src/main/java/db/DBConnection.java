package db;

public class DBConnection {
    private static DBConnection instance;

    private DBConnection(){

    }

    public static DBConnection getInstance(){
        return instance==null? instance=new DBConnection():instance;
    }

}
