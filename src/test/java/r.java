import com.epam.dao.Dao;
import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Role;
import com.epam.entity.User;

import java.util.ArrayList;
import java.util.List;

public class r {


    public static void main(String[] args) {
       List<String> strings=new ArrayList<>();
        strings.add(0,"Estai");
        strings.add(1,"FFFF");



//
//        Role role=Role.ADMIN;
//        Role role1=role;
//        role1=Role.ENROLLEE;
//        Role role2=Role.ENROLLEE;
//        if(role.equals(role1)) System.out.println("Ghb");
//        if(role.equals(role2)) System.out.println("Ghb1");


//        List<User> users = (List<User>) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand(){
//            @Override
//            public Object execute(DaoManager daoManager) {
//                return daoManager.getUserDao().findAll();
//            }
//        });
//        for (User user: users){
//            System.out.println(user);
//        }
    }

//    public static void main(String[] args) {
//        DaoManager daoManager = DaoFactory.getDaoFactory("jdbc").createDaoManager();
//          List<Faculty> faculties=( List<Faculty>)daoManager.executeAndClose(new DaoCommand() {
//              @Override
//              public Object execute(DaoFactory daoFactory) {
//                  List<Faculty> faculties = daoFactory.getFacultyDao().findAll();
//                  return faculties;
//              }
//          });
//    }
//    public static void main(String[] args) {
//        ConnectionPool pool = ConnectionPool.instance();
//        pool.setSetting("org.h2.Driver", "jdbc:h2:file:D:/JavaE/H2/commission", "roor", "123", 10);
//        try {
//            pool.initConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        DaoFactory factory = DaoFactory.getDaoFactory("jdbc");
//        EnrolleeDao dao = factory.getEnrolleeDao();
//        Enrollee enrollee=new Enrollee();
//         Subject maths= new Subject();
//                 maths.setName("maths");
//        Subject biology= new Subject();
//        biology.setName("biology");
//        Map <Subject, String> map= new HashMap<>();
//        map.put(biology, "5");
//        map.put(maths,"1");
//        enrollee.setId(1);
//        enrollee.setFirstName("Estai");
//        enrollee.setLastName("Boranov");
//        enrollee.setCertificate(4.85);
//        enrollee.setCertificateNumber("dff455569");
//        enrollee.setScore(map);
//        System.out.println(enrollee);
//
//        Enrollee enrollee1 = dao.create(enrollee);
//        System.out.println(enrollee1);
//    }


}