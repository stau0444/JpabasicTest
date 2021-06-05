import jpaTest.domain.Member;
import jpaTest.domain.Team;
import jpaTest.domain.embededdTest.Address;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address address = new Address("street","12333");
            Address address2 = new Address("street","12333");

            //롬복의 이퀄스를 사용해도 되지만 롬복 이퀄스는 순환참조를 일으킬수 있으니 오버라이딩 해서 사용하는 것이 좋다.
            System.out.println("address.equals(address2) = " + address.equals(address2));


            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }

    //멤버만 필요한 경우
    private static void printMember(Member member) {
        String name = member.getName();
        System.out.println("memberName = " + name);
    }

    //멤버와 팀이 모두 필요한 경우
    private static void printMemberAndTeam(Member member) {
        String name = member.getName();
        System.out.println("member = " + name);

        Team team = member.getTeam();
        String teamName = team.getName();
        System.out.println("teamName = " + teamName);
    }
}
